package com.example.demo.repository;

import com.example.demo.entity.ClientEntity;
import com.example.demo.entity.SaleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface SaleRepository extends CrudRepository<SaleEntity, Long>
{
	Page<SaleEntity> findAll(Pageable pageable);
}
