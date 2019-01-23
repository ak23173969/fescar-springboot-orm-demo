package com.alibaba.fescar.tm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alibaba.fescar.tm.dubbo.domain.UserDO;

/**
 * 
 * @author: wanpeng
 * @date:   2019年1月23日 下午7:18:03   
 *     
 * @Copyright: 2019 http://www.hotcomm.net All rights reserved.
 */
public interface UserDao extends JpaRepository<UserDO, Integer>  {
	
}
