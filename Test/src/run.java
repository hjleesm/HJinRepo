
public class run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Case 1 with three \"for\" statements");
		int height = 10;
		
		for(int i = 0; i < height; i++){
			for(int j = 0; j < i; j++)
				System.out.print(" ");
			for(int k = 0; k < height - i; k++)
				System.out.print("*");
			System.out.println("\\n");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println();
		System.out.println("Case 2 with \"if\" statement");
		
		for(int i = 0; i < height; i++){
			for(int j = 0; j < height; j++){
				if(j < i)
					System.out.print(" ");
				else
					System.out.print("*");
			}
			System.out.println("\\n");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
