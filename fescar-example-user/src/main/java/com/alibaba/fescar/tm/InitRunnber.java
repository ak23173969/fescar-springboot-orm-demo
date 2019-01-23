package com.alibaba.fescar.tm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fescar.tm.dao.UserDao;
import com.alibaba.fescar.tm.dubbo.UserService;
import com.alibaba.fescar.tm.dubbo.domain.UserDO;

/**
 * 
 * @author: wanpeng
 * @date:   2019年1月23日 下午7:18:19   
 *     
 * @Copyright: 2019 http://www.hotcomm.net All rights reserved.
 */
@Component
@Order(1)
public class InitRunnber implements ApplicationRunner {
	
	@Autowired
	UserDao userDao;	
	
	@Autowired
	UserService userService;
	
	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		List<UserDO> list = userDao.findAll();
		System.out.println(list);
		try {
			userService.addUser();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
