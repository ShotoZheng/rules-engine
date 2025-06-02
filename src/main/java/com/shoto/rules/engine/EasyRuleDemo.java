package com.shoto.rules.engine;

import com.shoto.rules.engine.entity.Person;
import com.shoto.rules.engine.enums.GenderEnum;
import com.shoto.rules.engine.enums.NationalityEnum;
import com.shoto.rules.engine.repository.PurchaseRepository;
import com.shoto.rules.engine.repository.impl.JdbcPurchaseRepository;
import com.shoto.rules.engine.rule.*;
import com.shoto.rules.engine.rule.group.GenderNationalityRuleUntGroup;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;

public class EasyRuleDemo {

    public static void main(String[] args) {
        // create a person instance (fact)
        Person tom = new Person("Tom", 19, GenderEnum.MALE, NationalityEnum.CHINA);
        int purchaseQuantity = 5; // 想购买5瓶
        Facts facts = new Facts();
        facts.put("person", tom);
        facts.put("purchaseQuantity", purchaseQuantity);

        // create a rule set
        Rules rules = new Rules();
//        rules.register(new AgeRule());
//        rules.register(new AlcoholRule());

        // composite rule
        rules.register(new GenderNationalityRuleUntGroup(new GenderRule(), new NationalityRule()));

        // 创建并注册规则(注入Repository)
        PurchaseRepository repository = new JdbcPurchaseRepository();
        rules.register(new AlcoholPurchaseLimitRule(repository));

        //create a default rules engine and fire rules on known facts
        RulesEngineParameters parameters = new RulesEngineParameters()
                .skipOnFirstAppliedRule(true); // true 满足其中一个规则则不继续执行其他规则
        RulesEngine rulesEngine = new DefaultRulesEngine(parameters);

        System.out.println("Tom: Hi! can I have some Vodka please?");
        rulesEngine.fire(rules, facts);

        if (Boolean.TRUE.equals(facts.get("genderNationalityPassed"))) {
            System.out.println("性别国家通过");
        } else {
            System.out.println("性别国家被拒绝");
        }

        // 检查购买是否被批准
        if (Boolean.FALSE.equals(facts.get("purchaseApproved"))) {
            System.out.println("购买被拒绝: " + facts.get("rejectionReason"));
        } else {
            System.out.println("购买已批准");
        }

//        System.out.println(tom);
    }
}
