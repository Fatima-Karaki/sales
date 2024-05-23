package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "client")
@Setter
@Getter
public class ClientEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "mobile")
	private String mobile;

}
