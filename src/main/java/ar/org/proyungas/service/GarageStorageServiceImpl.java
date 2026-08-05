package ar.org.proyungas.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.org.proyungas.exception.ErrorCode;
import ar.org.proyungas.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@Slf4j
public class GarageStorageServiceImpl implements GarageStorageService{
	
    private final S3Client s3Client;

    @Value("${storage.s3.bucket}")
    private String bucketName;
    
    @Value("${upload.max-size-mb:50}")
    private int maxSizeMb;

    public GarageStorageServiceImpl(S3Client s3Client) {
        this.s3Client = s3Client;
    }


	@Override
    public void uploadFile(MultipartFile file, String format, String status) {
        // 1. Check layer status
        if (List.of("APPROVED","PENDING","UNDER_REVIEW").contains(status)) {
        	log.error("Uploads not allowed for status " + status);
            throw new ValidationException(ErrorCode.INVALID_STATUS_ERROR);
        }

        // 2. Check file size
        if (file.getSize() > maxSizeMb * 1024 * 1024) {
        	log.error("File exceeds " + maxSizeMb + " MB limit");
            throw new ValidationException(ErrorCode.FILE_SIZE_ERROR);
        }

        // 3. Format-specific validation
        switch (format.toUpperCase()) {
            case "SHAPEFILE":
                validateShapefile(file);
                break;
            case "KML":
                if (!file.getOriginalFilename().toLowerCase().endsWith(".kml")) {
                	log.error("✗ File must have .kml extension");
                    throw new ValidationException(ErrorCode.INVALID_EXTENSION_ERROR);
                }
                break;
            case "KMZ":
                validateKmz(file);
                break;
            case "GEOJSON":
                validateGeoJson(file);
                break;
            case "GEOPACKAGE":
                if (!file.getOriginalFilename().toLowerCase().endsWith(".gpkg")) {
                	log.error("✗ File must have .gpkg extension");
                    throw new ValidationException(ErrorCode.INVALID_EXTENSION_ERROR);
                }
                break;
            default:
            	log.error("Unsupported format: " + format);
                throw new ValidationException(ErrorCode.INVALID_EXTENSION_ERROR);
        }

        // 4. Upload to Garage
        try {
            PutObjectRequest putRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(putRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        } catch (IOException e) {
            throw new RuntimeException("Error uploading file to Garage", e);
        }
    }

	@Override
	public byte[] downloadFile(String filename) {
        GetObjectRequest getRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .build();

        try (ResponseInputStream<GetObjectResponse> response = s3Client.getObject(getRequest)) {
            return response.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException("Error downloading file from Garage", e);
        }
	}

	@Override
	public void deleteFile(String filename) {
        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .build();
        s3Client.deleteObject(deleteRequest);
	}
	
	
	  // --- Validations ---

    private void validateShapefile(MultipartFile file) {
        try (ZipInputStream zis = new ZipInputStream(file.getInputStream())) {
            Set<String> entries = new HashSet<>();
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                entries.add(entry.getName());
            }
            String base = FilenameUtils.getBaseName(file.getOriginalFilename());
            List<String> required = List.of(base + ".shp", base + ".shx", base + ".dbf", base + ".prj");
            List<String> missing = required.stream()
                    .filter(r -> !entries.contains(r))
                    .collect(Collectors.toList());

            if (!missing.isEmpty()) {
            	log.error("✗ Missing required Shapefile parts: " + String.join(", ", missing));
                throw new ValidationException(ErrorCode.MISSING_SHAPEFILE_ERROR);
            }
        } catch (IOException e) {
        	log.error("✗ Invalid ZIP structure");
            throw new ValidationException(ErrorCode.INVALID_ZIP_ERROR);
        }
    }

    private void validateKmz(MultipartFile file) {
        try (ZipInputStream zis = new ZipInputStream(file.getInputStream())) {
            Set<String> entries = new HashSet<>();
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                entries.add(entry.getName());
            }
            // Example 5 checks:
            if (!entries.contains("doc.kml")) {
            	log.error("✗ Missing doc.kml");
            	throw new ValidationException(ErrorCode.INVALID_KMZ_ERROR);
            }
            	
            if (entries.stream().noneMatch(e -> e.endsWith(".png") || e.endsWith(".jpg"))) 
            	log.error("✗ No image resources found");
                throw new ValidationException(ErrorCode.INVALID_KMZ_ERROR);
            // add 3 more checks as needed (valid folder structure, no empty kml, etc.)
        } catch (IOException e) {
        	log.error("✗ Invalid KMZ structure");
            throw new ValidationException(ErrorCode.INVALID_KMZ_ERROR);
        }
    }

    private void validateGeoJson(MultipartFile file) {
        try {
            new ObjectMapper().readTree(file.getInputStream()); // basic JSON parse
        } catch (IOException e) {
        	log.error("✗ Invalid GeoJSON structure");
            throw new ValidationException(ErrorCode.INVALID_GEO_JSON_ERROR);
        }
    }
}