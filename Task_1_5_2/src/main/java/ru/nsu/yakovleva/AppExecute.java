package ru.nsu.yakovleva;

/**
 * AppExecute is a class for starting an application.
 */
public class AppExecute {
  /**
   * Get arguments from command line.
   *
   * @param args - arguments from command line.
   */
  public static void main(String[] args) {
    NotebookApp application = new NotebookApp();
    application.run(args);
  }
}
