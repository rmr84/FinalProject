import java.io.Serializable;
import java.util.Objects;
 
public class GiftCard implements Serializable {
    private int chronoNum; //keeps track of what number gift card this is
    private String codeNum; //unique code for the gift card
    private boolean isActive;
    
    private double balanceI; //gift card's initial balance
    private double balanceC; //gift card's current balance
    
    public GiftCard() {
        this(0, "ABC123XY", true, 100.00, 100.00);
    }

    public GiftCard(int chronoNum, String codeNum, boolean isActive, double balanceI, double balanceC) {
        this.chronoNum = chronoNum;
        this.codeNum = codeNum;
        this.isActive = isActive;
        this.balanceI = balanceI;
        this.balanceC = balanceC;
    }
      
    public int getChronoNum() {
        return chronoNum;
    }
    
    public void setChronoNum(int chronoNum) {
        this.chronoNum = chronoNum;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public double getBalanceI() {
        return balanceI;
    }
    
    public void setBalanceI(double balanceI) {
        this.balanceI = balanceI;
    }
   
    public double getBalanceC() {
        return balanceC;
    }
    
    public void setBalanceC(double balanceC) {
        this.balanceC = balanceC;
    }

    
    public void setCodeNum(String codeNum) {
        this.codeNum = codeNum;
    }

    public String getCodeNum() {
        return codeNum;
    }
    
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getActive() {
        return isActive;
    }
    
    @Override
    public String toString() {
        return chronoNum + " " + codeNum + " "  + isActive + " " + balanceI + " " + balanceC + "\n";
               
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(balanceC, balanceI, chronoNum, codeNum, isActive);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof GiftCard))
            return false;
        GiftCard other = (GiftCard) obj;
        return Double.doubleToLongBits(balanceC) == Double.doubleToLongBits(other.balanceC)
                && Double.doubleToLongBits(balanceI) == Double.doubleToLongBits(other.balanceI)
                && chronoNum == other.chronoNum && Objects.equals(codeNum, other.codeNum) && isActive == other.isActive;
    }
}
