package fact.it.winkelservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "winkel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Winkel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Change from a single ID to a list of album IDs
    @ElementCollection
    @CollectionTable(name = "winkel_albums", joinColumns = @JoinColumn(name = "winkel_id"))
    @Column(name = "album_id")
    private List<Long> albumIds; // List to store multiple album IDs
}
