package validators;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import exceptions.EmptyStringException;
import exceptions.InvalidNickNameException;


public class NickNameValidator {


	/**
	 * Checks whether the given nickname isvalid or not and if it is not it throws necessary exceptions
	 * @param str This is the user's nickname
	 * @throws EmptyStringException If nickname is empty, this exception is thrown
	 * @throws InvalidNickNameException If nickname is invalid,contains characters other than letters, numbers or "_" or
	 * is already taken by another user, this exception thrown
	 * @throws FileNotFoundException If UserInfo.txt is not found, this exception thrown
	 */
	public static void validateNickName(String str) throws EmptyStringException, InvalidNickNameException, FileNotFoundException {
		if (str.equals("")) {
			throw new EmptyStringException("Nick name cannot be empty!");
		}
		else if (!str.matches("^[a-zA-Z0-9_]*$")) {
			throw new InvalidNickNameException("Your nickname can only involve letters, numbers and underscore character!");
		}

		File file = new File("./src/UserInfo/UserInfo.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] userInfo = scanner.nextLine().split(",");
            if (userInfo[0].equals(str) ) {
            	throw new InvalidNickNameException("Nickname is already taken by another user!");
            }
            
        }
        scanner.close();
			
	}

}
