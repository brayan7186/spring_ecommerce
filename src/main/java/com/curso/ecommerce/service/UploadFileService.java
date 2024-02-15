package com.curso.ecommerce.service;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sound.midi.Patch;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {
 private String folder="images//";
	
 public String saveImages(MultipartFile file) throws IOException{
	 if(!file.isEmpty()) {
		 byte [] bytes=file.getBytes();
		 Path path =Paths.get(folder+file.getOriginalFilename());
		 Files.write(path, bytes);
	 }
	 
	 return "default.jpg";
 }
	
 
 public void deleteImages(String nombre) {
	 String ruta="images//";
	 File file =new File(ruta+nombre);
	 file.delete();
 }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
}