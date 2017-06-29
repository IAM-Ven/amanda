package com.timeyang.amanda.user.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.timeyang.amanda.core.jpa.domain.VersionedEntity;
import com.timeyang.amanda.core.valadation.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * 个人资料
 *
 * @author chaokunyang
 */
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
@AttributeOverride(name = "id", column = @Column(name = "profile_id"))
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE,
        fieldVisibility = JsonAutoDetect.Visibility.NONE,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE)
public class Profile extends VersionedEntity {

    /**
     * 头像地址
     */
    @JsonProperty
    private String avatar;

    /**
     * 个人姓名
     * 与username不同，username具有唯一性，可用于用户登录。
     * 而name不具备唯一约束
     */
    @JsonProperty
    private String name;
    // /**
    //  * 用户名
    //  */
    // @Column(nullable = false, unique = true)
    // @JsonProperty

    // private String username;

    /**
     * 引用user表主键
     */
    @Column(name = "user_id", insertable = false, updatable = false)
    @JsonProperty
    private Long userId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonProperty
    @Email
    private String email;

    /**
     * 个人简介
     */
    @JsonProperty
    private String biography ;

    /**
     * 个人网站
     */
    @JsonProperty
    private String url;

    /**
     * github账号
     */
    @JsonProperty
    private String githubUrl;

    /**
     * twitter账号
     */
    @JsonProperty
    private String twitterUrl;

    /**
     * 所在公司
     */
    @JsonProperty
    private String company;

    /**
     * 所在位置
     */
    @JsonProperty
    private String location;

    /**
     * 个人简介markdown文档，即详细简介
     */
    @Lob
    @JsonProperty
    private String mdBody;

    /**
     * 个人简介的markdown文本渲染后的html
     */
    @Lob
    @JsonProperty
    private String htmlBody;

    public Profile(String name, User user, String email, String biography, String url, String github, String twitter, String company, String location, String mdBody, String htmlBody, String avatar) {
        this.name = name;
        this.user = user;
        this.email = email;
        this.biography = biography;
        this.url = url;
        this.githubUrl = github;
        this.twitterUrl = twitter;
        this.company = company;
        this.location = location;
        this.mdBody = mdBody;
        this.htmlBody = htmlBody;
        this.avatar = avatar;
    }
}