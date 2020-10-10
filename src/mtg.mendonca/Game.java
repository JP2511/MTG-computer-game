package mtg.mendonca;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {

    private static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException {
        System.out.println("\n##############################################################################################################################################################");
        System.out.println("Welcome to the Magic The Gathering Arena RipOff Game!");
        System.out.println("For now only two players can play.");
        System.out.println("What are your names?");

        System.out.print("Player One: ");
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
                        ArrayList<Integer> numberOfPossibleLandChoices = thisTurnsPlayer.namesOfLandsInHand();
                        System.out.print("Choose a land to play: ");
                        int landChosen = returnUserInputIfValid(numberOfPossibleLandChoices, "Your choice: ");

                        System.out.println(thisTurnsPlayer.getName() + " played the following card:");
                        playCard(thisTurnsPlayer, otherPlayer, thisTurnsPlayer, landChosen, turn);
                    }
                }
                //end of playing a land

                // shows to the player the main phase menu and allows the player to choose an action
                System.out.println("\nYou are in the main phase now.");
                System.out.println("\n" + thisTurnsPlayer.getName() + ", what would you like to do next?");
                System.out.println("\t0 - Play a card;");
                System.out.println("\t1 - Use the effect of a card;");
                System.out.println("\t2 - Show hand;");
                System.out.println("\t3 - Show field;");
                System.out.println("\t4 - Move to the attack phase;");
                System.out.println("\t5 - Show graveyard;");
                System.out.println("\t6 - Give up;");
                System.out.print("Your choice: ");
                int actionChoice = returnUserInputIfValid(createSequentialArray(6), "Your choice: ");

                // while the player doesn't choose to move on to the attack phase or doesn't give up
                while (actionChoice != 4 && actionChoice != 6) {
                    switch (actionChoice) {
                        /*the player chooses to play a card, so the names of the non-land cards appear and ask him to choose one of the cards to play
                        then asks the other player if they would like to counter the card being played*/
                        case 0:
                            if(thisTurnsPlayer.areThereNonLandsInHand()) {
                                System.out.println("\nChoose a card: ");
                                ArrayList<String> namesAndIndexesOfCardChoices = thisTurnsPlayer.namesOfCardsInHand();
                                System.out.print("Your choice: ");
                                int cardChosen = returnUserInputIfValid(createAnArrayListOfChoices(namesAndIndexesOfCardChoices, false), "Your choice: ");
                                playCard(thisTurnsPlayer, otherPlayer, thisTurnsPlayer, cardChosen, turn);

                            } else {
                                System.out.println("\nYou don't have any cards to play.");
                            }
                            break;

                        /*the player chooses to use the effect of a card, but it is still work in progress*/
                        case 1:
                            useEffectOfCard(thisTurnsPlayer);
                            break;

                        /*the player chooses to see his or her own hand*/
                        case 2:
                            thisTurnsPlayer.showHand();
                            break;

                        /*the player chooses to see the field, with the other players field at the top and the current player's field at the bottom*/
                        case 3:
                            printField(thisTurnsPlayer, otherPlayer);
                            break;
                        case 5:
                            thisTurnsPlayer.showGraveyard();
                            break;
                    }

                    // shows again the main phase menu
                    System.out.println("\n" + thisTurnsPlayer.getName() + ", what would you like to do next?");
                    System.out.println("\t0 - Play a card;");
                    System.out.println("\t1 - Use the effect of a card;");
                    System.out.println("\t2 - Show hand;");
                    System.out.println("\t3 - Show field;");
                    System.out.println("\t4 - Move to the attack phase;");
                    System.out.println("\t5 - Show graveyard;");
                    System.out.println("\t6 - Give up;");
                    System.out.print("Your choice: ");
                    actionChoice = returnUserInputIfValid(createSequentialArray(6), "Your choice: ");
                }

                // if the player chooses to give up
                if (actionChoice == 6) {
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
                int attackOrNo = returnUserInputIfValid(createSequentialArray(1), "Your choice: ");

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
                        for (String s : creaturesToUseToAttack) {
                            System.out.println(s);
                        }

                        ArrayList<Integer> indexOfTargetOfAttack = new ArrayList<>();

                        // if the other player has one or more planeswalkers, it shows a list of all planeswalker + the other players name as targets for the attack
                        if (otherPlayer.isThereAPlaneswalker()) {
                            System.out.println("You can choose one of the following, for each creature, as a target for the attack of that creature:");
                            System.out.println("\t0 - Attack " + otherPlayer.getName() + ";");

                            // array used to confirm if the choice of target of the attacking creature is one that is possible
                            ArrayList<String> targetOptionsInStrings = new ArrayList<>();

                            for (int j = 0; j < otherPlayer.getPlaneswalker().size(); i++) {
                                System.out.println("\t" + (j + 1) + " - Attack " + otherPlayer.getPlaneswalker().get(j) + ";");
                                targetOptionsInStrings.add("\t" + (j + 1) + " - Attack " + otherPlayer.getPlaneswalker().get(j) + ";");
                            }

                            // while player doesn't choose to not choose anymore creatures, it asks the player to choose a creature and a target for its attack
                            System.out.print("Choose a card to attack with: ");
                            int indexOfCreatureToAttackWith = returnUserInputIfValid(createAnArrayListOfChoices(creaturesToUseToAttack, true), "Choose a card to attack with: ");

                            // asking the user to choose a target for the attacking creature just chosen
                            while (indexOfCreatureToAttackWith != 0) {
                                indexOfCreaturesToAttack.add(indexOfCreatureToAttackWith - 1);
                                System.out.print("Choose a target for the creature you just chose: ");
                                int indexOfTargetToAttackOfCreature = returnUserInputIfValid(createAnArrayListOfChoices(targetOptionsInStrings, true), "Choose a target for the creature you just chose: ");

                                // if indexOfTargetToAttackOfCreature == -1, then the target is the player
                                indexOfTargetOfAttack.add(indexOfTargetToAttackOfCreature - 1);

                                System.out.print("Choose a card to attack with: ");
                                indexOfCreatureToAttackWith = returnUserInputIfValid(createAnArrayListOfChoices(creaturesToUseToAttack, true), "Choose a card to attack with: ");
                            }
                        } else {
                            System.out.print("Choose a card to attack with: ");
                            int indexOfCreatureToAttackWith = returnUserInputIfValid(createAnArrayListOfChoices(creaturesToUseToAttack, true), "Choose a card to attack with: ");

                            //keep choosing attacking creatures unless the player chooses the 0 option
                            while (indexOfCreatureToAttackWith > 0) {
                                indexOfCreaturesToAttack.add(indexOfCreatureToAttackWith - 1);

                                // chooses the other player as the opponent
                                indexOfTargetOfAttack.add(-1);

                                System.out.print("Choose a card to attack with: ");
                                indexOfCreatureToAttackWith = returnUserInputIfValid(createAnArrayListOfChoices(creaturesToUseToAttack, true), "Choose a card to attack with: ");
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
                        ArrayList<ArrayList<String>> arrayListOfArrayListOfPossibleCreaturesToDefend = new ArrayList<>();
                        for(int j = 0; j < indexOfCreaturesToAttack.size(); j++) {
                            ArrayList<String> creaturesToPossiblyDefendEachCreature = otherPlayer.getAllCreaturesAbleToDefendNamesAndIndexs(thisTurnsPlayer.getCreaturesByIndex(indexOfCreaturesToAttack).get(j));
                            System.out.println("The following creatures are able to defend " + thisTurnsPlayer.getCreaturesByIndex(indexOfCreaturesToAttack).get(j).getName());
                            System.out.println("\t0 - I don't want to defend this creature or I don't want to defend this creature with anymore creatures.");
                            for (int h = 0; h < creaturesToPossiblyDefendEachCreature.size(); h++) {
                                // the number of the option corresponds to the index + 1
                                System.out.println(creaturesToPossiblyDefendEachCreature.get(h));
                            }
                            arrayListOfArrayListOfPossibleCreaturesToDefend.add(creaturesToPossiblyDefendEachCreature);
                        }

                        // asks the other player to choose one or more (or none) of his or her creatures to defend each of the attacking creatures
                        Hashtable<Integer, ArrayList<Integer>> dictionaryOfListOfDefendingCreatures = new Hashtable<>();
                        ArrayList<Integer> creaturesAlreadyChosenToDefend = new ArrayList<>();
                        for (int j = 0; j < indexOfCreaturesToAttack.size(); j++) {
                            int indexOfCreatureToDefend = 50;
                            ArrayList<Integer> creaturesToDefend = new ArrayList<>();
                            int g = 0;
                            while (indexOfCreatureToDefend > 0) {
                                System.out.print("Choose " + (g == 0 ? "a" : "another") + " creature to defend " + thisTurnsPlayer.getCreaturesByIndex(indexOfCreaturesToAttack).get(j).getName() + ":");
                                g++;

                                // getting the user input, making sure it is an integer
                                boolean defendingChoice = input.hasNextInt();
                                while(!defendingChoice ) {
                                    System.out.println("That's not a possible option!");
                                    System.out.print("Choose another creature to defend " + thisTurnsPlayer.getCreaturesByIndex(indexOfCreaturesToAttack).get(j).getName() + ":");
                                    input.nextLine();
                                    defendingChoice = input.hasNextInt();
                                }
                                indexOfCreatureToDefend = input.nextInt();
                                input.nextLine();
                                while(!isItInsideArrayList(creaturesAlreadyChosenToDefend, indexOfCreatureToDefend) && !isItInsideArrayList(createAnArrayListOfChoices(creaturesToPossiblyDefend, true), indexOfCreatureToDefend) && !isItInsideArrayList(createAnArrayListOfChoices(arrayListOfArrayListOfPossibleCreaturesToDefend.get(j), true), indexOfCreatureToDefend)) {
                                    System.out.println("That's not a possible option!");
                                    System.out.print("Choose another creature to defend " + thisTurnsPlayer.getCreaturesByIndex(indexOfCreaturesToAttack).get(j).getName() + ":");
                                    defendingChoice = input.hasNextInt();
                                    while(!defendingChoice ) {
                                        input.nextLine();
                                        System.out.println("That's not a possible option!");
                                        System.out.print("Choose another creature to defend " + thisTurnsPlayer.getCreaturesByIndex(indexOfCreaturesToAttack).get(j).getName() + ":");
                                        defendingChoice = input.hasNextInt();
                                        input.nextLine();
                                    }
                                    indexOfCreatureToDefend = input.nextInt();
                                    input.nextLine();
                                }
                                //end of getting user input

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
                                                boolean damageChoice = input.hasNextInt();
                                                while(!damageChoice ) {
                                                    System.out.println("\tThat's not a possible option!");
                                                    System.out.print("\tYour choice: ");
                                                    input.nextLine();
                                                    damageChoice = input.hasNextInt();
                                                }
                                                int damageToGive = input.nextInt();
                                                input.nextLine();

                                                int damageFromOneAttack = damageToDistribute - damageToGive;
                                                while (damageFromOneAttack < 0) {
                                                    System.out.println("\tThat's more damage to give than possible. Insert a lower value.");
                                                    System.out.print("\tHow much damage would you like to give to " + dictionaryOfListOfDefendingCreatures.get(indexOfCreaturesToAttack.get(j)).get(h) + ":");
                                                    damageChoice = input.hasNextInt();
                                                    while(!damageChoice ) {
                                                        System.out.println("\tThat's not a possible option!");
                                                        System.out.print("\tYour choice: ");
                                                        input.nextLine();
                                                        damageChoice = input.hasNextInt();
                                                    }
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
                System.out.println("\t4 - Show graveyard;");
                System.out.println("\t5 - End turn;");
                System.out.println("\t6 - Give up;");
                System.out.print("Your choice: ");
                int finalActionChoice = returnUserInputIfValid(createSequentialArray(6), "Your choice: ");

                // while the player doesn't choose to move on to the attack phase or doesn't give up
                while (finalActionChoice != 5 && finalActionChoice != 6) {
                    switch (finalActionChoice) {
                        /*the player chooses to play a card, so the names of the non-land cards appear and ask him to choose one of the cards to play
                        then asks the other player if they would like to counter the card being played*/
                        case 0:
                            if(thisTurnsPlayer.areThereNonLandsInHand()) {
                                System.out.println("\nChoose a card: ");
                                ArrayList<String> namesAndIndexesOfCardChoices = thisTurnsPlayer.namesOfCardsInHand();
                                System.out.print("Your choice: ");
                                int cardChosen = returnUserInputIfValid(createAnArrayListOfChoices(namesAndIndexesOfCardChoices, false), "Your choice: ");
                                playCard(thisTurnsPlayer, otherPlayer, thisTurnsPlayer, cardChosen, turn);

                            } else {
                                System.out.println("You don't have any cards to play.");
                            }
                            break;

                        /*the player chooses to use the effect of a card, but it is still work in progress*/
                        case 1:
                            useEffectOfCard(thisTurnsPlayer);
                            break;

                        /*the player chooses to see his or her own hand*/
                        case 2:
                            thisTurnsPlayer.showHand();
                            break;

                        /*the player chooses to see the field, with the other players field at the top and the current player's field at the bottom*/
                        case 3:
                            printField(thisTurnsPlayer, otherPlayer);
                            break;
                        case 4:
                            thisTurnsPlayer.showGraveyard();
                            break;
                    }

                    // shows again the final main phase menu
                    System.out.println("\n" + thisTurnsPlayer.getName() + ", what would you like to do next?");
                    System.out.println("\t0 - Play a card;");
                    System.out.println("\t1 - Use the effect of a card;");
                    System.out.println("\t2 - Show hand;");
                    System.out.println("\t3 - Show field;");
                    System.out.println("\t4 - Show graveyard;");
                    System.out.println("\t5 - End turn;");
                    System.out.println("\t6 - Give up;");
                    System.out.print("Your choice: ");
                    finalActionChoice = returnUserInputIfValid(createSequentialArray(6), "Your choice: ");
                }

                // if the player chooses to give up
                if (finalActionChoice == 6) {
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

        TimeUnit.SECONDS.sleep(3);
    }

    public static Deck createDeck(String color) {
        ArrayList<Card> cardsForTheDeck = new ArrayList<>();
        switch (color) {
            case "Red":
                for (int i = 0; i < 10; i++) {
                    cardsForTheDeck.add(new Land("Mountain A" + i, "Red", "", ""));
                    cardsForTheDeck.add(new Creature("JoJo A" + i, "Red", "R", "Haste. Double Strike.", 1, 1, "Hamon Master"));
                    cardsForTheDeck.add(new Creature("JoJo B"+ i, "Red", "R", "Haste. First Strike..", 1, 1, "Hamon Master"));
                    cardsForTheDeck.add(new Land("Mountain B" + i, "Red", "", ""));
                    cardsForTheDeck.add(new Instant("Sike, you thought" + i, "Red", "R", "Counter target spell. Cycling [R/U]."));
                    cardsForTheDeck.add(new Instant("Sike, you thought" + i, "Red", "R", "Counter target spell. Cycling [R/U]."));
                }
                break;
            case "Blue":
                for (int i = 0; i < 10; i++) {
                    cardsForTheDeck.add(new Land("Island A"+ i, "Blue", "", ""));
                    cardsForTheDeck.add(new Creature("Dior A"+ i, "Blue", "U", "Flying. Flash. Counter target spell.", 1, 1, "Vampire"));
                    cardsForTheDeck.add(new Creature("Dior B"+ i, "Blue", "U", "Reach.", 1, 2, "Vampire"));
                    cardsForTheDeck.add(new Land("Island B"+ i, "Blue", "", ""));
                    cardsForTheDeck.add(new Creature("Dior A"+ i, "Blue", "U", "Deathtouch. Cycling UU", 1, 1, "Vampire"));
                    cardsForTheDeck.add(new Creature("Dior A"+ i, "Blue", "U", "Vigilance", 1, 1, "Vampire"));
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
            for (Integer unorderedIndex : unorderedIndexes) {
                if (unorderedIndex < value) {
                    value = unorderedIndex;
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

    public static ArrayList<Integer> createSequentialArray(int highestnumber) {
        ArrayList<Integer> arrayCreated = new ArrayList<>();
        for(int i = 0; i <= highestnumber; i++) {
            arrayCreated.add(i);
        }
        return arrayCreated;
    }

    public static boolean isItInsideArrayList(ArrayList<Integer> possibleOptions, int numberToCheck) {
        for (Integer possibleOption : possibleOptions) {
            if (possibleOption == numberToCheck) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Integer> createAnArrayListOfChoices(ArrayList<String> listOfOptionsNamesAndIndexes, boolean addZero) {
        ArrayList<Integer> options = new ArrayList<>();
        if(addZero) {
            options.add(0);
        }
        for (String listOfOptionsNamesAndIndex : listOfOptionsNamesAndIndexes) {
            options.add(Integer.parseInt(listOfOptionsNamesAndIndex.split("\\t")[1].split(" - ")[0]));
        }
        return options;
    }

    public static int returnUserInputIfValid(ArrayList<Integer> options, String textAskingForInput) {
        boolean choice = input.hasNextInt();
        while(!choice) {
            System.out.println("That's not a possible option!");
            System.out.print(textAskingForInput);
            input.nextLine();
            choice = input.hasNextInt();
        }
        int optionChosen = input.nextInt();
        input.nextLine();
        while(!isItInsideArrayList(options, optionChosen)) {
            System.out.println("That's not a possible option!");
            System.out.print(textAskingForInput);
            choice = input.hasNextInt();
            while(!choice) {
                System.out.println("That's not a possible option!");
                System.out.print(textAskingForInput);
                input.nextLine();
                choice = input.hasNextInt();
            }
            optionChosen = input.nextInt();
            input.nextLine();
        }
        return optionChosen;
    }

    public static int returnUserInputCheckingOnlyIfInt() {
        boolean choice = input.hasNextInt();
        while (!choice) {
            System.out.println("That's not a possible option!");
            System.out.print("Your choice: ");
            input.nextLine();
            choice = input.hasNextInt();
        }
        int value = input.nextInt();
        input.nextLine();

        return value;
    }

    public static void playCard(Player currentPlayer, Player otherPlayer, Player playerThatPlayedTheCard, int cardChosenToPlay, int turn) {
        Card cardBeingPlayed = currentPlayer.getCardFromHand(cardChosenToPlay);
        ArrayList<Integer> cardToBePlayed = new ArrayList<>();
        cardToBePlayed.add(cardChosenToPlay);

        //if the card being played is a land, doesn't ask to pay to enter into field
        if(!cardBeingPlayed.getType().equals("Land") && currentPlayer.canPayForCards(cardToBePlayed).size() > 0) {
            boolean isThereEnoughMana = false;
            int k = 0;
            while(!isThereEnoughMana) {
                if(k != 0) {
                    System.out.println("\nThat was not the correct way of paying for the card. Try again.");
                }
                k += 1;
                currentPlayer.howToPayManaOfCard(cardBeingPlayed.getManaCost());
                currentPlayer.showUntappedLandsPerColor();

                //allows a player to choose how he or she will pay for the card being played
                System.out.println("\nPlease choose how many lands you would like to tap to play " + currentPlayer.getCardFromHand(cardChosenToPlay).getName());
                System.out.print("How many mountains (red lands) would you like to tap: ");
                int redLandsTotap = returnUserInputCheckingOnlyIfInt();

                System.out.print("How many forests (green lands) would you like to tap: ");
                int greenLandsTotap = returnUserInputCheckingOnlyIfInt();

                System.out.print("How many plains (white lands) would you like to tap: ");
                int whiteLandsTotap = returnUserInputCheckingOnlyIfInt();

                System.out.print("How many islands (blue lands) would you like to tap: ");
                int blueLandsTotap = returnUserInputCheckingOnlyIfInt();

                System.out.print("How many swamps (black lands) would you like to tap: ");
                int blackLandsTotap = returnUserInputCheckingOnlyIfInt();

                //shows the card and asks the other player if they would like to counter the card being played
                isThereEnoughMana = currentPlayer.playCardFromHandToStack(cardChosenToPlay, redLandsTotap, greenLandsTotap, whiteLandsTotap, blueLandsTotap, blackLandsTotap, cardBeingPlayed.getEffect().matches("[cC]ounter target.*spell"));
            }
            System.out.println(currentPlayer.getName() + " played the above card:");

            //check if the other player has cards to counter the card the current player is playing
            ArrayList<Integer> counterSpellsThatCanBePayed;
            if ((cardBeingPlayed.getType().equals("Instant") && cardBeingPlayed.getEffect().matches(".*[cC]ounter target.*spell.*")) || (cardBeingPlayed.getType().equals("Creature") && cardBeingPlayed.getEffect().matches(".*[cC]ounter target.*spell.*") && ((Creature) cardBeingPlayed).getFlash())) {
                counterSpellsThatCanBePayed = otherPlayer.canPayForCards(otherPlayer.checkForCounterSpell(currentPlayer.getLastCardFromCounterSpellStack()));
            } else {
                counterSpellsThatCanBePayed = otherPlayer.canPayForCards(otherPlayer.checkForCounterSpell(currentPlayer.getCardFromStack()));
            }

            //if there are counter spells to play, choose a card and ask the other player if it can be countered
            if (counterSpellsThatCanBePayed.size() > 0) {
                System.out.println(otherPlayer.getName() + " would you like to counter the card " + currentPlayer.getName() + " is going to play? (y/n)");

                String counterYesOrNo = input.nextLine();
                if (counterYesOrNo.equals("N") || counterYesOrNo.equals("n") || counterYesOrNo.equals("No") || counterYesOrNo.equals("NO") || counterYesOrNo.equals("no")) {
                    //if the amount counter spells played by the current player is equal or bigger than the opponent, play card, else move cards to graveyard
                    if (playerThatPlayedTheCard.countCounterSpellsUsed() >= (playerThatPlayedTheCard == currentPlayer ? otherPlayer : currentPlayer).countCounterSpellsUsed()) {

                        //check which player is playing the card and playing it
                        playerThatPlayedTheCard.playCardFromStackToField(turn);

                    } else {
                        playerThatPlayedTheCard.moveCardFromStackToGarbage();
                    }
                    currentPlayer.playCounterSpellFromStackToField();
                    currentPlayer.removeAllCounterSpells();
                    otherPlayer.removeAllCounterSpells();
                } else {
                    System.out.println("\nYou have the following cards to counter the card played: ");
                    for (Integer integer : counterSpellsThatCanBePayed) {
                        System.out.println("\t" + integer + " - " + otherPlayer.getCardFromHand(integer).getName());
                    }
                    System.out.print("Choose a card to play: ");
                    int counterSpellChosen = returnUserInputIfValid(counterSpellsThatCanBePayed, "Your choice: ");
                    playCard(otherPlayer, currentPlayer, playerThatPlayedTheCard, counterSpellChosen, turn);
                }
            } else {
                //if the amount counter spells played by the current player is equal or bigger than the opponent, play card, else move cards to graveyard
                if (playerThatPlayedTheCard.countCounterSpellsUsed() >= (playerThatPlayedTheCard == currentPlayer ? otherPlayer : currentPlayer).countCounterSpellsUsed()) {

                    //check which player is playing the card and playing it
                    playerThatPlayedTheCard.playCardFromStackToField(turn);

                } else {
                    playerThatPlayedTheCard.moveCardFromStackToGarbage();
                }
                currentPlayer.playCounterSpellFromStackToField();
                currentPlayer.removeAllCounterSpells();
                otherPlayer.removeAllCounterSpells();
            }
        } else if(!cardBeingPlayed.getType().equals("Land") && !(currentPlayer.canPayForCards(cardToBePlayed).size() > 0)) {
            System.out.println("You can not pay for that card");
            if((cardBeingPlayed.getType().equals("Instant") && cardBeingPlayed.getEffect().matches(".*[cC]ounter target.*spell.*")) || (cardBeingPlayed.getType().equals("Creature") && cardBeingPlayed.getEffect().matches(".*[cC]ounter target.*spell.*") && ((Creature) cardBeingPlayed).getFlash())) {
                //if the amount counter spells played by the current player is equal or bigger than the opponent, play card, else move cards to graveyard
                if (playerThatPlayedTheCard.countCounterSpellsUsed() >= (playerThatPlayedTheCard == currentPlayer ? otherPlayer : currentPlayer).countCounterSpellsUsed()) {
                    //check which player is playing the card and playing it
                    playerThatPlayedTheCard.playCardFromStackToField(turn);

                } else {
                    playerThatPlayedTheCard.moveCardFromStackToGarbage();
                }
                otherPlayer.playCounterSpellFromStackToField();
                currentPlayer.removeAllCounterSpells();
                otherPlayer.removeAllCounterSpells();
            }
        } else {
            currentPlayer.moveCardFromHandToStack(cardChosenToPlay);
            currentPlayer.playCardFromStackToField(turn);
        }
    }

    public static void payForEffectOfCard(Player currentPlayer, String mana) {
        currentPlayer.howToPayManaOfCard(mana);
        currentPlayer.showUntappedLandsPerColor();

        System.out.println("\nPlease choose how you would like to pay for the card.");
        System.out.print("How many mountains (red lands) would you like to tap: ");
        int redLandsTotap = returnUserInputCheckingOnlyIfInt();

        System.out.print("How many forests (green lands) would you like to tap: ");
        int greenLandsTotap = returnUserInputCheckingOnlyIfInt();

        System.out.print("How many plains (white lands) would you like to tap: ");
        int whiteLandsTotap = returnUserInputCheckingOnlyIfInt();

        System.out.print("How many islands (blue lands) would you like to tap: ");
        int blueLandsTotap = returnUserInputCheckingOnlyIfInt();

        System.out.print("How many swamps (black lands) would you like to tap: ");
        int blackLandsTotap = returnUserInputCheckingOnlyIfInt();

        while(!currentPlayer.canPayCard(mana, redLandsTotap, greenLandsTotap, whiteLandsTotap, blueLandsTotap, blackLandsTotap)) {
            System.out.println("\nThat is not how you pay for the effect. Please try again.");
            System.out.print("How many mountains (red lands) would you like to tap: ");
            redLandsTotap = returnUserInputCheckingOnlyIfInt();

            System.out.print("How many forests (green lands) would you like to tap: ");
            greenLandsTotap = returnUserInputCheckingOnlyIfInt();

            System.out.print("How many plains (white lands) would you like to tap: ");
            whiteLandsTotap = returnUserInputCheckingOnlyIfInt();

            System.out.print("How many islands (blue lands) would you like to tap: ");
            blueLandsTotap = returnUserInputCheckingOnlyIfInt();

            System.out.print("How many swamps (black lands) would you like to tap: ");
            blackLandsTotap = returnUserInputCheckingOnlyIfInt();
        }
        currentPlayer.tapLands(redLandsTotap, greenLandsTotap, whiteLandsTotap, blueLandsTotap, blackLandsTotap);
    }

    public static void useEffectOfCard(Player currentPlayer) {
        System.out.println("\nWould you like to use the effect of a card from:");
        System.out.println("\t0 - The hand");
        System.out.println("\t1 - The field");
        System.out.println("\t2 - The graveyard");
        int locationChoice = returnUserInputIfValid(createSequentialArray(2), "Your choice: ");

        switch(locationChoice) {
            case 0:
                ArrayList<String> cardsInHand = currentPlayer.getIndexAndNameOfAllCardsInHand();
                if(cardsInHand.size() > 0) {
                    System.out.println("\nChoose a card to use its effect: ");
                    System.out.println("\t0 - I don't want to use the effect of any of these cards.");
                    for(String cardNameAndIndex: cardsInHand) {
                        System.out.println(cardNameAndIndex);
                    }
                    int cardChosen = returnUserInputIfValid(createAnArrayListOfChoices(cardsInHand, true), "Your choice: ");
                    Card cardToUseEffects = currentPlayer.getCardFromHand(cardChosen - 1);

                    ArrayList<String> effectsPossible = cardToUseEffects.getCardsHandEffects();
                    if(effectsPossible.size() > 0) {
                        System.out.println("\nChoose an effect to use: ");
                        for (int j = 0; j < effectsPossible.size(); j++) {
                            System.out.println("\t" + j + " - " + effectsPossible.get(j));
                        }
                        int effectChoice = returnUserInputIfValid(createSequentialArray(effectsPossible.size()), "Your choice: ");

                        switch(effectsPossible.get(effectChoice)) {
                            case "Cycling":
                                String mana = currentPlayer.getCyclingPrice(cardToUseEffects);
                                if(currentPlayer.isAbleToPay(mana)) {
                                    payForEffectOfCard(currentPlayer, mana);
                                    currentPlayer.useCycling(cardChosen - 1);
                                } else {
                                    System.out.println("\nYou can't pay for the effect you chose.");
                                    break;
                                }
                                break;

                        }
                    } else {
                        System.out.println("\nThis card doesn't have any playable effects.");
                    }

                } else {
                    System.out.println("You don't have any cards in your hand.");
                }
                break;
        }
    }
}