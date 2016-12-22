package com.example;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.Test;

import javaslang.collection.List;
import javaslang.control.Validation;

public class PersonTest {

    @Test
    public void should_create_valid_person() {
        final Validation<List<String>, Person> personValidation = Person.builder().name("Mathias").age(36).build();

        then(personValidation.isValid());
        then(personValidation.get().getName()).isEqualTo("Mathias");
    }

    @Test
    public void should_have_validation_errors() {
        final Validation<List<String>, Person> personValidation = Person.builder().name("la").age(15).build();

        then(personValidation.isInvalid());
        then(personValidation.getError()).hasSize(2);
    }
}
