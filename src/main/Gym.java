package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gym {

  //Skapar lista för att hålla medlemmar
  private List<Member> allMembers = new ArrayList<>();

  //Variabel för att hålla filepath.
  private File checkInFile;

  //Constructor med path för inläsning och utskrivningsfilen som argument.
  public Gym(String membersFilePath,String checkInFilePath) throws IOException {
    loadAllMembers(membersFilePath);
    checkInFile = loadCheckInFile(checkInFilePath);
  }

  //Laddar check-in filen om den finns, annars skapar upp en ny.
  private File loadCheckInFile(String checkInFilePath) throws IOException {
    File file = new File(checkInFilePath);
    if (!file.exists()){
      file.createNewFile();
    }
    return file;
  }

  //laddar alla medlemmar och splittar in texten från member filen till namnm,personnr, och senaste betalning.
  private void loadAllMembers(String pathname) throws FileNotFoundException {
    var file = new File(pathname);
    var fileReader = new Scanner(file);
    while (fileReader.hasNextLine()) {
      var firstRow = fileReader.nextLine();
      var splitFirstRow = firstRow.split(", ");
      var personalIdentityNumber = splitFirstRow[0];
      var name = splitFirstRow[1];
      if (fileReader.hasNextLine()) {
        var secondRow = fileReader.nextLine();
        LocalDate lastPayment = LocalDate.parse(secondRow);
        Member member = new Member(name, personalIdentityNumber, lastPayment);
        allMembers.add(member);
      }
    }
  }

  public List<Member> getAllMembers() {
    return allMembers;
  }

  // Skriver till check-in filen
  public void checkIn(Member member) throws IOException {
   LocalDate now = LocalDate.now();
   String log = now + " - " + member.getName()+ " (" + member.getPersonalIdentityNumber() + ")\n";
    FileWriter fileWriter = new FileWriter(checkInFile,true);
    fileWriter.write(log);
    fileWriter.close();
  }

  public Member findMember(String nameOrId) throws MemberNotFoundException {
    return allMembers.stream()
        .filter(member -> member.getName().equalsIgnoreCase(nameOrId.trim()) || member.getPersonalIdentityNumber().equals(nameOrId.trim()))
        .findFirst()
        .orElseThrow(() -> new MemberNotFoundException(nameOrId));
  }
}
