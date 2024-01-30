package validators;

import exceptions.InvalidUserTypeException;

public class UserTypeValidator {
	/**
	 * Checks whether a user type is chosen or not and if it is not throws necessary exception
	 * @param str This is the string which tells use the user type , free, hobbyist, professional,administrator
	 * @throws InvalidUserTypeException this is thrown when user type is not chosen, an empty string,
	 */
	public static void validateUserType(String str) throws InvalidUserTypeException {
		if (str.equals("")) {
			throw new InvalidUserTypeException("You need to choose user type!");
		}
	}

}
