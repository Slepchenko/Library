package library;

public class CorrectAmount {

    public int checkAmount(int amount, int rentalPrice) {
        return Integer.compare(amount, rentalPrice);
    }

}
