package ro.mihai.terminal;

//import edu.tucn.scd.serverapp.position.Position;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import ro.mihai.position.*;

import java.util.List;

/**
 * @author Radu Miron
 * @version 1
 */

@Entity// maps class to DB table, object to table row, attribute to column
@Data // lombok annotation, creates equals(), hashCode() and toString at compile time
public class Terminal {
    @Id // PK
    private String id;

    private String terminalName;



//    @OneToMany(mappedBy = "terminal") // maps the one-to many relationship; each terminal has multiple positions
//    private List<Position> positions;

}
