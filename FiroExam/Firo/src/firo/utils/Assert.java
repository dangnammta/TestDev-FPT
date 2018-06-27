/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo.utils;

/**
 *
 * @author hoaronal
 */
public abstract class Assert {
	public static void isTrue(boolean expression , String message){
		if(!expression){
			throw  new IllegalArgumentException(message);
		}
	}
	
	public static void notNull(Object object, String message){
		if(object==null){
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void notNull(Object object){
		notNull(object,"[Assertion failed] - this argument is required; it must not be null");
	}
	
	public static void hasLength(String text, String message){
		if(!StringUtils.hasLength(text)){
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void notEmpty(Object[] array, String message){
		if(ObjectUtils.isEmpty(array)){
			throw new  IllegalArgumentException(message);
		}
	}
	
	public static void state(boolean expression, String message){
		if(!expression){
			throw new IllegalArgumentException(message);
		}
	}
}
