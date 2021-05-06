package com.cristobal.cifuentes.proyectoformativo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cristobal.cifuentes.proyectoformativo.model.entity.Image;

public interface IImageRepository extends JpaRepository<Image, Integer> {

}
