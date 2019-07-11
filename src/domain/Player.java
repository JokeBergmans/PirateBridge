package domain;

public class Player {

    private final String name;
    private int score;
    private int guess;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getGuess() {
        return guess;
    }

    public void setGuess(int guess) {
        this.guess = guess;
    }

    public void changeScore(int points) {
        this.score += points;
    }
}
