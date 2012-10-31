package com.otv.user.cache.srv;

import com.otv.user.User;
import com.otv.user.processor.DeleteUserProcessor;
import com.otv.user.processor.UpdateUserProcessor;
import com.tangosol.net.NamedCache;

/**
 * User Cache Service Interface
 * 
 * @author  onlinetechvision.com
 * @since   2 Jun 2012
 * @version 1.0.0
 *
 */
public interface IUserCacheService {

	/**
     * Gets Distributed User Cache
     *
     * @return NamedCache User Cache
     */
	NamedCache getUserCache();
	
	/**
     * Adds user to cache
     *
     * @param User user
     */
	void addUser(User user);
	
	/**
     * Updates user on the cache
     *
     * @param String userId
     * @param UpdateUserProcessor processor
     * 
     */
	void updateUser(String userId, UpdateUserProcessor processor);
	
	/**
     * Deletes user from the cache
     *
     * @param String userId
     * @param DeleteUserProcessor processor
     * 
     */
	void deleteUser(String userId, DeleteUserProcessor processor);
	
}
