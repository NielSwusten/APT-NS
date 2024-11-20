package fact.it.artiestservice.model;
import java.time.LocalDate;

import lombok.*;

import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.sql.Date;

@Document(collection = "artiest")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class Artiest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String description;
    private LocalDate dateOfBirth;

}
