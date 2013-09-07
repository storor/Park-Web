package ua.tolok.projects.project4.model.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
/**
 * UserInfo entity
 * @author Sergiy Tolok
 */

public class UserInfo implements Serializable {
    private Integer userInfoId;
    private Boolean role;
    private String login;
    private String password;
    private String firstName;
    private String surname;
    private String midleName;
    private Date regDate;
    private Collection<Owner> ownerCollection;
    private Collection<Forester> foresterCollection;

    public UserInfo() {
    }

    public UserInfo(Integer userInfoId) {
        this.userInfoId = userInfoId;
    }

    public UserInfo(Integer userInfoId, String login, String password, Date regDate) {
        this.userInfoId = userInfoId;
        this.login = login;
        this.password = password;
        this.regDate = regDate;
    }

    public Integer getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Integer userInfoId) {
        this.userInfoId = userInfoId;
    }
    
    public Boolean isOwner() {
        return role;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMidleName() {
        return midleName;
    }

    public void setMidleName(String midleName) {
        this.midleName = midleName;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Collection<Owner> getOwnerCollection() {
        return ownerCollection;
    }

    public void setOwnerCollection(Collection<Owner> ownerCollection) {
        this.ownerCollection = ownerCollection;
    }

    public Collection<Forester> getForesterCollection() {
        return foresterCollection;
    }

    public void setForesterCollection(Collection<Forester> foresterCollection) {
        this.foresterCollection = foresterCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userInfoId != null ? userInfoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserInfo)) {
            return false;
        }
        UserInfo other = (UserInfo) object;
        if ((this.userInfoId == null && other.userInfoId != null) || (this.userInfoId != null && !this.userInfoId.equals(other.userInfoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return login;
    }
    
}
