package com.example.demo.common;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Data
@Accessors(chain = true)
public class Response
{
	private Object result;
	private String status;
	private Integer statusCode;
	private HttpStatus httpStatus;
}

