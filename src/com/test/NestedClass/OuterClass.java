package com.test.NestedClass;


public class OuterClass
{
	int i = 10;   //Non-static Field of OuterClass

	static void methodOne()
	{
		System.out.println("Static method of OuterClass");
	}

	static class NestedClassOne
	{
		int i = 20;   //Non-static Field of NestedClassOne

		static void methodOne()
		{
			System.out.println("Static method of NestedClassOne");
		}
	}

	static class NestedClassTwo
	{
		int i = 30;    //Non-static Field of NestedClassTwo

		static void methodOne()
		{
			System.out.println("static method of NestedClassTwo");
		}
	}


	public static void main(String[] args)
	{
		OuterClass.methodOne();      //static member can be accessed directly through class name.
		OuterClass outer = new OuterClass();
		System.out.println(outer.i);  //Instance member must be accessed through object reference

		OuterClass.NestedClassOne.methodOne();  //static member can be accessed directly through class name.
		OuterClass.NestedClassOne nestedOne = new OuterClass.NestedClassOne();
		System.out.println(nestedOne.i);     //Instance member must be accessed through object reference

		OuterClass.NestedClassTwo.methodOne();  //static member can be accessed directly through class name.
		OuterClass.NestedClassTwo nestedTwo = new OuterClass.NestedClassTwo();
		System.out.println(nestedTwo.i);     //Instance member must be accessed through object reference
	}
}

