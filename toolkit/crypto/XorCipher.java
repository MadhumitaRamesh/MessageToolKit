
// CLASS: XorCipher

package toolkit.crypto;

public class XorCipher implements Encryptor {
    private int key;

    public XorCipher(int key) {
        if (key <= 0) {
            throw new IllegalArgumentException("Key must be a positive integer.");
        }
        this.key = key;
    }


    public String encrypt(String msg) throws IllegalArgumentException {
        if (msg == null || msg.isEmpty()) {
            throw new IllegalArgumentException("Message cannot be null or empty.");
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            char ch = msg.charAt(i);
            ch = (char) (ch ^ key);
            result.append(ch);
        }
        return result.toString();
    }

    public String decrypt(String cipher) throws IllegalArgumentException {
        if (cipher == null || cipher.isEmpty()) {
            throw new IllegalArgumentException("Ciphertext cannot be null or empty.");
        }
        return encrypt(cipher); 
    }
}