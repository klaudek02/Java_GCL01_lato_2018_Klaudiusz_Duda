package com.example.project;

import java.io.Serializable;

public class ResultMessage implements Serializable{

	private static final long serialVersionUID = 8825361718217588503L;
	private boolean result;
	ResultMessage(boolean result)
	{
		this.result = result;
	}
	public boolean getResult()
	{
		return result;
	}
}
