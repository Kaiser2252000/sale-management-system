/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A00_Support;

/**
 *
 * @author HL94NVT
 */
public class IsNumeric {
    public static  boolean isNumeric(String strNum) {
        try 
        {
            double d = Double.parseDouble(strNum);
        } 
        catch (NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
