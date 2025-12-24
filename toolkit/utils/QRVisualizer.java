
// CLASS QRVisualizer
	
package toolkit.utils;

public class QRVisualizer {
    
    public void visualizeQR(String text) {
        System.out.println("\n--- QR-Style Visualization ---");
        
        int size = 10;
        char[][] grid = new char[size][size];
        
        int textIndex = 0;
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(textIndex >= text.length()) {
                    textIndex = 0;
                }
                
                char ch = text.charAt(textIndex);
                if(ch % 2 == 0) {
                    grid[i][j] = '█';
                } else {
                    grid[i][j] = ' ';
                }
                
                textIndex++;
            }
        }
        
        System.out.println("┌────────────────────┐");
        for(int i = 0; i < size; i++) {
            System.out.print("│");
            for(int j = 0; j < size; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("│");
        }
        System.out.println("└────────────────────┘");
    }
}