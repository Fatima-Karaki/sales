package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ProductDto
{

	private String name;
	private String description;
	private String category;
}
