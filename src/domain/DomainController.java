package domain;

import java.util.ArrayList;
import java.util.List;

public class DomainController {

    private Game game;

    public DomainController() {
        this.game = new Game();
    }

    public void addPlayers(List<String> names) {
        names.forEach(n -> this.game.addPlayer(n));
    }

    public int getRound() {
        return this.game.getRound();
    }

    public List<String> getPlayers() {
        List<String> players = new ArrayList<>();
        this.game.getPlayers().forEach(p -> players.add(p.getName()));
        return players;
    }

    public void addGuesses(List<Integer> guesses) {
        this.game.addGuesses(guesses);
    }
}
