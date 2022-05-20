package entities;

import utils.IHasIdentifier;

public class SchoolPerson extends Person implements IHasIdentifier {
  protected String email;
  protected int ID = -1;

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

  public void setEmail (String newEmail) {
    this.email = newEmail;
  }

  public boolean hasIdSet () {
    return this.ID != -1;
  }

  @Override
  public String toString() {
    return String.format("ID: %s, Name: %s, Age: %s, Email: %s", ID, this.name, this.age, this.email);
  }
}