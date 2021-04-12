package mtg.mendonca;

import java.util.ArrayList;
import java.util.List;

public class Creature extends Card {
    private int attack;
    private int defense;
    private String subType;
    private boolean dead = false;
    private boolean permanent = false;
    private int turnInWhichItWasPlayed;
    private final boolean haste = getEffect().contains("Haste");
    private final boolean vigilance = getEffect().contains("Vigilance");
    private final boolean deathtouch = getEffect().contains("Deathtouch");
    private final boolean flying = getEffect().contains("Flying");
    private final boolean reach = getEffect().contains("Reach");
    private final boolean firstStrike = getEffect().contains("First Strike");
    private final boolean doubleStrike = getEffect().contains("Double Strike");
    private final boolean flash = getEffect().contains("Flash");
    private final boolean bloodRush = getEffect().contains("Bloodrush");
    private final boolean cycling = getEffect().contains("Cycling") && !getEffect().contains("Cyclicing abilities");

    {
        if(super.getEffect().contains(super.getName() + " enters the battlefield tapped")) {
            super.tap();
        }
    }

    public int getAttack() {
        return this.attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public boolean isDead() {
        return this.dead;
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public boolean isPermanent() {
        return this.permanent;
    }

    public boolean getVigilance() {
        return this.vigilance;
    }

    public boolean getDeathtouch() {
        return this.deathtouch;
    }

    public boolean getFlying() {
        return this.flying;
    }

    public boolean getReach() {
        return this.reach;
    }

    public boolean getFirstStrike() {
        return this.firstStrike;
    }

    public boolean getDoubleStrike() {
        return this.doubleStrike;
    }

    public boolean getFlash() {
        return this.flash;
    }

    public void setTurnInWhichItWasPlayed(int turnNumber) {
        this.turnInWhichItWasPlayed = turnNumber;
    }

    public Creature(String name, String color, String manaCost, String effect, int attack, int defense, String subType) {
        super(name, color, manaCost, "Creature", effect);
        this.attack = attack;
        this.defense = defense;
        this.subType = subType;
    }

    public void doDefense(int damageToTake, boolean hasDeathtouch) {
        if(super.isTapped()){
            System.out.println("You can not use this card to defend this turn.");
        } else{
            this.defense -= damageToTake;
            if(this.defense <= 0 || hasDeathtouch) {
                this.dead = true;
            }
        }
    }

    public void doAttack(Player nome){
        if(super.isTapped()){
            System.out.println("You can not use this card to attack this turn.");
        } else{
            nome.loseLife(this.doubleStrike ? 2*this.attack : this.attack);
            System.out.println(nome.getName() + " lost " + (this.doubleStrike ? 2*this.attack : this.attack) + " of life. " + nome.getName() + " has now " + nome.getLife() + " of life.");
            if(!this.vigilance) {
                super.tap();
            }
        }
    }

    public void doAttack(Planeswalker jake){
        if(super.isTapped()){
            System.out.println("You can not use this card to attack this turn.");
        } else{
            jake.setLife(jake.getLife() - (this.doubleStrike ? 2*this.attack : this.attack));
            System.out.println(jake.getName() + " lost " + (this.doubleStrike ? 2*this.attack : this.attack) + " of life. And therefore it now has " + jake.getLife() + " of life.");
            if(!this.vigilance) {
                super.tap();
            }
        }
    }

    public void doAttack(ArrayList<Creature> defendingCreatures, ArrayList<Integer> valueOfDamageToGive) {
        if(super.isTapped()){
            System.out.println("You can not use this card to attack this turn.");
        } else{
            int sumOfDefendingCreatureAttack = 0;
            int sumOfDefendingCreatureFirstStrike = 0;
            for(int i = 0; i < valueOfDamageToGive.size(); i++) {
                if( defendingCreatures.get(i).getFirstStrike() || defendingCreatures.get(i).getDoubleStrike()) {
                    sumOfDefendingCreatureFirstStrike += defendingCreatures.get(i).getAttack();
                    if((this.firstStrike && valueOfDamageToGive.get(i) > 0) || (this.doubleStrike && valueOfDamageToGive.get(i) > 0)) {
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

    public boolean canAttack(int turn) {
        if((this.turnInWhichItWasPlayed != turn && !isTapped()) || (this.haste && !isTapped())) {
            return true;
        } else {
            return false;
        }
    }

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