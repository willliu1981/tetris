package com.main.control.exception;

public class FileErrorException extends RuntimeException {
	private static String defaultMsg = "File error";

	public FileErrorException() {
		super(defaultMsg);
	}

	public FileErrorException(String msg) {
		super(defaultMsg + ":" + msg);
	}
}
