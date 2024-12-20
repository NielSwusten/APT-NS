package fact.it.artiestservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtiestResponse {
    private String name;
    private String description;
    private LocalDate dateOfBirth;
}
