package com.tool;

import java.util.ArrayDeque;
import java.util.LinkedList;

import com.control.exception.TNullException;

/*
 * stock Cube in this project
 */
public class Cycle<E> extends ArrayDeque<E> implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void rotateForward() {
		this.offerLast(this.pollFirst());
	}

	public void rotateBack() {
		this.offerFirst(this.pollLast());
	}

	public E get() {
		return this.peek();
	}

}
