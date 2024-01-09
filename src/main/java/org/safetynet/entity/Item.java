package org.safetynet.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Item {

	private int id;
	private String name;
	private String utility;
}
