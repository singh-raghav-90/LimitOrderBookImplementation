package com.limit.order.exception;

public class LimitOrderBookException extends Exception {

	private static final long serialVersionUID = 1L;

	public LimitOrderBookException(String failed) {
		super(failed);
	}

	public LimitOrderBookException(String failed, Throwable thr) {
		super(failed, thr);
	}

}
