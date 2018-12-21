package kr.ac.knu.lecture.game.blackjack;

import kr.ac.knu.lecture.exception.NotEnoughBalanceException;
import lombok.Getter;

/**
 * Created by rokim on 2018. 5. 26..
 */
public class Player {
    @Getter
    private long balance;
    @Getter
    private long currentBet;
    @Getter
    private boolean isPlaying;
    @Getter
    private Hand hand;

    public Player(long seedMoney, Hand hand) {
        this.balance = seedMoney;
        this.hand = hand;

        isPlaying = false;
    }

    public void reset() {
        hand.reset();
        isPlaying = false;
    }

    public void placeBet(long bet, boolean isDoubleDown) {
        if (balance < bet) {
            //throw new NotEnoughBalanceException();
            bet = balance;
        }
        balance -= bet;
        isPlaying = true;
        if (!isDoubleDown) {
            currentBet = bet;
            return;
        }
        currentBet += bet;
    }

    public void deal() {
        hand.drawCard();
        hand.drawCard();
    }

    public void win(double rate) {
        balance += currentBet * rate;
        System.out.println("win");
    }

    public void tie() {
        balance += currentBet;
        System.out.println("tie");
    }

    public void lost() {
        System.out.println("lost");
    }

    public Card hitCard() {
        return hand.drawCard();
    }

    public void stand() {
        this.isPlaying = false;
    }

}
