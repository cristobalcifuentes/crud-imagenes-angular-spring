package com.cristobal.cifuentes.proyectoformativo.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
	
	public String uploadFile(MultipartFile file);
	public boolean deleteFile(String fileName);

}
