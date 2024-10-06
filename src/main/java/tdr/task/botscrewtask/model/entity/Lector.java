package tdr.task.botscrewtask.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;
import tdr.task.botscrewtask.model.enums.Degree;

import java.time.Instant;

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

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Instant created;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated")
    private Instant updated;
}
