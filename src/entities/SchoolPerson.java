package entities;

public class SchoolPerson extends Person {
  public final String email;

  public SchoolPerson(String name, int age, String email) {
    super(name, age);
    this.email = email;
  }
}