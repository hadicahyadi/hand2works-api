package com.curiouslabs.hand2works.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ServiceLocator implements ApplicationContextAware{

	/** cached springframework ApplicationContext */
	@Autowired
	private static ApplicationContext applicationContext;

	/**
	 * loaded during springframework initialization
	 */
	@Override
	public void setApplicationContext( ApplicationContext ctx ) throws BeansException {
		
		applicationContext = ctx;
	}
	
	/**
	* get access to the springframework ApplicationContext from anywhere in application.
	* @return springfraework ApplicationContext
	*/
	public static ApplicationContext getApplicationContext() {
		
		return applicationContext;
	}
	
	/**
	 * same functionalities as @Autowired, it will get the reference to specified bean
	 * @param argBeanName bean identifier as registered into springframework applicationContext
	 * @return the reference to specified bean name or bean identifier
	 */
	public static Object getService( String argBeanName ) {
		
		return applicationContext.getBean( argBeanName );
	}

}
