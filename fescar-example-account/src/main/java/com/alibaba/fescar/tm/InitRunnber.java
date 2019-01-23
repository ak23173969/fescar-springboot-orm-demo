
package com.alibaba.fescar.tm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fescar.tm.dubbo.AccountService;
import com.alibaba.fescar.tm.mapper.AccountMapper;

/**
 * 
 * @author: wanpeng
 * @date:   2019年1月23日 下午7:17:34   
 *     
 * @Copyright: 2019 http://www.hotcomm.net All rights reserved.
 */
@Component
@Order(1)
public class InitRunnber implements ApplicationRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InitRunnber.class);
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	private AccountMapper accountMapper;

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		// 測試 比對 加入 全局事務后 單個業務方法是否回滾 
	/*	try {
			LOGGER.info("execute not GlobalTransactional Dubbo Method ");
			//accountService.debit("UA0001", 200);
			LOGGER.info("execute GlobalTransactional Dubbo Method ");
			accountService.debit();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
//	       jdbcTemplate.update("delete from account_tbl where user_id = 'U100001'");
//	        jdbcTemplate.update("insert into account_tbl(user_id, money) values ('U100001', 999)");
		accountMapper.delete("U100001");
		accountMapper.add(9999, "U100001");
	}

}
