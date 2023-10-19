package ro.mihai.position;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

/**
 * @author Radu Miron
 * @version 1
 */

@Entity
@SequenceGenerator(name = "POSITION_ID_SQ", sequenceName = "POSITION_ID_SQ", allocationSize = 1)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "POSITION")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POSITION_ID_SQ")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "LATITUDE")
    private String latitude;
    @Column(name = "LONGITUDE")
    private String longitude;
    @Column(name = "TERMINAL_ID")
    private String terminalId;

    @Column(updatable = false, nullable = false, name="CREATION_DATE")
    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date creationDate;
}

//@Entity
//@Data
//public class Position {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    private Integer id;
//
//    private String latitude;
//    private String longitude;
//    private String terminalId;
//
//    @Column(updatable = false, nullable = false)
//    @CreationTimestamp
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    private Date creationDate;
//}