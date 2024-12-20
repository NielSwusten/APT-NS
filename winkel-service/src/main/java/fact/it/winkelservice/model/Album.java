package fact.it.winkelservice.model;
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

    // Add this constructor
    public Album(Long id, String artiestId, String albumName) {
        this.id = id;
        this.artiestId = artiestId;
        this.albumName = albumName;
    }
}

