package com.cristobal.cifuentes.proyectoformativo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cristobal.cifuentes.proyectoformativo.model.entity.Image;
import com.cristobal.cifuentes.proyectoformativo.service.interfaces.IImageService;

@RestController
@RequestMapping("image")
public class ImageRestController {
	
	@Autowired
	private IImageService imageSer;
	
	@GetMapping
	public ResponseEntity<List<Image>> getImages(){
		
		
		return new ResponseEntity<>(imageSer.list(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Image> save(@ModelAttribute Image image, @RequestParam MultipartFile file){
		System.out.println(image);

		String keyName = file.getOriginalFilename();
		System.out.println(keyName);
				
		return new ResponseEntity<>(imageSer.saveImage(image), HttpStatus.OK);
	}
	
	
	
	
	
	
	

}
