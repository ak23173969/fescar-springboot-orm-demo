package com.alibaba.fescar.tm.dubbo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fescar.spring.annotation.GlobalTransactional;
import com.alibaba.fescar.tm.dao.UserDao;
import com.alibaba.fescar.tm.dubbo.UserService;
import com.alibaba.fescar.tm.dubbo.domain.UserDO;

@Service(interfaceClass = UserService.class, timeout = 60000)
@Component
public class UserServiceImple implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	@GlobalTransactional(name = "UserService.addUser", timeoutMills = 60000)
	public void addUser() {
		userDao.save(new UserDO("test", "test", 99));
	}

}
