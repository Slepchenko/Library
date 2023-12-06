package library.logic;

public class FinallyPrice {

    private static final int PERCENT_100 = 100;
    private static final int MONTHS_MIN = 3;
    private static final int MONTHS_MAX = 4;
    private static final int FINALLY_PRICE_MIN = 20;
    private static final int FINALLY_PRICE_MAX = 40;
    private static final int NO_PRICE = 0;

    public static String discount(int months) {
        return calculate(months) + "%";
    }

    public static int getFinallyPrice(int months, int rental) {
        int price = months * rental;
        int dis = price * calculate(months) / PERCENT_100;
        return price - dis;
    }

    private static int calculate(int months) {
        if (months == MONTHS_MIN) {
            return FINALLY_PRICE_MIN;
        }
        if (months >= MONTHS_MAX) {
            return FINALLY_PRICE_MAX;
        }
        return NO_PRICE;
    }

}
