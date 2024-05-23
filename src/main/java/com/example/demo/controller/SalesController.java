package com.example.demo.controller;

import com.example.demo.common.Response;
import com.example.demo.dto.SalesDto;
import com.example.demo.dto.UpdateTransactionDto;
import com.example.demo.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sale")
@RequiredArgsConstructor
public class SalesController
{

	private final SaleService saleService;

	@GetMapping
	public ResponseEntity<Response> getSales(@RequestParam Integer limit, Integer offset)
	{
		return ResponseEntity.ok(saleService.getSales(limit, offset));
	}

	@GetMapping("/{saleId}")
	public ResponseEntity<Response> getSaleByIdTransactions(@PathVariable Long saleId)
	{
		return ResponseEntity.ok(saleService.getSaleTransactions(saleId));
	}

	@PostMapping
	public ResponseEntity<Response> createSalesWithTransactions(@RequestBody SalesDto salesDto)
	{
		return ResponseEntity.ok(saleService.createSaleWithTransaction(salesDto));
	}

	@PutMapping("/{transactionId}")
	public ResponseEntity<Response> updatePriceAndQuantity(@PathVariable Long transactionId, @RequestBody UpdateTransactionDto dto)
	{
		return ResponseEntity.ok(saleService.updatePriceAndQuantity(transactionId, dto));
	}

}
