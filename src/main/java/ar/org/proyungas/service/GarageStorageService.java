package ar.org.proyungas.service;

import org.springframework.web.multipart.MultipartFile;

public interface GarageStorageService {
	public void uploadFile(MultipartFile file, String format, String status);
	public byte[] downloadFile(String filename);
	public void deleteFile(String filename);
}
