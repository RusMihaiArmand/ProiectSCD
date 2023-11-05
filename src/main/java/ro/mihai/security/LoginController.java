package ro.mihai.security;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.mihai.exceptions.CodeException;
import ro.mihai.exceptions.ErrorCode;
import ro.mihai.user.User;
import ro.mihai.user.UserService;


@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {


    private final PasswordEncoder passwordEncoder;
    private final UserService userService;


    @PostMapping
    public JwtTokenDTO login(@Valid @RequestBody CredentialsDTO credentialsDTO) throws Exception {

//        String encodedPass;
//        encodedPass = passwordEncoder.encode("1234");
//        System.out.println(encodedPass);


        String usName = credentialsDTO.getUsername();
        User user = userService.getUserByUsername(usName);

        String usPass = credentialsDTO.getPassword();

        if(passwordEncoder.matches(usPass, user.getPassword()))
        {
            JwtTokenDTO jwtTokenDTO = new JwtTokenDTO();
            jwtTokenDTO.setToken(JwtUtil.generateToken(credentialsDTO.getUsername()));
            return jwtTokenDTO;
        }
        else
        {
            throw new CodeException(
                CodeException.CodeExceptionElement.builder()
                    .errorDescription(ErrorCode.PASSWORD_NOT_MATCHING).build());
        }


    }
}
