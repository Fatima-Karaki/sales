package com.example.demo.common;

import com.example.demo.exception.BaseException;
import org.springframework.http.HttpStatus;

public class ResponseHelper
{

	public static Response SUCCESS()
	{
		return new Response().setStatus("success").setHttpStatus(HttpStatus.OK).setStatusCode(200);
	}

	public static Response SUCCESS(Object result)
	{
		return new Response().setStatus("success").setResult(result).setHttpStatus(HttpStatus.OK).setStatusCode(200);
	}

	public static Response CREATED(Object result)
	{
		return new Response().setStatus("created").setResult(result).setHttpStatus(HttpStatus.CREATED).setStatusCode(201);
	}

	public static Response FAILED(BaseException exception)
	{
		return new Response().setStatus("failed")
							 .setHttpStatus(exception.getHttpStatusCode())
							 .setStatus(exception.getMessage())
							 .setStatusCode(exception.getHttpStatusCode().value());
	}
}
