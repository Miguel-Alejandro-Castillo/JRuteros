package utils;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class RollbackException extends RuntimeException {
	private static final long serialVersionUID = 1L; 
	
	public RollbackException(){
		super();
	}
}
