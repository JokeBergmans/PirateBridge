package domain;

public class Player {

    private final String name;
    private int score;
    private int guess;
    private int won;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.guess = 0;
        this.won = 0;
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

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public void changeScore(int points) {
        this.score += points;
    }

    public boolean isGuessCorrect() {
        return this.guess == this.won;
    }
}
