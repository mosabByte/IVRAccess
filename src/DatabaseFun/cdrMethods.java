/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseFun;

import Common.Log;
import Connection.Conn;
import DataType.cdr;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author mosab
 */
public class cdrMethods  extends Conn{
    /*
     public String calldate;
    public String clid;
    public String src;
    public String dst;
    public String dcontext;
    public String channel;
    public String dstchannel;
    public String lastapp;
    public String duration;
    public String billsec;
    public String disposition;
    public String amaflags;
    public String uniqueid;
    public String operator;
    */
    
    public ArrayList<cdr>  getCdrInfo(){
        
        ResultSet resultset;
        ArrayList<cdr> productsInfo = new ArrayList();
        try{
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM IVR.cdr order by calldate desc limit 100;");
             
            resultset=statement.executeQuery();
            while(resultset.next()){
                cdr porduct = new cdr();
                porduct.setCalldate(resultset.getString("calldate"));
                porduct.setClid(resultset.getString("clid"));
                porduct.setSrc(resultset.getString("src"));
                porduct.setDst(resultset.getString("dst"));
                porduct.setDcontext(resultset.getString("dcontext"));
                porduct.setChannel(resultset.getString("channel"));
                porduct.setDstchannel(resultset.getString("dstchannel"));
                porduct.setLastapp(resultset.getString("lastapp"));
                porduct.setDuration(resultset.getString("duration"));
                porduct.setBillsec(resultset.getString("billsec"));
                porduct.setDisposition(resultset.getString("disposition"));
                porduct.setAmaflags(resultset.getString("amaflags"));
                porduct.setUniqueid(resultset.getString("uniqueid"));
                porduct.setOperator(resultset.getString("operator"));
                
                
                productsInfo.add(porduct);
                
            }
            resultset.close();
            
        }catch(Exception e){
            Log.writeEvent("Error in ProductsDataSource : "+ e.toString(),"");
         }
        return productsInfo;
    }
    
    public static void main (String [] args){
        cdrMethods xx = new cdrMethods();
         ArrayList<cdr> info =xx. getCdrInfo();
        for (cdr in : info){
            System.out.println("out  :"+ in.getClid());
        }
    }
    
}
