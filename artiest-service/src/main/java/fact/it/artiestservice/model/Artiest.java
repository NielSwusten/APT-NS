package fact.it.artiestservice.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity // Marks this as a JPA entity
@Table(name = "artiest") // Specifies the table name in the database
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Artiest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDate dateOfBirth;

}
