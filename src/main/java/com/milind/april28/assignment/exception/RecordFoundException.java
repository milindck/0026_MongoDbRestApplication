package com.milind.april28.assignment.exception;

import java.util.Date;

public class RecordFoundException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	private Date created = new Date();

	public RecordFoundException()
	{
	}

	public RecordFoundException(String message)
	{
		super(message);
	}

	public Date getCreated()
	{
		return created;
	}

	public void setCreated(Date created)
	{
		this.created = created;
	}
}
