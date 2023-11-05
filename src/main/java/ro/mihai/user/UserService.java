package ro.mihai.user;


import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.mihai.exceptions.CodeException;
import ro.mihai.exceptions.ErrorCode;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;


  public User getUserByUsername(String username) throws Exception {
    return userRepository.findById(username)
        .orElseThrow(() -> new CodeException(
            CodeException.CodeExceptionElement.builder()
                .errorDescription(ErrorCode.USER_NOT_FOUND).build()));
  }

}
