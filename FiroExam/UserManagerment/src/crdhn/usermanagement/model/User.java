package crdhn.usermanagement.model;
// Generated Jan 18, 2017 8:40:46 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User extends Model{

     private Integer id;
     private String username;
     private String password;
     private String email;
     private String fullname;
     private Date birthday;
     private String phone;
     private String gender;
     private Date createdDate;
     private Date activedDate;
     private Date updatedDate;
     private Integer status;
     private String passwordTemp;
     private String avatar;
     private Set<PermissionUser> permissionUsers = new HashSet<PermissionUser>(0);
     private Set<RoleUser> roleUsers = new HashSet<RoleUser>(0);

    public User() {
    }

	
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public User(String username, String password, String email, String fullname, Date birthday, String phone, String gender, Date createdDate, Date activedDate, Date updatedDate, Integer status, String passwordTemp, String avatar, Set<PermissionUser> permissionUsers, Set<RoleUser> roleUsers) {
       this.username = username;
       this.password = password;
       this.email = email;
       this.fullname = fullname;
       this.birthday = birthday;
       this.phone = phone;
       this.gender = gender;
       this.createdDate = createdDate;
       this.activedDate = activedDate;
       this.updatedDate = updatedDate;
       this.status = status;
       this.passwordTemp = passwordTemp;
       this.avatar = avatar;
       this.permissionUsers = permissionUsers;
       this.roleUsers = roleUsers;
    }
   

    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    public String getFullname() {
        return this.fullname;
    }
    
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getActivedDate() {
        return this.activedDate;
    }
    
    public void setActivedDate(Date activedDate) {
        this.activedDate = activedDate;
    }

    public Date getUpdatedDate() {
        return this.updatedDate;
    }
    
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    
    public String getPasswordTemp() {
        return this.passwordTemp;
    }
    
    public void setPasswordTemp(String passwordTemp) {
        this.passwordTemp = passwordTemp;
    }

    
    public String getAvatar() {
        return this.avatar;
    }
    
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Set<PermissionUser> getPermissionUsers() {
        return this.permissionUsers;
    }
    
    public void setPermissionUsers(Set<PermissionUser> permissionUsers) {
        this.permissionUsers = permissionUsers;
    }

    public Set<RoleUser> getRoleUsers() {
        return this.roleUsers;
    }
    
    public void setRoleUsers(Set<RoleUser> roleUsers) {
        this.roleUsers = roleUsers;
    }




}


