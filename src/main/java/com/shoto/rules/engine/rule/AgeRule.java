package com.shoto.rules.engine.rule;

import com.shoto.rules.engine.entity.Person;
import org.jeasy.rules.annotation.*;

@Rule(name = "age rule", description = "Check if person's age is > 18 and marks the person as adult")
public class AgeRule {

    @Condition
    public boolean isAdult(@Fact("person") Person person) {
        return person.getAge() > 18;
    }

    @Priority
    public int priority() {
        return 1;
    }

    @Action
    public void action(@Fact("person") Person person) {
        person.setAdult(true);
    }
}
