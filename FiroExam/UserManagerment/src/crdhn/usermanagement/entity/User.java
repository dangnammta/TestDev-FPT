package crdhn.usermanagement.entity;
// Generated Feb 7, 2017 3:19:26 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name="user"
    ,catalog="user_management"
)
public class User  implements java.io.Serializable {


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
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="username", nullable=false, length=100)
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    
    @Column(name="password", nullable=false)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    @Column(name="email", nullable=false)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="fullname", length=100)
    public String getFullname() {
        return this.fullname;
    }
    
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="birthday", length=19)
    public Date getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    
    @Column(name="phone", length=50)
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    @Column(name="gender", length=45)
    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date", length=19)
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="actived_date", length=19)
    public Date getActivedDate() {
        return this.activedDate;
    }
    
    public void setActivedDate(Date activedDate) {
        this.activedDate = activedDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_date", length=19)
    public Date getUpdatedDate() {
        return this.updatedDate;
    }
    
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    
    @Column(name="status")
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    
    @Column(name="password_temp")
    public String getPasswordTemp() {
        return this.passwordTemp;
    }
    
    public void setPasswordTemp(String passwordTemp) {
        this.passwordTemp = passwordTemp;
    }

    
    @Column(name="avatar", length=45)
    public String getAvatar() {
        return this.avatar;
    }
    
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<PermissionUser> getPermissionUsers() {
        return this.permissionUsers;
    }
    
    public void setPermissionUsers(Set<PermissionUser> permissionUsers) {
        this.permissionUsers = permissionUsers;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<RoleUser> getRoleUsers() {
        return this.roleUsers;
    }
    
    public void setRoleUsers(Set<RoleUser> roleUsers) {
        this.roleUsers = roleUsers;
    }




}


