package org.safetynet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.safetynet.entity.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;

@RestController
@AllArgsConstructor
public class DataController {

	@RequestMapping("/")
	@ResponseBody
	private ResponseEntity<Data> getData(){
		String dataURL = "https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json";
		RestTemplate restTemplate = new RestTemplate();

		Data data = restTemplate.getForObject(dataURL, Data.class);

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(new File("src/main/resources/data.json"), data );
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return new ResponseEntity<>( data , HttpStatus.OK);
	}
}
