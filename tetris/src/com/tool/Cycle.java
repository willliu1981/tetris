package com.tool;

import java.util.ArrayDeque;
import java.util.LinkedList;

import com.control.exception.TNullException;

/*
 * stock Cube in this project
 */
public class Cycle<E> extends ArrayDeque<E> {

	public void rotate() {

	}

	public E get() {
		if(this.peek()==null) {
			throw new TNullException("Cycle element is null");
		}
		return this.peek();
	}

}
