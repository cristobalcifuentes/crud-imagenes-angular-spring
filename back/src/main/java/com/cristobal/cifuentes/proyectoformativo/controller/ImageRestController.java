package com.cristobal.cifuentes.proyectoformativo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cristobal.cifuentes.proyectoformativo.model.entity.Image;
import com.cristobal.cifuentes.proyectoformativo.service.interfaces.IImageService;
import com.cristobal.cifuentes.proyectoformativo.service.interfaces.IStorageService;

import javassist.NotFoundException;

@RestController
@RequestMapping("image")
public class ImageRestController {
	
	@Autowired
	private IImageService imageSer;
	
	@Autowired
	private IStorageService storageSer;
	
	@GetMapping
	public ResponseEntity<List<Image>> getImages(){
		
		return new ResponseEntity<>(imageSer.list(), HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Image> getImageById(@PathVariable int id) throws NotFoundException{
		
		return new ResponseEntity<>(imageSer.getImageById(id), HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<Image> save(@RequestParam MultipartFile file,  @RequestParam String title, @RequestParam String description){
		
		String url = storageSer.uploadFile(file);
		Image image = new Image(url, title, description);
		return new ResponseEntity<>(imageSer.saveImage(image), HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable int id) throws NotFoundException{
		
		Image image = imageSer.getImageById(id);
		storageSer.deleteFile(image.getImage());
		imageSer.deleteById(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	
	@PutMapping
	public ResponseEntity<Image> update(@RequestBody Image image){
		
		return new ResponseEntity<Image>(imageSer.updateImage(image), HttpStatus.OK);
	}
	

}
