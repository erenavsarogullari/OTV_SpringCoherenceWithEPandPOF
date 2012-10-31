package com.otv.cache.updater.task;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import com.otv.common.SystemConstants;
import com.otv.user.User;
import com.otv.user.cache.srv.IUserCacheService;
import com.otv.user.processor.DeleteUserProcessor;
import com.otv.user.processor.UpdateUserProcessor;

/**
 * Cache Updater Task
 * 
 * @author  onlinetechvision.com
 * @since   2 Jun 2012
 * @version 1.0.0
 *
 */
public class CacheUpdaterTask implements BeanFactoryAware, Runnable {
	
	private static Logger log = Logger.getLogger(CacheUpdaterTask.class);
	private IUserCacheService userCacheService;
	private BeanFactory beanFactory;
	
	public void run() {				
		try {
			while(true) {
				/**
				 * Before the project is built for the first member, 
				 * this code block should be used instead of 
				 * method processRequestsOnSecondMemberOfCluster. 
				 */
//				processRequestsOnFirstMemberOfCluster();
				
				/**
				 * Before the project is built for the second member, 
				 * this code block should be used instead of 
				 * method processRequestsOnFirstMemberOfCluster. 
				 */
				processRequestsOnSecondMemberOfCluster();				
			}			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	  * Processes the cache requests on the first member of cluster... 
	  *
	  * @throws InterruptedException
	  */
	private void processRequestsOnFirstMemberOfCluster() throws InterruptedException {
		//Entry is added to cache...
		getUserCacheService().addUser(getUser("1", "Bruce", "Willis"));
		
		//Cache Entries are being printed...
		printCacheEntries();
		
		Thread.sleep(10000);
		
		User newUser = getUser("1", "Client", "Eastwood");
		//Existent Entry is updated on the cache...
		getUserCacheService().updateUser(newUser.getId(), new UpdateUserProcessor(newUser));
		
		//Cache Entries are being printed...
		printCacheEntries();
		
		Thread.sleep(10000);
		
		//Entry is deleted from cache...
		getUserCacheService().deleteUser(newUser.getId(), new DeleteUserProcessor());
		
		//Cache Entries are being printed...
		printCacheEntries();
		
		Thread.sleep(10000);
	}
	
	/**
	  * Processes the cache requests on the second member of cluster...
	  * 
	  * @throws InterruptedException
	  */
	private void processRequestsOnSecondMemberOfCluster() throws InterruptedException {
		//Entry is added to cache...
		getUserCacheService().addUser(getUser("2", "Nathalie", "Portman"));
				
		Thread.sleep(15000);
		
		User newUser = getUser("2", "Sharon", "Stone");
		//Existent Entry is updated on the cache...
		getUserCacheService().updateUser(newUser.getId(), new UpdateUserProcessor(newUser));
		
		User newUser2 = getUser("1", "Maria", "Sharapova");
		//Existent Entry is updated on the cache...
		getUserCacheService().updateUser(newUser2.getId(), new UpdateUserProcessor(newUser2));
		
		Thread.sleep(15000);
		
		//Entry is deleted from cache...
		getUserCacheService().deleteUser(newUser.getId(), new DeleteUserProcessor());
		
		Thread.sleep(15000);
	}
	
	/**
     * Prints cache entries
     *
     */
	private void printCacheEntries() {
		Collection<User> userCollection = (Collection<User>)getUserCacheService().getUserCache().values();
		for(User user : userCollection) {
			log.debug("Cache Content : "+user);
		}
	}
	
	/**
     * Gets new user instance...
     *
     * @param String user id
     * @param String user name
     * @param String user surname
     * @return User user
     */
	private User getUser(String id, String name, String surname) {
		User user = getNewUserInstance();
		user.setId(id);
		user.setName(name);
		user.setSurname(surname);
		
		return user;
	}
	
	/**
     * Gets user cache service...
     *
     * @return IUserCacheService userCacheService
     */
	public IUserCacheService getUserCacheService() {
		return userCacheService;
	}

	/**
     * Sets user cache service...
     *
     * @param IUserCacheService userCacheService
     */
	public void setUserCacheService(IUserCacheService userCacheService) {
		this.userCacheService = userCacheService;
	}
	
	/**
     * Gets a new instance of User Bean
     *
     * @return User 
     */
    public User getNewUserInstance() {
        return  (User) getBeanFactory().getBean(SystemConstants.BEAN_NAME_USER);
    }
    
    /**
     * Gets bean factory
     *
     * @return BeanFactory 
     */
    public BeanFactory getBeanFactory() {
		return beanFactory;
	}

    /**
     * Sets bean factory
     *
     * @param BeanFactory beanFactory
     * @throws BeansException
     */
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

}
