package tomatosolutions.najdiprevoz.services;

import tomatosolutions.najdiprevoz.models.auth.User;

public interface AuthService {
    String authenticateUser(String usernameOrEmail, String password);
    User registerUser(String username, String email, String password, String name);
    Boolean isUsernameAvailable(String username);
    Boolean isEmailAvailable(String email);
}
