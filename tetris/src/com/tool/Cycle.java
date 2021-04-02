package com.tool;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

import com.main.control.exception.TNullException;

/*
 * stock Cube in this project
 */
public class Cycle<E> extends ArrayDeque<E> implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int currentPointer;

	public void rotateLeft() {
		this.offerFirst(this.pollLast());
		runCyclePointer(-1);
	}

	public void rotateRight() {
		this.offerLast(this.pollFirst());
		runCyclePointer(+1);
	}

	/*
	 * protecte pointer in the range, only pointer
	 */
	protected void runCyclePointer(int move) {
		this.currentPointer += move;
		if (this.currentPointer < 0) {
			this.currentPointer = this.size() - 1;
		}
		if (this.currentPointer >= this.size()) {
			this.currentPointer = 0;
		}
	}

	public void resetPointerToCurrentElement() {
		this.currentPointer = 0;
	}

	public E get(int index) {
		Cycle<E> c = this.stream().collect(Collectors.toCollection(Cycle::new));
		c.rotateTo(index);
		return c.get();
	}

	public void rotateTo(int index) {
		while (index != this.currentPointer) {
			int half = this.size() / 2;
			int difference = index - this.currentPointer;
			half *= difference > 0 ? 1 : -1;
			if (difference <= half) {
				this.rotateRight();
				System.out.println("-R-");
			} else {
				this.rotateLeft();
				System.out.println("-L-");
			}
		}
	}

	/*
	 * get and set
	 */
	public E get() {
		return this.peek();
	}

	public int getCurrentPointer() {
		return this.currentPointer;
	}

	public void insertAtTheBack(E e) {
		this.offerLast(this.pollFirst());
		this.offerFirst(e);
		runCyclePointer(+1);
	}

}
