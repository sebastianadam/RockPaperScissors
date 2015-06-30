
package rockpaperscissors;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * A simple implementation of the Rock, Paper, Scissors game
 * @author Sebastian
 */
public class RockPaperScissors {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    String name1 = "";
    String name2 = "";
    
    Type person1 = null;
    Type person2 = null;
    
    int turns = 1;
    String message1 = "turn";
    
    Scanner scanner = new Scanner(System.in);
    
    int count = 0;
    EnumMap<Type, Integer> enumMap = new EnumMap<> (Type.class);
    
    System.out.println("Let's play Rock, Paper, Scissors!"
            + "\nPlease enter first player's name");
    name1 = scanner.next();
    System.out.println("Please enter second player's name");
    name2 = scanner.next();
    
    boolean tie = true;
    while (tie) {
      while (person1 == null) {
        System.out.println(name1 + ", please enter your move:"
                                 + " R (rock), P (paper) or S (scissors)");
        person1 = Type.parseType(scanner.next());
      }
      while (person2 == null) {
        System.out.println(name2 + ", please enter your move:"
                + " R (rock), P (paper) or S (scissors)");
        person2 = Type.parseType(scanner.next());
      }
      if (person1.equals(person2)) {
        if (enumMap.isEmpty()) {
          enumMap.put(person1, 2);
        } else {
          if (enumMap.containsKey(person1)) {
            count = enumMap.get(person1);
          }
          enumMap.put(person1, count + 2);
        }
        System.out.println("It's a tie! Let's try another turn.");
        turns ++;
        person1 = null;
        person2 = null;
        tie = true;
      } else {
        tie = false;
      }
    }
    
    // display result depending on who wins
    if (person1.beats(person2)) {
      System.out.println(name1 + " wins as " + person1
                         + " beats " + person2);
    } else {
      System.out.println(name2 + " wins as " + person2
                         + " beats " + person1);
    }
    
    // count each move
    if (!enumMap.isEmpty()) {
      if (enumMap.containsKey(person1)) {
        count = enumMap.get(person1);
        enumMap.put(person1, count + 1);
      }
      if (enumMap.containsKey(person2)) {
        count = enumMap.get(person2);
        enumMap.put(person2, count + 1);
      }
    } else {
      enumMap.put(person1, 1);
      enumMap.put(person2, 1);
    }
    
    // store the most used move(s)
    Integer mostUsed = null;
    List <String> mostUsedList = new ArrayList<>();
    for (Entry <Type, Integer> entry : enumMap.entrySet()) {
      if (mostUsed == null || mostUsed < entry.getValue()) {
        mostUsed = entry.getValue();
        mostUsedList.clear();
        mostUsedList.add(entry.getKey().toString());
      } else if (mostUsed.equals(entry.getValue())) {
        mostUsedList.add(entry.getKey().toString());
      }
    }
    
    // print statistics
    if (turns > 1) {
      message1 = "turns";
    }
    System.out.println("The game took " + turns + " " + message1);
    
    if (mostUsedList.size() > 0) {
      if (mostUsedList.size() == 1) {
        System.out.println("The most used move during the game was "
                + mostUsedList.get(0));
      } else {
        System.out.println("The two most used moves during the game were "
                + mostUsedList.get(0) + " and " + mostUsedList.get(1));
      }
    }
  }
}