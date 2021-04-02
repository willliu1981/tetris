package com.main.control.exception;

public class TNullException extends RuntimeException {
	private static String defaultMsg = "Data is null";

	public TNullException() {
		super(defaultMsg);
	}

	public TNullException(String msg) {
		super(defaultMsg + ":" + msg);
	}
}
