package com.alibaba.fescar.tm.dubbo.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fescar.core.context.RootContext;
import com.alibaba.fescar.spring.annotation.GlobalTransactional;
import com.alibaba.fescar.tm.dubbo.AccountService;
import com.alibaba.fescar.tm.dubbo.OrderService;
import com.alibaba.fescar.tm.dubbo.domain.Order;
import com.alibaba.fescar.tm.mapper.OrderMapper;

@Service(interfaceClass=OrderService.class,timeout=10000)
@Component
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    
    @Reference(check =false)
    private AccountService accountService;
    
    @Resource
    private OrderMapper orderMapper;

    @Override
    @GlobalTransactional(name="OrderService.create",timeoutMills=60000)
    public Order create(String userId, String commodityCode, int orderCount) {
        LOGGER.info("Order Service Begin ... xid: " + RootContext.getXID());

        // 计算订单金额
        int orderMoney = calculate(commodityCode, orderCount);

        // 从账户余额扣款
        accountService.debit(userId, orderMoney);

        final Order order = new Order();
        order.userId = userId;
        order.commodityCode = commodityCode;
        order.count = orderCount;
        order.money = orderMoney;

        orderMapper.add(order.userId, order.commodityCode, order.money, order.count);

        LOGGER.info("Order Service End ... Created " + order);

        return order;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    private int calculate(String commodityId, int orderCount) {
        return 200 * orderCount;
    }

}
