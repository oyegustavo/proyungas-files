package ar.org.proyungas.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface GarageStorageService {
	public void uploadFile(MultipartFile file, String format, String status);
	public byte[] downloadFile(String filename);
	public void deleteFile(String filename);
	public List<String> listFiles();
}
