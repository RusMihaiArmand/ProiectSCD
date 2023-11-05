package ro.mihai.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//user1      abc       $2a$10$NOSMxmgO/DrliAQQkrQ4Q.6XdaaQS0MgXyc8cGGEfXHWptCsNYLH6
//user2      1234      $2a$10$kv6MkENOP9KOnT/7HoWdlelgaixRfMMXNvYBBuenolg8HypT4YfOK

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "USERS")
public class User {

  @Id // PK
  @Column(name = "USERNAME")
  private String username;

  @Column(name = "PASSWORD")
  private String password;

}
