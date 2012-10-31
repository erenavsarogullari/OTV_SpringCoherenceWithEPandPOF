package com.otv.user;

import java.io.IOException;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;

/**
 * User Bean
 * 
 * @author  onlinetechvision.com
 * @since   2 Jun 2012
 * @version 1.0.0
 *
 */
public class User implements PortableObject {

	private String id;
	private String name;
	private String surname;
	
	/**
     * Gets User Id
     *
     * @return String id
     */
	public String getId() {
		return id;
	}

	/**
     * Sets User Id
     *
     * @param String id
     */
	public void setId(String id) {
		this.id = id;
	}

	/**
     * Gets User Name
     *
     * @return String name
     */
	public String getName() {
		return name;
	}
	
	/**
     * Sets User Name
     *
     * @param String name
     */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
     * Gets User Surname
     *
     * @return String surname
     */
	public String getSurname() {
		return surname;
	}
	
	/**
     * Sets User Surname
     *
     * @param String surname
     */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("Id : ").append(id);
		strBuilder.append(", Name : ").append(name);
		strBuilder.append(", Surname : ").append(surname);
		return strBuilder.toString();
	}

	/**
	 * Restore the contents of a user type instance by reading its state 
	 * using the specified PofReader object.
	 * 
	 * @param PofReader in
	 */
	public void readExternal(PofReader in) throws IOException {
		this.id = in.readString(0);
		this.name = in.readString(1);
		this.surname = in.readString(2);
	}

	/**
	 * Save the contents of a POF user type instance by writing its state 
	 * using the specified PofWriter object.
	 * 
	 * @param PofWriter out
	 */
	public void writeExternal(PofWriter out) throws IOException {
		out.writeString(0, id);
		out.writeString(1, name);
		out.writeString(2, surname);
	}
}
