package com.alibaba.fescar.tm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fescar.tm.bean.Storage;
import com.alibaba.fescar.tm.dubbo.StorageService;
import com.alibaba.fescar.tm.mapper.StorageMapper;

/**
 * 
 * @author: wanpeng
 * @date:   2019年1月23日 下午7:18:50   
 *     
 * @Copyright: 2019 http://www.hotcomm.net All rights reserved.
 */
@Component
@Order(1)
public class InitRunnber implements ApplicationRunner {
	
	@Autowired
	StorageMapper storageMapper;
	
	@Autowired
	StorageService storageService;
	
	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		storageService.insertDuct("C00321", "ss");
		//storageMapper.insertSelective(new Storage("C00321", "ss"));
//		storageMapper.delele("C00321");
//		storageMapper.add("C00321", 100);
//		try {
//			storageService.checkTransient("C00321", 2);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
