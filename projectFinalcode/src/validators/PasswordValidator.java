package validators;

import exceptions.EmptyStringException;
import exceptions.InvalidAgeException;
import exceptions.InvalidPasswordException;

public class PasswordValidator {

	public PasswordValidator() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param str This is the given password value
	 * @throws InvalidPasswordException If password doesn't include at leat one uppercase , one lowercase letters and one number
	 * or includes characters other than letters, numbers, underscore character, asteriks and dot and is less then 8 characters
	 * and "has password" in it, this exception is thrown
	 */
	public static void validatePassword(String str) throws InvalidPasswordException {
		if (str.equals("")) {
			throw new InvalidPasswordException("Your password must be longer than 8 characters!");
		}
		else if(str.length()<8) {
			throw new InvalidPasswordException("Your password must be longer than 8 characters!");
		}
		else if (str.matches(".*password.*")) {
			throw new InvalidPasswordException("Your password cannot have 'password in it!");
		}
		else if(!str.matches(".*[A-Z].*")) {
			throw new InvalidPasswordException("Your password should include at least one uppercase letter!");
		}
		else if(!str.matches(".*[a-z].*")) {
			throw new InvalidPasswordException("Your password should include at least one lowercase letter!");
		}
		else if(!str.matches(".*\\d.*")) {
			throw new InvalidPasswordException("Your password should include at least one digit!");
		}
		else if(str.matches(".*[^a-zA-Z0-9_*\\.].*")) {
			throw new InvalidPasswordException("Your password cannot have characters other than letters, numbers, underscore character, asteriks and dot!");
		}
	}

}
