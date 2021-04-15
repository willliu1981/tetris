package com.main.control.exception;

public class LogicErrorException extends RuntimeException {
	private static String defaultMsg = "Logic error";

	public LogicErrorException() {
		super(defaultMsg);
	}

	public LogicErrorException(String msg) {
		super(defaultMsg + ":" + msg);
	}
}
