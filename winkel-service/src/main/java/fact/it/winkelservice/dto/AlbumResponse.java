package fact.it.winkelservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumResponse {
    private Long id;
    private String name;
    private String artistId;
}
