package ro.mihai.position;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import ro.mihai.terminal.Terminal;


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
//    @Column(name = "TERMINAL_ID")
//    private String terminalId;

    @ManyToOne
    @JoinColumn(name = "TERMINAL_ID")
    private Terminal terminal;

    @Column(updatable = false, nullable = false, name="CREATION_DATE")
    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date creationDate;
}

