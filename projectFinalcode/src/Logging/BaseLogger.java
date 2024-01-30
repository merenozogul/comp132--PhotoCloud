package Logging;

import java.util.Date;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
public class BaseLogger {

	public BaseLogger() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Takes initial time and final time of the process and writes the initial time and message in "PhotoCloudInfo.txt".
	 * Also adds the time spent on that process and writes how many ms is passed.
	 * @param it initial time that the process is started
	 * @param ft final time that the process is terminated
	 * @param info information about what happened in that process
	 */
	public static void info(Date it,Date ft,String info) {
		System.out.println(info);
		 String filename = "./src/Logging/PhotoCloudInfo.txt";
		 long timeInterval = ft.getTime() - it.getTime();
		 BufferedWriter bw = null;
		 
		 String newInfo=String.format("[%s] [INFO] %s, took: %d ms", it.toString(),info,timeInterval);
		 try {
			bw = new BufferedWriter(new FileWriter(filename, true));
			bw.newLine();
            bw.write(newInfo);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
	        if (bw != null) {
	            try {
	                bw.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
		 
		 
		
	}
	
	/**
	 * Takes initial time and final time of the process and writes the initial time and error message in "PhotoCloudError.txt".
	 * Also adds the time spent on that process and writes how many ms is passed.
	 * @param it initial time that the process is started
	 * @param ft final time that the process is terminated
	 * @param info information about what kind of error happened in that process
	 */
	public static void error(Date it,Date ft,String info) {
		String filename = "./src/Logging/PhotoCloudError.txt";
		 long timeInterval = ft.getTime() - it.getTime();
		 BufferedWriter bw = null;
		 String newError=String.format("[%s] [ERROR] %s, took: %d ms", it.toString(),info,timeInterval);
		 try {
				bw = new BufferedWriter(new FileWriter(filename, true));
				bw.newLine();
	            bw.write(newError);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
	        if (bw != null) {
	            try {
	                bw.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
		}
	}

}
