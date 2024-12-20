package fact.it.winkelservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WinkelResponse {
    private Long id;
    private String name;
    private List<AlbumResponse> albums; // List of album responses
}

