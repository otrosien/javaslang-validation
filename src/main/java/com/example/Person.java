package com.example;


import static lombok.AccessLevel.PRIVATE;

import javaslang.collection.List;
import javaslang.control.Validation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = PRIVATE)
@Getter
public class Person {

    @Builder
    public static Validation<List<String>, Person> validateBuild(String name, Integer age) {
        return Validation.combine(validateName(name), validateAge(age)).ap(Person::new);
    }

    String name;
    Integer age;

    private static Validation<String, Integer> validateAge(int age) {
        return age < 18
                ? Validation.invalid("Age must be at least " + 18)
                : Validation.valid(age);
    }

    private static Validation<String, String> validateName(String name) {
        return name != null && name.length() > 5
                ? Validation.valid(name)
                : Validation.invalid("Name is too short:");
    }
}
