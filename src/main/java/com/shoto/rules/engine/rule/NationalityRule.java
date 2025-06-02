package com.shoto.rules.engine.rule;

import com.shoto.rules.engine.entity.Person;
import com.shoto.rules.engine.enums.NationalityEnum;
import org.jeasy.rules.annotation.*;

@Rule(name = "nationality rule", description = "chinese are allowed to buy alcohol")
public class NationalityRule {

    @Condition
    public boolean isChinese(@Fact("person") Person person) {
        return NationalityEnum.CHINA.equals(person.getNationality());
    }

    @Priority
    public int priority() {
        return 3;
    }

    @Action
    public void action() {
        System.out.println("you are allowed to buy alcohol, because you are chinese");
    }
}
