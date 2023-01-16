package com.thg.accelerator23.connectn.ai.ultimatewinner;

import com.thehutgroup.accelerator.connectn.player.*;

import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

import static java.lang.Math.random;
import static java.util.Collections.list;


public class UltimateWinner2 extends Player {

    public UltimateWinner2(Counter counter) {
        //TODO: fill in your name here
        super(counter, UltimateWinner.class.getName());
    }
    //  @Override
//  public int makeMove(Board board) {
//    int randomNum = 0 + (int)(random() * 9);
//    System.out.println(board.getCounterAtPosition(new Position(1, 1)));
//    //TODO: some crazy analysis
//    //TODO: make sure said analysis uses less than 2G of heap and returns within 10 seconds on whichever machine is running it
//    return randomNum;
//  }
    boolean maximizingPlayer = true;
    @Override
    public int makeMove(Board board) {
//    int chosenMove = minimaxChoice(board, 4, maximizingPlayer);
//    int randomNum = (int)(random() * 9);
//    minimaxChoice(board, 4, maximizingPlayer);
//    Counter[] counters = new Counter[4];
//    counters[0] = this.getCounter();
//    counters[1] = this.getCounter();
//    counters[2] = this.getCounter();
//    counters[3] = this.getCounter();
//    int test = evaluateWindow(counters, this.getCounter());
//    System.out.println(counters[0]);
//    System.out.println(counters[1]);
//    System.out.println(counters[2]);
//    System.out.println(counters[3]);
//    System.out.println(test);
//    int test2 = scorePosition(board, this.getCounter());
//    System.out.println(test2);
        MinimaxReturn ans = minimax(board, 3, true);
        if (!board.hasCounterAtPosition(new Position(ans.getColumn(), board.getConfig().getHeight()-1))){
            System.out.println("X's turn");
            System.out.println(ans.getValue());
            System.out.println(ans.getColumn());
            return ans.getColumn();
        } else {
            for (int i=0; i < board.getConfig().getHeight(); i++) {
                if (!board.hasCounterAtPosition(new Position(i, board.getConfig().getHeight()-1))) {
                    return i;
                }
            } return 9;
        }
    }

    public MinimaxReturn minimax(Board board, int depth, boolean maximizingPlayer) {
        BoardAnalyser boardAnalyser = new BoardAnalyser(board.getConfig());
        GameState gameState = boardAnalyser.calculateGameState(board);
        if (depth == 0 || gameState.isEnd()) {
            if (gameState.isEnd()){
                if (winningMove(board, this.getCounter())) {
                    return new MinimaxReturn( -1,1000000000);
                } else if (winningMove(board, this.getCounter().getOther())) {
                    return new MinimaxReturn( -1,-1000000000);
                } else {
                    return new MinimaxReturn(-1, 0);
                }
            } else {
                return new MinimaxReturn(-1, scorePosition(board, this.getCounter()));
            }
        }
        if (maximizingPlayer) {
            int value = -1000000000;
            int column = 0;
            for (int i=0; i < board.getConfig().getWidth(); i++) {
                if (!board.hasCounterAtPosition(new Position(i, board.getConfig().getHeight()-1))){
                    try {
                        Board boardCopy = new Board(board, i, this.getCounter());
                        int newScore = minimax(boardCopy, depth-1, false).getValue();
                        if (newScore > value) {
                            System.out.println(newScore);
                            value = newScore;
                            column = i;
                        }
                    } catch (InvalidMoveException e) {
                        System.out.println("Error occurred");
                    }
                };
            } return new MinimaxReturn(column, value);
        } else {
            int value = 1000000000;
            int column = 0;
            for (int i=0; i < board.getConfig().getWidth(); i++) {
                if (!board.hasCounterAtPosition(new Position(i, board.getConfig().getHeight()-1))){
                    try {
                        Board boardCopy = new Board(board, i, this.getCounter().getOther());
                        int newScore = minimax(boardCopy, depth - 1, true).getValue();
                        if (newScore < value) {

                            value = newScore;
                            column = i;
                        }
                    } catch (InvalidMoveException e) {
                        System.out.println("Error occurred");
                    }
                };
            } return new MinimaxReturn(column, value);
        }

    }

    public boolean winningMove(Board board, Counter counter) {
        for (int columns = 0; columns < board.getConfig().getWidth(); columns++) {
            for (int rows = 0; rows < board.getConfig().getHeight() -3; rows++) {
                if (board.getCounterAtPosition(new Position(columns, rows)) == counter && board.getCounterAtPosition(new Position(columns, rows + 1)) == counter && board.getCounterAtPosition(new Position(columns, rows + 2)) == counter && board.getCounterAtPosition(new Position(columns, rows + 3)) == counter) {

                    return true;
                }
            }
        }
        for (int columns = 0; columns < board.getConfig().getWidth() -3; columns++) {
            for (int rows = 0; rows < board.getConfig().getHeight(); rows++) {
                if (board.getCounterAtPosition(new Position(columns, rows)) == counter && board.getCounterAtPosition(new Position(columns + 1, rows)) == counter && board.getCounterAtPosition(new Position(columns + 2, rows)) == counter && board.getCounterAtPosition(new Position(columns + 3, rows)) == counter) {

                    return true;
                }
            }
        }
        for (int columns = 0; columns < board.getConfig().getWidth() -3; columns++) {
            for (int rows = 0; rows < board.getConfig().getHeight() -3; rows++) {
                if (board.getCounterAtPosition(new Position(columns, rows)) == counter && board.getCounterAtPosition(new Position(columns +1, rows +1)) == counter && board.getCounterAtPosition(new Position(columns +2, rows +2)) == counter && board.getCounterAtPosition(new Position(columns +3, rows +3)) == counter) {

                    return true;
                }
            }
        }
        for (int columns = 0; columns < board.getConfig().getWidth() -3; columns++) {
            for (int rows = 3; rows < board.getConfig().getHeight(); rows++) {
                if (board.getCounterAtPosition(new Position(columns, rows)) == counter && board.getCounterAtPosition(new Position(columns +1, rows -1)) == counter && board.getCounterAtPosition(new Position(columns +2, rows -2)) == counter && board.getCounterAtPosition(new Position(columns +3, rows -3)) == counter) {

                    return true;
                }
            }
        }
        return false;
    }

    private int minimaxChoice(Board board, int depth, boolean maximizingPlayer) {
        winningMove(board, Counter.O);
        winningMove(board, Counter.X);
        scorePosition(board, Counter.O);
        scorePosition(board, Counter.X);
        return 5;
    }

    public int evaluateWindow (Counter[] window, Counter counter) {
        int score = 0;
        Counter other = counter.getOther();
        int playerCount = 0;
        int otherCount = 0;
        int emptyCount = 0;
        for (Counter current : window){
            if (current != null) {
                if (current.getOther().getOther() == counter.getOther().getOther()) {
                    playerCount++;
                } else if (current.getOther().getOther() == other) {
                    otherCount++;
                }
            } else {
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
        } else if (otherCount == 2 && emptyCount == 2) {
            score -= 1;
        }
        return score;
    }

    //minute 15
    public int scorePosition (Board board, Counter counter) {
        int score = 0;
        int column4 = 4;
        ArrayList<Counter> column4Array = new ArrayList<>();
        for (int n=0; n < board.getConfig().getHeight(); n++) {
            column4Array.add(board.getCounterAtPosition(new Position(column4, n)));
        }
        int column4Count = Collections.frequency(column4Array, counter);
        score += column4Count * 6;

        int column5 = 5;
        ArrayList<Counter> column5Array = new ArrayList<>();
        for (int n=0; n < board.getConfig().getHeight(); n++) {
            column5Array.add(board.getCounterAtPosition(new Position(column5, n)));
        }
        int column5Count = Collections.frequency(column5Array, counter);
        score += column5Count * 6;

        int column6 = 6;
        ArrayList<Counter> column6Array = new ArrayList<>();
        for (int n=0; n < board.getConfig().getHeight(); n++) {
            column6Array.add(board.getCounterAtPosition(new Position(column6, n)));
        }
        int column6Count = Collections.frequency(column6Array, counter);
        score += column6Count * 3;

        int column3 = 3;
        ArrayList<Counter> column3Array = new ArrayList<>();
        for (int n=0; n < board.getConfig().getHeight(); n++) {
            column3Array.add(board.getCounterAtPosition(new Position(column3, n)));
        }
        int column3Count = Collections.frequency(column3Array, counter);
        score += column3Count * 3;

        // Horizontal
        for (int r = 0; r < board.getConfig().getHeight(); r++) {
            //ArrayList row = new ArrayList();
            for (int c = 0; c < board.getConfig().getWidth() - 3; c++) {
                //row.add(board.getCounterAtPosition(new Position(r, c)));
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





}
