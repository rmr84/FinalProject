
import java.io.Serializable;
import java.util.Scanner;
public class User implements Serializable {
	static Scanner in = new Scanner(System.in);
	public static String userID;      // string for the user id 

	private List<String> giftCards = new ArrayList<String>(); 

	public List<String> getGiftCards() {
		return giftCards;
	}

	
	public User(String inputString){
		String[] tokens = inputString.split(";");
		userID = tokens[0];
	}

	public User()
	{};
	
    public String getUserId() {
		return userID;
    }


    /**
     * @param giftCards the giftCards to set
     */
    public void setGiftCards(List<String> giftCards) {
        this.giftCards = giftCards;
    }

	public void setUserID(String s)
	{
		this.userID = s;
	}

}
