package library.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinallyPriceTest {

    @Test
    public void when1day100Rental200DepositThen300() {
        int result = FinallyPrice.getFinallyPrice(1, 200, 100, false);
        assertEquals(result, 300);
    }

    @Test
    public void when5day100Rental200DepositThen600() {
        int result = FinallyPrice.getFinallyPrice(5, 200, 100, false);
        assertEquals(result, 600);
    }

    @Test
    public void when12day100Rental200DepositThen920() {
        int result = FinallyPrice.getFinallyPrice(12, 200, 100, false);
        assertEquals(result, 920);
    }

    @Test
    public void when1dayStudent100Rental200DepositThen250() {
        int result = FinallyPrice.getFinallyPrice(1, 200, 100, true);
        assertEquals(result, 250);
    }

    @Test
    public void when5dayStudent100Rental200DepositThen400() {
        int result = FinallyPrice.getFinallyPrice(5, 200, 100, true);
        assertEquals(result, 400);
    }

    @Test
    public void when12dayStudent100Rental200DepositThen560() {
        int result = FinallyPrice.getFinallyPrice(12, 200, 100, true);
        assertEquals(result, 560);
    }

}