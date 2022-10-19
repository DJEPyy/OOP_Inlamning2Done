package main;

import java.io.IOException;
import java.util.Scanner;

public class Program {
  public static void main(String[] args) throws IOException {
    boolean run = true;
    Gym gym = new Gym("src/main/Members.txt", "src/main/CheckInFile.txt");
    Scanner sc = new Scanner(System.in);
    while (run) {
      System.out.println("Ange namn eller personnummer(YYMMDDNNNN) på personen du vill söka på eller skriv exit för avsluta: ");
      String input = sc.nextLine();
      if (input.equalsIgnoreCase("exit")) {
        run = false;
      } else {
        try {
          Member member = gym.findMember(input);
          System.out.println(member.getInfo());
          gym.checkIn(member);
        } catch (MemberNotFoundException e) {
          System.out.println(e.getMessage());
        }
      }
    }
  }
}

