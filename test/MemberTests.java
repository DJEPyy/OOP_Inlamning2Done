import main.Member;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class MemberTests {

  @Test
  public void hasPaidYearlyMembershipFeeForDate_whenLastPaymentDateIsOneYearAgo_shouldReturnFalse() {
    //arrange
    Member member = new Member("Jepz", "1234567891", LocalDate.now().minusYears(1));

    //act
    boolean result = member.isActive();

    //assert
    assertFalse(result);
  }

  @Test
  public void hasPaidYearlyMembershipFeeForDate_whenLastPaymentDateIsLessThanOneYearAgo_shouldReturnTrue() {
    //arrange
    Member member = new Member("Jepz", "1234567891", LocalDate.now().minusMonths(1));

    //act
    boolean result = member.isActive();

    //assert
    assertTrue(result);
  }

  @Test
  public void hasPaidYearlyMembershipFeeForDate_whenLastPaymentDateIsAlmostOneYearAgo_shouldReturnTrue() {
    //arrange
    Member member = new Member(
        "Jepz",
        "1234567891",
        LocalDate.now()
            .minusYears(1)
            .plusDays(1));

    //act
    boolean result = member.isActive();

    //assert
    assertTrue(result);
  }
}
