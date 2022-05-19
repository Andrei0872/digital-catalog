package entities;

public class Person {
  protected final String name;
  protected final int age;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName () {
    return name;
  }

  public int getAge () {
    return age;
  }
}