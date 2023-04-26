package com.zjava8;

public class Student implements Cloneable {

	String name;
	String gender;

	public Student(String name, String gender) {
		super();
		this.name = name;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Student other = (Student) obj;
		if (gender == null) {
			if (other.gender != null) {
				return false;
			}
		} else if (!gender.equals(other.gender)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		Student s1 = new Student("s", "m");
		Student s2 = new Student("s", "m");

		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));

		int data = 100;
		System.out.println(data);
		method(data);
		System.out.println(data);

		try {
			method(s1);
			Student s3 = (Student) s1.clone();

			s3.setName("t");
			System.out.println(s1.getName());
			Student s4 = s1;
			s4.setName("q");
			System.out.println(s1.getName());
			StringBuffer buffer = new StringBuffer();
			buffer.reverse();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	public static void method(Student s1) {
		s1.setName("method");
	}

	public static void method(int data) {
		data = data + 50;
	}

}
