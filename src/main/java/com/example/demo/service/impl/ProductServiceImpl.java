package com.example.demo.service.impl;

import com.example.demo.common.Response;
import com.example.demo.common.ResponseHelper;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.ProductEntity;
import com.example.demo.exception.BaseException;
import com.example.demo.exception.ExceptionCode;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService
{

	private final ProductRepository productRepository;

	@Override
	public Response createProduct(ProductDto productDto)
	{
		ProductEntity productEntity = new ProductEntity();
		BeanUtils.copyProperties(productDto, productEntity);
		productEntity.setCreationDate(LocalDateTime.now());

		productRepository.save(productEntity);
		return ResponseHelper.CREATED(productEntity);
	}

	@Override
	public Response getProducts(Integer limit, Integer offset, Long productId)
	{
		if(productId != null)
		{
			Optional<ProductEntity> productEntity = productRepository.findById(productId);
			if(productEntity.isEmpty())
			{
				throw new BaseException(ExceptionCode.PRODUCT_NOT_FOUND);
			}
			return ResponseHelper.SUCCESS(productEntity.get());
		}

		Pageable pageable = PageRequest.of(offset, limit, Sort.by("creationDate").descending());
		Page<ProductEntity> productEntities = productRepository.findAll(pageable);
		return ResponseHelper.CREATED(productEntities);
	}

	@Override
	public Response updateProduct(Long productId, ProductDto productDto)
	{
		Optional<ProductEntity> productEntity = productRepository.findById(productId);
		if(productEntity.isEmpty())
		{
			throw new BaseException(ExceptionCode.PRODUCT_NOT_FOUND);
		}

		ProductEntity product = productEntity.get();

		Optional.ofNullable(productDto.getName()).ifPresent(product::setName);
		Optional.ofNullable(productDto.getDescription()).ifPresent(product::setDescription);
		Optional.ofNullable(productDto.getCategory()).ifPresent(product::setCategory);

		productRepository.save(product);
		return ResponseHelper.SUCCESS(product);
	}
}
