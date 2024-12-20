package fact.it.albumservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlbumResponse {
    private Long id;
    private String artiestId;
    private String albumName;
    private LocalDate releaseDate;
}