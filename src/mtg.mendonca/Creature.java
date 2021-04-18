package mtg.mendonca;

import java.util.*;

public class Creature extends Card {

    private final String subType;
    private final int attack;
    private final int defense;

    private int turnInWhichItWasPlayed;
    private boolean dead;


    /*   The value of defense during the turn. After the turn ends, it goes back to the value it had before it was
     * changed by temporary effects.
     */
    private int defenseDuringTurn;

    // Pairs of values that change the attack and defense indefinitly, eg: "+1/+1" or "0/-2"
    private ArrayList<int[]> counters = new ArrayList<>();

    /*   Pairs of values that change the attack and defense temporarily, for example, does added by a creature with
     * bloodrush.
     */
    private Hashtable<Card, int[]> tempCounters = new Hashtable();


    // abilities a creature might have
    private boolean haste = getEffect().contains("Haste");
    private boolean vigilance = getEffect().contains("Vigilance");
    private boolean deathtouch = getEffect().contains("Deathtouch");
    private boolean flying = getEffect().contains("Flying");
    private boolean reach = getEffect().contains("Reach");
    private boolean firstStrike = getEffect().contains("First Strike");
    private boolean doubleStrike = getEffect().contains("Double Strike");
    private boolean flash = getEffect().contains("Flash");
    private boolean bloodRush = getEffect().contains("Bloodrush");
    private boolean cycling = getEffect().contains("Cycling") && !getEffect().contains("Cyclicing abilities");


    {
        // Checks if an artifact enters the battlefield tapped or not
        if(super.getEffect().contains(super.getName() + " enters the battlefield tapped")) {
            super.tap();
        }
    }


    /**
     * Returns the attack of the card. It accounts for possibly added counters and the temporary counters.
     *
     * @return Integer that represents the attack of the creature.
     */
    public int getAttack() {
        int attackValue = this.attack;

        for(int[] counter: this.counters) {
            attackValue += counter[0];
        }

        for(Card key: this.tempCounters.keySet()) {
            attackValue += this.tempCounters.get(key)[0];
        }

        return attackValue;
    }


    /**
     *   Returns the defense of the card. It accounts for the temporary damage taken to the card during the turn and
     * also considers the added counters and the temporary counters.
     *
     * @return Integer that represents the defense of the creature.
     */
    public int getDefense() {
        int defenseValue = this.defenseDuringTurn;

        for(int[] counter: this.counters) {
            defenseValue += counter[1];
        }

        for(Card key: this.tempCounters.keySet()) {
            defenseValue += this.tempCounters.get(key)[1];
        }

        return defenseValue;
    }


    /**
     *   Determines if the creature card is dead. This function is useful to determine if a creature is going to the
     * graveyard after a battle phase.
     *
     * @return Boolean True if the creature is dead and false if the creature is alive.
     */
    public boolean isDead() {
        return this.dead;
    }


    /**
     * Determines if the creature has vigilance.
     *
     * @return Boolean True if the creature has vigilance and false if the creature doesn't.
     */
    public boolean getVigilance() {
        return this.vigilance;
    }


    /**
     * Determines if the creature has deathtouch.
     *
     * @return Boolean True if the creature has deathtouch and false if the creature doesn't.
     */
    public boolean getDeathtouch() {
        return this.deathtouch;
    }


    /**
     * Determines if the creature has flying.
     *
     * @return Boolean True if the creature has flying and false if the creature doesn't.
     */
    public boolean getFlying() {
        return this.flying;
    }


    /**
     * Determines if the creature has reach.
     *
     * @return Boolean True if the creature has reach and false if the creature doesn't.
     */
    public boolean getReach() {
        return this.reach;
    }


    /**
     * Determines if the creature has first-strike.
     *
     * @return Boolean True if the creature has first-strike and false if the creature doesn't.
     */
    public boolean getFirstStrike() {
        return this.firstStrike;
    }


    /**
     * Determines if the creature has double-strike.
     *
     * @return Boolean True if the creature has double-strike and false if the creature doesn't.
     */
    public boolean getDoubleStrike() {
        return this.doubleStrike;
    }


    /**
     * Determines if the creature has flash.
     *
     * @return Boolean True if the creature has flash and false if the creature doesn't.
     */
    public boolean getFlash() {
        return this.flash;
    }


    /**
     * Allows for alterations to the defense during the turn.
     *
     * @param value Integer amount that decreases or increases the value of the defenseDuringTurn value.
     */
    public void alterDefense(int value) {
        this.defenseDuringTurn += value;
    }


    /**
     *   Sets the turn in which a card was played. It is used to determine if a creature is able to attack during the
     * current turn. A creature cannot attack during the turn it was played, unless it has haste.
     *
     * @param turnNumber Number of the turn a creature was played.
     */
    public void setTurnInWhichItWasPlayed(int turnNumber) {
        this.turnInWhichItWasPlayed = turnNumber;
    }


    /**
     * Adds an indefinite counter to the counters arrayList.
     *
     * @param pairOfValues Pair of values in a string separated by a '/' to add to the attack and to the defense in
     *                their respective order.
     */
    public void addCounter(String pairOfValues) {
        int[] counterValues = new int[2];

        for (int i = 0; i < 2; i++) {
            counterValues[i] = Integer.parseInt(pairOfValues.split("/")[i]);
        }

        this.counters.add(counterValues);
    }


    /**
     * Adds an indefinite counter to the counters arrayList.
     *
     * @param keyCard Card responsible for adding the temporary counters to the creature.
     * @param pairOfValues Pair of values in a string separated by a '/' to add to the attack and to the defense in
     *                their respective order.
     */
    public void addTempCounter(Card keyCard, String pairOfValues) {
        int[] counterValues = new int[2];

        counterValues[0] = Integer.parseInt(pairOfValues.split("/")[0]);
        counterValues[1] = Integer.parseInt(pairOfValues.split("/")[1]);

        if (this.tempCounters.containsKey(keyCard)) {
            int[] oldCounterValues = this.tempCounters.get(keyCard);

            counterValues[0] += oldCounterValues[0];
            counterValues[1] += oldCounterValues[1];
        }

        this.tempCounters.put(keyCard, counterValues);
    }


    /**
     * Class constructor.
     *
     * @param name A string that represents the name of the card that is being created.
     * @param color A string that represents the color of the card that is being created.
     * @param manaCost A string that represents the mana cost of the card that is being created.
     * @param type A string that represents the type of the card that is being created.
     * @param effect A string that represents the abilities of the card that is being created.
     * @param attack An integer that represents the attack of the creature.
     * @param defense An integer that represents the life or defense of the creature.
     * @param subType A string that represents the subtype of the creature.
     */
    public Creature(String name, String color, String manaCost, String effect, int attack, int defense, String subType)
    {
        super(name, color, manaCost, "Creature", effect);
        this.attack = attack;
        this.defense = defense;
        this.defenseDuringTurn = defense;
        this.subType = subType;
    }


    /**
     * Resets the defenseDuringTurn value to the original defense value.
     */
    public void resetDefense() {
        this.defenseDuringTurn = this.defense;
    }


    /**
     * It handles damaged temporary damaage either taken during the attack phase or other phases during the turn.
     *
     * @param damageToTake An integer that represents the temporary damage to be taken by the creature.
     * @param hasDeathtouch A boolean that determines if the damage taken by this creature is being delt by a creature
     *                 with death touch.
     */
    public void doDefense(int damageToTake, boolean hasDeathtouch) {
        if (super.isTapped()) {
            System.out.println("You can not use this card to defend this turn.");

        } else {
            this.defenseDuringTurn -= damageToTake;
            if (this.defenseDuringTurn <= 0 || hasDeathtouch) {
                this.dead = true;
            }
        }
    }


    /**
     * It handle the attack mechanism to a player.
     *
     * @param player The Player to attack.
     */
    public void doAttack(Player player){
        if (super.isTapped()) {
            System.out.println("You can not use this card to attack this turn.");

        } else {
            player.loseLife(this.doubleStrike ? 2*this.attack : this.attack);
            System.out.println(player.getName() + " lost " + (this.doubleStrike ? 2*this.attack : this.attack) +
                    " of life. " + player.getName() + " has now " + player.getLife() + " of life.");

            if (!this.vigilance) {
                super.tap();
            }
        }
    }


    /**
     * It handles the attack mechanism to a planeswalker.
     *
     * @param player The Player to attack.
     */
    public void doAttack(Planeswalker planewalker){
        if (super.isTapped()) {
            System.out.println("You can not use this card to attack this turn.");

        } else {
            planewalker.setLife(planewalker.getLife() - (this.doubleStrike ? 2*this.attack : this.attack));
            System.out.println(planewalker.getName() + " lost " + (this.doubleStrike ? 2*this.attack : this.attack) +
                    " of life. And therefore it now has " + planewalker.getLife() + " of life.");

            if (!this.vigilance) {
                super.tap();
            }
        }
    }


    /**
     *   Handles the first part of the attack phase. The attack phase is divided in early phase for creatures with
     * first-strike and double strike and normal phase for cards with double-strike and cards without first-strike. It
     * calculates the combined attack of the defending creatures to the attacking creature, it determines if any of the
     * defending creatures have deathtouch and would therefore kill the attacking creature and it makes a list of the
     * creatures that are going to defend during the normal phase of the attack phase.
     *
     * @param defendingCreature ArrayList of the defending creatures.
     *
     * @return Combined attack of the defending creatures in an integer.
     * @return A boolean with true if any of the defending creatures has deathtouch.
     * @return An arrayList of the creatures that are going to defend during the normal phase of the attack phase.
     */
    public List firstStrikePhase(ArrayList<Creature> defendingCreature) {
        int sumDefendingCreaturesAttack = 0;
        boolean anyHasDeathtouch = false;
        ArrayList<Creature> notFirstStrike = new ArrayList();

        for (Creature defender: defendingCreature) {
            if (defender.getFirstStrike() || defender.getDoubleStrike()) {
                sumDefendingCreaturesAttack += defender.getAttack();

                if (defender.getDeathtouch()) {
                    anyHasDeathtouch = true;
                }
                if (defender.getDoubleStrike()) {
                    notFirstStrike.add(defender);
                }
            } else {
                notFirstStrike.add(defender);
            }
        }

        return List.of(sumDefendingCreaturesAttack, anyHasDeathtouch, notFirstStrike);
    }

    /**
     * It handles the attack mechanism when the attacking creature is going to be defended by one or more creatures.
     *
     * @param defendingCreatures A list of creatures that are going to defend against the damage that would be delt to
     *                       a player or a planeswalker by the attacking creature.
     * @param valueOfDamageToGive A list of values that correspond to the damage that each defending creature will
     *                       receive. The specific damage is given to the defending creature with the same index on the
     *                       defendingCreatures list.
     */
    public void doAttack(ArrayList<Creature> defendingCreatures, ArrayList<Integer> valueOfDamageToGive) {
        if(super.isTapped()){
            System.out.println("You can not use this card to attack this turn.");
        } else{
            List resultOfFirstStrikePhase = firstStrikePhase(defendingCreatures);

            int sumOfDefendingCreatureAttack = 0;
            alterDefense((int) resultOfFirstStrikePhase.get(0));
            if (getDefense() <= 0 || (boolean) resultOfFirstStrikePhase.get(1)) {
                this.dead = true;
            }

            // in the works

            if(!this.dead) {
                for (int i = 0; i < valueOfDamageToGive.size(); i++) {
                    if (!defendingCreatures.get(i).getFirstStrike() && !defendingCreatures.get(i).isDead()) {
                        if(this.firstStrike || this.doubleStrike) {
                            defendingCreatures.get(i).doDefense(valueOfDamageToGive.get(i), this.deathtouch);
                            if(!defendingCreatures.get(i).isDead() ) {
                                if(this.doubleStrike) {
                                    defendingCreatures.get(i).doDefense(valueOfDamageToGive.get(i), this.deathtouch);
                                }
                                sumOfDefendingCreatureAttack += defendingCreatures.get(i).getAttack();
                            }
                        } else {
                            defendingCreatures.get(i).doDefense(valueOfDamageToGive.get(i), this.deathtouch);
                            sumOfDefendingCreatureAttack += defendingCreatures.get(i).getAttack();
                        }
                        if(defendingCreatures.get(i).getDeathtouch()) {
                            this.dead = true;
                        }
                    }
                }
                if (sumOfDefendingCreatureAttack >= this.defense) {
                    this.dead = true;
                }
            }
        }
    }


    /**
     * Determines if a creature is able to attack during the current time.
     *
     * @param turn An integer that represents the current turn.
     *
     * @return A boolean with the value of true if the creature is able to attack and false if it cannot.
     */
    public boolean canAttack(int turn) {
        return (this.turnInWhichItWasPlayed != turn && !isTapped()) || (this.haste && !isTapped());
    }


    /**
     * Creates a list with all the cards attributes.
     *
     * @return ArrayList<String> of the attributes of the card (name, color, mana cost, type, subtype, attack, defense
     *     and abilities) and with the information regarding if it is tapped or not.
     */
    @Override
    public ArrayList<String> getCardAttributes() {

        ArrayList<String> attributes = new ArrayList<>();

        attributes.add("Name: " + super.getName());
        attributes.add("Color: " + super.getColor());
        attributes.add("ManaCost: " + super.getManaCost());
        attributes.add("Type: " + super.getType());
        attributes.add("SubType: " + this.subType);
        attributes.add("Attack: " + getAttack());
        attributes.add("Defense: " + getDefense());

        if (isTapped()) {
            attributes.add("TAPPED - T A P P E D");
        }

        attributes.add("Effect: " + super.getEffect());

        return attributes;
    }


    /**
     * Creates an array with the lines that are going to create the creature in the terminal.
     *
     * @return An array of lines that constitute the visualization of the creature in the terminal.
     */
    @Override
    public String[] getCard() {

        ArrayList<String> formattedAttributes = super.formatCardAttributesSize(this.getCardAttributes(), 29);
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
     * Checks to see if the creature has abilities that can be cast from the hand such as Cycling.
     *
     * @return List of the names of the abilities the creature has that are able to be cast from the hand.
     */
    @Override
    public ArrayList<String> getCardsHandEffects() {
        ArrayList<String> effectsPossible = new ArrayList<>();
        if(this.cycling) {
            effectsPossible.add("Cycling");
        }
        if(this.bloodRush) {
            effectsPossible.add("Bloodrush");
        }
        return effectsPossible;
    }
}