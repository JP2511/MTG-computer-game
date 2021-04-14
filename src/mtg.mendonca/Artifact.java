package mtg.mendonca;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Artifact extends Card {

    private final boolean cycling = getEffect().contains("Cycling") && !getEffect().contains("Cyclicing abilities");
    private boolean ableToEquip;

    {
        // Checks if the artifact has the ability to be equipped to a creature card
        Pattern abilityToEquip = Pattern.compile(".*[Ee]quip(: | )..*");
        Matcher matchesEquip = abilityToEquip.matcher(super.getEffect());
        ableToEquip = matchesEquip.matches();

        // Checks if an artifact enters the battlefield tapped or not
        if(super.getEffect().contains(super.getName() + " enters the battlefield tapped")) {
            super.tap();
        }
    }

    /**
     * Artifact class constructor.
     *
     * @param name Name of the instant/card.
     * @param color String representation of the color of the instant/card.
     * @param manaCost String representation of the color of the instant/card.
     * @param effect String with the abilities of the instant.
     */
    public Artifact(String name, String color, String manaCost, String effect) {
        super(name, color, manaCost, "Artifact", effect);
    }


    /**
     * Empty artifact class constructor.
     */
    public Artifact() {
        this("No Name", "No Color", "0", "No Effect");
    }


    /**
     * Checks to see if the artifact has abilities that can be cast from the hand such as Cycling.
     *
     * @return List of the names of the abilities the artifact has that are able to be cast from the hand.
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