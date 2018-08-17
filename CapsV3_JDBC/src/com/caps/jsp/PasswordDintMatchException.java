package com.caps.jsp;

@SuppressWarnings("serial")
public class PasswordDintMatchException extends RuntimeException{
	private String msg = " ";
	
	PasswordDintMatchException() {
		msg=" ";
	}
	
	PasswordDintMatchException(String msg){
		this.msg=msg;
	}
	
	public String getMessage() {
		return this.msg;
	}
}
