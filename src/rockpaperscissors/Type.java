
package rockpaperscissors;

/**
 * A helper class for the Rock, Paper, Scissors game.
 * Provides the three types of moves and for each type
 * there is another type that beats it.
 * @author Sebastian
 */
public enum Type {
  
  ROCK {
    @Override public boolean beats(Type otherType) {
      return otherType == SCISSORS;
    }
  },
  PAPER {
    @Override public boolean beats(Type otherType) {
      return otherType == ROCK;
    }
  },
  SCISSORS {
    @Override public boolean beats(Type otherType) {
      return otherType == PAPER;
    }
  };
  
  private int count;
  private Type() {
    count = 0;
  }
  
  int currentCount() {
    return count;
  }
  
  public static Type parseType(String value) {
    value = value.toUpperCase();
    if (value.length() > 1) {
      System.out.println("Please enter only a single character.");
      return null;
    }
    if (value.charAt(0) == 'R') {
      return ROCK;
    } else if (value.charAt(0) == 'P') {
      return PAPER;
    } else if (value.charAt(0) == 'S') {
      return SCISSORS;
    } else {
      System.out.println("Invalid input. Please try again.");
      return null;
    }
  }
  
  public abstract boolean beats(Type otherType);
}