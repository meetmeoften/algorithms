package com.hackerrank.sorting;

public class Customer {

	private int id;
	private String name;



	public Customer(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Customer other = (Customer) obj;
		if (id != other.id) {
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

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public static void main(String[] args) {
		Customer c1 = new Customer(1, "s");
		Customer c2 = new Customer(1, "s");

		System.out.println(c1 == c2);

		System.out.println(c1.equals(c2));
	}

	/**
	 * 	public static Map<Character, String[]> DIGIT_LETTERS = new HashMap<>();

	static {
		DIGIT_LETTERS.put('0', new String[] {"0"});
		DIGIT_LETTERS.put('1', new String[] {"1"});
		DIGIT_LETTERS.put('2', new String[] {"a", "b", "c"});
		DIGIT_LETTERS.put('3', new String[] {"d", "e", "f"});
		DIGIT_LETTERS.put('4', new String[] {"g", "h", "i"});
		DIGIT_LETTERS.put('5', new String[] {"j", "k", "l"});
		DIGIT_LETTERS.put('6', new String[] {"m", "n", "o"});
		DIGIT_LETTERS.put('7', new String[] {"p", "q", "r", "s"});
		DIGIT_LETTERS.put('8', new String[] {"t", "u", "v"});
		DIGIT_LETTERS.put('9', new String[] {"w", "x", "y", "z"});

	}
	 */



}
