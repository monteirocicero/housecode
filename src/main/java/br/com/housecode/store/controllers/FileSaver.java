package br.com.housecode.store.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;

import br.com.housecode.store.conf.AmazonConfiguration;

@Component
public class FileSaver extends AmazonConfiguration {
	
	@Autowired
	private AmazonS3Client s3;
	
	public String write(String baseFolder, MultipartFile file) {
		try {
			s3.putObject("housecode", file.getOriginalFilename(), file.getInputStream(), new ObjectMetadata());
			return "http://localhost:9444/s3/housecode/" + file.getOriginalFilename() + "?noAuth=true";
		} catch (AmazonClientException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	
}
