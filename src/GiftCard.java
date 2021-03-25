import java.io.Serializable;
 
public class GiftCard implements Serializable{
    private int chronoNum; //keeps track of what number gift card this is
    private String codeNum; //unique code for the gift card
    private boolean isActive;
    //variable for expiration date
    private double balanceI; //gift card's initial balance
    private double balanceC; //gift card's current balance
    
    public GiftCard() {
        this(0, "ABC123XY", true, 100.00, 100.00);
    }
    public GiftCard(int chrono, String code, boolean active, double initBal, double currBal) {
        setChrono(chrono);
        setCodeNum(code);
        setActive(active);
        //DO EXPIRATION DATE
        setInitBal(initBal);
        setCurrBal(currBal);
    }
    public int getChrono() {
        return chronoNum;
    }
    public void setChrono(int chrono) {
        chronoNum = chrono;
    }
    public String getCodeNum() {
        return codeNum;
    }
    public void setCodeNum(String code) {
        codeNum = code;
    }
    public boolean getActive() {
        return isActive;
    } 
    public void setActive(boolean active) {
        isActive = active;
    }
    public double getInitBal() {
        return balanceI;
    }
    public void setInitBal(double balI) {
        balanceI = balI;
    }
    public double getCurrBal() {
        return balanceC;
    }
    public void setCurrBal(double balC) {
        balanceC = balC;
    }
    public String toString (){
        return chronoNum + " " + codeNum + " " + isActive +
        " " + balanceI + " " + balanceC + "\n";
    }
}
