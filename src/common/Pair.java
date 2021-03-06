package common;

public class Pair extends Bet {

    public Pair() {
        this(0);
    }

    public Pair(double amount) {
        super("Pair", amount);
    }

    public double winning(int number) {
        if(number != 0 && number%2 == 0){
            return (amount * 36) / 18;
        }
        return 0;
    }

    public String toString() {
        return code + " / " + amount;
    }

    public String getCode() {
        return code.toUpperCase() + " " + amount;
    }

    public Pair clone() {
        return (Pair) super.clone();
    }
}
