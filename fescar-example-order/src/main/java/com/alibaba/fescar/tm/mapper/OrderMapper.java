package com.alibaba.fescar.tm.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author: wanpeng
 * @date:   2019年1月23日 下午7:20:11   
 *     
 * @Copyright: 2019 http://www.hotcomm.net All rights reserved.
 */
public interface OrderMapper {
	
	@Insert("insert into order_tbl (user_id, commodity_code, count, money) values (#{userId}, #{commodity_code}, #{count}, #{money})1")
	public void add(@Param("userId")String userId,@Param("commodity_code")String commodity_code,@Param("money")double money,@Param("count")Integer count);
	
}
