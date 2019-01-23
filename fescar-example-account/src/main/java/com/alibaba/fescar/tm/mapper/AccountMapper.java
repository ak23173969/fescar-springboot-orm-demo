package com.alibaba.fescar.tm.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 
 * @author: wanpeng
 * @date:   2019年1月23日 下午7:17:42   
 *     
 * @Copyright: 2019 http://www.hotcomm.net All rights reserved.
 */
public interface AccountMapper {
	
	@Update("update account_tbl set money = money - #{money} where user_id = #{userId}")
	public void update(@Param("money")double money,@Param("userId")String userId);
	
	@Delete("delete from account_tbl where user_id = #{userId}")
	public void delete(@Param("userId")String userId);
	
	@Insert("insert into account_tbl(user_id, money) values (#{userId},#{money})")
	public void add(@Param("money")double money,@Param("userId")String userId);
	
}
