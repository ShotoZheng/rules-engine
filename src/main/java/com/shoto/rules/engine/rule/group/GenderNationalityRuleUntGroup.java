package com.shoto.rules.engine.rule.group;

import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.support.composite.UnitRuleGroup;

@Rule(name = "gender and nationality rule", description = "male and chinese are allowed to buy alcohol")
public class GenderNationalityRuleUntGroup extends UnitRuleGroup {

    public GenderNationalityRuleUntGroup(Object... rules) {
        for (Object rule : rules) {
            super.addRule(rule);
        }
    }

    @Override
    public void execute(Facts facts) throws Exception {
        super.execute(facts); // 先执行父类逻辑

        // 设置校验通过标志
        facts.put("genderNationalityPassed", true);
//        System.out.println("GenderNationalityRuleUntGroup 性别和国籍校验通过");
    }
}
