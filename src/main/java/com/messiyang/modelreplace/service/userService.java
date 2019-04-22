package com.messiyang.modelreplace.service;

import com.messiyang.modelreplace.model.NewUser;


import java.util.List;

public interface userService {
    List<NewUser> exportUserInfo(String date);
}
