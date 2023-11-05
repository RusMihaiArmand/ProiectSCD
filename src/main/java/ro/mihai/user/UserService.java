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


  public User getUserByUsername(String username) {
    return userRepository.findById(username)
        .orElseThrow(() -> new CodeException(
            CodeException.CodeExceptionElement.builder()
                .errorDescription(ErrorCode.USER_NOT_FOUND).build()));
  }


  public void createUser(UserDTO newUser) {

    User us = userRepository.findById(newUser.getUsername()).orElse(null);

    if (us == null) {

      User toSave = new User();
      toSave.setUsername(newUser.getUsername());
      toSave.setPassword(passwordEncoder.encode(newUser.getPassword()));

      UserMapper.INSTANCE.mapUserEtyToUserDto(userRepository.save(toSave));

    } else {
      throw new CodeException(
          CodeException.CodeExceptionElement.builder()
              .errorDescription(ErrorCode.USERNAME_IN_USE).build());
    }

  }


  @Transactional
  public void edit(PasswordChangeData data) {

    User user = userRepository.findById(data.getUsername())
        .orElseThrow(() -> new CodeException(
            CodeException.CodeExceptionElement.builder()
                .errorDescription(ErrorCode.USER_NOT_FOUND).build()));

    if (!passwordEncoder.matches(data.getPassword(), user.getPassword())) {
      throw new CodeException(
          CodeException.CodeExceptionElement.builder()
              .errorDescription(ErrorCode.PASSWORD_NOT_MATCHING).build());
    }

    if (data.getPassword().equals(data.getNewPassword())) {
      throw new CodeException(
          CodeException.CodeExceptionElement.builder()
              .errorDescription(ErrorCode.SAME_PASSWORD).build());
    }

    user.setPassword(passwordEncoder.encode(data.getNewPassword()));

    UserMapper.INSTANCE.mapUserEtyToUserDto(userRepository.save(user));

  }

  @Transactional
  public void delete(UserDTO delUser) {

    User user = userRepository.findById(delUser.getUsername())
        .orElseThrow(() -> new CodeException(
            CodeException.CodeExceptionElement.builder()
                .errorDescription(ErrorCode.USER_NOT_FOUND).build()));

    if (!passwordEncoder.matches(delUser.getPassword(), user.getPassword())) {
      throw new CodeException(
          CodeException.CodeExceptionElement.builder()
              .errorDescription(ErrorCode.PASSWORD_NOT_MATCHING).build());
    }

    userRepository.deleteById(delUser.getUsername());
  }

}
