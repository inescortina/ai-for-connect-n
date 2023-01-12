package com.thg.accelerator23.connectn.ai.ultimatewinner;

import com.thehutgroup.accelerator.connectn.player.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;


public class UltimateWinner extends Player {
  public UltimateWinner(Counter counter) {
    //TODO: fill in your name here
    super(counter, UltimateWinner.class.getName());
  }

  public int scorePosition (Board board, Counter counter) {
    counter = this.getCounter();
    System.out.println(counter);
    int score = 0;
    int centerPosition = board.getConfig().getWidth()/2 - 1;
    System.out.println(centerPosition);
    ArrayList<Counter> centerArray = new ArrayList<>();
    for (int n=0; n < board.getConfig().getHeight(); n++) {
      centerArray.add(board.getCounterAtPosition(new Position(centerPosition, n)));
    }
    int centerCount = Collections.frequency(centerArray, counter);
    System.out.println(centerCount);
    score += centerCount * 3;
    return score;

  }

//  public MinimaxReturn minimax(Board board, int depth, boolean maximizingPlayer) {
//    BoardAnalyser boardAnalyser = new BoardAnalyser(board.getConfig());
//    GameState gameState = boardAnalyser.calculateGameState(board);
//    if (depth == 0 || gameState.isEnd()) {
//      if (gameState.isEnd()){
//        if (winningMove(board, "X")) {
//          return new MinimaxReturn( -1,1000000000);
//        } else if (winningMove(board, "O")) {
//          return new MinimaxReturn( -1,-1000000000);
//        } else {
//          return new MinimaxReturn(-1, 0);
//        }
//      } else {
//        return new MinimaxReturn(-1, scorePosition());
//      }
//    }
//    if (maximizingPlayer) {
//      int value = 1000000000;
//      int column = 0;
//      for (int i=0; i < board.getConfig().getWidth(); i++) {
//        if (!board.hasCounterAtPosition(new Position(i, board.getConfig().getHeight()-1))){
//          Board boardCopy = new Board(board, i, Counter);
//          int newScore = minimax(boardCopy, depth-1, true).getValue();
//          if (newScore > value) {
//            value = newScore;
//            column = i;
//          }
//        };
//      } return new MinimaxReturn(column, value);
//    } else {
//      int value = -1000000000;
//      int column = 0;
//      for (int i=0; i < board.getConfig().getWidth(); i++) {
//        if (!board.hasCounterAtPosition(new Position(i, board.getConfig().getHeight()-1))){
//          Board boardCopy = new Board(board, i, Counter);
//          int newScore = minimax(boardCopy, depth-1, false).getValue();
//          if (newScore < value) {
//            value = newScore;
//            column = i;
//          }
//        };
//      } return new MinimaxReturn(column, value);
//    }
//
//  }

  @Override
  public int makeMove(Board board) {
    //TODO: some crazy analysis
    //TODO: make sure said analysis uses less than 2G of heap and returns within 10 seconds on whichever machine is running it

    int score = scorePosition(board, Counter.O);
    System.out.println(score);

    Random rand = new Random();
    int upperbound = 10;
    int randomColumn = rand.nextInt(upperbound);
    if (!board.hasCounterAtPosition(new Position(4, board.getConfig().getHeight()-1))){
      return 4;
    } else {
      return randomColumn;
    }
  }
}
