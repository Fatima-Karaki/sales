package com.example.demo.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@Accessors(chain = true)
public class BaseException extends RuntimeException
{
	private HttpStatus httpStatusCode = HttpStatus.BAD_REQUEST;

	public BaseException(String message)
	{
		super(message);
	}

	public BaseException(ExceptionCode exceptionCode)
	{
		super(exceptionCode.getMessage());
	}
}
