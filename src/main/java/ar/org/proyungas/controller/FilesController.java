package ar.org.proyungas.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ar.org.proyungas.exception.ValidationException;
import ar.org.proyungas.response.ResponseMessage;
import ar.org.proyungas.service.GarageStorageService;

@RestController
@RequestMapping("/files")
@CrossOrigin("http://localhost:8081")
public class FilesController {

    private final GarageStorageService storageService;

    public FilesController(GarageStorageService storageService) {
        this.storageService = storageService;
    }

    // Upload endpoint
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("format") String format,
            @RequestParam("status") String status,
            @RequestParam("layerId") Long layerId) {
        try {
        	storageService.uploadFile(file, format, status);
            return ResponseEntity.ok(new ResponseMessage("✓ File ready: " + file.getOriginalFilename()));
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(new ResponseMessage("✗ " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new ResponseMessage("Error uploading file: " + e.getMessage()));
        }
    }
    
    // Multi-file upload endpoint
    @PostMapping("/upload-multiple")
    public ResponseEntity<List<ResponseMessage>> uploadMultipleFiles(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("format") String format,
            @RequestParam("status") String status,
            @RequestParam("layerId") Long layerId) {

        List<ResponseMessage> results = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
            	storageService.uploadFile(file, format, status);
                results.add(new ResponseMessage("✓ File ready: " + file.getOriginalFilename()));
            } catch (ValidationException e) {
                results.add(new ResponseMessage("✗ " + file.getOriginalFilename() + " - " + e.getMessage()));
            } catch (Exception e) {
                results.add(new ResponseMessage("✗ " + file.getOriginalFilename() + " - Error: " + e.getMessage()));
            }
        }

        return ResponseEntity.ok(results);
    }

    // Download endpoint
    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String filename) {
        try {
            byte[] data = storageService.downloadFile(filename);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .body(data);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Delete endpoint
    @DeleteMapping("/{filename}")
    public ResponseEntity<ResponseMessage> deleteFile(@PathVariable String filename) {
        try {
            storageService.deleteFile(filename);
            return ResponseEntity.ok(new ResponseMessage("Deleted file successfully: " + filename));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new ResponseMessage("Error deleting file: " + e.getMessage()));
        }
    }
    
    @GetMapping("/list")
    public ResponseEntity<List<String>> listFiles() {
        try {
            List<String> files = storageService.listFiles();
            return ResponseEntity.ok(files);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Collections.emptyList());
        }
    }

}