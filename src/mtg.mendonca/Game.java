package mtg.mendonca;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        System.out.println("\n##############################################################################################################################################################");
        System.out.println("Welcome to the Magic The Gathering Arena RipOff Game!");
        System.out.println("For now only two players can play.");
        System.out.println("What are your names?");

        System.out.print("Player One: ");
        Scanner input = new Scanner(System.in);
        String player1name = input.nextLine();
        System.out.print("Player Two: ");
        String player2name = input.nextLine();

        Player player1 = new Player(player1name, createDeck("Red"));
        Player player2 = new Player(player2name, createDeck("Blue"));

        int turn = 0;
        Player[] players = new Player[2];
        players[0] = player1;
        players[1] = player2;

        while(player1.getLife() > 0 && player2.getLife() > 0) {
            boolean endGame = false;
            String playerWhoGaveUp = "No Name";
            for(int i = 0; i < players.length; i++) {

                Player thisTurnsPlayer = players[i];
                System.out.println("\n==========================================================================================================================================================");
                System.out.println(players[i].getName() + ", your turn started now.");
                turn++;  // counts the turns that have passed

                // untaps all cards of the field at the beginning of the turn
                thisTurnsPlayer.untapAllCardsOnField();

                // if it's the first turn of the player, they draw their hand and have the chance to mulligan
                if(turn == 1 || turn == 2) {
                    thisTurnsPlayer.shuffleDeck();
                    thisTurnsPlayer.drawCards(7);
                    thisTurnsPlayer.showHand();

                    System.out.println("Would you like to mulligan (redraw hands but with one less card)? (y/n)");
                    String mulliganYesOrNo = input.nextLine();
                    while(mulliganYesOrNo.equals("y") || mulliganYesOrNo.equals("Y") || mulliganYesOrNo.equals("Yes") || mulliganYesOrNo.equals("YES") || mulliganYesOrNo.equals("yes")) {
                        thisTurnsPlayer.mulligan();
                        thisTurnsPlayer.showHand();
                        System.out.println("Would you like to mulligan (redraw hands but with one less card) (y/n)?");
                        mulliganYesOrNo = input.nextLine();
                    }
                }

                // if it's not the very first turn, at the beginning of each player's turn they draw a card and see their hand
                if(turn != 1) {
                    thisTurnsPlayer.drawCards(1);
                    thisTurnsPlayer.showHand();
                }

                // asks a player if they want to play a land. If they do, it asks the other player if they would like to counter the land being played
                System.out.println("Would you like to play a land? (y/n)");
                String landYesOrNo = input.nextLine();
                if(landYesOrNo.equals("y") || landYesOrNo.equals("Y") || landYesOrNo.equals("Yes") || landYesOrNo.equals("YES") || landYesOrNo.equals("yes")) {
                    thisTurnsPlayer.namesOfLandsInHand();
                    System.out.print("Choose a land to play: ");
                    int landChosen = input.nextInt();
                    input.nextLine();

                    System.out.println(thisTurnsPlayer.getName() + " played the following card:");
                    thisTurnsPlayer.moveCardFromHandToStack(landChosen);
                    System.out.println((i == 0 ? players[1].getName() : players[0].getName()) + " would you like to counter the card " + thisTurnsPlayer.getName() + " is going to play? (y/n)" );

                    String counterYesOrNo = input.nextLine();  //needs completion
                    if(counterYesOrNo.equals("N") || counterYesOrNo.equals("n") || counterYesOrNo.equals("No") || counterYesOrNo.equals("NO") || counterYesOrNo.equals("no")) {
                        thisTurnsPlayer.playCardFromStackToField();
                    }
                }

                // shows to the player the main phase menu and allows the player to choose an action
                System.out.println("\n" + thisTurnsPlayer.getName() + ", what would you like to do next?");
                System.out.println("\t0 - Play a card;");
                System.out.println("\t1 - Use the effect of a card;");
                System.out.println("\t2 - Show hand;");
                System.out.println("\t3 - Show field;");
                System.out.println("\t4 - Move to the attack phase;");
                System.out.println("\t5 - Give up;");
                System.out.print("Your choice: ");
                int actionChoice = input.nextInt();
                input.nextLine();

                // while the player doesn't choose to move on to the attack phase or doesn't give up
                while(actionChoice != 4 && actionChoice != 5) {
                    switch(actionChoice) {
                        /*the player chooses to play a card, so the names of the non-land cards appear and ask him to choose one of the cards to play
                        then asks the other player if they would like to counter the card being played*/
                        case 0:
                            thisTurnsPlayer.namesOfCardsInHand();
                            System.out.print("Choose a card: ");
                            int cardChosen = input.nextInt();
                            input.nextLine();

                            thisTurnsPlayer.howToPayManaOfCard(thisTurnsPlayer.getCardFromHand(cardChosen));

                            // a flag to see if a card is a creature, so it can be defined in what turn it was played
                            boolean itIsACreature = false;
                            if(thisTurnsPlayer.getCardFromHand(cardChosen).getType().equals("Creature")) {
                                itIsACreature = true;
                            }
                            thisTurnsPlayer.showUntappedLandsPerColor();

                            //allows a player to choose how he or she will pay for the card being played
                            System.out.println("\nPlease choose how many lands you would like to tap to play " + thisTurnsPlayer.getCardFromHand(cardChosen).getName());
                            System.out.print("How many mountains (red lands) would you like to tap: ");
                            int redLandsTotap = input.nextInt();
                            input.nextLine();
                            System.out.print("How many forests (green lands) would you like to tap: ");
                            int greenLandsTotap = input.nextInt();
                            input.nextLine();
                            System.out.print("How many plains (white lands) would you like to tap: ");
                            int whiteLandsTotap = input.nextInt();
                            input.nextLine();
                            System.out.print("How many islands (blue lands) would you like to tap: ");
                            int blueLandsTotap = input.nextInt();
                            input.nextLine();
                            System.out.print("How many swamps (black lands) would you like to tap: ");
                            int blackLandsTotap = input.nextInt();
                            input.nextLine();

                            //shows the card and asks the other player if they would like to counter the card being played
                            System.out.println(thisTurnsPlayer.getName() + " played the following card:");
                            boolean isThereEnoughMana = thisTurnsPlayer.playCardFromHandToStack(cardChosen, redLandsTotap, greenLandsTotap, whiteLandsTotap, blueLandsTotap, blackLandsTotap);
                            if(isThereEnoughMana) {
                                System.out.println((i == 0 ? players[1].getName() : players[0].getName()) + " would you like to counter the card " + thisTurnsPlayer.getName() + " is going to play? (y/n)" );
                                String counterYesOrNo = input.nextLine();
                                if (counterYesOrNo.equals("N") || counterYesOrNo.equals("n") || counterYesOrNo.equals("No") || counterYesOrNo.equals("NO") || counterYesOrNo.equals("no")) {
                                    thisTurnsPlayer.playCardFromStackToField();
                                    if(itIsACreature) {
                                        thisTurnsPlayer.defineTheTurnACreatureWasPlayedOfTheLastAddedCreature(turn);
                                    }
                                }
                            }
                            break;

                        /*the player chooses to use the effect of a card, but it is still work in progress*/
                        case 1:
                            System.out.println("Still in development...");
                            break;

                        /*the player chooses to see his or her own hand*/
                        case 2:
                            thisTurnsPlayer.showHand();
                            break;

                        /*the player chooses to see the field, with the other players field at the top and the current player's field at the bottom*/
                        case 3:
                            printField(thisTurnsPlayer, (i == 0 ? players[1] : players[0]));
                            break;
                    }

                    // shows again the main phase menu
                    System.out.println("\n" + thisTurnsPlayer.getName() + ", what would you like to do next?");
                    System.out.println("\t0 - Play a card;");
                    System.out.println("\t1 - Use the effect of a card;");
                    System.out.println("\t2 - Show hand;");
                    System.out.println("\t3 - Show field;");
                    System.out.println("\t4 - Move to the attack phase;");
                    System.out.println("\t5 - Give up;");
                    System.out.print("Your choice: ");
                    actionChoice = input.nextInt();
                    input.nextLine();
                }

                // if the player chooses to give up
                if(actionChoice == 5) {
                    endGame = true;
                    playerWhoGaveUp = thisTurnsPlayer.getName();
                    break;
                }

                // moves on to the attack phase and presents the attack phase menu
                System.out.println("\n" + thisTurnsPlayer.getName() + ", what would you like to do next?");
                System.out.println("\t0 - Attack;");
                System.out.println("\t1 - Move to the final main phase;");
                System.out.print("Your choice: ");
                int attackOrNo = input.nextInt();
                input.nextLine();

                // if the player chooses to attack
                if(attackOrNo == 0) {
                    //shows the field to the current player
                    printField(thisTurnsPlayer, i == 0 ? players[1] : players[0]);
                    ArrayList<String> creaturesToUseToAttack = thisTurnsPlayer.getCreaturesNameAndIndex(turn);

                    // if the current player doesn't have any creatures able to attack this turn, it skips the attack phase
                    if(creaturesToUseToAttack.size() == 0) {
                        System.out.println("You don't have any creatures to attack this turn.");

                    } else {
                        //if the current player has creatures that are able to attack, it creates a list of said creatures
                        System.out.println("You can choose one or more of the following creatures to attack: ");
                        ArrayList<Integer> indexOfCreaturesToAttack = new ArrayList<>();
                        for(int j = 0; j < creaturesToUseToAttack.size(); j++) {
                            System.out.println("\t0 - Don't want to choose anymore creatures to attack.");
                            System.out.println(creaturesToUseToAttack.get(j));
                        }

                        // if the other player has one or more planeswalkers, it shows a list of all planeswalker + the other players name as targets for the attack
                        if((i == 0 ? players[1] : players[0]).isThereAPlaneswalker()) {
                            System.out.println("You can choose one of the following, for each creature, as a target for the attack of that creature:");
                            System.out.println("\t0 - Attack " + (i == 0 ? players[1].getName() : players[0].getName()) + ";");
                            for(int j = 0; j < (i == 0 ? players[1] : players[0]).getPlaneswalker().size(); i++) {
                                System.out.println("\t" + (j+1) + " - Attack " + (i == 0 ? players[1] : players[0]).getPlaneswalker().get(j) + ";");
                            }

                            // while player doesn't choose to not choose anymore creatures, it asks the player to choose a creature and a target for its attack
                            ArrayList<Integer> indexOfTargetOfAttack = new ArrayList<>();
                            System.out.print("Choose a card to attack with: ");
                            int indexOfCreatureToAttackWith = input.nextInt();
                            input.nextLine();
                            while(indexOfCreatureToAttackWith != 0) {
                                indexOfCreaturesToAttack.add(indexOfCreatureToAttackWith);
                                System.out.print("Choose a target for the creature you just chose: ");
                                int indexOfTargetToAttackOfCreature = input.nextInt();
                                input.nextLine();
                                indexOfTargetOfAttack.add(indexOfTargetToAttackOfCreature != 0 ? indexOfTargetToAttackOfCreature - 1 : indexOfTargetToAttackOfCreature);

                                System.out.print("Choose a card to attack with: ");
                                indexOfCreatureToAttackWith = input.nextInt();
                                input.nextLine();
                            }

                            // it shows the other player the field, and the choices of attack of the current player
                            System.out.println((i == 0 ? players[1].getName() : players[0].getName()) + " what would you like to defend?");
                            printField((i == 0 ? players[1] : players[0]), thisTurnsPlayer);
                            System.out.println("\n" + thisTurnsPlayer + " is going to attack: ");
                            for(int j = 0; j < indexOfCreaturesToAttack.size(); j++) {
                                System.out.println("\t" + (indexOfTargetOfAttack.get(j) == 0 ? "You" : thisTurnsPlayer.getPlaneswalkersNameAtIndex(indexOfTargetOfAttack.get(j)-1) ) +
                                        " with " + thisTurnsPlayer.getCreaturesByIndex(indexOfCreaturesToAttack).get(i).getName() + ";");
                            }

                            // shows a list of the other player's creatures that are able to defend
                            System.out.println("\n" + (i == 0 ? players[1].getName() : players[0].getName()) + ", you have the following creatures available to defend:");
                            ArrayList<String> creaturesToPossiblyDefend = (i == 0 ? players[1] : players[0]).getAllCreaturesAbleToDefendNamesAndIndexs();
                            System.out.println("\t0 - I don't want to defend this creature or I don't want to defend this creature with anymore creatures.");
                            for(int j = 0; j < creaturesToPossiblyDefend.size(); j++) {
                                // the number of the option corresponds to the index + 1
                                System.out.println(creaturesToPossiblyDefend.get(j));
                            }

                            //asks the other player to choose one or more (or none) of his or her creatures to defend each of the attacking creatures
                            ArrayList<ArrayList<Integer>> arrayOfCreaturesToDefendEachListIsAnAttackersDefense = new ArrayList<>();
                            int indexOfCreatureToDefend = 50;
                            for(int j = 0; j < indexOfCreaturesToAttack.size(); j++ ) {
                                ArrayList<Integer> creaturesToDefend = new ArrayList<>();
                                int g = 0;
                                while (indexOfCreatureToDefend > 0) {
                                    System.out.print("Choose " + ( g == 0 ? "a" : "another" ) + " creature to defend " + thisTurnsPlayer.getCreaturesByIndex(indexOfCreaturesToAttack).get(j).getName() + ":");
                                    indexOfCreatureToDefend = input.nextInt();
                                    input.nextLine();
                                    creaturesToDefend.add(indexOfCreatureToDefend);
                                }
                                arrayOfCreaturesToDefendEachListIsAnAttackersDefense.add(creaturesToDefend);
                            }
                        }
                    }
                }
            }

            if(endGame) {
                System.out.println(playerWhoGaveUp + " has given up.");
                break;
            }
        }
    }

    public static Deck createDeck(String color) {
        ArrayList<Card> cardsForTheDeck = new ArrayList<>();
        switch (color) {
            case "Red":
                for (int i = 0; i < 20; i++) {
                    cardsForTheDeck.add(new Land("Mountain", "Red", "", ""));
                    cardsForTheDeck.add(new Creature("JoJo", "Red", "R", "", 1, 1, "Hamon Master"));
                    cardsForTheDeck.add(new Creature("JoJo", "Red", "R", "", 1, 1, "Hamon Master"));
                }
                break;
            case "Blue":
                for (int i = 0; i < 20; i++) {
                    cardsForTheDeck.add(new Land("Island", "Blue", "", ""));
                    cardsForTheDeck.add(new Creature("Dior", "Blue", "U", "", 1, 1, "Vampire"));
                    cardsForTheDeck.add(new Creature("Dior", "Blue", "U", "", 1, 1, "Vampire"));
                }
                break;
        }
        Deck deck = new Deck(cardsForTheDeck);
        return deck;
    }

    public static void printField(Player playerThisTurn, Player opponent) {
        System.out.println("-----------------------------------------------------------------------" + opponent.getName() + " FIELD----------------------------------------------------------------------------------");
        opponent.showField();
        System.out.println("-----------------------------------------------------------------------" + playerThisTurn.getName() + " FIELD----------------------------------------------------------------------------------");
        playerThisTurn.showField();
        System.out.println("------------------------------------------------------------------------------END FIELD---------------------------------------------------------------------------------");
    }
}

