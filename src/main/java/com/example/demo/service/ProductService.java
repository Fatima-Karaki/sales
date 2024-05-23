package com.example.demo.service;

import com.example.demo.common.Response;
import com.example.demo.dto.ProductDto;

public interface ProductService
{
	Response createProduct(ProductDto productDto);

	Response getProducts(Integer limit, Integer offset, Long productId);

	Response updateProduct(Long productId, ProductDto productDto);
}
