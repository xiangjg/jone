package com.jone.api.system;

import com.jone.entity.system.User;

public interface UserService {
    User findByUserName(String userName);
}
