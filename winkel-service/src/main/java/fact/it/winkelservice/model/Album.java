package fact.it.albumservice.model;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "albums")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String artiestId;
    private String albumName;
    private LocalDate releaseDate;


}

