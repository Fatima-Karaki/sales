package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UpdateTransactionDto
{
	private Integer quantity;
	private Double price;
}
