package com.main.control.exception;

public class EOFException extends RuntimeException {
	private static String defaultMsg = "Data is EOF";

	public EOFException() {
		super(defaultMsg);
	}

	public EOFException(String msg) {
		super(defaultMsg + ":" + msg);
	}
}
