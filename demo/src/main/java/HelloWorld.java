import java.util.Scanner;

import game.Game;

public class HelloWorld {

    public static void main(String[] args) {
		new Game().Run();
		int[] arr;
		Scanner scanner = new Scanner(System.in);
		int n =  scanner.nextInt();
		arr = new int[n*n];
		
		
		
		if(3<= n && n<= 8){
			for(int i =0; i< n; i++)
				for(int k = 0; k< n; k++){
					arr[i*n+k] = scanner.nextInt(); 
				}
			for(int i =0; i< n; i++){
				for(int k = 0; k< n; k++){
					System.out.print(arr[i*n+k] + " "); 
				}
				System.out.print("\n");
			}
				
		}
		scanner.close();
	}
}