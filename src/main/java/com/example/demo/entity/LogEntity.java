package com.example.demo.entity;

import com.example.demo.common.OperationTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "log")
@Setter
@Getter
@Accessors(chain = true)
public class LogEntity
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "operationType")
	private String operationType;

	@Column(name = "creation_date")
	private LocalDateTime creationDate;

	@Column(name = "transaction_id")
	private Long transactionId;
}
