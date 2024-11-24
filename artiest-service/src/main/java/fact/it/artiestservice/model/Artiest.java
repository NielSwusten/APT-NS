package fact.it.artiestservice.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "artiest")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Artiest {

    @Id
    private Long id;
    private String name;
    private String description;
    private LocalDate dateOfBirth;

}
