package org.safetynet.assembler;

import org.safetynet.entity.Item;
import org.safetynet.model.ItemModel;
import org.safetynet.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Component
public class ItemAssembler {
public List<ItemModel> toFindAllModel(List<Item> items){
	return items.stream().map(item -> {
		return new ItemModel(item.getId(), item.getName(), item.getUtility(), "belong");
	}).toList();
}
}
