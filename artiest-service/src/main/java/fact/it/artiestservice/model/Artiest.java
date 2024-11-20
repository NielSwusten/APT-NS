package fact.it.artiestservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.sql.Date;

@Document(value = "artiest")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Artiest {

    private String id;
    private String skuCode;
    private String name;
    private String description;
    private Date dateOfBirth;

}
