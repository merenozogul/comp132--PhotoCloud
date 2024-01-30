package main;

public class StringHandlerr {

	public StringHandlerr() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Takes a string and put it in a center of another string of length width and fills the remaining part
	 * with white spaces, it is used to set the size of buttons in GridLayout
	 * @param width This is the length of the returned string
	 * @param s this is the string that will be sorrounded by white spaces
	 * @return
	 */
	public static String centerString (int width, String s) {
		String newS="";
        int sizeOfString = width - s.length();
        int stringStart = s.length() + sizeOfString / 2;
        for(int i=0;i<stringStart;i++) {
        	newS+=" ";
        }
        newS+=s;
        if (s.length()%2==1) {
	        for(int i=0;i<stringStart-2;i++) {
	        	newS+=" ";
	        }
        }
        else {
        	for(int i=0;i<stringStart;i++) {
	        	newS+=" ";
	        }
        }
        return newS;
    }
	

}
