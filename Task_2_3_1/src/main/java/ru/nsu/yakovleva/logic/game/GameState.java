package ru.nsu.yakovleva.logic.game;

/**
 * Enum for state of the game.
 */
public enum GameState {
    DEFEAT("GAME OVER"),
    VICTORY("YOU WIN"),
    PLAY("SNAKE GAME");

    private final String state;

    GameState(String state) {
        this.state = state;
    }

    /**
     * Return the state of a game in a string value.
     * @return state of a game in a string value.
     */
    @Override
    public String toString() {
        return state;
    }
}
