package fact.it.artiestservice.model;

import lombok.*;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "artiest")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Artiest {

    @Id
    private Long id; // Changed from String to Long
    private String name;
    private String description;
    private LocalDate dateOfBirth;
}
