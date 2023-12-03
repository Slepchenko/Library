package library;

import java.util.Random;

public class BookCondition {

    public int forfeitCount() {
        Random random = new Random();
        int condition = random.nextInt(0, 100);
        if (condition >= 0 && condition <= 49) {
            return 0;
        }
        if (condition >= 50 && condition <= 80) {
            return 25;
        }
        return 50;
    }

}
