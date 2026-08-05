package ar.org.proyungas.service;

import org.springframework.web.multipart.MultipartFile;

public interface GarageStorageService {
	public void uploadFile(Long layerId, MultipartFile file, String format);
	public byte[] downloadFile(String filename);
	public void deleteFile(String filename);
}
