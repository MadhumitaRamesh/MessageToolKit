
// CLASS: CompressionUtil

package toolkit.utils;

public class CompressionUtil {
    
    public String compressEncrypt(String msg, int key) {
        String compressed = compress(msg);
        
        String encrypted = "";
        for(int i = 0; i < compressed.length(); i++) {
            char ch = compressed.charAt(i);
            ch = (char)(ch ^ key);
            encrypted += ch;
        }
        
        return encrypted;
    }
    
    public String decompressDecrypt(String cipher, int key) {
        String decrypted = "";
        for(int i = 0; i < cipher.length(); i++) {
            char ch = cipher.charAt(i);
            ch = (char)(ch ^ key);
            decrypted += ch;
        }
        
        return decompress(decrypted);
    }
    
    private String compress(String msg) {
        if(msg.length() == 0) return "";
        
        String result = "";
        int count = 1;
        char current = msg.charAt(0);
        
        for(int i = 1; i < msg.length(); i++) {
            if(msg.charAt(i) == current) {
                count++;
            } else {
                result += current;
                if(count > 1) {
                    result += count;
                }
                current = msg.charAt(i);
                count = 1;
            }
        }
        
        result += current;
        if(count > 1) {
            result += count;
        }
        
        return result;
    }
    
    private String decompress(String compressed) {
        String result = "";
        
        for(int i = 0; i < compressed.length(); i++) {
            char ch = compressed.charAt(i);
            
            if(Character.isDigit(ch)) {
                int count = Character.getNumericValue(ch);
                char prev = result.charAt(result.length() - 1);
                for(int j = 1; j < count; j++) {
                    result += prev;
                }
            } else {
                result += ch;
            }
        }
        
        return result;
    }
}