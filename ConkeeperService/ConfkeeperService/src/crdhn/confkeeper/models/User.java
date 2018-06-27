/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crdhn.confkeeper.models;

/**
 *
 * @author vietanh
 */
public class User {
	private String name;
	private Integer age;
	
	public User(){
	
	}

	public User(String name, Integer age){
		this.age = age;
		this.name = name;
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	
}
