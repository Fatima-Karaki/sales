package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "sale")
@Accessors(chain = true)
public class SaleEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "creation_date")
	private LocalDateTime creationDate;

	@Column(name = "client_id")
	private Long clientId;

	@Column(name = "seller")
	private String seller;

	@Column(name = "total")
	private double total;

	@OneToMany(mappedBy = "saleId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SaleTransactionEntity> saleTransactions;
}
