package com.example.demo.repository;

import com.example.demo.entity.SaleTransactionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SaleTransactionRepository extends CrudRepository<SaleTransactionEntity, Long>
{
	List<SaleTransactionEntity> findBySaleId(Long saleId);
}
