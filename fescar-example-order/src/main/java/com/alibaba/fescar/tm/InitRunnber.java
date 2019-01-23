package com.alibaba.fescar.tm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fescar.tm.dubbo.OrderService;

/**
 * 
 * @author: wanpeng
 * @date:   2019年1月23日 下午7:19:58   
 *     
 * @Copyright: 2019 http://www.hotcomm.net All rights reserved.
 */
@Component
@Order(1)
public class InitRunnber implements ApplicationRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InitRunnber.class);
	
	@Autowired
	private OrderService orderService;

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		LOGGER.info("execute dubbo method and try GlobalTransactional ");
	/*	try {
			orderService.create("U100001", "F001", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

}
