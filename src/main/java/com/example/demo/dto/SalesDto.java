package com.example.demo.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class SalesDto
{
	private Long clientId;
	private String seller;
	private List<TransactionDto> transactionDtoList;
}
