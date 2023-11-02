package ro.mihai.security;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Radu Miron
 * @version 1
 */
@RestController // creates an instance of the current class
@RequestMapping("/login") // maps the requests starting with '/login' to this controller
public class LoginController {

    @PostMapping
    public JwtTokenDTO login(CredentialsDTO credentialsDTO) {
        // todo: validate credentials; you need to create a User entity and save users in DB;
        // the users should have at least the 'username' and the 'password' fields;
        // the password should be encrypted with BCrypt before saving it into the DB.
        // at the moment this endpoint returns a valid token for any credentials;
        // return the token if credentials are valid; throw UnauthorizedException otherwise
        JwtTokenDTO jwtTokenDTO = new JwtTokenDTO();
        jwtTokenDTO.setToken(JwtUtil.generateToken(credentialsDTO.getUsername()));
        return jwtTokenDTO;
    }
}
