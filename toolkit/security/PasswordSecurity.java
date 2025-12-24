
//Class: PasswordSecurity


package toolkit.security;

public class PasswordSecurity {
    
    public int checkStrength(String password) throws IllegalArgumentException {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
        
        int score = 0;
        
        // Check length 
        int length = password.length();
        if(length >= 8) {
            score = score + 2;  
        }
        if(length >= 12) {
            score = score + 1;  
        }
        if(length >= 16) {
            score = score + 1;  
        }
        
        // Check for lowercase letters (a-z)
        boolean hasLowercase = false;
        for(int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if(ch >= 'a' && ch <= 'z') {
                hasLowercase = true;
                break;
            }
        }
        if(hasLowercase) {
            score = score + 1;
        }
        
        // Check for uppercase letters (A-Z)
        boolean hasUppercase = false;
        for(int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if(ch >= 'A' && ch <= 'Z') {
                hasUppercase = true;
                break;
            }
        }
        if(hasUppercase) {
            score = score + 2;
        }
        
        // Check for numbers (0-9)
        boolean hasNumber = false;
        for(int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if(ch >= '0' && ch <= '9') {
                hasNumber = true;
                break;
            }
        }
        if(hasNumber) {
            score = score + 2;
        }
        
        // Check for special characters (!@#$%^&* etc.)
        boolean hasSpecial = false;
        for(int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            // Check if it's NOT a letter or number
            if(!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9'))) {
                hasSpecial = true;
                break;
            }
        }
        if(hasSpecial) {
            score = score + 1;
        }
        
        // Maximum score is 10
        if(score > 10) {
            score = 10;
        }
        
        return score;
    }
    
    public double estimateCrackTime(String password) throws IllegalArgumentException {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
        
        int charsetSize = 0;
        
        // Check for lowercase (a-z) = 26 characters
        boolean hasLowercase = false;
        for(int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if(ch >= 'a' && ch <= 'z') {
                hasLowercase = true;
                break;
            }
        }
        if(hasLowercase) {
            charsetSize = charsetSize + 26;
        }
        
        // Check for uppercase (A-Z) = 26 characters
        boolean hasUppercase = false;
        for(int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if(ch >= 'A' && ch <= 'Z') {
                hasUppercase = true;
                break;
            }
        }
        if(hasUppercase) {
            charsetSize = charsetSize + 26;
        }
        
        // Check for numbers (0-9) = 10 characters
        boolean hasNumber = false;
        for(int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if(ch >= '0' && ch <= '9') {
                hasNumber = true;
                break;
            }
        }
        if(hasNumber) {
            charsetSize = charsetSize + 10;
        }
        
        // Check for special characters = 32 characters
        boolean hasSpecial = false;
        for(int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if(!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9'))) {
                hasSpecial = true;
                break;
            }
        }
        if(hasSpecial) {
            charsetSize = charsetSize + 32;
        }
        

        if(charsetSize == 0) {
            charsetSize = 26;
        }
        
        
        long combinations = 1;
        try {
            for(int i = 0; i < password.length(); i++) {
                combinations = combinations * charsetSize;
                if(combinations > 1000000000) {
                    break;
                }
            }
           
            double seconds = combinations / 1000000000.0;
            return seconds;
        } catch (ArithmeticException e) {
            return Double.MAX_VALUE; // Handle overflow for very long passwords
        }
    }
}