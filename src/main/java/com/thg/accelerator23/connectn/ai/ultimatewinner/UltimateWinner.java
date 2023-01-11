package com.thg.accelerator23.connectn.ai.ultimatewinner;

import com.thehutgroup.accelerator.connectn.player.*;

import java.util.Random;


public class UltimateWinner extends Player {
  public UltimateWinner(Counter counter) {
    //TODO: fill in your name here
    super(counter, UltimateWinner.class.getName());
  }

  public MinimaxReturn minimax(Board board, int depth, boolean maximizingPlayer) {
    BoardAnalyser boardAnalyser = new BoardAnalyser(board.getConfig());
    GameState gameState = boardAnalyser.calculateGameState(board);
    if (depth == 0 || gameState.isEnd()) {
      if (gameState.isEnd()){
        if (winningMove(board, "X")) {
          return new MinimaxReturn( -1,1000000000);
        } else if (winningMove(board, "O")) {
          return new MinimaxReturn( -1,-1000000000);
        } else {
          return new MinimaxReturn(-1, 0);
        }
      } else {
        return new MinimaxReturn(-1, scorePosition());
      }
    }
    if (maximizingPlayer) {
      int value = 1000000000;
      Random rand = new Random();
      int upperbound = 10;
      int randomColumn = rand.nextInt(upperbound);
      int column = randomColumn;
      for (int i=0; i < board.getConfig().getWidth(); i++) {
        if (!board.hasCounterAtPosition(new Position(i, board.getConfig().getHeight()-1))){
          Board boardCopy = new Board(board, )
          placePiece
          int newScore = minimax(boardCopy, depth-1, true).getValue();
          if (newScore > value) {
            value = newScore;
            column = i;
          }
        };
      } return new MinimaxReturn(column, value);
    } else {
      int value = -1000000000;
      Random rand = new Random();
      int upperbound = 10;
      int randomColumn = rand.nextInt(upperbound);
      int column = randomColumn;
      for (int i=0; i < board.getConfig().getWidth(); i++) {
        if (!board.hasCounterAtPosition(new Position(i, board.getConfig().getHeight()-1))){
          placePiece
          int newScore = minimax(boardCopy, depth-1, false).getValue();
          if (newScore < value) {
            value = newScore;
            column = i;
          }
        };
      } return new MinimaxReturn(column, value);
    }

  }

  @Override
  public int makeMove(Board board) {
    //TODO: some crazy analysis
    //TODO: make sure said analysis uses less than 2G of heap and returns within 10 seconds on whichever machine is running it
    minimax(board, depth, whichTurn)
      if depth is 0 or the game is over:
        return None, score

      if it is your turn
            for ( i<width){
              if(isvalid){
                1. make a copy
                2. place a counter
                3. call minimax() as opponent side
                4. if the score is value
              }
            }



    Random rand = new Random();


    int upperbound = 10;
    int randomColumn = rand.nextInt(upperbound);
    System.out.println();
    if (!board.hasCounterAtPosition(new Position(4, board.getConfig().getHeight()-1))){
      return 4;
    } else {
      return randomColumn;
    }
  }
}
