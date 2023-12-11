/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A00_Support;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author HL94NVT
 */
public class DateValidation {
    public static boolean dateValidation(String date)
    {
        boolean status = false;
        if (checkDate(date)) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
        dateFormat.parse(date);
        status = true;
        } catch (Exception e) {
            status = false;
        }
    }
    return status;
    }
    
    public static boolean checkDate(String date) {
    String pattern = "([0-9]{4})-(0?[1-9]|1[0-2])-(0?[1-9]|[12][0-9]|3[01])";
    boolean flag = false;
    if (date.matches(pattern)) {
      flag = true;
    }
    return flag;
  }
    public static void main(String[] args) {
        System.out.print(dateValidation("2000-01-01"));
    }
}
