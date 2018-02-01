/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;
import Common.Log;
 import java.sql.Connection;
import java.sql.DriverManager;
 

/**
 *
 * @author mosab
 */
public class Conn {
   public  Connection connection = null;
    
    public Conn() {
        try {
            
            Class.forName("org.mariadb.jdbc.Driver");

            String databaserServer = Log.getConfigurationParameter ("databaseServer","127.0.0.1");
            String databaserUser = Log.getConfigurationParameter ("databaseUser","root");
            String databaserpasswrod= Log.getConfigurationParameter ("databasePassword","Code-sd.com");
           

            connection = DriverManager.getConnection(
            "jdbc:mariadb://" + databaserServer + "/IVR" +
                  "?user="+databaserUser+"&password="+databaserpasswrod+"&useUnicode=true&characterEncoding=UTF-8"); 


        }
        catch (Exception ex ) {
           Log.writeEvent("Error in : Connection : "+ex.toString(),"");
         }        
    }          
     public static void main(String [] args){
       Conn x =new Conn();
    }
    
    
    
    
   public void close() {
       try {
         if (connection != null) {
            connection.close();
            connection = null;
         }
       } catch (Exception ex){
           Log.writeEvent("Error while disconnect: " + ex.toString(),"");
       }
   }
    
}
