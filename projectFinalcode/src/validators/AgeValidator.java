package validators;

import exceptions.InvalidAgeException;
import exceptions.EmptyStringException;

public class AgeValidator {


	/**
	 * Checks if a given age value is a valid age and if it is not, throws necessary exceptions
	 * @param age This is the age of the user
	 * @throws EmptyStringException when age parameter is empty, this exception is thrown
	 * @throws InvalidAgeException When age doesn't include numbers or lessthen zero, this exception is thrown
	 */
	public static void validateAge(String age) throws EmptyStringException, InvalidAgeException {
		if (age.equals("")) {
			throw new EmptyStringException("Age cannot be empty!");
		}
		else if(age.length()>3) {
			throw new InvalidAgeException("Age cannot be that much!");
		}
		else if (!age.matches("\\d+")) {
			throw new InvalidAgeException("Your age must be a number!");
		}

		else if(Integer.parseInt(age)<0) {
			throw new InvalidAgeException("Your age must be greater than 0!");
		}
		
			
	}

}
