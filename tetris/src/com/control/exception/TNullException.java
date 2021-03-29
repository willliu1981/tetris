package com.control.exception;

public class TNullException extends RuntimeException {
	private static String defaultMsg = "Null";

	public TNullException() {
		super("Data is null");
	}

	public TNullException(String msg) {
		super(defaultMsg + ":" + msg);
	}
}
