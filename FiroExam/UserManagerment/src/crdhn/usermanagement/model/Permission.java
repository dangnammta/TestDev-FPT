package crdhn.usermanagement.model;

import java.util.HashSet;
import java.util.Set;



public class Permission  implements java.io.Serializable {


     private Integer idPermission;
     private int permission;
     private String name;
     private String description;
     private Set<PermissionUser> permissionUsers = new HashSet<PermissionUser>(0);

    public Permission() {
    }

	
    public Permission(int permission, String name) {
        this.permission = permission;
        this.name = name;
    }
    public Permission(int permission, String name, String description, Set<PermissionUser> permissionUsers) {
       this.permission = permission;
       this.name = name;
       this.description = description;
       this.permissionUsers = permissionUsers;
    }
   
    public Integer getIdPermission() {
        return this.idPermission;
    }
    
    public void setIdPermission(Integer idPermission) {
        this.idPermission = idPermission;
    }

    
    public int getPermission() {
        return this.permission;
    }
    
    public void setPermission(int permission) {
        this.permission = permission;
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

    public Set<PermissionUser> getPermissionUsers() {
        return this.permissionUsers;
    }
    
    public void setPermissionUsers(Set<PermissionUser> permissionUsers) {
        this.permissionUsers = permissionUsers;
    }




}


