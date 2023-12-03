package library;

import java.util.Random;

public class Librarian {

//    public int forfeitCount(int forfeitCount) {
//        Random random = new Random();
//        int condition = random.nextInt(0, 100);
//        if (condition >= 0 && condition <= 50) {
//            forfeitCount = 0;
//            return 0;
//        }
//        if (condition > 50 && condition < 81) {
//            forfeitCount = 25;
//            return 25;
//        }
//        forfeitCount = 50;
//        return 50;
//    }

    public String forfeitMessage(int forfeitCount) {
        return switch (forfeitCount) {
            case 0 -> "Книга в идеальном состоянии, всё хорошо, можете сдавать!";
            case 25 -> "Книга имеет незначительные повреждения, за которые нужно доплатить. С вас:";
            default -> "Книга имеет серьезные повреждения которые Вам обойдутся в:";
        };
    }

}
