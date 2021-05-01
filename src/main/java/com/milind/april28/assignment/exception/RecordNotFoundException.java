package com.milind.april28.assignment.exception;

import java.util.Date;

public class RecordNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	Date created = new Date();

	public RecordNotFoundException()
	{
	}

	public RecordNotFoundException(String message)
	{
		super(message);
	}

	public RecordNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public RecordNotFoundException(Throwable cause)
	{
		super(cause);
	}

}
