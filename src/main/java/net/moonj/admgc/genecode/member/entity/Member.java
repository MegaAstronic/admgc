package net.moonj.admgc.genecode.member.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author admgc
 * @since 2018-09-26
 */
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * identifier
     */
    private String id;

    private String nickname;

    private String username;

    private LocalDateTime regAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public LocalDateTime getRegAt() {
        return regAt;
    }

    public void setRegAt(LocalDateTime regAt) {
        this.regAt = regAt;
    }

    @Override
    public String toString() {
        return "Member{" +
        "id=" + id +
        ", nickname=" + nickname +
        ", username=" + username +
        ", regAt=" + regAt +
        "}";
    }
}
