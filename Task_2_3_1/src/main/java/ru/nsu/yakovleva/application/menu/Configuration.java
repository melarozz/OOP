package ru.nsu.yakovleva.application.menu;

/**
 * Represents the configuration settings for the game.
 */
public record Configuration(
        double cellSize,

        int rowsNumber,

        int columnsNumber,

        int maximumScore,

        int fruitsNumber,

        int snakeSpeed) {
}
