package br.com.sbcuni.exception;

public class SbcuniException extends Exception {

	private static final long serialVersionUID = -147997906471489703L;

	public SbcuniException(String msg, Exception e) {
		super(msg, e);
	}

	public SbcuniException(String msg) {
		super(msg);
	}

	public SbcuniException(Exception e) {
		super(e);
	}

	public SbcuniException() {

	}

}
