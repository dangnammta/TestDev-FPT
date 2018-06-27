/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crdhn.usermanagement.model;

/**
 *
 * @author hoaronal
 */
public class RoleUser extends Model{
	
     private Integer id;
     private Role role;
     private User user;

    public RoleUser() {
    }

    public RoleUser(Role role, User user) {
       this.role = role;
       this.user = user;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return this.role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
}
