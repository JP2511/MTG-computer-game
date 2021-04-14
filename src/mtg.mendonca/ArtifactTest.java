package mtg.mendonca;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArtifactTest {

    /* -----------------------------------------------------------------------------------------------------------------
     * Tests for the getCardsHandEffects() function
     * -----------------------------------------------------------------------------------------------------------------
     */

    /**
     * Test for an artifact that has no abilities different from counter target spell that can be cast from the hand.
     */
    @Test
    void getCardsHandEffects_none() {
        Artifact artifact1 = new Artifact("", "", "", "Doesn't have any desirable abilities");
        assertEquals(new ArrayList<String>(), artifact1.getCardsHandEffects());
    }


    /**
     * Test for an artifact that has Cycling.
     */
    @Test
    void getCardsHandEffects_cycling() {
        Artifact artifact1 = new Artifact("", "", "", "Doesn't have any desirable abilities" +
                ", besides Cycling.");

        String[] result = new String[1];
        result[0] = "Cycling";
        assertArrayEquals(result, artifact1.getCardsHandEffects().toArray(new String[0]));
    }
}