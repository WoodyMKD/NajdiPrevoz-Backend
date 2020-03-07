package tomatosolutions.najdiprevoz.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tomatosolutions.najdiprevoz.models.auth.User;
import tomatosolutions.najdiprevoz.repositories.UserRepository;
import tomatosolutions.najdiprevoz.security.CurrentUser;
import tomatosolutions.najdiprevoz.security.UserPrincipal;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/me")
    public UserPrincipal getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return currentUser;
    }
}
