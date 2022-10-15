import main.Member;
import main.MemberNotFoundException;
import main.MemberRepository;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;


public class MemberRepositoryTests {
  @Test
  public void constructor_whenMembersFileNotFound_shouldThrowFileNotFoundException() throws FileNotFoundException {
    //arrange,act & assert
    assertThrows(FileNotFoundException.class, () -> new MemberRepository("notMembersFile.txt"));
  }

  @Test
  public void getAllMembers_whenMembersFileFound_shouldReturnListOfAllMembers() throws FileNotFoundException {
    //arrange
    var memberRepository = new MemberRepository("test/TestMembers.txt");

    //act
    var result = memberRepository.getAllMembers();

    //assert
    assertEquals(result.size(), 2);
    var membersArray = result.toArray(new Member[0]);
    Member firstMember = membersArray[0];
    Member secondMember = membersArray[1];
    assertEquals(firstMember.getName(), "Engelbrekt Sjölin");
    assertEquals(firstMember.getPersonalIdentityNumber(), "9009094179");
    assertEquals(firstMember.getLastPaymentDate().toString(), "2022-01-30");
    assertEquals(secondMember.getName(), "Jan-Olov Ericsson");
    assertEquals(secondMember.getPersonalIdentityNumber(), "8402178316");
    assertEquals(secondMember.getLastPaymentDate().toString(), "2016-12-16");
  }

  @Test
  public void findMember_whenSearchingForNameAndMemberExists_shouldReturnMember() throws Exception {
    //arrange
    var memberRepository = new MemberRepository("test/TestMembers.txt");

    //act
    Member result = memberRepository.findMember("jan-OLov EricsSon");

    //assert
    assertEquals(result.getName(), "Jan-Olov Ericsson");
    assertEquals(result.getPersonalIdentityNumber(), "8402178316");
    assertEquals(result.getLastPaymentDate().toString(), "2016-12-16");
  }

  @Test
  public void findMember_whenSearchingWithNoMatch_shouldThrowMemberNotFoundException() throws Exception {
    //arrange
    var memberRepository = new MemberRepository("test/TestMembers.txt");

    //act & assert
    assertThrows(MemberNotFoundException.class,()-> memberRepository.findMember("No Match"));
  }

  @Test
  public void findMember_whenSearchingForPersonalIdentityNumberAndMemberExists_shouldReturnMember() throws Exception {
    //arrange
    var memberRepository = new MemberRepository("test/TestMembers.txt");

    //act
    Member result = memberRepository.findMember("8402178316");

    //assert
    assertEquals(result.getName(), "Jan-Olov Ericsson");
    assertEquals(result.getPersonalIdentityNumber(), "8402178316");
    assertEquals(result.getLastPaymentDate().toString(), "2016-12-16");
  }
}
