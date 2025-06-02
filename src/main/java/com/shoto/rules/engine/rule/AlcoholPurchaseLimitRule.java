package com.shoto.rules.engine.rule;

import com.shoto.rules.engine.entity.Person;
import com.shoto.rules.engine.repository.PurchaseRepository;
import org.jeasy.rules.annotation.*;
import org.jeasy.rules.api.Facts;

@Rule(name = "alcoholPurchaseLimitRule", description = "限制用户购买酒的数量不超过20瓶")
public class AlcoholPurchaseLimitRule {

    private final PurchaseRepository purchaseRepository;
    private final int maxAllowed = 20; // 最大允许数量

    // 通过构造器注入Repository
    public AlcoholPurchaseLimitRule(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Condition
    public boolean checkPurchaseLimit(@Fact("person") Person person, @Fact("purchaseQuantity") int purchaseQuantity) {
        // 获取用户已购买数量
        int alreadyPurchased = purchaseRepository.getPurchasedAlcoholCount(person.getId());

        // 检查新购买后是否超过限制
        return (alreadyPurchased + purchaseQuantity) > maxAllowed;
    }

    @Action
    public void rejectPurchase(Facts facts) {
        Person person = facts.get("person");
        int purchaseQuantity = facts.get("purchaseQuantity");
        int alreadyPurchased = purchaseRepository.getPurchasedAlcoholCount(person.getId());

        System.out.printf(
                "拒绝销售: %s 已购买%d瓶酒，再购买%d瓶将超过上限%d瓶%n",
                person.getName(),
                alreadyPurchased,
                purchaseQuantity,
                maxAllowed
        );

        // 可以设置一个标志表示购买被拒绝
        facts.put("purchaseApproved", false);
        facts.put("rejectionReason", "超过购买数量限制");
    }

    @Priority
    public int getPriority() {
        return 1; // 设置较高优先级
    }
}
