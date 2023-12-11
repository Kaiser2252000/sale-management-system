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
public class IsInt {
    public static boolean isStringInt(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        }   catch (NumberFormatException ex)
        {
            return false;
        }
}   
    
}
