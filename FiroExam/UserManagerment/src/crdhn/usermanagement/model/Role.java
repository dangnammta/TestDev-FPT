/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crdhn.usermanagement.model;

import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author hoaronal
 */
public class Role extends Model{
	private Integer idRole;
     private int role;
     private String name;
     private String description;
     private Set<RoleUser> roleUsers = new HashSet<RoleUser>(0);

    public Role() {
    }

	
    public Role(int role, String name) {
        this.role = role;
        this.name = name;
    }
    public Role(int role, String name, String description, Set<RoleUser> roleUsers) {
       this.role = role;
       this.name = name;
       this.description = description;
       this.roleUsers = roleUsers;
    }
    public Integer getIdRole() {
        return this.idRole;
    }
    
    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    
    public int getRole() {
        return this.role;
    }
    
    public void setRole(int role) {
        this.role = role;
    }

    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public Set<RoleUser> getRoleUsers() {
        return this.roleUsers;
    }
    
    public void setRoleUsers(Set<RoleUser> roleUsers) {
        this.roleUsers = roleUsers;
    }



}
