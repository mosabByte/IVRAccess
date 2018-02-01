/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author mosab
 */
public class Log {
            static private String lastError;

     public static boolean isUnixLike(){
	
	return File.separator.equals("/");
     }
      public static void writeEvent(String eventText, String logname) {
        
        writeEvent(eventText, logname, "");
    }
    public static void writeEvent(String eventText, String logname, String logdir) {
        
        
       try {  
              
            // Get current day
            Date today = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(today);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            
	    // Default directory if it is null
            if ((logdir == null) || (logdir.isEmpty())){
		if (isUnixLike()) {
		    logdir = "/var/log/code";
		}
		else {
		    logdir =  System.getProperty("user.home")  + "\\code\\log";
		}
	    }

	    // Remove last seperator from directory
	    if (logdir.substring(logdir.length()-1, logdir.length()).equals(File.separator)) {
			logdir = logdir.substring(0, logdir.length() -1);
			
	    }
	    
            // Get today file name
            String fileName;
            fileName = logdir + File.separator + "ivr" + "-" + day + ".log";
            
            
            // Check log directory existance
            File dir = new File(logdir);
            
            if (!dir.exists()){
                dir.mkdir();
            }            
            
            File file = new File(fileName);
            
            // Recycle file after 1 month
            if (file.exists()) {
                
                Date fileDate = new Date();
                Date yesterday = new Date();
                yesterday.setTime(today.getTime() - 1000 * 60 * 60 * 24);
                fileDate.setTime(file.lastModified());
                
                // Delete old file
                if (fileDate.before(yesterday)) {
                    file.delete();
                }
            }
            
	    // Write into log file
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(today.toString() + " : " + eventText + "\n");  
            writer.close();
            
              
        } catch (IOException e) {  
            System.out.println("Unable to write: " + eventText + ", in log file: " + e.toString());
        }     
    }
    
     public static String getConfigurationParameter(String parameterName, String defaultValue){
        
       return getConfigurationParameter(parameterName, defaultValue, "middleware.ini");
        
         
    }
    public static String getConfigurationParameter(String parameterName, String defaultValue, String aFile) {
        
       Properties prop  = new Properties();
       try

       {
         if ((!aFile.contains(File.separator)) && isUnixLike()){
                aFile = "/etc/code/" + aFile;
	 }else {
                aFile = System.getProperty("user.home")  + "\\code\\" + aFile;
	}
       
         String text;
	 try (FileInputStream stream = new FileInputStream(aFile)) {
	      prop.load(stream);
	      text = prop.getProperty(parameterName, defaultValue);
	 }
           
         return(text);
         
       }
       catch (IOException ex) {
         lastError = "Error in getConfiguration: " + ex.toString();
         return(defaultValue);
       }
     
    }
    
}
