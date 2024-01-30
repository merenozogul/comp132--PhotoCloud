package validators;

import exceptions.EmptyStringException;
import exceptions.InvalideNameException;

public class NameSurnameValidator {

	
	/**
	 * Checks whether given name or surname is valid or not and if it is not, throws neccessary exceptions
	 * @param str This is a string, a name or a surname
	 * @param nameOrSurname This is tells us if str is a name or a surname
	 * @throws EmptyStringException If given name or surname is empty, this exception occurs
	 * @throws InvalideNameException If name or surname contains nonletter characters, this exception occurs
	 */
	public static void validateNameSurname(String str,String nameOrSurname) throws EmptyStringException, InvalideNameException {
		if (str.equals("")) {
			throw new EmptyStringException(nameOrSurname+" cannot be empty!");
		}
		else if (!str.matches("^[a-zA-Z\\s]*$")) {
			throw new InvalideNameException("Your name or suname can only involve English letters, nothing else!");
		}
			
	}

}
