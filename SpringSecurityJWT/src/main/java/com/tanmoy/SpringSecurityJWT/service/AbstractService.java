package com.tanmoy.SpringSecurityJWT.service;

import java.util.List;
import java.util.logging.Logger;

/**
 * AbstractService class can be used globally for all the service class of
 * domain classes.
 * 
 * @author tanmoy.tushar
 * @since 2022-03-04
 * @param <T> pass domain class here
 */
public abstract class AbstractService<T> {
	protected final static Logger LOGGER = Logger.getLogger(AbstractService.class.getName());

	/**
	 * Get integer from string value
	 * 
	 * @param str
	 * @return int value
	 */
	public int getInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Get T object by id, when type of id is integer, if you have another type of
	 * id then overload the method
	 * 
	 * @param id
	 * @return T object
	 */
	public abstract T findById(int id);

	/**
	 * Get list of all rows of T table.
	 * 
	 * @return all rows of T
	 */
	public abstract List<T> findAll();

	/**
	 * To save T object
	 * 
	 * @param obj
	 * @return true if successfully saved, otherwise false;
	 */
	public abstract boolean save(T obj);

	/**
	 * To update T object
	 * 
	 * @param obj
	 * @return true if successfully updated, otherwise false;
	 */
	public abstract boolean update(T obj);

	/**
	 * To delete T object
	 * 
	 * @param id
	 * @return true if successfully deleted, otherwise false;
	 */
	public abstract boolean deleteById(int id);
}
