package ro.mihai.terminal;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.mihai.position.*;

import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "TERMINAL")
public class Terminal {

  @Id // PK
  @Column(name = "ID")
  private String id;

  @Column(name = "NAME")
  private String terminalName;


  @OneToMany(mappedBy = "terminal")
  private List<Position> positions;

}
