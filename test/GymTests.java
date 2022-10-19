import main.Member;
import main.MemberNotFoundException;
import main.Gym;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;


public class GymTests {
  private String membersFile = "test/TestMembers.txt";
  private String checkInFile = "test/CheckInFile.txt";

  @BeforeEach
  public void beforeEach(){
    File file = new File(checkInFile);
    if(file.exists()){
      file.delete();
    }
  }

  @Test
  public void constructor_whenMembersFileNotFound_shouldThrowFileNotFoundException() throws FileNotFoundException {
    //arrange,act & assert
    assertThrows(FileNotFoundException.class, () -> new Gym("notMembersFile.txt",checkInFile));
  }

  @Test
  public void getAllMembers_whenMembersFileFound_shouldReturnListOfAllMembers() throws IOException {
    //arrange
    var Gym = new Gym(membersFile,checkInFile);

    //act
    var result = Gym.getAllMembers();

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
    var gym = new Gym(membersFile,checkInFile);

    //act
    Member result = gym.findMember("jan-OLov EricsSon");

    //assert
    assertEquals(result.getName(), "Jan-Olov Ericsson");
    assertEquals(result.getPersonalIdentityNumber(), "8402178316");
    assertEquals(result.getLastPaymentDate().toString(), "2016-12-16");
  }

  @Test
  public void findMember_whenSearchingWithNoMatch_shouldThrowMemberNotFoundException() throws Exception {
    //arrange
    var gym = new Gym(membersFile,checkInFile);

    //act & assert
    assertThrows(MemberNotFoundException.class,()-> gym.findMember("No Match"));
  }

  @Test
  public void findMember_whenSearchingForPersonalIdentityNumberAndMemberExists_shouldReturnMember() throws Exception {
    //arrange
    Gym gym = new Gym(membersFile,checkInFile);

    //act
    Member result = gym.findMember("8402178316");

    //assert
    assertEquals(result.getName(), "Jan-Olov Ericsson");
    assertEquals(result.getPersonalIdentityNumber(), "8402178316");
    assertEquals(result.getLastPaymentDate().toString(), "2016-12-16");
  }

  @Test
  public void constructor_whenNoCheckInFileExists_shouldCreateFile() throws Exception{
    //arrange
    File file = new File(checkInFile);
    assertFalse(file.exists());

    //act
    new Gym(membersFile,checkInFile);

    //assert
    assertTrue(file.exists());
  }
}
