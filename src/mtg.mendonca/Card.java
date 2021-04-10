package mtg.mendonca;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public abstract class Card {
    /**
     * Class that has all the general card attributes and functionalities.
     */
    
    private String name;
    private String color;
    private String manaCost;
    private String type;
    private String effect;
    private boolean tapped;


    public String getName() {
        /**
         * Returns the name of the card.
         *
         * Parameters
         * ----------
         * None.
         *
         * Returns
         * -------
         * string
         *       Name of the card.
         */

        return this.name;
    }


    public String getColor() {
        /**
         * Returns the color of the card.
         *
         * Parameters
         * ----------
         * None.
         *
         * Returns
         * -------
         * string
         *       Color of the card.
         */

        return this.color;
    }


    public String getManaCost() {
        /**
         * Returns the mana cost of the card.
         *
         * Parameters
         * ----------
         * None.
         *
         * Returns
         * -------
         * string
         *       Mana cost of the card.
         */

        return this.manaCost;
    }


    public String getType() {
        /**
         * Returns the type of the card.
         *
         * Parameters
         * ----------
         * None.
         *
         * Returns
         * -------
         * string
         *       Type of the card.
         */

        return this.type;
    }


    public String getEffect() {
        /**
         * Returns the abilities of the card.
         *
         * Parameters
         * ----------
         * None.
         *
         * Returns
         * -------
         * string
         *       Abilities of the card.
         */

        return this.effect;
    }


    public boolean isTapped() {
        /**
         * Determines if a card is tapped or not.
         *
         * Parameters
         * ----------
         * None.
         *
         * Returns
         * -------
         * boolean
         *       True if the card is tapped and false if it's not.
         */

        return this.tapped;
    }


    public Card(String name, String color, String manaCost, String type, String effect) {
        /**
         * Class constructor.
         *
         * Parameters
         * ----------
         * name : string
         *       Name of the card that is being created.
         * color : string
         *       Color of the card that is being created.
         * manaCost : string
         *       Mana cost of the card that is being created.
         * type : string
         *       Type of the card that is being created.
         * effects : string
         *       Abilities of the card that is being created.
         *
         * Returns
         * -------
         * None.
         */

        this.name = name;
        this.color = color;
        this.manaCost = manaCost;
        this.type = type;
        this.effect = effect;
    }


    public void tap() {
        /**
         * Taps the card. The class variable tapped is converted to true.
         *
         * Parameters
         * ----------
         * None.
         *
         * Returns
         * -------
         * None.
         */

        this.tapped = true;
    }


    public void untap() {
        /**
         * Untaps the card. The class variable tapped is converted to false.
         *
         * Parameters
         * ----------
         * None.
         *
         * Returns
         * -------
         * None.
         */

        this.tapped = false;
    }


    public ArrayList<String> getCardAttributes() {
        /**
         * Creates a list with all the cards attributes.
         *
         * Parameters
         * ----------
         * None.
         *
         * Returns
         * -------
         * ArrayList<String>
         *       Attributes of the cards (name, color, mana cost, type and abilities).
         */

        ArrayList<String> attributes = new ArrayList();

        attributes.add("Name: " + this.name);
        attributes.add("Color: " + this.color);
        attributes.add("ManaCost: " + this.manaCost);
        attributes.add("Type: " + this.type);
        attributes.add("Effect: " + this.effect);

        return attributes;
    }


    public ArrayList<String> formatCardAttributesSize(ArrayList<String> attributes, int nChars) {
        /**
         *    Creates a list with all the cards attributes. Each element of the list corresponds to a line with less
         * than nChars characters. So lines that are longer than nChars are separated in multiple lines.
         *
         * Parameters
         * ----------
         * attributes : ArrayList<String>
         *       Attributes of the cards (name, color, mana cost, type and abilities).
         * nChars : integer
         *       Maximum number of characters each line can have in the new arrayList.
         *
         * Returns
         * -------
         * ArrayList<String>
         *       Attributes of the cards formatted so that each line of the array has less than nChars characters.
         */

        ArrayList<String> formattedAttributes = new ArrayList();

        for(String attributeLine: attributes) {
            String lastLineOfAttribute = attributeLine;

            while(lastLineOfAttribute.length() > nChars) {
                formattedAttributes.add(lastLineOfAttribute.substring(0, nChars));
                lastLineOfAttribute = lastLineOfAttribute.substring(nChars);
            }

            if (lastLineOfAttribute.length() > 0) {
                formattedAttributes.add(lastLineOfAttribute);
            }
        }

        return formattedAttributes;
    }


    public String sizeString(String line, int size) {
        /**
         *    Resizes a string in accordance with the preferred size. If the string is too short, it adds whitespaces,
         * if it is too long, it removes the extra characters and if it's the right size, it returns the same string.
         *
         * Parameters
         * ----------
         * line : string
         *       String that is going to be formatted.
         * size : integer
         *       Size that the returned string must be.
         *
         * Returns
         * -------
         * string
         *       Formatted string (string with the correct size).
         */

        if (line.length() < size) {

            String newLine = line;
            for(int j = 0; j < size - line.length(); j++) {
                newLine += " ";
            }
            return newLine;

        } else if (line.length() > size) {

            return line.substring(0, size);
        }

        return line;
    }


    public String[] getCard() {  //creates a String array containing the contents of the card
        /**
         * Creates an array with the lines that are going to create the card in the terminal.
         *
         * Parameters
         * ----------
         * None.
         *
         * Returns
         * -------
         * array<String>
         *       Lines that constitute the visualization of the card in the terminal.
         */

        ArrayList<String> formattedAttributes = formatCardAttributesSize(getCardAttributes(), 29);
        String[] carta = new String[15];

        for(int i = 0; i<15; i++){

            if (i == 0 || i == 14) {
                carta[i] = "---------------------------------";
            } else if (i == 12 && formattedAttributes.size() > 10) {
                carta[i] = "| " + sizeString(formattedAttributes.get(i-2), 26) + "... |";

            } else if ((i > 1 && i < 13) && i-2 < formattedAttributes.size()) {
                carta[i] = "| " + sizeString(formattedAttributes.get(i-2), 29) + " |";

            } else {
                carta[i] = "|                               |";
            }
        }

        return carta;
    }


    public void showCard() {
        /**
         * Prints the card to the terminal.
         *
         * Parameters
         * ----------
         * None.
         *
         * Returns
         * -------
         * None.
         */

        String[] carta = getCard();
        for(int i = 0; i < carta.length; i++) {
            System.out.println(carta[i]);
        }
    }

    public abstract ArrayList<String> getCardsHandEffects();
}