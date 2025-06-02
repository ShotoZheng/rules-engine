package com.shoto.rules.engine.entity;

import com.shoto.rules.engine.enums.GenderEnum;
import com.shoto.rules.engine.enums.NationalityEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Person {
    private String id;
    private String name;
    private int age;
    private GenderEnum gender;
    private NationalityEnum nationality;
    private boolean adult;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, GenderEnum gender, NationalityEnum nationality) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.nationality = nationality;
    }
}
