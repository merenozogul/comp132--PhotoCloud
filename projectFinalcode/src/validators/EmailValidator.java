package validators;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import exceptions.EmptyStringException;
import exceptions.InvalidEmailException;
import exceptions.InvalidNickNameException;


public class EmailValidator {


	
	/**
	 * Checks whether the given e mail is a valid email or not and if it is not , throws neccessary exceptions
	 * 
	 * @param str This is an email of a user
	 * @throws EmptyStringException If user enters an empty email, this exception is thrown
	 * @throws InvalidEmailException If email is not in standart email format or is already taken by
	 * another user, this exception is thrown 
	 * @throws FileNotFoundException If UserInfo.txt is not found, this exception is thrown
	 */
	public static void validateEmail(String str) throws EmptyStringException, InvalidEmailException, FileNotFoundException {
		if (str.equals("")) {
			throw new EmptyStringException("Email cannot be empty!");
		}
		else if(!str.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			throw new InvalidEmailException("Your email is invalid!");
		}
		File file = new File("./src/UserInfo/UserInfo.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] userInfo = scanner.nextLine().split(",");
            if (userInfo[2].equals(str) ) {
            	throw new InvalidEmailException("Email is already taken by another user!");
            }
            
        }
        scanner.close();
			
	}

}
