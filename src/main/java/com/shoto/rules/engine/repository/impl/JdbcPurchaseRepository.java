package com.shoto.rules.engine.repository.impl;

import com.shoto.rules.engine.repository.PurchaseRepository;

// 示例实现（实际项目中可以使用JPA/MyBatis等）
public class JdbcPurchaseRepository implements PurchaseRepository {

    @Override
    public int getPurchasedAlcoholCount(String userId) {
        // 这里实现实际的数据库查询逻辑
        // 返回该用户已购买的酒数量
        return 16; // 示例返回0
    }
}