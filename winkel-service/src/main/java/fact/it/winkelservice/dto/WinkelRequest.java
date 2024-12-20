package fact.it.winkelservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WinkelRequest {
    private String name;
    private Long albumId; // Single album ID
}
