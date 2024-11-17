package com.otdot.hgm.utils;


import com.otdot.hgm.documents.User;
import com.otdot.hgm.security.UserDetailsImpl;
import com.otdot.hgm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextUtils {

    @Autowired
    UserService userService;

    public User getUserFromSecurityContext() {
        try {
            UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            User user = userService.findUserById(userDetails.getId()).orElse(null);
            if (user == null) {
                System.out.println("User not found");
            }
            return user;
        } catch (Exception e) {
            System.out.printf("Error occurred. Message: %s Cause: %s", e.getMessage(), e.getCause());
            return null;
        }
    }

}
