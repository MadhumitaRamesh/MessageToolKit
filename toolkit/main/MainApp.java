
// CLASS: MainApp

package toolkit.main;

import toolkit.crypto.*;
import toolkit.security.*;
import toolkit.utils.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainApp {
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("  Message Encryption & Security Toolkit");
        System.out.println("===========================================\n");
        
        boolean running = true;
        while (running) {
            showMenu();
            System.out.print("Enter your choice: ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); 
                if (choice == 0) {
                    running = false;
                    System.out.println("\nThank you for using the toolkit!");
                } else {
                    handleChoice(choice);
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine();
            }
            
            System.out.println();
        }
        
        scanner.close();
    }
    
    private static void showMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Caesar Cipher Encryption/Decryption");
        System.out.println("2. XOR Cipher Encryption/Decryption");
        System.out.println("3. Password Strength Checker");
        System.out.println("4. Steganography (Hide/Extract Message)");
        System.out.println("5. Compression with Encryption");
        System.out.println("6. QR-Style Visualization");
        System.out.println("0. Exit");
        System.out.println("-----------------");
    }
    
    private static void handleChoice(int option) {
        try {
            switch (option) {
                case 1:
                    caesarMenu();
                    break;
                case 2:
                    xorMenu();
                    break;
                case 3:
                    passwordMenu();
                    break;
                case 4:
                    steganographyMenu();
                    break;
                case 5:
                    compressionMenu();
                    break;
                case 6:
                    qrMenu();
                    break;
                default:
                    System.out.println("Error: Invalid choice. Please select a number between 0 and 6.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
    
    private static void caesarMenu() {
        System.out.println("\n--- Caesar Cipher ---");
        System.out.print("Enter message: ");
        String msg = scanner.nextLine();
        System.out.print("Enter shift value (1-25): ");
        try {
            int shift = scanner.nextInt();
            scanner.nextLine();
            CaesarCipher caesar = new CaesarCipher(shift);
            String encrypted = caesar.encrypt(msg);
            System.out.println("Encrypted: " + encrypted);
            System.out.println("Decrypted: " + caesar.decrypt(encrypted));
        } catch (InputMismatchException e) {
            System.out.println("Error: Shift must be a number.");
            scanner.nextLine();
        }
    }
    
    private static void xorMenu() {
        System.out.println("\n--- XOR Cipher ---");
        System.out.print("Enter message: ");
        String msg = scanner.nextLine();
        System.out.print("Enter key (number): ");
        try {
            int key = scanner.nextInt();
            scanner.nextLine();
            XorCipher xor = new XorCipher(key);
            String encrypted = xor.encrypt(msg);
            System.out.println("Encrypted: " + encrypted);
            System.out.println("Decrypted: " + xor.decrypt(encrypted));
        } catch (InputMismatchException e) {
            System.out.println("Error: Key must be a number.");
            scanner.nextLine();
        }
    }
    
    private static void passwordMenu() {
    System.out.println("\n--- Password Strength Checker ---");
    System.out.print("Enter password: ");
    String password = scanner.nextLine();
    try {
        PasswordSecurity ps = new PasswordSecurity();
        int strength = ps.checkStrength(password);
        double crackTime = ps.estimateCrackTime(password);
        System.out.println("Strength Score: " + strength + "/10");
        if (crackTime == Double.MAX_VALUE) {
            System.out.println("Estimated crack time: Too long to estimate!");
        } else {
            System.out.println("Estimated crack time: " + crackTime + " seconds");  
        }
    } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
    }
}
    private static void steganographyMenu() {
        System.out.println("\n--- Steganography ---");
        System.out.println("1. Hide message");
        System.out.println("2. Extract message");
        System.out.print("Choice: ");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            Steganography steg = new Steganography();
            if (choice == 1) {
                System.out.print("Enter cover text: ");
                String cover = scanner.nextLine();
                System.out.print("Enter secret message: ");
                String secret = scanner.nextLine();
                String result = steg.hideMessage(cover, secret);
                System.out.println("Stego text: " + result);
            } else if (choice == 2) {
                System.out.print("Enter stego text: ");
                String stego = scanner.nextLine();
                String extracted = steg.extractMessage(stego);
                System.out.println("Hidden message: " + extracted);
            } else {
                System.out.println("Error: Invalid choice. Please select 1 or 2.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Choice must be a number.");
            scanner.nextLine();
        }
    }
    
    private static void compressionMenu() {
        System.out.println("\n--- Compression with Encryption ---");
        System.out.print("Enter message: ");
        String msg = scanner.nextLine();
        System.out.print("Enter key: ");
        try {
            int key = scanner.nextInt();
            scanner.nextLine();
            CompressionUtil cu = new CompressionUtil();
            String compressed = cu.compressEncrypt(msg, key);
            System.out.println("Compressed & Encrypted: " + compressed);
            System.out.println("Decompressed & Decrypted: " + cu.decompressDecrypt(compressed, key));
        } catch (InputMismatchException e) {
            System.out.println("Error: Key must be a number.");
            scanner.nextLine();
        }
    }
    
    private static void qrMenu() {
        System.out.println("\n--- QR-Style Visualization ---");
        System.out.print("Enter text to visualize: ");
        String text = scanner.nextLine();
        try {
            QRVisualizer qr = new QRVisualizer();
            qr.visualizeQR(text);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}