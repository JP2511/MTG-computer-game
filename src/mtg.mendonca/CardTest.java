package mtg.mendonca;

import org.mockito.Mockito;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    /* -----------------------------------------------------------------------------------------------------------------
     * Tests for the formatCardAttributesSize() function
     * -----------------------------------------------------------------------------------------------------------------
     */

    /**
     *    Test for formatCardAttributesSize() with the ArrayList attributes size equal to zero.
     */
    @org.junit.jupiter.api.Test
    void formatCardAttributesSize_empty() {
        ArrayList<String> attributes = new ArrayList();
        Card mockCard = Mockito.mock(Card.class, Mockito.CALLS_REAL_METHODS);
        assertEquals(new ArrayList(), mockCard.formatCardAttributesSize(attributes, 4));
    }


    /**
     *    Test for formatCardAttributesSize() with the ArrayList attributes size equal to one, with length of attribute
     * smaller than nChars.
     */
    @org.junit.jupiter.api.Test
    void formatCardAttributesSize_1allSmaller() {
        ArrayList<String> attributes = new ArrayList();
        attributes.add("A");

        String[] result = new String[1];
        result[0] = "A";

        Card mockCard = Mockito.mock(Card.class, Mockito.CALLS_REAL_METHODS);
        String[] afterApplicationOfFunction = mockCard.formatCardAttributesSize(attributes, 4).toArray(new
                String[0]);

        assertArrayEquals(result, afterApplicationOfFunction);
    }


    /**
     *    Test for formatCardAttributesSize() with the ArrayList attributes size equal to one, with length of attribute
     * equal to nChars.
     */
    @org.junit.jupiter.api.Test
    void formatCardAttributesSize_1allEqual() {
        ArrayList<String> attributes = new ArrayList();
        attributes.add("A");

        String[] result = new String[1];
        result[0] = "A";

        Card mockCard = Mockito.mock(Card.class, Mockito.CALLS_REAL_METHODS);
        String[] afterApplicationOfFunction = mockCard.formatCardAttributesSize(attributes, 1).toArray(new
                String[0]);

        assertArrayEquals(result, afterApplicationOfFunction);
    }


    /**
     *    Test for formatCardAttributesSize() with the ArrayList attributes size equal to one and with length of
     * attribute one times bigger than nChars.
     */
    @org.junit.jupiter.api.Test
    void formatCardAttributesSize_1all1Bigger() {
        ArrayList<String> attributes = new ArrayList();
        attributes.add("Name: name of card");

        String[] result = new String[2];
        result[0] = "Name: name";
        result[1] = " of card";

        Card mockCard = Mockito.mock(Card.class, Mockito.CALLS_REAL_METHODS);
        String[] afterApplicationOfFunction = mockCard.formatCardAttributesSize(attributes, 10).toArray(new
                String[0]);

        assertArrayEquals(result, afterApplicationOfFunction);
    }


    /**
     *    Test for formatCardAttributesSize() with the ArrayList attributes size equal to one and with length of
     * attribute more than one times bigger than nChars.
     */
    @org.junit.jupiter.api.Test
    void formatCardAttributesSize_1allMoreBigger() {
        ArrayList<String> attributes = new ArrayList();
        attributes.add("Name: name of card is the following");

        String[] result = new String[4];
        result[0] = "Name: name";
        result[1] = " of card i";
        result[2] = "s the foll";
        result[3] = "owing";

        Card mockCard = Mockito.mock(Card.class, Mockito.CALLS_REAL_METHODS);
        String[] afterApplicationOfFunction = mockCard.formatCardAttributesSize(attributes, 10).toArray(new
                String[0]);

        assertArrayEquals(result, afterApplicationOfFunction);
    }


    /**
     *    Test for formatCardAttributesSize() with the ArrayList attributes size bigger than one, with length of all
     * elements of attribute smaller than nChars.
     */
    @org.junit.jupiter.api.Test
    void formatCardAttributesSize_2allSmaller() {
        ArrayList<String> attributes = new ArrayList();
        attributes.add("A");
        attributes.add("B");

        String[] result = new String[2];
        result[0] = "A";
        result[1] = "B";

        Card mockCard = Mockito.mock(Card.class, Mockito.CALLS_REAL_METHODS);
        String[] afterApplicationOfFunction = mockCard.formatCardAttributesSize(attributes, 4).toArray(new
                String[0]);

        assertArrayEquals(result, afterApplicationOfFunction);
    }


    /**
     *    Test for formatCardAttributesSize() with the ArrayList attributes size bigger than one, with length of attribute
     * equal to nChars.
     */
    @org.junit.jupiter.api.Test
    void formatCardAttributesSize_2allEqual() {
        ArrayList<String> attributes = new ArrayList();
        attributes.add("A");
        attributes.add("B");

        String[] result = new String[2];
        result[0] = "A";
        result[1] = "B";

        Card mockCard = Mockito.mock(Card.class, Mockito.CALLS_REAL_METHODS);
        String[] afterApplicationOfFunction = mockCard.formatCardAttributesSize(attributes, 1).toArray(new
                String[0]);

        assertArrayEquals(result, afterApplicationOfFunction);
    }


    /**
     *    Test for formatCardAttributesSize() with the ArrayList attributes size bigger than one and with length of
     * attribute one times bigger than nChars.
     */
    @org.junit.jupiter.api.Test
    void formatCardAttributesSize_2all1Bigger() {
        ArrayList<String> attributes = new ArrayList();
        attributes.add("Name: name of card");
        attributes.add("Color: card's color");

        String[] result = new String[4];
        result[0] = "Name: name";
        result[1] = " of card";
        result[2] = "Color: car";
        result[3] = "d's color";

        Card mockCard = Mockito.mock(Card.class, Mockito.CALLS_REAL_METHODS);
        String[] afterApplicationOfFunction = mockCard.formatCardAttributesSize(attributes, 10).toArray(new
                String[0]);

        assertArrayEquals(result, afterApplicationOfFunction);
    }


    /**
     *    Test for formatCardAttributesSize() with the ArrayList attributes size bigger than one and with length of
     * attribute more than one times bigger than nChars.
     */
    @org.junit.jupiter.api.Test
    void formatCardAttributesSize_2allMoreBigger() {
        ArrayList<String> attributes = new ArrayList();
        attributes.add("Name: name of card is the following");
        attributes.add("Color: color of the card");

        String[] result = new String[7];
        result[0] = "Name: name";
        result[1] = " of card i";
        result[2] = "s the foll";
        result[3] = "owing";
        result[4] = "Color: col";
        result[5] = "or of the ";
        result[6] = "card";

        Card mockCard = Mockito.mock(Card.class, Mockito.CALLS_REAL_METHODS);
        String[] afterApplicationOfFunction = mockCard.formatCardAttributesSize(attributes, 10).toArray(new
                String[0]);

        assertArrayEquals(result, afterApplicationOfFunction);
    }


    /**
     *    Test for formatCardAttributesSize() with the ArrayList attributes size bigger than one and with all possible
     * lengths of attributes.
     */
    @org.junit.jupiter.api.Test
    void formatCardAttributesSize_2differentSizes() {
        ArrayList<String> attributes = new ArrayList();
        attributes.add("Name: name of card is the following");
        attributes.add("Type: text");
        attributes.add("Mana: 2");
        attributes.add("Color: card's color");

        String[] result = new String[8];
        result[0] = "Name: name";
        result[1] = " of card i";
        result[2] = "s the foll";
        result[3] = "owing";
        result[4] = "Type: text";
        result[5] = "Mana: 2";
        result[6] = "Color: car";
        result[7] = "d's color";

        Card mockCard = Mockito.mock(Card.class, Mockito.CALLS_REAL_METHODS);
        String[] afterApplicationOfFunction = mockCard.formatCardAttributesSize(attributes, 10).toArray(new
                String[0]);

        assertArrayEquals(result, afterApplicationOfFunction);
    }

    /* -----------------------------------------------------------------------------------------------------------------
     * Tests for the sizeString() function
     * -----------------------------------------------------------------------------------------------------------------
     */

    /**
     * Test for sizeString with string length equal to zero, size equal to 0 and string length equal to size.
     */
    @org.junit.jupiter.api.Test
    void sizeString_0same0() {
        Card mockCard = Mockito.mock(Card.class, Mockito.CALLS_REAL_METHODS);
        assertEquals("", mockCard.sizeString("", 0));
    }


    /**
     * Test for sizeString with string length equal to zero, size bigger than 0 and string length smaller than size.
     */
    @org.junit.jupiter.api.Test
    void sizeString_0under() {
        Card mockCard = Mockito.mock(Card.class, Mockito.CALLS_REAL_METHODS);
        assertEquals("  ", mockCard.sizeString("", 2));
    }


    /**
     * Test for sizeString with string length bigger than zero, size equal to 0 and string length bigger than size.
     */
    @org.junit.jupiter.api.Test
    void sizeString_over0() {
        Card mockCard = Mockito.mock(Card.class, Mockito.CALLS_REAL_METHODS);
        assertEquals("", mockCard.sizeString("aaaa", 0));
    }


    /**
     * Test for sizeString with string length bigger than zero, size bigger than 0 and string length bigger than size.
     */
    @org.junit.jupiter.api.Test
    void sizeString_over() {
        Card mockCard = Mockito.mock(Card.class, Mockito.CALLS_REAL_METHODS);
        assertEquals("aaa", mockCard.sizeString("aaaaa", 3));
    }


    /**
     * Test for sizeString with string length bigger than zero, size bigger than 0 and string length equal to size.
     */
    @org.junit.jupiter.api.Test
    void sizeString_same() {
        Card mockCard = Mockito.mock(Card.class, Mockito.CALLS_REAL_METHODS);
        assertEquals("aaa", mockCard.sizeString("aaa", 3));
    }


    /**
     * Test for sizeString with string length bigger than zero, size bigger than 0 and string length smaller than size.
     */
    @org.junit.jupiter.api.Test
    void sizeString_under() {
        Card mockCard = Mockito.mock(Card.class, Mockito.CALLS_REAL_METHODS);
        assertEquals("a  ", mockCard.sizeString("a", 3));
    }
}