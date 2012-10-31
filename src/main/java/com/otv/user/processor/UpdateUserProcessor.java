package com.otv.user.processor;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.otv.user.User;
import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.util.InvocableMap.Entry;
import com.tangosol.util.processor.AbstractProcessor;

/**
 * Update User Processor
 * 
 * @author  onlinetechvision.com
 * @since   2 Jun 2012
 * @version 1.0.0
 *
 */
public class UpdateUserProcessor extends AbstractProcessor implements PortableObject { 

	private static Logger logger = Logger.getLogger(UpdateUserProcessor.class);
	private User newUser;
	
	/**
     * This empty constructor is added for Portable Object Format(POF).
     *
     */
	public UpdateUserProcessor() {
	
	}

	public UpdateUserProcessor(User newUser) {
		this.newUser = newUser;
	}

	/**
     * Processes a Map.Entry object.
     *
     * @param Entry entry
     * @return Object newUser
     */
	public Object process(Entry entry) {
		Object newValue = null;
		try {			
			newValue = getNewUser();
			entry.setValue(newValue);
     	} catch (Exception e) {
     		logger.error("Error occured when entry was being processed!", e);
		}
     	
		return newValue;
	}
	
	/**
     * Gets new user
     *
     * @return User newUser
     */
	public User getNewUser() {
		return newUser;
	}

	/**
     * Sets new user
     *
     * @param User newUser
     */
	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

	/**
	 * Restore the contents of a user type instance by reading its state 
	 * using the specified PofReader object.
	 * 
	 * @param PofReader in
	 */
	public void readExternal(PofReader in) throws IOException {
		setNewUser((User) in.readObject(0));
	}

	/**
	 * Save the contents of a POF user type instance by writing its state 
	 * using the specified PofWriter object.
	 * 
	 * @param PofWriter out
	 */
	public void writeExternal(PofWriter out) throws IOException {
		out.writeObject(0, getNewUser());
	}

}
