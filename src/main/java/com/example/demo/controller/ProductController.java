package com.example.demo.controller;

import com.example.demo.common.Response;
import com.example.demo.dto.ProductDto;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController
{

	private final ProductService productService;

	@GetMapping
	public ResponseEntity<Response> getProducts(@RequestParam Integer limit, Integer offset, @RequestParam(required = false) Long productId)
	{
		return ResponseEntity.ok(productService.getProducts(limit, offset, productId));
	}

	@PostMapping
	public ResponseEntity<Response> createProduct(@RequestBody ProductDto productDto)
	{
		return ResponseEntity.ok(productService.createProduct(productDto));
	}

	@PutMapping("/{productId}")
	public ResponseEntity<Response> updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto)
	{
		return ResponseEntity.ok(productService.updateProduct(productId, productDto));
	}

}
