package com.test.polymorphism;

public class Main {

	public static void main(String[] args) {
		Animal animal = new Animal();
		System.out.println(animal.i);
		animal.name();

		Dog dog = new Dog();
		System.out.println(dog.i);
		dog.name();

		Animal animal1 = new Dog();
		System.out.println(animal1.i);
		animal1.name();

		Dog dog1 = (Dog) new Animal();
		dog1.name();

		String s = "Suga";
		for (int i = 0; i < 10; i++) {
			s = s + i;
		}
		System.out.println(s);
	}

}
