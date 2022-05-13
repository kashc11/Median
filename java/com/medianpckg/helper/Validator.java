package com.medianpckg.helper;

public class Validator {
    public static final int SUCCESS = 1;
    
    //method to validate name
    public static boolean checkName(String name) {
        
         //the name should contain a — z, A — Z, and whitespace character with a min of 3 char and a max of 50
        return name.matches("[a-zA-Z\\s]{3,50}");
    }
    
    //method to validate email and phone
    public static boolean checkEmailOrPhone(String emailOrPhone) {
        // check email first
       
        
        //format: email@domain.abc or email@domain.abc.xyz
        if (!emailOrPhone.matches("[a-z0-9-_]{3,30}@[a-z0-9-]{3,30}([.][a-z1-9]{2,5}){1,2}")) {
            
            //min of 9 digits and a max of 11 digits
            if (!emailOrPhone.matches("\\d{9,11}")) {
                return false;
            }
        }
        return true;
    }
   
    //method to validate password
    public static boolean checkPassword(String password) {
        return password.matches("(?=.*\\d+.*)(?=.*[A-Z]+.*)\\w{4,8}");
    }
}
