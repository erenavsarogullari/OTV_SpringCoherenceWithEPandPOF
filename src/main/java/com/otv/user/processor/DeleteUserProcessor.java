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
 * Delete User Processor
 * 
 * @author  onlinetechvision.com
 * @since   2 Jun 2012
 * @version 1.0.0
 *
 */
public class DeleteUserProcessor extends AbstractProcessor implements PortableObject { 

	private static Logger logger = Logger.getLogger(DeleteUserProcessor.class);
	
	/**
     * Processes a Map.Entry object.
     *
     * @param Entry entry
     * @return Object user
     */
	public Object process(Entry entry) {
		User user = null;
		try {	
			user = (User) entry.getValue();
			entry.remove(true);
     	} catch (Exception e) {
     		logger.error("Error occured when entry was being processed!", e);
		}
     	
		return user;
	}

	/**
	 * Restore the contents of a user type instance by reading its state 
	 * using the specified PofReader object.
	 * 
	 * @param PofReader in
	 */
	public void readExternal(PofReader in) throws IOException {
		
	}

	/**
	 * Save the contents of a POF user type instance by writing its state 
	 * using the specified PofWriter object.
	 * 
	 * @param PofWriter out
	 */
	public void writeExternal(PofWriter out) throws IOException {
		
	}

}
