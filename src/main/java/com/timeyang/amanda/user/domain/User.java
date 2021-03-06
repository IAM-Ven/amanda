package com.timeyang.amanda.user.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.timeyang.amanda.authority.UserAuthority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.search.annotations.Field;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.util.Set;

/**
 * 用户
 *
 * @author chaokunyang
 * @create 2017-04-15
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE,
        fieldVisibility = JsonAutoDetect.Visibility.NONE,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE)
public class User implements UserDetails, CredentialsContainer, Cloneable {

    private long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String name;

    private byte[] hashedPassword;

    private Set<UserAuthority> authorities;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    // public User(String username, byte[] hashedPassword, Set<UserAuthority> authorities, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
    //     this.username = username;
    //     this.hashedPassword = hashedPassword;
    //     this.authorities = authorities;
    //     this.accountNonExpired = accountNonExpired;
    //     this.accountNonLocked = accountNonLocked;
    //     this.credentialsNonExpired = credentialsNonExpired;
    //     this.enabled = enabled;
    // }

    @JsonProperty
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Field
    @JsonProperty
    @Override
    public String getUsername() {
        return username;
    }

    @Basic(fetch = FetchType.LAZY)
    public byte[] getHashedPassword() {
        return hashedPassword;
    }

    @Transient
    @Override
    public String getPassword() {
        return hashedPassword == null ? null :
                new String(hashedPassword, StandardCharsets.UTF_8);
    }

    @Override
    public void eraseCredentials() {
        hashedPassword = null;
    }

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_authority", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    })
    @JsonProperty
    @Override
    public Set<UserAuthority> getAuthorities() {
        return authorities;
    }

    @JsonProperty
    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @JsonProperty
    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @JsonProperty
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHashedPassword(byte[] hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setAuthorities(Set<UserAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return username.equals(user.username);
    }

    @Field
    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public User clone() {
        try {
            return (User) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e); // not possible
        }
    }

    /**
     * 加载权限
     */
    public int loadAuthorities() {
        return authorities.size();
    }

    @Override
    public String toString() {
        return this.username;
    }

}
