package mtg.mendonca;


import javax.sound.midi.Soundbank;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        System.out.println("\n================================================================================================================");
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
            for(int i = 0; i < players.length; i++) {

                Player thisTurnsPlayer = players[i];
                System.out.println("\n================================================================================================================");
                System.out.println(players[i].getName() + ", your turn started now.");
                turn++;

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
                if(turn != 1) {
                    thisTurnsPlayer.drawCards(1);
                    thisTurnsPlayer.showHand();
                }

                System.out.println("Would you like to play a land? (y/n)");
                String landYesOrNo = input.nextLine();
                if(landYesOrNo.equals("y") || landYesOrNo.equals("Y") || landYesOrNo.equals("Yes") || landYesOrNo.equals("YES") || landYesOrNo.equals("yes")) {
                    thisTurnsPlayer.namesOfLandsInHand();
                    System.out.print("Choose a land to play: ");
                    int landChosen = input.nextInt();
                    input.nextLine();

                    thisTurnsPlayer.moveCardFromHandToStack(landChosen);
                    System.out.println((i == 0 ? players[1].getName() : players[0].getName()) + " would you like to counter? (y/n)" );
                    String counterYesOrNo = input.nextLine();  //needs completion
                    if(counterYesOrNo.equals("N") || counterYesOrNo.equals("n") || counterYesOrNo.equals("No") || counterYesOrNo.equals("NO") || counterYesOrNo.equals("no")) {
                        thisTurnsPlayer.playCardFromStackToField();
                    }
                }

                System.out.println(thisTurnsPlayer.getName() + ", what would you like to do next?");
                System.out.println("\t0 - Play a card;");
                System.out.println("\t1 - Use the effect of a card;");
                System.out.println("\t2 - Show hand;");
                System.out.println("\t3 - Show field;");
                System.out.println("\t4 - Move to the attack phase;");
                System.out.println("\t5 - Give up;");
                System.out.print("Your choice: ");
                int actionChoice = input.nextInt();
                input.nextLine();

                while(actionChoice != 4 && actionChoice != 5) {
                    switch(actionChoice) {
                        case 0:
                            thisTurnsPlayer.namesOfCardsInHand();
                            System.out.print("Choose a card: ");
                            int cardChosen = input.nextInt();
                            input.nextLine();

                            thisTurnsPlayer.howToPayManaOfCard(thisTurnsPlayer.getCardFromHand(cardChosen));
                            thisTurnsPlayer.showUntappedLandsPerColor();

                            System.out.println("Please choose how many lands you would like to tap to play " + thisTurnsPlayer.getCardFromHand(cardChosen).getName());
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

                            boolean isThereEnoughMana = thisTurnsPlayer.playCardFromHandToStack(cardChosen, redLandsTotap, greenLandsTotap, whiteLandsTotap, blueLandsTotap, blackLandsTotap);
                            if(isThereEnoughMana) {
                                System.out.println((i == 0 ? players[1].getName() : players[0].getName()) + " would you like to counter? (y/n)");
                                String counterYesOrNo = input.nextLine();
                                if (counterYesOrNo.equals("N") || counterYesOrNo.equals("n") || counterYesOrNo.equals("No") || counterYesOrNo.equals("NO") || counterYesOrNo.equals("no")) {
                                    thisTurnsPlayer.playCardFromStackToField();
                                }
                            }
                            break;
                    }
                }
            }
            break;
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
}

