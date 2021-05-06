package com.cristobal.cifuentes.proyectoformativo.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristobal.cifuentes.proyectoformativo.model.entity.Image;
import com.cristobal.cifuentes.proyectoformativo.model.repository.IImageRepository;
import com.cristobal.cifuentes.proyectoformativo.service.interfaces.IImageService;

@Service
public class ImageServiceImplement implements IImageService {

	@Autowired
	private IImageRepository imageRep; 
	
	
	@Override
	public Image getImageById(int idImage) {
		
		return imageRep.findById(idImage).get();
	}

	@Override
	public Image saveImage(Image image) {
	
		return imageRep.save(image);
	}

	@Override
	public List<Image> list() {
		
		return imageRep.findAll();
	}

	@Override
	public boolean deleteById(int id) {
		
		imageRep.deleteById(id);
		return true;
	}

	@Override
	public Image editImage(Image image) {
		
		return imageRep.save(image);
	}

}
