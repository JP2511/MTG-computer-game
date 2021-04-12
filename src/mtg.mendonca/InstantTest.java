package mtg.mendonca;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InstantTest {

    /* -----------------------------------------------------------------------------------------------------------------
     * Tests for the sizeString() function
     * -----------------------------------------------------------------------------------------------------------------
     */

    /**
     * Test for instant that has no abilities different from counter target spell that can be cast from the hand.
     */
    @Test
    void getCardsHandEffects_none() {
        Instant instant1 = new Instant("", "", "", "Doesn't have any desirable abilities");
        assertEquals(new ArrayList<String>(), instant1.getCardsHandEffects());
    }


    /**
     * Test for instant that has Cycling.
     */
    @Test
    void getCardsHandEffects_cycling() {
        Instant instant1 = new Instant("", "", "", "Doesn't have any desirable abilities" +
                ", besides Cycling.");

        String[] result = new String[1];
        result[0] = "Cycling";
        assertArrayEquals(result, instant1.getCardsHandEffects().toArray(new String[0]));
    }
}