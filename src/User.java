
import java.util.Random;
import java.util.Scanner;
public class User {
	Scanner in = new Scanner(System.in);
	private String userID;      // string for the user id 

	private List<String> giftCards = new ArrayList<String>();

	public List<String> getGiftCards() {
		return giftCards;

	}

	
	public User(String inputString){
		String[] tokens = inputString.split(";");
		userID = tokens[0];
		
		
	}
	
    public String getUserId() {

        System.out.println("Enter user ID: ");
		in.nextLine();
		return userID;
    }

    public boolean validateID(String userId) {
        if (userId == getUserId()) {
            return true;
        }
        else {
            return false;
        }
    }

	public static int rand(int max, int min) {

    	String code = "";
        int itemp = 0;
        char rChar; 
        for (int i = 0; i < 3; i++) {
            itemp = rand(1,0);                              //50/50 chance it does a letter or number
            if (itemp==0) {
                itemp = rand(90,65);
                rChar = (char)itemp;
                code = code + rChar;
            }
            else {
                itemp = rand(9,0);
                code = code + itemp;
            }
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;        //this method from google works great :)

    }
    
	
}
