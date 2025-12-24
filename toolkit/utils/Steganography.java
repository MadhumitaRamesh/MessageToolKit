
// CLASS: Steganography

package toolkit.utils;

public class Steganography {
    
    public String hideMessage(String cover, String secret) {
        String result = "";
        int secretIndex = 0;
        
        for(int i = 0; i < cover.length(); i++) {
            result += cover.charAt(i);
            
            if((i + 1) % 3 == 0 && secretIndex < secret.length()) {
                result += secret.charAt(secretIndex);
                secretIndex++;
            }
        }
        
        while(secretIndex < secret.length()) {
            result += secret.charAt(secretIndex);
            secretIndex++;
        }
        
        return result;
    }
    
    public String extractMessage(String stegoText) {
        String result = "";
        
        for(int i = 3; i < stegoText.length(); i += 4) {
            result += stegoText.charAt(i);
        }
        
        return result;
    }
}