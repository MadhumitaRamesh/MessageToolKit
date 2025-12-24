
// CLASS: CaesarCipher


package toolkit.crypto;

public class CaesarCipher implements Encryptor {
    private int shift;

    public CaesarCipher(int shift) {
        if (shift < 0) {
            throw new IllegalArgumentException("Shift cannot be negative.");
        }
        this.shift = shift % 26;
    }

   
    public String encrypt(String msg) throws IllegalArgumentException {
        if (msg == null || msg.isEmpty()) {
            throw new IllegalArgumentException("Message cannot be null or empty.");
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            char ch = msg.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                int position = ch - 'a';
                position = (position + shift) % 26;
                ch = (char) (position + 'a');
            } else if (ch >= 'A' && ch <= 'Z') {
                int position = ch - 'A';
                position = (position + shift) % 26;
                ch = (char) (position + 'A');
            }
            result.append(ch);
        }
        return result.toString();
    }

    
    public String decrypt(String cipher) throws IllegalArgumentException {
        if (cipher == null || cipher.isEmpty()) {
            throw new IllegalArgumentException("Ciphertext cannot be null or empty.");
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < cipher.length(); i++) {
            char ch = cipher.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                int position = ch - 'a';
                position = (position - shift + 26) % 26;
                ch = (char) (position + 'a');
            } else if (ch >= 'A' && ch <= 'Z') {
                int position = ch - 'A';
                position = (position - shift + 26) % 26;
                ch = (char) (position + 'A');
            }
            result.append(ch);
        }
        return result.toString();
    }
}