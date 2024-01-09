package org.safetynet.service.impl;

import lombok.AllArgsConstructor;
import org.safetynet.entity.Item;
import org.safetynet.repository.ItemRepository;
import org.safetynet.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemServiceImpl  implements ItemService {

	private final ItemRepository repository;

	@Override
	public List<Item> findAll(){
		return repository.findAll();
	}
}