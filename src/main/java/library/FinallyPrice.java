package library;

public class FinallyPrice {

    public static String discount(int months) {
        return calculate(months) + "%";
    }

    public static int getFinallyPrice(int months, int rental) {
        int price = months * rental;
        int dis = price * calculate(months) / 100;
        return price - dis;
    }

    private static int calculate(int months) {
        if (months == 3) {
            return 20;
        }
        if (months >= 4) {
            return 40;
        }
        return 0;
    }

}
