/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseFun;

import DataType.cdr;
import java.util.ArrayList;

/**
 *
 * @author mosab
 */
public class cdrControl {
    
    public static ArrayList<cdr> getCdrInfo(String productID) {
        cdrMethods product = new cdrMethods();
        ArrayList<cdr> info = product.getCdrInfo();
        product.close();
        return info;

    }
    
}
