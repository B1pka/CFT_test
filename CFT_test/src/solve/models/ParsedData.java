package solve.models;

import java.util.*;

public class ParsedData {

	private List<Integer> integers = new ArrayList<>();
	private List<Float> floats = new ArrayList<>();
	private List<String> strings = new ArrayList<>();

	public ParsedData(List<Integer> integers, List<Float> floats, List<String> strings) {
		this.integers = integers;
		this.floats = floats;
		this.strings = strings;
	}

	public List<Integer> getIntegers() {
		return integers;
	}

	public List<Float> getFloats() {
		return floats;
	}

	public List<String> getStrings() {
		return strings;
	}

	public void sort() {
		Collections.sort(integers);
		Collections.sort(floats);
		Collections.sort(strings);
	}

	public void printData() {
		System.out.println("Integers: " + integers);
		System.out.println("Floats: " + floats);
		System.out.println("Strings: " + strings);
	}
}