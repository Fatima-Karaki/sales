package com.example.demo.exception;

import com.example.demo.common.Response;
import com.example.demo.common.ResponseHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler
{
	@ExceptionHandler(BaseException.class)
	ResponseEntity<Response> onCustomException(BaseException e)
	{
		return ResponseEntity.status(e.getHttpStatusCode()).body(ResponseHelper.FAILED(e));
	}
}
