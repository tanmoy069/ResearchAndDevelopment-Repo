package com.tanmoy.CustomAnnotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tanmoy.CustomAnnotation.Processor.JsonFieldProcessor;
import com.tanmoy.CustomAnnotation.annotations.JsonField;
import com.tanmoy.CustomAnnotation.exception.JsonProcessorException;
import com.tanmoy.CustomAnnotation.model.Users;

/**
 * Spring boot class of {@link CustomAnnotationApplication}
 * 
 * @author tanmoy.tushar
 * @since Mar 3, 2022
 *
 */
@SpringBootApplication
public class CustomAnnotationApplication {

	public static void main(String[] args) throws JsonProcessorException {
		SpringApplication.run(CustomAnnotationApplication.class, args);

		CustomAnnotationApplication caa = new CustomAnnotationApplication();
		caa.checkJsonField();
	}

	/**
	 * Check {@link JsonField} annotation here.
	 * 
	 * @throws JsonProcessorException
	 */
	public void checkJsonField() throws JsonProcessorException {
		Users user = new Users();
		user.setPassword("encrypted_password_xyz");
		JsonFieldProcessor processor = new JsonFieldProcessor();
		processor.processor(user);
	}

}
