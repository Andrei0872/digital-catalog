package entities;

import utils.IHasIdentifier;

public class Person implements IHasIdentifier {
  protected final String name;
  protected final int age;

  private static int IDs = 0;
  private final int ID;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;

    IDs++;
    this.ID = IDs;
  }

  @Override
  public int getId () {
    return ID;
  }

  @Override
  public String toString() {
    return String.format("Person ID: %s, Name: %s, Age: %s", ID, this.name, this.age);
  }
}