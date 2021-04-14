package com.main.control.exception;

public class TCastException extends RuntimeException {
	private static String defaultMsg = "Cannot be cast";

	public TCastException() {
		super(defaultMsg);
	}

	public TCastException(String msg) {
		super(defaultMsg + ":" + msg);
	}
}
