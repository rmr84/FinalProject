
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.io.FileWriter;

import java.nio.file.Files;
import java.nio.file.Paths;


public class GcClass
{
    public final static File ALL_CARDS = new File("dat.ser");

    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {

        Scanner incmd = new Scanner(System.in);
        String letter = "x";
        String inLine = "";
        String[] sArray;
        ArrayList<GiftCard> gcList = new ArrayList<GiftCard>();
        HashGiftCard<User, ArrayList<GiftCard>> map = initGiftCard();     // implement hash dictionary here
        
        try
        {
            
            File gcout = new File("giftcard.txt");                               // create new file
            Scanner in = new Scanner(gcout);
            PrintWriter fout = new PrintWriter(new FileOutputStream(gcout, true));                       // printwriter to write to file    


            if (gcout.exists() && in.hasNext())
            {
                while (in.hasNext())
                {
                    GiftCard gc = new GiftCard();                            // create gift card object

                    inLine = in.nextLine();
                    sArray = inLine.split("\\s");                             // splits into array based on spaces
                    gc.setChronoNum(Integer.parseInt(sArray[0]));
                    gc.setCodeNum(sArray[1]);
                    gc.setActive(sArray[2].equalsIgnoreCase("true"));
                    gc.setBalanceI(Double.parseDouble(sArray[3]));
                    gc.setBalanceC(Double.parseDouble(sArray[4]));
                    gcList.add(gc);

                }

            }

            else
            { //file does not yet exist, need to create file
                try
                {
                    gcout.createNewFile();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
            // prompt user for user ID here
            User user = new User();
            System.out.println("Enter user ID: ");
		    String userID = incmd.nextLine();
            user.setUserID(userID);

	        System.out.println("Hello, User!: " + user.getUserId());


            while (!(letter.equalsIgnoreCase("Y") || letter.equalsIgnoreCase("N")))
            { //check input for alter collection
                System.out.println("Would you like to modify the gift card collection or add a new one? (Y/N):");
                letter = incmd.nextLine();


                if (letter.equalsIgnoreCase("Y"))
                {
                    while (!(letter.equalsIgnoreCase("A") || letter.equalsIgnoreCase("M")))
                    { //check input for add or modify

                        System.out.println("Are you adding a new gift card or modifying an existing gift card? (A)/(M)");
                        letter = incmd.nextLine();
                    }
                    int modNum = -1;
                    if (letter.equalsIgnoreCase("M")) { // have to add writeToFile() method here also 
                        if (gcList.isEmpty()) {
                            System.out.println("Error, no gift cards to add.");
                        }
                        while (modNum <= 0 || modNum > gcList.size()) { //check number within list
                            //create input for number\
                            System.out.println("What gift card would you like to modify?");
                            modNum = incmd.nextInt();
                        }
                        String attrib = "";
                        while (!(attrib.equalsIgnoreCase("B") || attrib.equalsIgnoreCase("A"))) {// check user input for attributes to modify
                            System.out.println("What attributes will you like to modify? (B for Balance, A for Active)\n");
                            incmd.nextLine();
                            attrib = incmd.nextLine();
                            double balTry = -1;
                            if (attrib.equalsIgnoreCase("B")) {     //create variables for parsing
                                while (balTry > gcList.get(modNum-1).getBalanceC() || balTry < 0) {//makes sure updated balance isn't higher than current
                                    System.out.println("What should the balance be updated to?");
                                    balTry = incmd.nextDouble();
                                    }
                                    gcList.get(modNum-1).setBalanceC(balTry);//get a specific location in the array and set the current balance
                            }
                            else {
                                String actInput = "";
                                String active = gcList.get(modNum-1).getActive() ? " active" : " inactive";//ternary to help tell user current active status
                                while (!(actInput.equalsIgnoreCase("Y") || actInput.equalsIgnoreCase("N"))) {    // check user input for active or inactive
                                    System.out.println("Would you like to change the activity status of the gift card? (Y/N) It is currently" + active + ". \n");
                                    actInput = incmd.nextLine();
                                    if (actInput.equalsIgnoreCase("Y")) {
                                        gcList.get(modNum-1).setActive(!(gcList.get(modNum-1).getActive()));     // get the modnum from index and set the modnum to active if the "get" doesn't find an active card object
                                    }
                                }
     
                            }
                        }
                    }
                    else if (letter.equalsIgnoreCase("A"))
                    {
                        System.out.println("Okay, adding new gift card...");

                        GiftCard newGC = new GiftCard();            //create & add new gift card & set number based on list size
                        gcList.add(newGC);


                        newGC.setChronoNum((gcList.size() - 1) + 1);

                        System.out.println("This will be gift card number " + newGC.getChronoNum() + "\n");
                        String code = "";
                        code = codeRand();
                        newGC.setCodeNum(code);

                        for (int i = 0; i < gcList.size() - 1 - 1; i++)
                        {
                            while (gcList.get(newGC.getChronoNum() - 1).getCodeNum().equalsIgnoreCase(gcList.get(i).getCodeNum()))
                                ;
                            { // error is here
                                code = codeRand();
                                newGC.setCodeNum(code);
                            }

                        }
                        //code rand will make a sequence of 8 random digits

                        System.out.println("Randomizing gift card code... \nGift card code will be " + code + "\n");

                        String active = "line";
                        while (!(active.equalsIgnoreCase("Y") || active.equalsIgnoreCase("N")))
                        {//get active status from user
                            System.out.println("Is this gift card currently active? (Y/N)");
                            active = incmd.nextLine();
                        }
                        if (active.equalsIgnoreCase("Y")) newGC.setActive(true);
                        else newGC.setActive(false);
                        System.out.println("Active/Inactive status set!\n");
                        String balInput = "";        //create variables for parsing
                        double balTry = 0;
                        double currentBal = 0;
                        boolean flag = true;
                        while (flag)
                        { 
                            System.out.println("What is the initial balance of this card? Ex 100.00 ");
                            balInput = incmd.nextLine();
                            try
                            {    //try catch for parsing to ensure a double is input
                                balTry = Double.parseDouble(balInput);
                                flag = false;
                            }
                            catch (NumberFormatException e)
                            {
                                e.printStackTrace();
                            }
                        }
                        newGC.setBalanceI(balTry);
                        System.out.println("Inital balance set!\n");
                        balInput = "";

                        while (!(balInput.equalsIgnoreCase("Y") || balInput.equalsIgnoreCase("N")))
                        {
                            System.out.println("Is the current balance of this card different than the initial? (Y/N)");
                            
                            balInput = incmd.nextLine();
                        }
                        if (balInput.equalsIgnoreCase("Y"))
                        {
                            balInput = "";
                            flag = true;
                            while (flag || balTry > newGC.getBalanceI())
                            {         //make sure current balance will not be greater than initial
                                System.out.println("Please enter current balance (note: current balance must be less than initial): Ex. 50.25");
                                balInput = incmd.nextLine();
                                try
                                {    //try catch for parsing to ensure a double is input
                                    balTry = Double.parseDouble(balInput);
                                    flag = false;
                                }
                                catch (NumberFormatException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                            newGC.setBalanceC(balTry);
                        }
                        else if (balInput.equalsIgnoreCase("N"))
                        {               //balance is same as initial, hasn't been used
                            newGC.setBalanceC(newGC.getBalanceI());
                        }
                        System.out.println("Current balance set!\n");
                        System.out.println("Card added to collection successfully!");
                    }


                }
                else
                {//they are finished adding/modifying
                    System.out.println(print(gcList));
                    try
                    {
                        FileWriter myWriter = new FileWriter("giftcard.txt");
                        myWriter.write(print(gcList));
                        map.put(user, gcList);
                        write(map);
                        myWriter.close();
                        System.out.println("Successfully wrote to the file.");
                    }
                    catch (IOException e)
                    {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }

                }
            }
            fout.close();
            in.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }


        incmd.close();
    }


    private static String print(List<GiftCard> gcList)
    {     // method to print the data
        String ret = "";
        for (int i = 0; i < gcList.size(); i++)
        {
            ret = ret + gcList.get(i);
        }
        return ret;

    }

    private static String codeRand()
    {                        //creates a random 8 digit code of random combination of letters and numbers
        String code = "";
        int itemp = 0;
        char rChar;
        for (int i = 0; i < 8; i++)
        {
            itemp = rand(1, 0);                              //50/50 chance it does a letter or number
            if (itemp == 0)
            {
                itemp = rand(90, 65);
                rChar = (char) itemp;
                code = code + rChar;
            }
            else
            {
                itemp = rand(9, 0);
                code = code + itemp;
            }
        }
        return code;
    }

    public static int rand(int max, int min)
    {

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;        //this method from google works great :)

    }

    public static void write(HashGiftCard<User, ArrayList<GiftCard>> map)
    {
        File file = new File("dat.ser");

        try
        {
            FileOutputStream fileoutputstream = new FileOutputStream(file);
            ObjectOutputStream objectoutputstream = new ObjectOutputStream(fileoutputstream);
            objectoutputstream.writeObject(map);
            objectoutputstream.close();
            fileoutputstream.close();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static HashGiftCard<User, ArrayList<GiftCard>> initGiftCard()
    {

        if (!(ALL_CARDS.exists()))
        {
            try
            {
                ALL_CARDS.createNewFile();
            }
            catch (IOException e)
            {

                e.printStackTrace();
            }
        }

        HashGiftCard<User, ArrayList<GiftCard>> map = new HashGiftCard<>();
        try
        {
            if (Files.size(Paths.get(ALL_CARDS.getAbsolutePath()))== 0) return map;
            FileInputStream fis = new FileInputStream(ALL_CARDS);
            ObjectInputStream ois = new ObjectInputStream(fis);
            map = (HashGiftCard<User, ArrayList<GiftCard>>) ois.readObject();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return map;
    }
} 
