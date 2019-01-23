package com.alibaba.fescar.tm.dubbo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fescar.core.context.RootContext;
import com.alibaba.fescar.spring.annotation.GlobalTransactional;
import com.alibaba.fescar.tm.dubbo.AccountService;
import com.alibaba.fescar.tm.mapper.AccountMapper;

@Service(interfaceClass = AccountService.class, timeout = 2000)
@Component
public class AccountServiceImpl implements AccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private AccountMapper accountMapper;

	@Override
	public void debit(String userId, int money) {
		LOGGER.info("NOT GlobalTransactional");
		LOGGER.info("Account Service ... xid: " + RootContext.getXID());
		accountMapper.update(money, userId);
		LOGGER.info("Account Service End ... ");
	}

	@Override
	@GlobalTransactional(timeoutMills = 300000, name = "dubbo-demo-tx")
	public void debit() {
		LOGGER.info("Account Service ... xid: " + RootContext.getXID());
		accountMapper.update(200, "UA0001");
		LOGGER.info("Account Service End ... ");
		if(true) {
			throw new RuntimeException("001");
		}
	}

}
