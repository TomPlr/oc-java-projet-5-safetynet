package org.safetynet.repository.impl;

import org.safetynet.entity.Item;
import org.safetynet.repository.ItemRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

	private final List<Item> items = List.of(Item.builder()
			.id(1)
			.name("name1")
			.utility("utility1")
			.build());

	@Override
	public List<Item> createItems(){
		return items;
	}
}
