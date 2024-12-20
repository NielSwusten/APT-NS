package fact.it.albumservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumRequest {
    private String artiestId;
    private String albumName;
    private LocalDate releaseDate;
}
