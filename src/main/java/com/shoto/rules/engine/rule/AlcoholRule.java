package com.shoto.rules.engine.rule;

import com.shoto.rules.engine.entity.Person;
import org.jeasy.rules.annotation.*;

@Rule(name = "alcohol rule", description = "children are not allowed to buy alcohol")
public class AlcoholRule {

    @Condition
    public boolean allow(@Fact("person") Person person) {
        return !person.isAdult();
    }

    @Priority
    public int priority() {
        return 2;
    }

    @Action(order = 0)
    public void abandon() {
        System.out.println("Shop: Sorry, you are not allowed to buy alcohol");
    }

    @Action(order = 1)
    public void reason(@Fact("person") Person person) {
        System.out.println("Shop: Because you are not adult: " + person.isAdult());
    }
}
