package main;

public class MemberNotFoundException extends Exception {
  public MemberNotFoundException(String nameOrId) {
    super("No member was found with name or personal identity number " + nameOrId);
  }
}
