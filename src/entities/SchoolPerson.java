package entities;

import utils.IHasIdentifier;

public class SchoolPerson extends Person implements IHasIdentifier {
  private final String email;
  private int ID = -1;

  public SchoolPerson(String name, int age, String email) {
    super(name, age);
    this.email = email;
  }

  public void setId (int ID) {
    this.ID = ID;
  }

  @Override
  public int getId () {
    return ID;
  }

  public String getEmail () {
    return email;
  }

  @Override
  public String toString() {
    return String.format("ID: %s, Name: %s, Age: %s, Email: %s", ID, this.name, this.age, this.email);
  }
}