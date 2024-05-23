package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ClientDto
{
	private String name;
	private String lastName;
	private String mobile;
}
