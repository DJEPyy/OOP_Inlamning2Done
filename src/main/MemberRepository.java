package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberRepository {
  private List<Member> allMembers;

  public MemberRepository(String pathname) throws FileNotFoundException {
    loadAllMembers(pathname);
  }

  private void loadAllMembers(String pathname) throws FileNotFoundException {
    var file = new File(pathname);
    var fileReader = new Scanner(file);
    List<Member> members = new ArrayList<>();
    while (fileReader.hasNextLine()) {
      var firstRow = fileReader.nextLine();
      if (fileReader.hasNextLine()) {
        var splitFirstRow = firstRow.split(", ");
        var personalIdentityNumber = splitFirstRow[0];
        var name = splitFirstRow[1];
        var secondRow = fileReader.nextLine();
        LocalDate lastPayment = LocalDate.parse(secondRow);
        Member member = new Member(name, personalIdentityNumber, lastPayment);
        members.add(member);
      }
    }
    allMembers = members;
  }

  public List<Member> getAllMembers() {
    return allMembers;
  }

  public Member findMember(String nameOrId) throws MemberNotFoundException {
    return allMembers.stream()
        .filter(member -> member.getName().equalsIgnoreCase(nameOrId) || member.getPersonalIdentityNumber().equals(nameOrId))
        .findFirst()
        .orElseThrow(()-> new MemberNotFoundException(nameOrId));
  }
}
