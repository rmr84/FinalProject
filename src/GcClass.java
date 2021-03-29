// 1. stage all changes
// 2. commit
// 3. pull 
// 4. push 

import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.util.Random;
import java.io.FileWriter;

//TO-DO List:
//Iterator (DONE)
//Figure out second data structure (DONE)
//File output working --DONE
//modifying a current value presently does not work
//randomization method needs to be altered to ensure no duplicates (DONE)
 
public class GcClass {
    public static void main(String[] args) {
     
    Scanner incmd = new Scanner(System.in);
    String letter = "x";
    String inLine = "";
    String[] sArray;
    int index = 0;
    List<GiftCard> gcList = new ArrayList<GiftCard>();
    HashGiftCard<String, User> giftCard = new HashGiftCard<String, User>();     // implement hash dictionary here

    try {    
        File gcout = new File("giftcard.txt");                               // create new file
        Scanner in = new Scanner(gcout);
        PrintWriter fout = new PrintWriter(gcout);                           // printwriter to write to file    
         
        
        //String userName = UserManager.promptForUser()

        if (gcout.exists() && in.hasNext()) {
            while (in.hasNext()) {
                    GiftCard gc = new GiftCard();                            // create gift card object
                    inLine = in.nextLine();
                    sArray=inLine.split("\\s");                             // splits into array based on spaces
                        gc.setChrono(Integer.parseInt(sArray[0]));
                        gc.setCodeNum(sArray[1]);
                        gc.setActive(sArray[2].equalsIgnoreCase("true"));
                        gc.setInitBal(Integer.parseInt(sArray[3]));
                        gc.setCurrBal(Double.parseDouble(sArray[4]));
                        gcList.add(gc);
                        //UserManager.addCardToUser(userName, gc.getCodeNum);      
                       
            }
             
        }

        else { //file does not yet exist, need to create file
            try {gcout.createNewFile();} catch (IOException e){}
 
        }
        // prompt user for 
        while (!(letter.equalsIgnoreCase("Y") || letter.equalsIgnoreCase("N"))) { //check input for alter collection
            System.out.println("Would you like to modify the gift card collection or add a new one? (Y/N):");
            letter = incmd.nextLine();
            
         
            if (letter.equalsIgnoreCase("Y")) {
                while (!(letter.equalsIgnoreCase("A") || letter.equalsIgnoreCase("M"))) { //check input for add or modify
 
                System.out.println("Are you adding a new gift card or modifying an existing gift card? (A)/(M)");
                letter = incmd.nextLine();
                 }
                if (letter.equalsIgnoreCase("M")) {
                    int modNum = -1;
                    while (!(modNum<=0 || modNum >gcList.size())) { //check number within list
                        //create input for number
                        String balInput = "";
                        int balTry = 0;
                        boolean flag = true;
                            while (flag){
                                System.out.println("What number would you like to modify?");
                                balInput = incmd.nextLine();
                                try{    //try catch for parsing to ensure a double is input
                                    balTry = Integer.parseInt(balInput);
                                    flag = false;
                                }
                                catch (NumberFormatException e){
                                }
                            }
                        modNum = balTry;
                    }
 
                    String attrib = "";
                    while (!(attrib.equalsIgnoreCase("B") || attrib.equalsIgnoreCase("A"))) {// check user input for attributes to modify
                        System.out.println("What attributes will you like to modify? (B for Balance, A for Active) \n");
                        if (attrib.equalsIgnoreCase("B")) {
                            String balInput = "";        //create variables for parsing
                            double balTry = 0;
                            boolean flag = true;
                            while (flag || balTry > gcList.get(modNum).getCurrBal()){//makes sure updated balance isn't higher than current
                                System.out.println("What should the balance be updated to?");
                                balInput = incmd.nextLine();
                                try{    //try catch for parsing to ensure a double is input
                                    balTry = Double.parseDouble(balInput);
                                    flag = false;
                                }
                                catch (NumberFormatException e){
                                }
                            }
                            gcList.get(modNum).setCurrBal(balTry);       //get a specific location in the array and set the current balance
                        }
                        else {
                            String actInput = "";
                            String active = gcList.get(modNum).getActive() ? "active" : "inactive";//ternary to help tell user current active status
                            while (!(actInput.equalsIgnoreCase("Y") || actInput.equalsIgnoreCase("N"))) {    // check user input for active or inactive
                                System.out.println("Would you like to change the activity status of the gift card? (Y/N) It is currently" + active + ". \n");
                                if (actInput.equalsIgnoreCase("Y")) {
                                    gcList.get(modNum).setActive(!(gcList.get(modNum).getActive()));     // get the modnum from index and set the modnum to active if the "get" doesn't find an active card object
                                }
                            }
 
                        }
                    }
                     
                }
                else if (letter.equalsIgnoreCase("A")){
                    System.out.println("Okay, adding new gift card...");
 
                    GiftCard newGC = new GiftCard();            //create & add new gift card & set number based on list size
                    gcList.add(newGC);
                    //get(newGC.put());
                   // HashGiftCard.put(User, 1);
                    newGC.setChrono((gcList.size()-1) +1);
 
                    System.out.println("This will be gift card number "  + newGC.getChrono() + "\n");
                    String code = "";
                    code = codeRand();
                	newGC.setCodeNum(code);
                  
                    for (int i = 0; i < gcList.size() - 1 - 1; i++) {
                    	while (gcList.get(newGC.getChrono()).getCodeNum().equalsIgnoreCase(gcList.get(i).getCodeNum())); { // error is here
                    		code = codeRand();
                            newGC.setCodeNum(code);
                    	}
                    
                    }
                    //code rand will make a sequence of 8 random digits
 
                    System.out.println("Randomizing gift card code... \nGift card code will be " + code + "\n");
                    
                    String active = "poop";
                    while (!(active.equalsIgnoreCase("Y") || active.equalsIgnoreCase("N"))) {//get active status from user
                        System.out.println("Is this gift card currently active? (Y/N)");
                        active = incmd.nextLine();
                    }
                    if (active.equalsIgnoreCase("Y")) newGC.setActive(true);
                    else newGC.setActive(false);
                    System.out.println("Active/Inactive status set!\n");
                    String balInput = "";        //create variables for parsing
                    double balTry = 0;
                    boolean flag = true;
                    while (flag){
                        System.out.println("What is the initial balance of this card? Ex 100.00 ");
                        balInput = incmd.nextLine();
                        try{    //try catch for parsing to ensure a double is input
                            balTry = Double.parseDouble(balInput);
                            flag = false;
                            }
                        catch (NumberFormatException e){
                    }
                }
                    newGC.setInitBal(balTry);
                    System.out.println("Inital balance set!\n");
                    balInput = "";
 
                    while (!(balInput.equalsIgnoreCase("Y") || balInput.equalsIgnoreCase("N"))) {
                        System.out.println("Is the current balance of this card different than the initial? (Y/N)");
                        balInput = incmd.nextLine();
                    }
                    if (balInput.equalsIgnoreCase("Y")) {
                        balInput="";
                        flag = true;
                        while (flag || balTry > newGC.getInitBal()){         //make sure current balance will not be greater than initial
                            System.out.println("What is the current balance of this card? Ex. 50.25");
                            balInput=incmd.nextLine();
                            try{    //try catch for parsing to ensure a double is input
                                balTry = Double.parseDouble(balInput);
                                flag = false;
                            }
                        catch (NumberFormatException e){}
                        }
                        newGC.setCurrBal(balTry);
                    }
                    else if (balInput.equalsIgnoreCase("N")){               //balance is same as initial, hasn't been used
                        newGC.setCurrBal(newGC.getInitBal());
                    }
                    System.out.println("Current balance set!\n");
                    System.out.println("Card added to collection successfully!");
                 }
 
            
        } else {//they are finished adding/modifying
            System.out.println(print(gcList));
             try {
                FileWriter myWriter = new FileWriter("giftcard.txt");
                myWriter.write(print(gcList));
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
              } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }
 
            }
            }
            fout.close();
            in.close();
    } catch (FileNotFoundException e) {
        System.out.println(e.getLocalizedMessage());
    }
    
 
incmd.close();
}
    private static String print(List<GiftCard> gcList) {     // method to print the data
        String ret="";
        for (int i = 0; i < gcList.size(); i++) {
            ret = ret + gcList.get(i);  
        }
        return ret;
}
    private static String codeRand() {	                    //creates a random 8 digit code of random combination of letters and numbers
        
    	
    	String code = "";
        int itemp =0;
        char rChar; 
        for (int i = 0; i<8;i++) {
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
        return code;
    }
    public static int rand(int max, int min) {
    	
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;        //this method from google works great :)

    }
} 