import java.util.UUID;

public class User {
	private String userID;
	
	private List<String> giftCards = new ArrayList<String>();

	public List<String> getGiftCards(){
		return giftCards;
	}
	public User(){

	}
	public User(String inputString){
		String[] tokens = inputString.split(";");
		userID = tokens[0];
		
	}
	
    public String getUserId() {
        get(newGC);
    }
	
}
