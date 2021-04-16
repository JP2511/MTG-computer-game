package mtg.mendonca;

import java.util.ArrayList;
import java.util.List;

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

    // pairs of values that change the attack and defense temporarily, eg: "+1/+1" or "0/-2"
    private ArrayList<String> counters = new ArrayList<>();


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
     * Returns the attack of the card. It accounts for possibly added counters.
     *
     * @return Integer that represents the attack of the creature.
     */
    public int getAttack() {
        // counter mechanism missing
        return this.attack;
    }


    /**
     *   Returns the defense of the card. It accounts for the temporary damage taken to the card during the turn and
     * also considers added counters.
     *
     * @return Integer that represents the defense of the creature.
     */
    public int getDefense() {
        // counter mechanism missing
        return this.defenseDuringTurn;
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
     *   Sets the turn in which a card was played. It is used to determine if a creature is able to attack during the
     * current turn. A creature cannot attack during the turn it was played, unless it has haste.
     *
     * @param turnNumber Number of the turn a creature was played.
     */
    public void setTurnInWhichItWasPlayed(int turnNumber) {
        this.turnInWhichItWasPlayed = turnNumber;
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
            int sumOfDefendingCreatureAttack = 0;
            int sumOfDefendingCreatureFirstStrike = 0;
            for(int i = 0; i < valueOfDamageToGive.size(); i++) {
                if( defendingCreatures.get(i).getFirstStrike() || defendingCreatures.get(i).getDoubleStrike()) {
                    sumOfDefendingCreatureFirstStrike += defendingCreatures.get(i).getAttack();
                    if((this.firstStrike && valueOfDamageToGive.get(i) > 0) ||
                            (this.doubleStrike && valueOfDamageToGive.get(i) > 0)) {
                        defendingCreatures.get(i).doDefense(valueOfDamageToGive.get(i), this.deathtouch);
                    }
                    if(defendingCreatures.get(i).getDeathtouch()) {
                        this.dead = true;
                    }
                }
            }
            if(sumOfDefendingCreatureFirstStrike >= this.defense) {
                this.dead = true;
            }
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
     * Creates an array with the lines that are going to create the creature in the terminal.
     *
     * @return An array of lines that constitute the visualization of the creature in the terminal.
     */
    @Override
    public String[] getCard () {  //creates a String array containing the contents of the card
        String[] carta = new String[15];
        List caracteristicas = new ArrayList();
        caracteristicas.add("Name: " + super.getName());
        caracteristicas.add("Color: " + super.getColor());
        caracteristicas.add("ManaCost: " + super.getManaCost());
        caracteristicas.add("Type: " + super.getType());
        caracteristicas.add("SubType: " + this.subType);
        caracteristicas.add("Attack: " + this.attack);
        caracteristicas.add("Defense: " + this.defense);
        String efeito = "Effect: " + super.getEffect();
        if(efeito.length() + 4 > 33) {
            String a = "";
            for(int i = 0; i < efeito.length(); i++) {
                if( a.length() < 29 && i != efeito.length()-1) {
                    a += efeito.charAt(i);
                } else if(a.length() < 29 && i == efeito.length()-1) {
                    caracteristicas.add(a + efeito.charAt(i));
                } else {
                    caracteristicas.add(a);
                    a = "" + efeito.charAt(i);
                    if(i == efeito.length() - 1) {
                        caracteristicas.add(a);
                    }
                }
            }
        } else {
            caracteristicas.add(efeito);
        }

        if(isTapped())  {
            caracteristicas.add("T A P P E D -- TAPPED");
        }

        for(int i = 0; i<15; i++){
            if(i==0 || i==14) {
                carta[i] = "---------------------------------";
            } else if (i > 1 & i <caracteristicas.size() + 2 ) {
                String valor = "";
                for(int j = 0; j < 33; j++){
                    if(j==0 || j == 32){
                        valor += "|";
                    } else if( j > 1 && j < ((String) caracteristicas.get(i-2)).length() + 2) {
                        valor += ((String) caracteristicas.get(i-2)).charAt(j-2);
                    } else {
                        valor += " ";
                    }
                }
                carta[i] = valor;
            } else {
                carta[i] = "|                               |";
            }
        }
        return carta;
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