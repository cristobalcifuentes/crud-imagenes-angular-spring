package com.cristobal.cifuentes.proyectoformativo.service.interfaces;

import java.util.List;

import com.cristobal.cifuentes.proyectoformativo.model.entity.Image;

import javassist.NotFoundException;

public interface IImageService {
	
	public Image getImageById(int idImage) throws NotFoundException;
	
	public Image saveImage(Image image);
	
	public List<Image> list();
	
	public boolean deleteById(int Id);
	
	public Image updateImage(Image image);
	
	

}
