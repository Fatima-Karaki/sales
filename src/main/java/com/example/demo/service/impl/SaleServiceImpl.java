package com.example.demo.service.impl;

import com.example.demo.common.OperationTypeEnum;
import com.example.demo.common.Response;
import com.example.demo.common.ResponseHelper;
import com.example.demo.dto.SalesDto;
import com.example.demo.dto.TransactionDto;
import com.example.demo.dto.UpdateTransactionDto;
import com.example.demo.entity.LogEntity;
import com.example.demo.entity.SaleEntity;
import com.example.demo.entity.SaleTransactionEntity;
import com.example.demo.exception.BaseException;
import com.example.demo.exception.ExceptionCode;
import com.example.demo.repository.*;
import com.example.demo.service.SaleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class SaleServiceImpl implements SaleService
{
	private final LogEntityRepository logEntityRepository;
	private final SaleRepository saleRepository;
	private final SaleTransactionRepository saleTransactionRepository;
	private final ClientRepository clientRepository;
	private final ProductRepository productRepository;

	@Override
	public Response getSales(Integer limit, Integer offset)
	{
		Pageable pageable = PageRequest.of(offset, limit, Sort.by("creationDate").descending());
		Page<SaleEntity> saleEntityPage = saleRepository.findAll(pageable);
		return ResponseHelper.SUCCESS(saleEntityPage);
	}

	@Override
	public Response getSaleTransactions(Long saleId)
	{
		Optional<SaleEntity> saleEntity = saleRepository.findById(saleId);
		if(saleEntity.isEmpty())
		{
			throw new BaseException(ExceptionCode.SALE_NOT_FOUND);
		}
		return ResponseHelper.SUCCESS(saleEntity);
	}

	@Override
	@Transactional
	public Response createSaleWithTransaction(SalesDto salesDto)
	{
		if(!clientRepository.existsById(salesDto.getClientId()))
		{
			throw new BaseException(ExceptionCode.CLIENT_NOT_FOUND);
		}

		SaleEntity saleEntity = new SaleEntity().setSeller(salesDto.getSeller())
												.setClientId(salesDto.getClientId())
												.setCreationDate(LocalDateTime.now());

		saleRepository.save(saleEntity);

		double total = this.addTransactionsAndCalculatePrice(saleEntity.getId(), salesDto.getTransactionDtoList());

		saleEntity.setTotal(total);
		saleRepository.save(saleEntity);

		return ResponseHelper.CREATED(saleEntity);
	}

	@Override
	@Transactional
	public Response updatePriceAndQuantity(Long transactionId, UpdateTransactionDto dto)
	{
		Optional<SaleTransactionEntity> saleTransactionEntity = saleTransactionRepository.findById(transactionId);
		if(saleTransactionEntity.isEmpty())
		{
			throw new BaseException(ExceptionCode.SALE_TRANSACTION_NOT_FOUND);
		}
		SaleTransactionEntity saleTransaction = saleTransactionEntity.get();

		OperationTypeEnum operationTypeEnum;

		if(dto.getQuantity() != null && dto.getPrice() != null)
		{
			saleTransaction.setQuantity(dto.getQuantity());
			saleTransaction.setPrice(dto.getPrice());
			operationTypeEnum = OperationTypeEnum.UPDATE_PRICE_AND_QUANTITY;
		}
		else if(dto.getQuantity() != null)
		{
			saleTransaction.setQuantity(dto.getQuantity());
			operationTypeEnum = OperationTypeEnum.UPDATE_QUANTITY;
		}
		else if(dto.getPrice() != null)
		{
			saleTransaction.setPrice(dto.getPrice());
			operationTypeEnum = OperationTypeEnum.UPDATE_PRICE;
		}
		else
		{
			throw new BaseException(ExceptionCode.SALE_TRANSACTION_UPDATE_EMPTY);
		}

		saleTransactionRepository.save(saleTransaction);

		LogEntity logEntity = new LogEntity().setCreationDate(LocalDateTime.now())
											 .setOperationType(operationTypeEnum.name())
											 .setTransactionId(transactionId);

		logEntityRepository.save(logEntity);
		log.info("log of update added of operation {}", logEntity.getOperationType());

		this.updateSaleTotal(transactionId);
		return ResponseHelper.SUCCESS(saleTransaction);
	}

	private double addTransactionsAndCalculatePrice(Long saleId, List<TransactionDto> transactionDtoList)
	{
		double[] total = { 0.0 };
		transactionDtoList.forEach(transactionDto -> {
			SaleTransactionEntity saleTransactionEntity = new SaleTransactionEntity();
			if(productRepository.findById(transactionDto.getProductId()).isEmpty())
			{
				throw new BaseException(ExceptionCode.PRODUCT_NOT_FOUND);
			}
			BeanUtils.copyProperties(transactionDto, saleTransactionEntity);
			saleTransactionEntity.setSaleId(saleId);
			saleTransactionRepository.save(saleTransactionEntity);
			total[0] = total[0] + transactionDto.getPrice() * transactionDto.getQuantity();
		});
		return total[0];
	}

	private void updateSaleTotal(Long transactionId)
	{
		Optional<SaleTransactionEntity> saleTransactionEntity = saleTransactionRepository.findById(transactionId);
		Optional<SaleEntity> saleEntity = saleRepository.findById(saleTransactionEntity.get().getSaleId());

		SaleEntity sale = saleEntity.get();
		double[] total = { 0.0 };
		sale.getSaleTransactions().forEach(transaction -> {
			total[0] = total[0] + transaction.getPrice() * transaction.getQuantity();
		});

		sale.setTotal(total[0]);
		saleRepository.save(sale);
	}
}
