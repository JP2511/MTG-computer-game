package mtg.mendonca;

import java.util.ArrayList;
import java.util.Hashtable;
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

        while (player1.getLife() > 0 && player2.getLife() > 0) {
            boolean endGame = false;
            String playerWhoGaveUp = "No Name";
            for (int i = 0; i < players.length; i++) {

                Player thisTurnsPlayer = players[i];
                Player otherPlayer = (i == 0 ? players[1] : players[0]);
                System.out.println("\n==========================================================================================================================================================");
                System.out.println(players[i].getName() + ", your turn started now.");
                turn++;  // counts the turns that have passed

                // untaps all cards of the field at the beginning of the turn
                thisTurnsPlayer.untapAllCardsOnField();

                // if it's the first turn of the player, they draw their hand and have the chance to mulligan
                if (turn == 1 || turn == 2) {
                    thisTurnsPlayer.shuffleDeck();
                    thisTurnsPlayer.drawCards(7);
                    thisTurnsPlayer.showHand();

                    System.out.println("Would you like to mulligan (redraw hands but with one less card)? (y/n)");
                    String mulliganYesOrNo = input.nextLine();
                    while (mulliganYesOrNo.equals("y") || mulliganYesOrNo.equals("Y") || mulliganYesOrNo.equals("Yes") || mulliganYesOrNo.equals("YES") || mulliganYesOrNo.equals("yes")) {
                        thisTurnsPlayer.mulligan();
                        thisTurnsPlayer.showHand();
                        System.out.println("Would you like to mulligan (redraw hands but with one less card) (y/n)?");
                        mulliganYesOrNo = input.nextLine();
                    }
                }

                // if it's not the very first turn, at the beginning of each player's turn they draw a card and see their hand
                if (turn != 1) {
                    thisTurnsPlayer.drawCards(1);
                    thisTurnsPlayer.showHand();
                }

                // asks a player if they want to play a land. If they do, it asks the other player if they would like to counter the land being played
                if (thisTurnsPlayer.areThereLandsInHand()) {
                    System.out.println("Would you like to play a land? (y/n)");
                    String landYesOrNo = input.nextLine();
                    if (landYesOrNo.equals("y") || landYesOrNo.equals("Y") || landYesOrNo.equals("Yes") || landYesOrNo.equals("YES") || landYesOrNo.equals("yes")) {
                        thisTurnsPlayer.namesOfLandsInHand();
                        System.out.print("Choose a land to play: ");
                        int landChosen = input.nextInt();
                        input.nextLine();

                        System.out.println(thisTurnsPlayer.getName() + " played the following card:");
                        thisTurnsPlayer.moveCardFromHandToStack(landChosen);
                        System.out.println(otherPlayer.getName() + " would you like to counter the card " + thisTurnsPlayer.getName() + " is going to play? (y/n)");

                        String counterYesOrNo = input.nextLine();  //needs completion
//                        if (counterYesOrNo.equals("N") || counterYesOrNo.equals("n") || counterYesOrNo.equals("No") || counterYesOrNo.equals("NO") || counterYesOrNo.equals("no")) {
                        thisTurnsPlayer.playCardFromStackToField();
//                        }
                    }
                }

                // shows to the player the main phase menu and allows the player to choose an action
                System.out.println("\nYou are in the main phase now.");
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
                while (actionChoice != 4 && actionChoice != 5) {
                    switch (actionChoice) {
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
                            if (thisTurnsPlayer.getCardFromHand(cardChosen).getType().equals("Creature")) {
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
                            boolean isThereEnoughMana = thisTurnsPlayer.playCardFromHandToStack(cardChosen, redLandsTotap, greenLandsTotap, whiteLandsTotap, blueLandsTotap, blackLandsTotap);
                            if (isThereEnoughMana) {
                                System.out.println(thisTurnsPlayer.getName() + " played the above card:");
                                System.out.println(otherPlayer.getName() + " would you like to counter the card " + thisTurnsPlayer.getName() + " is going to play? (y/n)");
                                String counterYesOrNo = input.nextLine();
//                                if (counterYesOrNo.equals("N") || counterYesOrNo.equals("n") || counterYesOrNo.equals("No") || counterYesOrNo.equals("NO") || counterYesOrNo.equals("no")) {
                                thisTurnsPlayer.playCardFromStackToField();
                                if (itIsACreature) {
                                    thisTurnsPlayer.defineTheTurnACreatureWasPlayedOfTheLastAddedCreature(turn);
                                }
//                                }
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
                            printField(thisTurnsPlayer, otherPlayer);
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
                if (actionChoice == 5) {
                    endGame = true;
                    playerWhoGaveUp = thisTurnsPlayer.getName();
                    break;
                }

                // moves on to the attack phase and presents the attack phase menu
                System.out.println("\nYou are in the attack phase now.");
                System.out.println("\n" + thisTurnsPlayer.getName() + ", what would you like to do next?");
                System.out.println("\t0 - Attack;");
                System.out.println("\t1 - Move to the final main phase;");
                System.out.print("Your choice: ");
                int attackOrNo = input.nextInt();
                input.nextLine();

                // if the player chooses to attack
                if (attackOrNo == 0) {
                    //shows the field to the current player
                    printField(thisTurnsPlayer, i == 0 ? players[1] : players[0]);
                    ArrayList<String> creaturesToUseToAttack = thisTurnsPlayer.getCreaturesNameAndIndex(turn);

                    // if the current player doesn't have any creatures able to attack this turn, it skips the attack phase
                    if (creaturesToUseToAttack.size() == 0) {
                        System.out.println("You don't have any creatures to attack this turn.");

                    } else {
                        //if the current player has creatures that are able to attack, it creates a list of said creatures
                        System.out.println("You can choose one or more of the following creatures to attack: ");
                        ArrayList<Integer> indexOfCreaturesToAttack = new ArrayList<>();
                        System.out.println("\t0 - Don't want to choose anymore creatures to attack.");
                        for (int j = 0; j < creaturesToUseToAttack.size(); j++) {
                            System.out.println(creaturesToUseToAttack.get(j));
                        }

                        ArrayList<Integer> indexOfTargetOfAttack = new ArrayList<>();

                        // if the other player has one or more planeswalkers, it shows a list of all planeswalker + the other players name as targets for the attack
                        if (otherPlayer.isThereAPlaneswalker()) {
                            System.out.println("You can choose one of the following, for each creature, as a target for the attack of that creature:");
                            System.out.println("\t0 - Attack " + otherPlayer.getName() + ";");
                            for (int j = 0; j < otherPlayer.getPlaneswalker().size(); i++) {
                                System.out.println("\t" + (j + 1) + " - Attack " + otherPlayer.getPlaneswalker().get(j) + ";");
                            }

                            // while player doesn't choose to not choose anymore creatures, it asks the player to choose a creature and a target for its attack
                            System.out.print("Choose a card to attack with: ");
                            int indexOfCreatureToAttackWith = input.nextInt();
                            input.nextLine();
                            while (indexOfCreatureToAttackWith != 0) {
                                indexOfCreaturesToAttack.add(indexOfCreatureToAttackWith - 1);
                                System.out.print("Choose a target for the creature you just chose: ");
                                int indexOfTargetToAttackOfCreature = input.nextInt();
                                input.nextLine();

                                // if indexOfTargetToAttackOfCreature == -1, then the target is the player
                                indexOfTargetOfAttack.add(indexOfTargetToAttackOfCreature - 1);

                                System.out.print("Choose a card to attack with: ");
                                indexOfCreatureToAttackWith = input.nextInt();
                                input.nextLine();
                            }
                        } else {
                            System.out.print("Choose a card to attack with: ");
                            int indexOfCreatureToAttackWith = input.nextInt();
                            input.nextLine();
                            while (indexOfCreatureToAttackWith > 0) {
                                indexOfCreaturesToAttack.add(indexOfCreatureToAttackWith - 1);

                                // chooses the other player as the opponent
                                indexOfTargetOfAttack.add(-1);

                                System.out.print("Choose a card to attack with: ");
                                indexOfCreatureToAttackWith = input.nextInt();
                                input.nextLine();
                            }
                        }

                        // it shows the other player the field, and the choices of attack of the current player
                        System.out.println(otherPlayer.getName() + " what would you like to defend?");
                        printField(otherPlayer, thisTurnsPlayer);
                        System.out.println("\n" + thisTurnsPlayer.getName() + " is going to attack: ");
                        for (int j = 0; j < indexOfCreaturesToAttack.size(); j++) {
                            System.out.println("\t" + (indexOfTargetOfAttack.get(j) == -1 ? "You" : thisTurnsPlayer.getPlaneswalkersNameAtIndex(indexOfTargetOfAttack.get(j) - 1)) +
                                    " with " + thisTurnsPlayer.getCreaturesByIndex(indexOfCreaturesToAttack).get(j).getName() + ";");
                        }

                        // shows a list of the other player's creatures that are able to defend
                        System.out.println("\n" + otherPlayer.getName() + ", you have the following creatures available to defend:");
                        ArrayList<String> creaturesToPossiblyDefend = otherPlayer.getAllCreaturesAbleToDefendNamesAndIndexs();
                        System.out.println("\t0 - I don't want to defend this creature or I don't want to defend this creature with anymore creatures.");
                        for (int j = 0; j < creaturesToPossiblyDefend.size(); j++) {
                            // the number of the option corresponds to the index + 1
                            System.out.println(creaturesToPossiblyDefend.get(j));
                        }

                        // asks the other player to choose one or more (or none) of his or her creatures to defend each of the attacking creatures
                        Hashtable<Integer, ArrayList<Integer>> dictionaryOfListOfDefendingCreatures = new Hashtable<>();
                        for (int j = 0; j < indexOfCreaturesToAttack.size(); j++) {
                            int indexOfCreatureToDefend = 50;
                            ArrayList<Integer> creaturesToDefend = new ArrayList<>();
                            int g = 0;
                            while (indexOfCreatureToDefend > 0) {
                                System.out.print("Choose " + (g == 0 ? "a" : "another") + " creature to defend " + thisTurnsPlayer.getCreaturesByIndex(indexOfCreaturesToAttack).get(j).getName() + ":");
                                g++;
                                indexOfCreatureToDefend = input.nextInt();
                                input.nextLine();
                                if (indexOfCreatureToDefend > 0) {
                                    creaturesToDefend.add(indexOfCreatureToDefend - 1);
                                }
                            }

                            dictionaryOfListOfDefendingCreatures.put(indexOfCreaturesToAttack.get(j), creaturesToDefend);
                        }

                        //flag for if there are creatures defending
                        int numberOfDefendingGroups = 0;

                        //creating an arrayList of the indexes of creatures that are attacking but aren't being defended
                        ArrayList<Integer> attackingCreaturesWithNoDefendingCreatures = new ArrayList<>();
                        ArrayList<Integer> attackingCreaturesWithoutDefenseTargets = new ArrayList<>();

                        // shows the current player how the other player plans on defending and lets the current player choose how the damage is going to be distributed
                        if (dictionaryOfListOfDefendingCreatures.size() > 0) {
                            System.out.println("\n" + otherPlayer.getName() + " wants to defend in the following way:");
                            for (int j = 0; j < indexOfCreaturesToAttack.size(); j++) {
                                if (dictionaryOfListOfDefendingCreatures.get(j).size() > 1) {
                                    String defenders = "";
                                    for (int g = 0; g < dictionaryOfListOfDefendingCreatures.get(indexOfCreaturesToAttack.get(j)).size(); g++) {
                                        if (g == dictionaryOfListOfDefendingCreatures.get(j).size() - 1) {
                                            defenders += "and " + creaturesToPossiblyDefend.get(dictionaryOfListOfDefendingCreatures.get(indexOfCreaturesToAttack.get(j)).get(g)).split(" - ")[1].split(";")[0];
                                        } else {
                                            defenders += creaturesToPossiblyDefend.get(dictionaryOfListOfDefendingCreatures.get(indexOfCreaturesToAttack.get(j)).get(g)).split(" - ")[1].split(";")[0] + ", ";
                                        }
                                    }
                                    System.out.println("\t" + otherPlayer.getName() + " wants to defend " +
                                            thisTurnsPlayer.getCreaturesByIndex(indexOfCreaturesToAttack).get(j).getName() + " with " +
                                            defenders);
                                    numberOfDefendingGroups++;
                                } else if (dictionaryOfListOfDefendingCreatures.get(j).size() == 1) {
                                    System.out.println("\t" + otherPlayer.getName() + " wants to defend " +
                                            thisTurnsPlayer.getCreaturesByIndex(indexOfCreaturesToAttack).get(j).getName() + " with " +
                                            creaturesToPossiblyDefend.get(dictionaryOfListOfDefendingCreatures.get(j).get(0)).split(" - ")[1].split(";")[0]);
                                    numberOfDefendingGroups++;
                                } else {
                                    System.out.println("\t" + otherPlayer.getName() + " doesn't want to defend " +
                                            thisTurnsPlayer.getCreaturesByIndex(indexOfCreaturesToAttack).get(j).getName());
                                }
                            }

                        } else {
                            System.out.println("\n" + otherPlayer.getName() + " doesn't want to defend.");
                        }

                        //flag for if there are creatures defending
                        boolean areThereDefendingCreatures = numberOfDefendingGroups != 0;
                        if(areThereDefendingCreatures) {
                            //show how much attack and life each creature has before asking how to distribute the damage
                            if (dictionaryOfListOfDefendingCreatures.size() > 0) {
                                System.out.println("\n" + thisTurnsPlayer.getName() + ", how would you like to distribute the damage?");

                                //creating an arrayList of the indexes of the indexes to remove from the indexOfCreaturesToAttack
                                ArrayList<Integer> indexesOfIndexesToRemoveFromIndexOfCreaturesToAttack = new ArrayList<>();

                                //showing the attack and life of each creature
                                for (int j = 0; j < indexOfCreaturesToAttack.size(); j++) {

                                    // gets the creature that is attacking
                                    ArrayList<Integer> creatureThatIsAttackingIndex = new ArrayList<>();
                                    creatureThatIsAttackingIndex.add(indexOfCreaturesToAttack.get(j));
                                    Creature currentPlayersCreature = thisTurnsPlayer.getCreaturesByIndex(creatureThatIsAttackingIndex).get(0);

                                    String information = "Your creature, " + currentPlayersCreature.getName() + ", has " + currentPlayersCreature.getAttack() + " of power and " +
                                            currentPlayersCreature.getDefense() + " of life. ";

                                    if (dictionaryOfListOfDefendingCreatures.get(j).size() > 1) {
                                        ArrayList<Creature> creaturesThatAreDefendingIndex = otherPlayer.getCreaturesByIndex(dictionaryOfListOfDefendingCreatures.get(indexOfCreaturesToAttack.get(j)));
                                        information += "And of the creatures that are defending: ";

                                        for (int h = 0; h < creaturesThatAreDefendingIndex.size(); h++) {
                                            if (h == creaturesThatAreDefendingIndex.size() - 1) {
                                                information += " and ";
                                            }
                                            information += creaturesThatAreDefendingIndex.get(h).getName() + ", has " + creaturesThatAreDefendingIndex.get(h).getAttack() +
                                                    " of power and has " + creaturesThatAreDefendingIndex.get(h).getDefense() + " of life";
                                            if (h != creaturesThatAreDefendingIndex.size() - 1) {
                                                information += "; ";
                                            }
                                        }

                                    } else if (dictionaryOfListOfDefendingCreatures.get(j).size() == 1) {
                                        ArrayList<Creature> creaturesThatAreDefendingIndex = otherPlayer.getCreaturesByIndex(dictionaryOfListOfDefendingCreatures.get(indexOfCreaturesToAttack.get(j)));
                                        information += "And of the creatures that are defending: " + creaturesThatAreDefendingIndex.get(0).getName() + ", has " + creaturesThatAreDefendingIndex.get(0).getAttack() +
                                                " of power and has " + creaturesThatAreDefendingIndex.get(0).getDefense() + " of life";
                                    } else {

                                        //adding the indexes of the creatures that are attacking but don't have any creatures defending
                                        attackingCreaturesWithNoDefendingCreatures.add(indexOfCreaturesToAttack.get(j));

                                        //removing the key and value pair of the creatures without a creature defending them
                                        dictionaryOfListOfDefendingCreatures.remove(indexOfCreaturesToAttack.get(j));

                                        //adding the indexes of the targets of the creatures that aren't being defended
                                        attackingCreaturesWithoutDefenseTargets.add(indexOfTargetOfAttack.get(j));

                                        //adding the index of the index of indexOfCreatureToAttack lis
                                        indexesOfIndexesToRemoveFromIndexOfCreaturesToAttack.add(j);
                                    }

                                    System.out.println("\t" + information);
                                }

                                // counter for removing elements in a list by index
                                int f = 0;
                                // removing creatures that are attacking but don't have at least one creature defending them
                                for(int h = 0; h < indexesOfIndexesToRemoveFromIndexOfCreaturesToAttack.size(); h++) {
                                    indexOfCreaturesToAttack.remove(indexesOfIndexesToRemoveFromIndexOfCreaturesToAttack.get(h) - f);
                                    f++;
                                }

                                // dictionary of creatures that defend as key and the damage that they'll take on as the value
                                Hashtable<Integer, ArrayList<Integer>> damageTakenByDefendingCreature = new Hashtable<>();

                                // asking the current player how to distribute the damage
                                System.out.println();
                                for (int j = 0; j < indexOfCreaturesToAttack.size(); j++) {

                                    // gets the creature that is attacking
                                    ArrayList<Integer> creatureThatIsAttackingIndex = new ArrayList<>();
                                    creatureThatIsAttackingIndex.add(indexOfCreaturesToAttack.get(j));
                                    Creature currentPlayersCreature = thisTurnsPlayer.getCreaturesByIndex(creatureThatIsAttackingIndex).get(0);

                                    ArrayList<Integer> damageEachDefendingCreatureOfAnAttackingCreatureWillGet = new ArrayList<>();

                                    if (dictionaryOfListOfDefendingCreatures.get(indexOfCreaturesToAttack.get(j)).size() > 0) {
                                        System.out.println(currentPlayersCreature.getName() + " has " + currentPlayersCreature.getAttack() + " of damage to distribute: ");
                                        int damageToDistribute = currentPlayersCreature.getAttack();

                                        for (int h = 0; h < dictionaryOfListOfDefendingCreatures.get(indexOfCreaturesToAttack.get(j)).size(); h++) {
                                            if (damageToDistribute > 0) {
                                                System.out.print("\tHow much damage would you like to give to " + otherPlayer.getCreaturesByIndex(dictionaryOfListOfDefendingCreatures.get(indexOfCreaturesToAttack.get(j))).get(h).getName() + ":");
                                                int damageToGive = input.nextInt();
                                                input.nextLine();

                                                int damageFromOneAttack = damageToDistribute - damageToGive;
                                                while (damageFromOneAttack < 0) {
                                                    System.out.println("\tThat's more damage to give than possible. Insert a lower value.");
                                                    System.out.print("\tHow much damage would you like to give to " +
                                                            dictionaryOfListOfDefendingCreatures.get(indexOfCreaturesToAttack.get(j)).get(h) + ":");
                                                    damageToGive = input.nextInt();
                                                    input.nextLine();
                                                    damageFromOneAttack = damageToDistribute - damageToGive;
                                                }
                                                damageToDistribute -= damageToGive;
                                                damageEachDefendingCreatureOfAnAttackingCreatureWillGet.add(damageToGive);
                                            } else {
                                                damageEachDefendingCreatureOfAnAttackingCreatureWillGet.add(0);
                                            }
                                        }

                                        damageTakenByDefendingCreature.put(indexOfCreaturesToAttack.get(j), damageEachDefendingCreatureOfAnAttackingCreatureWillGet);
                                    }
                                }


                                // preparing lists for the attack
                                ArrayList<Creature> creaturesThatAreAttacking = thisTurnsPlayer.getCreaturesByIndex(indexOfCreaturesToAttack);
                                ArrayList<Creature> creaturesThatAreDefending = new ArrayList<>();
                                ArrayList<Integer> indexesOfDefendingCreatures = new ArrayList<>();

                                //performing the attack
                                for(int j = 0; j < indexOfCreaturesToAttack.size(); j++) {
                                    ArrayList<Integer> indexesOfDefendingCreaturesOfASpecificCreature = dictionaryOfListOfDefendingCreatures.get(indexOfCreaturesToAttack.get(j));
                                    ArrayList<Creature> creaturesThatAreDefendingASpecificCreature = otherPlayer.getCreaturesByIndex(indexesOfDefendingCreaturesOfASpecificCreature);

                                    creaturesThatAreAttacking.get(j).doAttack(creaturesThatAreDefendingASpecificCreature, damageTakenByDefendingCreature.get(indexOfCreaturesToAttack.get(j)));

                                    creaturesThatAreDefending.addAll(creaturesThatAreDefendingASpecificCreature);
                                    indexesOfDefendingCreatures.addAll(indexesOfDefendingCreaturesOfASpecificCreature);
                                }

                                // attacking with the creatures that don't have creatures defending
                                for(int j = 0; j < attackingCreaturesWithoutDefenseTargets.size(); j++) {
                                    if(attackingCreaturesWithoutDefenseTargets.get(j) == -1) {
                                        thisTurnsPlayer.attack(otherPlayer, attackingCreaturesWithNoDefendingCreatures.get(j));
                                    } else {
                                        thisTurnsPlayer.attack(attackingCreaturesWithoutDefenseTargets.get(j), attackingCreaturesWithNoDefendingCreatures.get(j));
                                    }
                                }

                                //tapping attacking creatures with defenders
                                thisTurnsPlayer.tapCreatures(indexOfCreaturesToAttack);

                                //removing dead attacking creatures from field
                                ArrayList<Integer> deadAttackingCreatures = new ArrayList<>();
                                for(int j = 0; j < creaturesThatAreAttacking.size(); j++) {
                                    if(creaturesThatAreAttacking.get(j).isDead()) {
                                        deadAttackingCreatures.add(indexOfCreaturesToAttack.get(j));
                                    }
                                }
                                int[] orderedIndexesOfDeadAttackingCreatures = orderList(deadAttackingCreatures);
                                for(int j = 0; j < orderedIndexesOfDeadAttackingCreatures.length; j++) {
                                    thisTurnsPlayer.moveCardFromFieldToGarbage("Creature", orderedIndexesOfDeadAttackingCreatures[j] - j);
                                }

                                //removing dead defending creatures from field
                                ArrayList<Integer> deadDefendingCreatures = new ArrayList<>();
                                for(int j = 0; j < creaturesThatAreDefending.size(); j++) {
                                    if(creaturesThatAreDefending.get(j).isDead()) {
                                        deadDefendingCreatures.add(indexesOfDefendingCreatures.get(j));
                                    }
                                }
                                int[] orderedIndexesOfDeadDefendingCreatures = orderList(deadDefendingCreatures);
                                for(int j = 0; j < orderedIndexesOfDeadDefendingCreatures.length; j++) {
                                    otherPlayer.moveCardFromFieldToGarbage("Creature", orderedIndexesOfDeadDefendingCreatures[j] - j);
                                }
                            }
                        } else {
                            //attack when there are no creatures defending
                            System.out.println();
                            for(int j = 0; j < indexOfCreaturesToAttack.size(); j++) {
                                if(indexOfTargetOfAttack.get(j) == -1) {
                                    thisTurnsPlayer.attack(otherPlayer, indexOfCreaturesToAttack.get(j));
                                } else {
                                    thisTurnsPlayer.attack(indexOfTargetOfAttack.get(j), indexOfCreaturesToAttack.get(j));
                                }
                            }
                        }
                    }
                }

                // shows to the player the final main phase menu and allows the player to choose an action
                System.out.println("\nYou are in the final main phase now.");
                System.out.println("\n" + thisTurnsPlayer.getName() + ", what would you like to do next?");
                System.out.println("\t0 - Play a card;");
                System.out.println("\t1 - Use the effect of a card;");
                System.out.println("\t2 - Show hand;");
                System.out.println("\t3 - Show field;");
                System.out.println("\t4 - End turn;");
                System.out.println("\t5 - Give up;");
                System.out.print("Your choice: ");
                int finalActionChoice = input.nextInt();
                input.nextLine();

                // while the player doesn't choose to move on to the attack phase or doesn't give up
                while (finalActionChoice != 4 && finalActionChoice != 5) {
                    switch (finalActionChoice) {
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
                            if (thisTurnsPlayer.getCardFromHand(cardChosen).getType().equals("Creature")) {
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
                            boolean isThereEnoughMana = thisTurnsPlayer.playCardFromHandToStack(cardChosen, redLandsTotap, greenLandsTotap, whiteLandsTotap, blueLandsTotap, blackLandsTotap);
                            if (isThereEnoughMana) {
                                System.out.println(thisTurnsPlayer.getName() + " played the above card:");
                                System.out.println(otherPlayer.getName() + " would you like to counter the card " + thisTurnsPlayer.getName() + " is going to play? (y/n)");
                                String counterYesOrNo = input.nextLine();
//                                if (counterYesOrNo.equals("N") || counterYesOrNo.equals("n") || counterYesOrNo.equals("No") || counterYesOrNo.equals("NO") || counterYesOrNo.equals("no")) {
                                thisTurnsPlayer.playCardFromStackToField();
                                if (itIsACreature) {
                                    thisTurnsPlayer.defineTheTurnACreatureWasPlayedOfTheLastAddedCreature(turn);
                                }
//                                }
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
                            printField(thisTurnsPlayer, otherPlayer);
                            break;
                    }

                    // shows again the final main phase menu
                    System.out.println("\n" + thisTurnsPlayer.getName() + ", what would you like to do next?");
                    System.out.println("\t0 - Play a card;");
                    System.out.println("\t1 - Use the effect of a card;");
                    System.out.println("\t2 - Show hand;");
                    System.out.println("\t3 - Show field;");
                    System.out.println("\t4 - End turn;");
                    System.out.println("\t5 - Give up;");
                    System.out.print("Your choice: ");
                    finalActionChoice = input.nextInt();
                    input.nextLine();
                }

                // if the player chooses to give up
                if (finalActionChoice == 5) {
                    endGame = true;
                    playerWhoGaveUp = thisTurnsPlayer.getName();
                    break;
                }
            }

            if (endGame) {
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
                    cardsForTheDeck.add(new Land("Mountain" + i, "Red", "", ""));
                    cardsForTheDeck.add(new Creature("JoJo" + i, "Red", "R", "", 1, 1, "Hamon Master"));
                    cardsForTheDeck.add(new Creature("JoJo"+ i, "Red", "R", "", 1, 2, "Hamon Master"));
                }
                break;
            case "Blue":
                for (int i = 0; i < 20; i++) {
                    cardsForTheDeck.add(new Land("Island"+ i, "Blue", "", ""));
                    cardsForTheDeck.add(new Creature("Dior"+ i, "Blue", "U", "", 1, 1, "Vampire"));
                    cardsForTheDeck.add(new Creature("Dior"+ i, "Blue", "U", "", 1, 2, "Vampire"));
                }
                break;
        }
        return  new Deck(cardsForTheDeck);
    }

    public static void printField(Player playerThisTurn, Player opponent) {
        System.out.println("-----------------------------------------------------------------------" + opponent.getName() + "'s FIELD----------------------------------------------------------------------------------");
        opponent.showField();
        System.out.println("-----------------------------------------------------------------------" + playerThisTurn.getName() + "'s FIELD----------------------------------------------------------------------------------");
        playerThisTurn.showField();
        System.out.println("------------------------------------------------------------------------------END FIELD---------------------------------------------------------------------------------");
    }

    public static int[] orderList(ArrayList<Integer> unorderedIndexes) {
        int[] orderedIndexes = new int[unorderedIndexes.size()];
        for(int i = 0; i < orderedIndexes.length; i++) {
            int value = 1000;
            //finds which is the biggest value
            for(int j = 0; j < unorderedIndexes.size(); j++) {
                if(unorderedIndexes.get(j) < value) {
                    value = unorderedIndexes.get(j);
                }
            }
            orderedIndexes[i] = value;
            //removes the biggest value from the unorderedIndexes list
            for(int j = 0; j < unorderedIndexes.size(); j++) {
                if(unorderedIndexes.get(j) == value) {
                    unorderedIndexes.remove(j);
                }
            }
        }
        return orderedIndexes;
    }
}

