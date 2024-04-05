package ru.nsu.yakovleva.application.menu;

/**
 * Represents the configuration settings for the game.
 */
public record Configuration(
        /**
         * The size of each cell in the game grid.
         */
        double cellSize,

        /**
         * The number of rows in the game grid.
         */
        int rowsNumber,

        /**
         * The number of columns in the game grid.
         */
        int columnsNumber,

        /**
         * The maximum score achievable in the game.
         */
        int maximumScore,

        /**
         * The number of fruits initially placed on the game grid.
         */
        int fruitsNumber,

        /**
         * The speed of the snake in the game.
         */
        int snakeSpeed) {
}
