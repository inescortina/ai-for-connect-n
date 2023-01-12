package com.thg.accelerator23.connectn.ai.ultimatewinner;

import com.thehutgroup.accelerator.connectn.player.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static java.lang.Math.random;
import static java.util.Collections.list;
//import static sun.nio.ch.DatagramChannelImpl.AbstractSelectableChannels.forEach;


public class UltimateWinner extends Player {
  public UltimateWinner(Counter counter) {
    //TODO: fill in your name here
    super(counter, UltimateWinner.class.getName());
  }

//  public int evaluateWindow (Counter[] window, Counter counter) {
//    int score = 0;
//    Counter other = counter.getOther();
//    int playerCount = 0;
//    int otherCount = 0;
//    int emptyCount = 0;
//    for (Counter current : window){
//      if (current.getStringRepresentation() == counter.getStringRepresentation()){
//        playerCount++;
//      } else if (current.getStringRepresentation() == other.getStringRepresentation()) {
//        otherCount++;
//      } else {
//        emptyCount++;
//      }
//    }
//    if (playerCount == 4) {
//      score += 100;
//    } else if (playerCount == 3 && emptyCount == 1) {
//      score += 5;
//    } else if (playerCount == 2 && emptyCount == 2) {
//      score += 2;
//    }if (otherCount == 3 && emptyCount == 1) {
//      score -= 4;
//    }
//    return score;
//  }

public int evaluateWindow (Counter[] window, Counter counter) {
    int score = 0;
    Counter other = counter.getOther();
    int playerCount = 0;
    int otherCount = 0;
    int emptyCount = 0;
    for (Counter current : window){
      if (current != null) {
        if (current.getStringRepresentation() == counter.getStringRepresentation()){
          playerCount++;
        } else if (current.getStringRepresentation() == other.getStringRepresentation()) {
        otherCount++;
        }} else {
        emptyCount++;
      }
    }
    if (playerCount == 4) {
      score += 100;
    } else if (playerCount == 3 && emptyCount == 1) {
      score += 5;
    } else if (playerCount == 2 && emptyCount == 2) {
      score += 2;
    }if (otherCount == 3 && emptyCount == 1) {
      score -= 4;
    }
    return score;
  }


  //minute 15
  public int scorePosition (Board board, Counter counter) {
    int score = 0;

    // Center
    int centerPosition = board.getConfig().getWidth()/2;
    ArrayList<Counter> centerArray = new ArrayList<>();
    for (int n = 0; n < board.getConfig().getHeight(); n++) {
      centerArray.add(board.getCounterAtPosition(new Position(centerPosition, n)));
    }
    int centerCount = Collections.frequency(centerArray, counter);
    score += centerCount * 3;


    // Horizontal
    for (int r = 0; r < board.getConfig().getHeight(); r++) {
      for (int c = 0; c < board.getConfig().getWidth() - 3; c++) {
        Counter [] window = new Counter [4];
        for (int i = 0; i < window.length; i++) {
          window [i] = board.getCounterAtPosition(new Position(c+i, r));
        }
        score += evaluateWindow(window, counter);
//        Counter [] window2 = {
//                board.getCounterAtPosition(new Position(c, r)),
//                board.getCounterAtPosition(new Position(c+1, r)),
//                board.getCounterAtPosition(new Position(c+2, r)),
//                board.getCounterAtPosition(new Position(c+3, r))
        //};
      }
    }
    // Vertical
    for (int c = 0; c < board.getConfig().getWidth(); c++) {
      for (int r = 0; r < board.getConfig().getHeight() - 3; r++) {
        Counter[] window = new Counter[4];
        for (int i = 0; i < window.length; i++) {
          window[i] = board.getCounterAtPosition(new Position(c, r + i));
        }
        score += evaluateWindow(window, counter);
      }
    }

    // Diagonal /
    for (int c = 0; c < board.getConfig().getWidth() - 3; c++) {
      for (int r = 0; r < board.getConfig().getHeight() - 3; r++) {
        Counter[] window = new Counter[4];
        for (int i = 0; i < window.length; i++) {
          window[i] = board.getCounterAtPosition(new Position(c + i, r + i));
        }
        score += evaluateWindow(window, counter);
      }
    }

    // Opposite Diagonal \
    for (int c = 0; c < board.getConfig().getWidth() - 3; c++) {
      for (int r = 0; r < board.getConfig().getHeight() - 3; r++) {
        Counter[] window = new Counter[4];
        for (int i = 0; i < window.length; i++) {
          window[i] = board.getCounterAtPosition(new Position(c + 3 - i, r + i));
        }
        score += evaluateWindow(window, counter);
      }
    }
    return score;
  }

  @Override
  public int makeMove(Board board) {
    int score = scorePosition(board, Counter.O);
    System.out.println(score);
    return 0;
  }

    //TODO: some crazy analysis
    //TODO: make sure said analysis uses less than 2G of heap and returns within 10 seconds on whichever machine is running it
//    minimax(board, depth, whichTurn)
//      if depth is 0 or the game is over:
//        return None, score
//
//      if it is your turn
//            for ( i<width){
//              if(isvalid){
//                1. make a copy
//                2. place a counter
//                3. call minimax() as opponent side
//                4. if the score is value






}
