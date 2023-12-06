package library.logic;

import java.util.Random;

public class BookCondition {

    private static final int RANGE_START = 1;
    private static final int RANGE_FINISH = 100;
    private static final int RANGE_MIN = 50;
    private static final int RANGE_AVER = 80;
    private static final int FORFEIT_ZERO = 0;
    private static final int FORFEIT_MIN = 25;
    private static final int FORFEIT_MAX = 50;

    public int forfeitCount() {
        Random random = new Random();
        int condition = random.nextInt(RANGE_START, RANGE_FINISH);
        if (condition >= RANGE_START && condition <= RANGE_MIN) {
            return FORFEIT_ZERO;
        }
        if (condition > RANGE_MIN && condition <= RANGE_AVER) {
            return FORFEIT_MIN;
        }
        return FORFEIT_MAX;
    }

}
