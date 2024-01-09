package org.safetynet.controller;


import lombok.AllArgsConstructor;
import org.safetynet.assembler.ItemAssembler;
import org.safetynet.entity.Item;
import org.safetynet.model.ItemModel;
import org.safetynet.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ItemController {
	private final ItemAssembler assembler;
	private final ItemService service;

	@GetMapping("/")
	public ResponseEntity<List<ItemModel>> findAll(){
		return new ResponseEntity<>(assembler.toFindAllModel(service.findAll()), HttpStatus.OK);
	}
}
