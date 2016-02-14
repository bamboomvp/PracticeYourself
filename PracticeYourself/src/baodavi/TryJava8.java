package baodavi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class TryJava8 {

	interface Formula {
		double calculate(int a);

		default double sqrt(int a) {
			return Math.sqrt(a);
		}
	}

	@FunctionalInterface
	interface Converter<F, T> {
		T convert(F from);
	}

	static class Something {
		String startsWith(String s) {
			return String.valueOf(s.charAt(0));
		}
	}

	static class Person {
		String firstName;
		String lastName;

		Person() {
		}

		Person(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}

		@Override
		public String toString() {
			return this.lastName + ", " + this.firstName;
		}
	}

	interface PersonFactory<P extends Person> {
		P create(String firstName, String lastName);
	}

	public static void main(String[] args) {
		// //****
		List<String> names = Arrays.asList("baodi", "chenxuanwei", "yehui",
				"wanglili", "zhangqiu");
		System.out.println(names);
		Collections.sort(names);
		System.out.println(names);
		Collections.sort(names, (a, b) -> b.compareTo(a));
		System.out.println(names);

		// //****
		Formula formula = new Formula() {
			@Override
			public double calculate(int a) {
				return sqrt(a * 100);
			}
		};
		System.out.println(formula.calculate(100));

		// //****
		Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
		Integer converted = converter.convert("123");
		System.out.println(converted); // 123

		// //****
		Something something = new TryJava8.Something();
		Converter<String, String> converter2 = something::startsWith;
		String converted2 = converter2.convert("Java");
		System.out.println(converted2); // "J"

		// //****
		PersonFactory<Person> personFactory = Person::new;
		Person person = personFactory.create("Peter", "Parker");
		System.out.println(person);

		// //****
		Predicate<String> predicate = (s) -> s.length() > 0;

		predicate.test("foo"); // true
		predicate.negate().test("foo"); // false

		Function<String, Integer> toInteger = Integer::valueOf;
		Function<String, String> backToString = toInteger
				.andThen(String::valueOf);

		backToString.apply("123"); // "123"

		// //****
		Optional<String> optional = Optional.of("bam");

		optional.isPresent(); // true
		optional.get(); // "bam"
		optional.orElse("fallback"); // "bam"

		optional.ifPresent((s) -> System.out.println(s.charAt(0))); // "b"

		// //****
		List<String> stringCollection = new ArrayList<>();
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");

		stringCollection.stream().filter((s) -> s.startsWith("a"))
				.forEach(System.out::println);
		stringCollection.stream().sorted().filter((s) -> s.startsWith("a"))
				.forEach(System.out::println);
		System.out.println(stringCollection);
		stringCollection.stream().map(String::toUpperCase)
				.sorted((a, b) -> b.compareTo(a)).forEach(System.out::println);
		System.out.println(stringCollection);

		Optional<String> reduced = stringCollection.stream().sorted()
				.reduce((s1, s2) -> s1 + "#" + s2);

		reduced.ifPresent(System.out::println);

		// //****

	}
}
