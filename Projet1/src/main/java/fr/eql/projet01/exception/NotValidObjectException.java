package fr.eql.projet01.exception;

import java.util.ArrayList;
import java.util.List;

public class NotValidObjectException extends AecServiceException {
	private static final long serialVersionUID = 1L;
	private List<String> errors;
	
	public NotValidObjectException() {
		this.errors = new ArrayList<String>();
	}
	
	public NotValidObjectException(List<String> errors) {
		this.errors = errors;
	}
}