package main;

import java.time.LocalDate;

public class Member {
  String name;
  String personalIdentityNumber;
  LocalDate lastPaymentDate;

  public Member(String name, String personalIdentityNumber, LocalDate lastPaymentDate) {
    this.name = name;
    this.personalIdentityNumber = personalIdentityNumber;
    this.lastPaymentDate = lastPaymentDate;
  }

  public String getName() {
    return name;
  }

  public boolean hasPaidYearlyMembershipFee() {
    var dateOneYearAgo = LocalDate.now().minusYears(1);
    return lastPaymentDate.isAfter(dateOneYearAgo);
  }

  public String getPersonalIdentityNumber() {
    return personalIdentityNumber;
  }

  public LocalDate getLastPaymentDate() {
    return lastPaymentDate;
  }
}
