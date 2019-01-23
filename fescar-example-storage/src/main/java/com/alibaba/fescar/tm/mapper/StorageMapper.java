package com.alibaba.fescar.tm.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author: wanpeng
 * @date:   2019年1月23日 下午7:19:03   
 *     
 * @Copyright: 2019 http://www.hotcomm.net All rights reserved.
 */
public interface StorageMapper {
	
	public void delele(@Param("commodity_code")String commodity_code);
	
	public void add(@Param("commodity_code")String commodity_code,@Param("count")Integer count);
	
	public void updateCount(@Param("commodity_code")String commodity_code,@Param("count")Integer count);
}
