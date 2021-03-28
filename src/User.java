import java.util.UUID;

public class User {
	private String userID;
	private String firstName;
	private String lastName;
	private List<String> giftCards = new ArrayList<String>();

	public List<String> getGiftCards(){
		return giftCards;
	}
	public User(){

	}
	public User(String inputString){
		String[] tokens = inputString.split(";");
		userID = tokens[0];
		firstName = tokens[1];
		lastName = tokens[2];
	}
	public User(String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
		this.userID = UUID.randomUUID().toString();

	}
    public String getUserId() {
        get(newGC);
    }
	
}
