package br.com.housecode.store.conf;

import org.springframework.context.annotation.Bean;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;

public class AmazonConfiguration {

	private static final String ENDPOINT_AMAZON_LOCAL = "http://localhost:9444/s3";

	public AmazonConfiguration() {
		super();
	}
	
	@Bean
	private AmazonS3Client s3Ninja() {
		AWSCredentials credentials = new BasicAWSCredentials("AKIAIOSFODNN7EXAMPLE", "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY");
		AmazonS3Client newClient = new AmazonS3Client(credentials, new ClientConfiguration());
		newClient.setS3ClientOptions(new S3ClientOptions().withPathStyleAccess(true));
		newClient.setEndpoint(ENDPOINT_AMAZON_LOCAL);
		return newClient;
	}

}