package com.alibaba.fescar.tm;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

/**
 * 
 * @author: wanpeng
 * @date:   2019年1月23日 下午7:18:26   
 *     
 * @Copyright: 2019 http://www.hotcomm.net All rights reserved.
 */
@SpringBootApplication(scanBasePackages = { "com.alibaba.fescar.tm" })
@EnableDubboConfiguration
public class UserRunner {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(UserRunner.class).web(WebApplicationType.NONE).run(args);
	}
	
}
