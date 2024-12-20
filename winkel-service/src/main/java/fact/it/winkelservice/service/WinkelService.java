package fact.it.winkelservice.service;

import fact.it.winkelservice.dto.*;
import fact.it.winkelservice.model.Album;
import fact.it.winkelservice.model.Winkel;
import fact.it.winkelservice.repository.AlbumRepository;
import fact.it.winkelservice.repository.WinkelRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WinkelService {

    private final WinkelRepository winkelRepository;
    private final AlbumRepository albumRepository; // Add this repository for album lookups

    @PostConstruct
    public void loadData() {
        // Same as before
    }

    public List<WinkelResponse> getWinkels() {
        return winkelRepository.findAll().stream()
                .map(this::mapToWinkelResponse)
                .collect(Collectors.toList());
    }

    public Optional<WinkelResponse> getWinkelById(Long id) {
        return winkelRepository.findById(id).map(this::mapToWinkelResponse);
    }

    public WinkelResponse addWinkel(WinkelRequest winkelRequest) {
        Winkel winkel = mapToWinkelEntity(winkelRequest);
        Winkel savedWinkel = winkelRepository.save(winkel);
        return mapToWinkelResponse(savedWinkel);
    }

    private Winkel mapToWinkelEntity(WinkelRequest request) {
        Winkel winkel = new Winkel();
        winkel.setName(request.getName());

        // Fetch album by ID and associate it with the Winkel
        Optional<Album> album = albumRepository.findById(request.getAlbumId());
        winkel.setAlbums(album.map(Collections::singletonList).orElse(Collections.emptyList()));

        return winkel;
    }

    private WinkelResponse mapToWinkelResponse(Winkel winkel) {
        return new WinkelResponse(
                winkel.getId(),
                winkel.getName(),
                winkel.getAlbums().stream()
                        .map(this::mapToAlbumResponse)
                        .collect(Collectors.toList())
        );
    }

    private AlbumResponse mapToAlbumResponse(Album album) {
        return new AlbumResponse(album.getId(), album.getAlbumName(), album.getArtiestId());
    }
}
