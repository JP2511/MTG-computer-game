package mtg.mendonca;

import java.util.ArrayList;

/**
 * Class that handles Instant and all their innate abilities.
 */
public class Instant extends Card {

    // determines if an instant has the cycling ability
    private final boolean cycling = getEffect().contains("Cycling") && !getEffect().contains("Cyclicing abilities");

    /**
     * Instant class constructor.
     *
     * @param name Name of the instant/card.
     * @param color String representation of the color of the instant/card.
     * @param manaCost String representation of the color of the instant/card.
     * @param effect String with the abilities of the instant.
     */
    public Instant(String name, String color, String manaCost, String effect) {
        super(name, color, manaCost, "Instant", effect);
    }


    /**
     * Empty instant class constructor.
     */
    public Instant() {
        this("No Name", "No Color", "0", "No Effect");
    }


    /**
     *    Checks to see if the instant has abilities that can be cast from the hand such as Cycling that are not counter
     * target spells.
     *
     * @return List of the names of the abilities the instant has that are able to be cast from the hand.
     */
    @Override
    public ArrayList<String> getCardsHandEffects() {
        ArrayList<String> effectsPossible = new ArrayList<>();
        if(this.cycling) {
            effectsPossible.add("Cycling");
        }
        return effectsPossible;
    }
}