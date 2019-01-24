/*
 *  Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.alibaba.fescar.tm.dubbo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fescar.core.context.RootContext;
import com.alibaba.fescar.spring.annotation.GlobalTransactional;
import com.alibaba.fescar.tm.bean.Storage;
import com.alibaba.fescar.tm.dubbo.OrderService;
import com.alibaba.fescar.tm.dubbo.StorageService;
import com.alibaba.fescar.tm.mapper.StorageMapper;

/**
 * Please add the follow VM arguments:
 * <pre>
 *     -Djava.net.preferIPv4Stack=true
 * </pre>
 */
@Service(interfaceClass=StorageService.class,timeout=2000)
@Component
public class StorageServiceImpl implements StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageService.class);
    
    @Autowired
    StorageMapper storageMapper;
    
    
    @Reference(check =false)
    private OrderService orderService;
    
    @Override
    public void deduct(String commodityCode, int count) {
        LOGGER.info("Storage Service Begin ... xid: " + RootContext.getXID());
        storageMapper.updateCount(commodityCode, count);
        LOGGER.info("Storage Service End ... ");
    }

	@Override
	 @GlobalTransactional(name="StorageService.deduct",timeoutMills=60000)
	public void checkTransient(String commodityCode, int count) {
		this.deduct(commodityCode, count);
		this.orderService.create("U100001", "F001", 1);
	}

	@Override
	@GlobalTransactional(name="StorageService.insertDuct",timeoutMills=60000)
	public void insertDuct(String commodityCode, String count) {
		storageMapper.deleteByPrimaryKey(73);
		storageMapper.insertSelective(new Storage(commodityCode, count));
	}

}
