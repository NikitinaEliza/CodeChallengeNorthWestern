package com.Game;


public class game {
    private int playerCardVal=0;

    private int dealerCardVal=0;

    private BlackjackHand dealer = new BlackjackHand();

    private BlackjackHand player = new BlackjackHand();

    private BlackjackHand splitHand = new BlackjackHand();

    public game(){

    }

    public void newHand(Deck card){

        player.addCard(card.dealCard());

        dealer.addCard(card.dealCard());

        player.addCard(card.dealCard());

        dealer.addCard(card.dealCard());

        System.out.println("player has: "+ player.getCard(0)+" and "+player.getCard(1));

        System.out.println("Dealer's up card: "+ dealer.getCard(0));
    }

    public void dealerRound(Deck card ){

        while(dealer.getBlackjackValue()<17 ){

            this.showDealerCards();

            Card oneCard=card.dealCard();
            dealer.addCard(oneCard);
            System.out.println(" dealer hit card, card is: "+ oneCard);
            System.out.println("dealer total is: "+ dealer.getBlackjackValue());

        }
    }

    public void endOfHand(){
        player.clear();

        dealer.clear();
    }

    public void showDealerCards(){
        String dealerHand;
        int i=dealer.getCardCount();

        while(i>0){

            System.out.println(" dealer now has "+ dealer.getCard(dealer.getCardCount()-i));
            i--;
        }

    }

    public int getPlayerVal(){
        return player.getBlackjackValue();
    }

    public void result(BlackjackHand player){

        if(player.getBlackjackValue()>21){
            System.out.println("player busted Dealer wins! Player vs dealer: "+ player.getBlackjackValue()+" vs "+ dealer.getBlackjackValue());

        } else if(dealer.getBlackjackValue()>21){
            System.out.println("Player wins! Player vs dealer: "+ player.getBlackjackValue()+" vs "+ dealer.getBlackjackValue());

        } else if(player.getBlackjackValue()>dealer.getBlackjackValue() ){
            System.out.println(" player wins !Player vs dealer: "+ player.getBlackjackValue()+" vs "+ dealer.getBlackjackValue());
        } else if(player.getBlackjackValue()<dealer.getBlackjackValue() ){
            System.out.println( "Dealer wins !Player vs dealer: "+ player.getBlackjackValue()+" vs "+ dealer.getBlackjackValue());
        } else if(player.getBlackjackValue()== dealer.getBlackjackValue())
            System.out.println(" it's a tie !Player vs dealer: "+ player.getBlackjackValue()+" vs "+ dealer.getBlackjackValue());
    }

    public boolean CheckdealerBJ() {

        return dealer.getBlackjackValue() == 21;
    }

    public boolean CheckplayerBJ(){
        return player.getBlackjackValue() == 21;
    }

    public BlackjackHand getPlayerHand(){
        return player;
    }

    public BlackjackHand getDealerHand(){
        return dealer;
    }

    public static int hit(BlackjackHand person, Deck card){
        int playerCardVal=0;

        Card oneCard=card.dealCard();
        person.addCard(oneCard);
        System.out.println(" player hit card, card is: "+ oneCard);
        playerCardVal = person.getBlackjackValue();

        System.out.println("player now has : "+ playerCardVal);

        return playerCardVal;
    }

    public static void stand(){

    }

    public static BlackjackHand split(BlackjackHand playerhand , Deck card){

        BlackjackHand splitHand = new BlackjackHand();

        splitHand.addCard(playerhand.getCard(1));
        splitHand.addCard(card.dealCard());
        return splitHand;

    }

    public static int Double(BlackjackHand player, Deck card){

        int playerCardVal=0;
        Card oneCard=card.dealCard();
        player.addCard(oneCard);
        System.out.println(" player hit card, card is: "+ oneCard);
        playerCardVal = player.getBlackjackValue();

        System.out.println("player now has : "+ playerCardVal);

        return playerCardVal;
    }

}
