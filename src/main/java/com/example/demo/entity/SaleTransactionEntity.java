package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sale_transaction")
@Getter
@Setter
public class SaleTransactionEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "sale_id")
	private Long saleId;

	@Column(name = "product_id")
	private Long productId;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "price")
	private double price;
}
