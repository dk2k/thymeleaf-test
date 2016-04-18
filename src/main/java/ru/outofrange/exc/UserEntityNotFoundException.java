package ru.outofrange.exc;

public class UserEntityNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserEntityNotFoundException(){};
	
    //Constructor that accepts a message
    public UserEntityNotFoundException(String message)
    {
       super(message);
    }
}
