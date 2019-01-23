package com.alibaba.fescar.tm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

/**
 * 
 * @author: wanpeng
 * @date:   2019年1月23日 下午7:17:03   
 *     
 * @Copyright: 2019 http://www.hotcomm.net All rights reserved.
 */
@SpringBootApplication(scanBasePackages = { "com.alibaba.fescar.tm" })
@EnableDubboConfiguration
@MapperScan(basePackages= {"com.alibaba.fescar.tm.mapper"})
public class AccountRunner {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(AccountRunner.class).web(WebApplicationType.NONE).run(args);
	}
	
}
