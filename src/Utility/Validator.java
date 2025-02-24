package Utility;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;

public  class Validator {
    public static boolean isValidEmailAddress(String email) {
        // .getInstance() returns a shared instance of EmailValidator. 
        // This is a common pattern for classes that don’t need multiple instances, 
        // known as a singleton pattern.
        
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email.trim());
    }
    
    public static boolean isValidPassword(String password){
        RegexValidator passwordPattern = new RegexValidator(
                "^(?=.*[A-Z])(?=.*[a-z])(?=.*[*a-zA-Z0-9]).{8,}$");
        return passwordPattern.isValid(password);
    }
    
    public static boolean isValidPhoneNum(String phoneNum){
        RegexValidator phoneNumPattern1 = new RegexValidator("^\\+?[1-9][0-9]{0,2}[0-9]{8,12}$");
        RegexValidator phoneNumPattern2 = new RegexValidator("^\\+?[1-9][0-9]{0,2}\\s[0-9]{8,12}$");
        return phoneNumPattern1.isValid(phoneNum) || phoneNumPattern2.isValid(phoneNum);
    }
}
