package mtg.mendonca;

import java.util.ArrayList;

/**
 * Class that has all the general card attributes and functionalities.
 */
public abstract class Card {
    
    private String name;
    private String color;
    private String manaCost;
    private String type;
    private String effect;
    private boolean tapped;


    /**
     * Returns the name of the card.
     *
     * @return A string with the name of the card.
     */
    public String getName() {
        return this.name;
    }


    /**
     * Returns the color of the card.
     *
     * @return A string with the color of the card.
     */
    public String getColor() {
        return this.color;
    }


    /**
     * Returns the mana cost of the card.
     *
     * @return A string representation of the mana cost of the card.
     */
    public String getManaCost() {
        return this.manaCost;
    }


    /**
     * Returns the type of the card.
     *
     * @return A string that represents that type of the card.
     */
    public String getType() {
        return this.type;
    }


    /**
     * Returns the abilities of the card.
     *
     * @return A string with the abilities of the card.
     */
    public String getEffect() {
        return this.effect;
    }


    /**
     * Determines if a card is tapped or not.
     *
     * @return A boolean with the value of true if the card is tapped and false if it's not.
     */
    public boolean isTapped() {
        return this.tapped;
    }


    /**
     * Class constructor.
     *
     * @param name A string that represents the name of the card that is being created.
     * @param color A string that represents the color of the card that is being created.
     * @param manaCost A string that represents the mana cost of the card that is being created.
     * @param type A string that represents the type of the card that is being created.
     * @param effect A string that represents the abilities of the card that is being created.
     */
    public Card(String name, String color, String manaCost, String type, String effect) {


        this.name = name;
        this.color = color;
        this.manaCost = manaCost;
        this.type = type;
        this.effect = effect;
    }


    /**
     * Taps the card. The class variable tapped is converted to true.
     */
    public void tap() {
        this.tapped = true;
    }


    /**
     * Untaps the card. The class variable tapped is converted to false.
     */
    public void untap() {
        this.tapped = false;
    }


    /**
     * Creates a list with all the cards attributes.
     *
     * @return ArrayList<String> of the attributes of the card (name, color, mana cost, type and abilities).
     */
    public ArrayList<String> getCardAttributes() {

        ArrayList<String> attributes = new ArrayList<>();

        attributes.add("Name: " + this.name);
        attributes.add("Color: " + this.color);
        attributes.add("ManaCost: " + this.manaCost);
        attributes.add("Type: " + this.type);
        attributes.add("Effect: " + this.effect);

        return attributes;
    }


    /**
     *    Creates a list with all the cards attributes. Each element of the list corresponds to a line with less
     * than nChars characters. So lines that are longer than nChars are separated in multiple lines.
     *
     * @param attributes ArrayList<String> of the attributes of the cards (name, color, mana cost, type and abilities).
     * @param nChars Maximum number of characters each line can have in the new arrayList. nChars must be bigger than 0.
     *
     * @return ArrayList<String> of the attributes of the cards formatted so that each line of the array has less than
     * nChars characters.
     */
    public ArrayList<String> formatCardAttributesSize(ArrayList<String> attributes, int nChars) {

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


    /**
     *    Resizes a string in accordance with the preferred size. If the string is too short, it adds whitespaces,
     * if it is too long, it removes the extra characters and if it's the right size, it returns the same string.
     *
     * @param line String that is going to be formatted.
     * @param size Integer that describes the size that the returned string must be. It must have a value bigger or
     *             equal to 0.
     *
     * @return Formatted string (string with the correct size).
     */
    public String sizeString(String line, int size) {

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


    /**
     * Creates an array with the lines that are going to create the card in the terminal.
     *
     * @return An array of lines that constitute the visualization of the card in the terminal.
     */
    public String[] getCard() {

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


    /**
     * Prints the card to the terminal.
     */
    public void showCard() {
        String[] carta = getCard();

        for(int i = 0; i < carta.length; i++) {
            System.out.println(carta[i]);
        }
    }

    public abstract ArrayList<String> getCardsHandEffects();
}