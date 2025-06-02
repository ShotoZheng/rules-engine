package com.shoto.rules.engine.rule;

import com.shoto.rules.engine.entity.Person;
import com.shoto.rules.engine.enums.GenderEnum;
import org.jeasy.rules.annotation.*;

@Rule(name = "gender rule", description = "male are allowed to buy alcohol")
public class GenderRule {

    @Condition
    public boolean isMale(@Fact("person") Person person) {
        return GenderEnum.MALE.equals(person.getGender());
    }

    @Priority
    public int priority() {
        return 3;
    }

    @Action
    public void action() {
        System.out.println("you are allowed to buy alcohol, because you are male");
    }
}
