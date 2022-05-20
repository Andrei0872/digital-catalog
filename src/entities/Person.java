package entities;

public class Person {
  protected String name;
  protected int age;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName () {
    return name;
  }

  public void setName (String newName) {
    this.name = newName;
  }

  public int getAge () {
    return age;
  }

  public void setAge (int newAge) {
    this.age = newAge;
  }
}