package ro.mihai.user;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;


  @PostMapping
  public ResponseEntity<Void> create(@Valid @RequestBody UserDTO newUser) {

    userService.createUser(newUser);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping
  public ResponseEntity<Void> edit(@Valid @RequestBody PasswordChangeData data) {

    userService.edit(data);

    return ResponseEntity.noContent().build();
  }


  @DeleteMapping
  public ResponseEntity<Void> delete(@Valid @RequestBody UserDTO user) {

    userService.delete(user);
    return ResponseEntity.noContent().build();
  }

}
