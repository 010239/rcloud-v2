package com.chinasofti.rcloud.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
@Component
public class InitSystem implements ApplicationContextAware, InitializingBean {
	
    private ApplicationContext applicationContext;
	
	private void init(){
		SpringContextUtil.getInstance().setContext(applicationContext);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		 init();

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;

	}

}
