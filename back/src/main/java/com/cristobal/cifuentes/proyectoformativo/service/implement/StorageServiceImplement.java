package com.cristobal.cifuentes.proyectoformativo.service.implement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.cristobal.cifuentes.proyectoformativo.service.interfaces.IStorageService;


@Service
public class StorageServiceImplement implements IStorageService{
	
    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;
    
    private static final String URL = "https://proyecto-formativo.s3.us-east-2.amazonaws.com/";

	@Override
	public String uploadFile(MultipartFile file) {

    	File fileObj = convertMultiPartFileToFile(file);
        String fileName =  UUID.randomUUID().toString();
        s3Client.putObject(new PutObjectRequest(bucketName, fileName+".png", fileObj));
        fileObj.delete();
        return s3Client.getUrl(bucketName, fileName+".png" ).toExternalForm();
	}

	@Override
	public boolean deleteFile(String fileName) {

		String newFileName = fileName.replaceAll(URL, "");
		s3Client.deleteObject(bucketName, newFileName);
		return true;
	}
	
	
    private File convertMultiPartFileToFile(MultipartFile file) {
        
    	File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            System.out.println("Error converting multipartFile to file "+e);
        }
        return convertedFile;
    }
    

    
    

    

}
