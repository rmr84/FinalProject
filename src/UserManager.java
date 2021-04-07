import java.util.Scanner;

public class UserManager {
    private static HashGiftCard<String, User> users = new HashGiftCard<String, User>();

    public static void initialize() {
        // code to open "Users.txt" file 
        Scanner scanner = new Scanner("Users.txt");
        while (scanner.hasNext()){
            String s = scanner.nextLine();
            User user = new User(s);
            users.put(user.getUserId(), user);
            UserManager.addCardToUser(userID, GC.getCodeNum);
        }
    }

    public static User enterUserID(){
        String s = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter User ID: ");
        s = scanner.nextLine();
        User u = users.get(s);
        return u;
    }

    public static AddCardForUser(String userId, String cardNumber){
        users.get(userId).getGiftCards().add(cardNumber);
    }


    scanner.close();

}
