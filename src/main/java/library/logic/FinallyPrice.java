package library.logic;

public class FinallyPrice {

    private static final int PERCENT_100 = 100;
    private static final int DAYS_MIN = 4;
    private static final int DAYS_MAX = 10;
    private static final int FINALLY_PRICE_MIN = 20;
    private static final int FINALLY_PRICE_MAX = 40;
    private static final int NO_PRICE = 0;
    private static final int STUDENT_DISCOUNT = 2;

    public static String discount(int days) {
        return calculate(days) + "%";
    }

    public static int getFinallyPrice(int days, int deposit, int rental, boolean student) {
        if (calculate(days) == NO_PRICE && !student) {
            return deposit + getDiscountPrice(days, rental, false);
        }
        if (student) {
            return deposit + getDiscountPrice(days, rental, true);
        }
        return deposit + getDiscountPrice(days, rental, false);
    }

    public static int getDiscountPrice(int days, int rental, boolean student) {
        rental *= days;
        if (calculate(days) == NO_PRICE && !student) {
            return rental;
        }
        int discount = rental * calculate(days) / PERCENT_100;
        rental -= discount;
        if (student) {
            return (rental / STUDENT_DISCOUNT);
        }
        return rental;
    }

    private static int calculate(int days) {
        if (days >= DAYS_MIN && days <= DAYS_MAX) {
            return FINALLY_PRICE_MIN;
        }
        if (days > DAYS_MAX) {
            return FINALLY_PRICE_MAX;
        }
        return NO_PRICE;
    }

}
