package domain;

import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(String name) {
        this.players.add(new Player(name));
    }

    public void addGuesses(List<Integer> guesses) {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setGuess(guesses.get(i));
        }
    }
}
