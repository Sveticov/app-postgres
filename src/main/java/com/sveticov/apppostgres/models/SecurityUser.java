package com.sveticov.apppostgres.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
public class SecurityUser {
    @NonNull
    @Pattern(regexp = "[a-zA-Z]+", message = "user error")
    private String user_sec;
    @NonNull
    @Pattern(regexp = "[a-zA-Z]+", message = "password error")
    private String password_sec;

    public SecurityUser() {
    }

    public SecurityUser(String user_sec, String password_sec) {
        this.user_sec = user_sec;
        this.password_sec = password_sec;
    }
}
