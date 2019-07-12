package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DomainController {

    private Game game;

    public DomainController() {
        this.game = new Game();
    }

    public void addPlayers(List<String> names) {
        names.forEach(n -> game.addPlayer(n));
    }

    public int getRound() {
        return this.game.getRound();
    }

    public List<String> getPlayers() {
        List<String> players = new ArrayList<>();
        game.getPlayers().forEach(p -> players.add(p.getName()));
        return players;
    }

    public void addGuesses(List<Integer> guesses) {
        this.game.addGuesses(guesses);
    }

    public void addResults(List<Integer> results) {
        game.addResults(results);
    }

    public List<String> getCorrectPlayers() {
        List<String> players = new ArrayList<>();
        game.getCorrectPlayers().forEach(p -> players.add(p.getName()));
        return players;
    }

    public void calculateScores() {
        game.calculateScores();
    }

    public void addBonusPoints(String player) {
        game.addBonusPoints(player);
    }

    public void addBonusPoints(String player, Integer pirates) {
        game.addBonusPoints(player, pirates);
    }

    public Map<String, Integer> getScores() {
        return game.getScores();
    }

    public void startNewRound() {
        game.startNewRound();
    }

    public String getWinner() {
        return game.getWinner().getName();
    }
}
