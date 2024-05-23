package com.example.demo.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode
{

	PRODUCT_NOT_FOUND("Product type not found"),
	CLIENT_NOT_FOUND("Client not found"),
	SALE_NOT_FOUND("Sale not found"),
	SALE_TRANSACTION_UPDATE_EMPTY("DTO to update transaction is empty"),
	SALE_TRANSACTION_NOT_FOUND("Sale transaction not found");

	private final String message;

	ExceptionCode(String message)
	{
		this.message = message;
	}

}
