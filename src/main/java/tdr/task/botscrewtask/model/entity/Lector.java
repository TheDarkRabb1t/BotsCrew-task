package tdr.task.botscrewtask.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import tdr.task.botscrewtask.model.enums.Degree;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Enumerated
    @Column(name = "degree", nullable = false)
    @JdbcTypeCode(SqlTypes.SMALLINT)
    private Degree degree;

    @Column(name = "salary", nullable = false)
    private Double salary;

}
