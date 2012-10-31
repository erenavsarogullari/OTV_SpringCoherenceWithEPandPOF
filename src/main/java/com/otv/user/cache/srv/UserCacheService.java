package com.otv.user.cache.srv;

import com.otv.cache.listener.UserMapListener;
import com.otv.common.SystemConstants;
import com.otv.user.User;
import com.otv.user.processor.DeleteUserProcessor;
import com.otv.user.processor.UpdateUserProcessor;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

/**
 * User Cache Service
 * 
 * @author  onlinetechvision.com
 * @since   2 Jun 2012
 * @version 1.0.0
 *
 */
public class UserCacheService implements IUserCacheService {
	
	private NamedCache userCache = null;	
	
	public UserCacheService() {
		setUserCache(CacheFactory.getCache(SystemConstants.USER_CACHE));
		//UserMap Listener is registered to listen user-cache operations
		getUserCache().addMapListener(new UserMapListener());		
	}	
	
	/**
     * Adds user to cache
     *
     * @param User user
     */
	public void addUser(User user) {
		getUserCache().put(user.getId(), user);		
	}

	/**
     * Deletes user from the cache
     *
     * @param String userId
     * @param DeleteUserProcessor processor
     * 
     */
	public void deleteUser(String userId, DeleteUserProcessor processor) {
		getUserCache().invoke(userId, processor);
	}
	
	/**
     * Updates user on the cache
     *
     * @param String userId
     * @param UpdateUserProcessor processor
     * 
     */
	public void updateUser(String userId, UpdateUserProcessor processor) {
		getUserCache().invoke(userId, processor);
	}

	/**
     * Gets Distributed User Cache
     *
     * @return NamedCache User Cache
     */
	public NamedCache getUserCache() {
		return userCache;
	}

	/**
     * Sets User Cache
     *
     * @param NamedCache userCache
     */
	public void setUserCache(NamedCache userCache) {
		this.userCache = userCache;
	}
	
}
