package crdhn.usermanagement.model;
// Generated Feb 7, 2017 3:19:26 PM by Hibernate Tools 4.3.1


public class PermissionUser  implements java.io.Serializable {


     private Integer id;
     private Permission permission;
     private User user;

    public PermissionUser() {
    }

    public PermissionUser(Permission permission, User user) {
       this.permission = permission;
       this.user = user;
    }
   

    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Permission getPermission() {
        return this.permission;
    }
    
    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }




}

