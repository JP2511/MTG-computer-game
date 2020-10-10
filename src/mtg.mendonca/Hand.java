package mtg.mendonca;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hand {
    private ArrayList<Card> hand = new ArrayList<>();

    public void sendToHand(ArrayList<Card> cards) {
        for(int i = 0; i < cards.size(); i++) {
            this.hand.add(cards.get(i));
        }
    }

    public void sendToHand(Card card) {
        this.hand.add(card);
    }

    public Card removeFromHand(int index) {
        Card cardToRemoveFromHand = this.hand.get(index);
        this.hand.remove(index);
        return cardToRemoveFromHand;
    }

    public Card getCardFromHand(int index) {
        return this.hand.get(index);
    }

    public void showHand(int n) {
        if(n > this.hand.size()) {
            n = this.hand.size();
        }
        ArrayList<Card> stackedCardsLine = new ArrayList();
        System.out.println("----------------------------------------------------------------------------------HAND----------------------------------------------------------------------------------");
        for(int i = 0; i < n; i++) {
            if((i+1) % 5 != 0 && i != n-1) {
                stackedCardsLine.add(this.hand.get(i));
            } else {
                stackedCardsLine.add(this.hand.get(i));
                for(int g = 0; g < 15; g++) {
                    for (int j = 0; j < stackedCardsLine.size(); j++) {
                        if(j != stackedCardsLine.size()-1) {
                            System.out.print(stackedCardsLine.get(j).getCard()[g] + "   ");
                        } else {
                            System.out.println(stackedCardsLine.get(j).getCard()[g]);
                        }
                    }
                }
                stackedCardsLine.clear();
            }
        }
        System.out.println("------------------------------------------------------------------------------END HAND----------------------------------------------------------------------------------");
    }

    public int sizeOfHand() {
        return this.hand.size();
    }

    public ArrayList<String> namesOfCardsInHand() {
        ArrayList<String> namesAndIndexes = new ArrayList<>();
        for(int i = 0; i < this.hand.size(); i++) {
            if(!this.hand.get(i).getType().equals("Land")) {
                System.out.println("\t" + i + " - " + this.hand.get(i).getName());
                namesAndIndexes.add("\t" + i + " - " + this.hand.get(i).getName());
            }
        }
        return namesAndIndexes;
    }

    public ArrayList<Integer> namesOfLandsInHand() {
        ArrayList<Integer> possibleLandsToChoose = new ArrayList<>();
        for(int i = 0; i < this.hand.size(); i++) {
            if(this.hand.get(i).getType().equals("Land")) {
                System.out.println(i + " - " + this.hand.get(i).getName());
                possibleLandsToChoose.add(i);
            }
        }
        return possibleLandsToChoose;
    }

    public boolean areThereLandsInHand() {
        for (Card card : this.hand) {
            if (card.getType().equals("Land")) {
                return true;
            }
        }
        return false;
    }

    public boolean areThereNonLandsCardsInHand() {
        for (int i = 0; i < this.hand.size(); i++) {
            if(!this.hand.get(i).getType().equals("Land")) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> checkForCounterSpell(Card cardBeingPlayed) {
        ArrayList<Integer> indexesOfCounterStrikes = new ArrayList<>();
        for (int i = 0; i < this.hand.size(); i++) {
            if((this.hand.get(i).getType().equals("Creature") && this.hand.get(i).getEffect().contains("Flash")) || this.hand.get(i).getType().equals("Instant")) {
                String effectOfCard = this.hand.get(i).getEffect();
                if (effectOfCard.matches(".*[cC]ounter target.*spell.*")) {
                    Pattern pattern = Pattern.compile(".*[cC]ounter target (.*) spell.*");
                    Matcher matcher = pattern.matcher(effectOfCard);
                    if (matcher.find()) {
                        if (matcher.group(1).contains(cardBeingPlayed.getType().toLowerCase()) && !matcher.group(1).contains("non")) {
                            indexesOfCounterStrikes.add(i);
                        } else if (!matcher.group(1).contains(cardBeingPlayed.getType().toLowerCase()) && matcher.group(1).contains("non")) {
                            indexesOfCounterStrikes.add(i);
                        } else if (matcher.group(1).contains(cardBeingPlayed.getColor().toLowerCase())) {
                            indexesOfCounterStrikes.add(i);
                        } else if (matcher.group(1).contains("multicolored") && cardBeingPlayed.getColor().contains("and")) {
                            indexesOfCounterStrikes.add(i);
                        }
                    } else {
                        indexesOfCounterStrikes.add(i);
                    }
                }
            }
        }
        return indexesOfCounterStrikes;
    }

    public ArrayList<String> getIndexAndNameOfAllCardsInHand() {
        ArrayList<String> cardsInHand = new ArrayList<>();
        for(int i = 0; i < this.hand.size(); i++) {
            cardsInHand.add("\t" + (i + 1) + " - " + this.hand.get(i).getName());
        }
        return cardsInHand;
    }
}