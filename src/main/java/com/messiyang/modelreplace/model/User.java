package com.messiyang.modelreplace.model;

import com.messiyang.modelreplace.util.EntityComment;
import lombok.Data;

@Data
public class User {
    @EntityComment("id")
    private String id;

    @EntityComment("userName")
    private String name;

    @EntityComment("realName")
    private String noname;

    @EntityComment("password")
    private String password1;


}