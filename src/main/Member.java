package main;

import java.time.LocalDate;

public class Member {
  private String name;
  private String personalIdentityNumber;
  private LocalDate lastPaymentDate;

  public Member(String name, String personalIdentityNumber, LocalDate lastPaymentDate) {
    this.name = name;
    this.personalIdentityNumber = personalIdentityNumber;
    this.lastPaymentDate = lastPaymentDate;
  }

  public String getName() {
    return name;
  }

  public boolean isActive() {
    var dateOneYearAgo = LocalDate.now().minusYears(1);
    return lastPaymentDate.isAfter(dateOneYearAgo);
  }

  public String getPersonalIdentityNumber() {
    return personalIdentityNumber;
  }

  public String getInfo(){
    String isMemberActive = isActive() ? "ja" : "nej";
    return "Namn: " + name + "\n"
        + "Personnr: " + personalIdentityNumber + "\n"
        + "Aktivt medlemskap: " + isMemberActive;
  }

  public LocalDate getLastPaymentDate() {
    return lastPaymentDate;
  }
}

