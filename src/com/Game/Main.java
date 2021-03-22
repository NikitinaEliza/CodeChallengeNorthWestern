package com.Game;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {

        Deck card = new Deck();

        card.shuffle();

        int cardsLeft= card.cardsLeft();

        BlackjackHand player = new BlackjackHand();
        BlackjackHand splithand = new BlackjackHand();
        boolean split=false;

        BlackjackHand dealer = new BlackjackHand();

        while( cardsLeft>=15){

            game hand = new game();

            hand.newHand(card);

            player=hand.getPlayerHand();

            dealer =hand.getDealerHand();

            if(hand.CheckdealerBJ()){
                System.out.println("BOOM! Dealer has Blackjack! ");
            }
            else if(hand.CheckplayerBJ()){
                System.out.println("Player has Blackjack! ");
            }

            else{
                System.out.println("Split/1? Double /2? Hit/0? Stand/3");

                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                String st = bufferRead.readLine();
                int choice= Integer.parseInt(st);

                int playerCardVal=hand.getPlayerVal();
                switch (choice) {
                    case 0 -> {
                        String hit = "h";
                        while ((hit.equals("h") || hit.equals("H")) && playerCardVal <= 21) {
                            playerCardVal = game.hit(player, card);

                            System.out.println("value now is: " + playerCardVal);

                            if (playerCardVal <= 21) {
                                System.out.println("H/N?");
                                hit = bufferRead.readLine();
                            }
                        }
                        if (playerCardVal <= 21) {
                            hand.dealerRound(card);
                        }
                    }
                    case 1 -> {
                        split = true;
                        int handoneValue = 0;
                        int handtwoValue = 0;
                        if (player.getCard(0).getValue() == player.getCard(1).getValue() || (player.getCard(0).getValue() >= 10 && player.getCard(1).getValue() >= 10)) {
                            System.out.println("spliting cards");
                            splithand = game.split(player, card);
                            player.removeCard(1);

                            player.addCard(card.dealCard());

                            handoneValue = player.getBlackjackValue();

                            System.out.println("split hand 1: " + player.getCard(0) + " " + player.getCard(1) + " HIT?");

                            String ss = bufferRead.readLine();

                            while ((ss.equals("h") || ss.equals("H")) && handoneValue <= 21) {

                                handoneValue = game.hit(player, card);

                                System.out.println("value now is: " + handoneValue);

                                if (handoneValue <= 21) {
                                    System.out.println("H/N?");
                                    ss = bufferRead.readLine();
                                }
                            }

                            System.out.println("split hand 2: " + splithand.getCard(0) + "  " + splithand.getCard(1) + "Hit?");

                            handtwoValue = splithand.getBlackjackValue();
                            String s2 = bufferRead.readLine();

                            while ((s2.equals("h") || s2.equals("H")) && handtwoValue <= 21) {

                                handtwoValue = game.hit(splithand, card);

                                System.out.println("value now is: " + handtwoValue);

                                if (handtwoValue <= 21) {
                                    System.out.println("H/N?");

                                    s2 = bufferRead.readLine();
                                }
                            }

                            if (handoneValue <= 21 || handtwoValue <= 21) {
                                hand.dealerRound(card);
                            }

                        }
                    }
                    case 2 -> {
                        playerCardVal = game.Double(player, card);
                        if (playerCardVal <= 21) {
                            hand.dealerRound(card);
                        }
                    }
                    case 3 -> {
                        game.stand();
                        if (playerCardVal <= 21) {
                            hand.dealerRound(card);
                        }
                    }
                }

            }

            System.out.println("End of hand ,show dealers card: ");
            hand.showDealerCards();
            if(split){
                hand.result(splithand);
                hand.result(player);


                splithand.clear();
                split=false;
            }
            else
                hand.result(player);
            cardsLeft=card.cardsLeft();
            System.out.println("next hand  ");
            hand.endOfHand();
        }


    }
}
