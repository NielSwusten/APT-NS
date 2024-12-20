package fact.it.winkelservice.service;

import fact.it.winkelservice.dto.WinkelRequest;
import fact.it.winkelservice.dto.WinkelResponse;
import fact.it.winkelservice.model.Winkel;
import fact.it.winkelservice.repository.WinkelRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WinkelService {

    private final WinkelRepository winkelRepository;

    public void clearAllData() {
        winkelRepository.deleteAll();
    }

    @PostConstruct
    public void loadData() {
        clearAllData(); // Ensure all old data is removed before loading new data

        List<Winkel> winkelList = List.of(
                new Winkel(null, "mediamarkt", 1L),
                new Winkel(null, "ebay", 2L)
        );

        winkelRepository.saveAll(winkelList);
    }

    public List<WinkelResponse> getWinkels() {
        return winkelRepository.findAll()
                .stream()
                .map(this::toWinkelResponse)
                .collect(Collectors.toList());
    }

    public Optional<WinkelResponse> getWinkelById(Long id) {
        return winkelRepository.findById(id)
                .map(this::toWinkelResponse);
    }

    public WinkelResponse addWinkel(WinkelRequest winkelRequest) {
        Winkel winkel = toWinkel(winkelRequest);
        return toWinkelResponse(winkelRepository.save(winkel));
    }

    private Winkel toWinkel(WinkelRequest request) {
        return new Winkel(null, request.getName(), request.getAlbumId());
    }

    private WinkelResponse toWinkelResponse(Winkel winkel) {
        return new WinkelResponse(winkel.getId(), winkel.getName(), winkel.getAlbumId());
    }
}
