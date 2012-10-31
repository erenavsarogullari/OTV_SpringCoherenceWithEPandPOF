package com.otv.exe;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.otv.cache.updater.task.CacheUpdaterTask;
import com.otv.common.SystemConstants;

/**
 * Application Class
 * 
 * @author  onlinetechvision.com
 * @since   2 Jun 2012
 * @version 1.0.0
 *
 */
public class Application {

	/**
	 * Starts the application
	 * 
	 * @param  String[] args
	 *
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(SystemConstants.APPLICATION_CONTEXT_FILE_NAME);

		CacheUpdaterTask cacheUpdaterTask = (CacheUpdaterTask) context.getBean(SystemConstants.BEAN_NAME_CACHE_UPDATER_TASK);
		Thread cacheUpdater = new Thread(cacheUpdaterTask);
		cacheUpdater.start();
	}
}
