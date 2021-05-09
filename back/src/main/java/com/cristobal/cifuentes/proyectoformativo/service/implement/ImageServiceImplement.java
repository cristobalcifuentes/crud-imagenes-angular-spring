package com.cristobal.cifuentes.proyectoformativo.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristobal.cifuentes.proyectoformativo.exception.ModelNotFoundException;
import com.cristobal.cifuentes.proyectoformativo.model.entity.Image;
import com.cristobal.cifuentes.proyectoformativo.model.repository.IImageRepository;
import com.cristobal.cifuentes.proyectoformativo.service.interfaces.IImageService;

import javassist.NotFoundException;

@Service
public class ImageServiceImplement implements IImageService {

	@Autowired
	private IImageRepository imageRep; 
	
	
	@Override
	public Image getImageById(int idImage) throws NotFoundException {

		Optional<Image>  image = imageRep.findById(idImage);
		if(!image.isPresent()) {
			throw new ModelNotFoundException( "id "+ idImage + " no encontrado");
		}
		return image.get();
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
	public Image updateImage(Image image) {
		
		Optional<Image> optionalImage = imageRep.findById(image.getId());
		
		if(optionalImage.isPresent()) {
			Image newImage = optionalImage.get();
			newImage.setDescription(image.getDescription());
			newImage.setTitle(image.getTitle());
			return imageRep.save(newImage);
		} else throw new ModelNotFoundException( "id "+ image.getId() + " no encontrado");
		
	}

}
