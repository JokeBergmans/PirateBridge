package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private int round;
    private ArrayList<Player> players;

    public Game() {
        this.round = 1;
        players = new ArrayList<>();
    }

    public int getRound() {
        return round;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(String name) {
        players.add(new Player(name));
    }

    public void addGuesses(List<Integer> guesses) {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setGuess(guesses.get(i));
        }
    }

    public void addResults(List<Integer> results) {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setWon(results.get(i));
        }
    }

    public List<Player> getCorrectPlayers() {
        List<Player> players = new ArrayList<>();
        this.players.forEach(p -> {
            if (p.isGuessCorrect()) {
                players.add(p);
            }
        });
        return players;
    }

    public void calculateScores() {
        players.forEach(p -> {
            if (p.isGuessCorrect()) {
                if (p.getGuess() == 0) {
                    p.changeScore(round * 10);
                } else {
                    p.changeScore(p.getWon() * 20);
                }
            } else {
                if (p.getGuess() == 0) {
                    p.changeScore(-round * 10);
                } else {
                    p.changeScore(Math.abs(p.getGuess() - p.getWon()) * -1);
                }
            }
        });
    }

    public void addBonusPoints(String player) {
        players.forEach(p -> {
            if (p.getName().equals(player)) {
                p.changeScore(50);
            }
        });

    }

    public void addBonusPoints(String player, Integer pirates) {
        players.forEach(p -> {
            if (p.getName().equals(player)) {
                p.changeScore(30 * pirates);
            }
        });
    }

    public Map<String, Integer> getScores() {
        Map<String, Integer> scores = new HashMap<>();
        players.forEach(p -> scores.put(p.getName(), p.getScore()));
        return scores;
    }

    public void startNewRound() {
        round++;
        players.forEach(p -> {
            p.setGuess(0);
            p.setWon(0);
        });
    }

    public Player getWinner() {
        int maxScore = 0;
        Player winner = null;
        for (Player p : players) {
            if (p.getScore() > maxScore) {
                winner = p;
                maxScore = p.getScore();
            }
        }
        return winner;
    }
}
