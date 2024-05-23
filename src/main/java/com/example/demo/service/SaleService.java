package com.example.demo.service;

import com.example.demo.common.Response;
import com.example.demo.dto.SalesDto;
import com.example.demo.dto.UpdateTransactionDto;

public interface SaleService
{
	Response getSales(Integer limit, Integer offset);

	Response getSaleTransactions(Long saleId);

	Response createSaleWithTransaction(SalesDto salesDto);

	Response updatePriceAndQuantity(Long transactionId, UpdateTransactionDto dto);
}
