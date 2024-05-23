package com.example.demo.repository;

import com.example.demo.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long>
{
	Page<ProductEntity> findAll(Pageable pageable);
}
