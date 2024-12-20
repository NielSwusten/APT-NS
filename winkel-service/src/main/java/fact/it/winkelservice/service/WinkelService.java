package fact.it.winkelservice.service;
import java.time.LocalDate;  // Add this import

import fact.it.winkelservice.dto.*;
import fact.it.winkelservice.model.Album;
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

    @PostConstruct
    public void loadData() {
        if (winkelRepository.count() <= 0) {
            Winkel winkel1 = new Winkel();
            winkel1.setName("MediaMarkt");
            winkel1.setAlbums(List.of(
                    new Album(null, "2", "Break", LocalDate.of(2023, 1, 15)),
                    new Album(null, "1", "The One", LocalDate.of(2022, 12, 10))
            ));

            Winkel winkel2 = new Winkel();
            winkel2.setName("eBay");
            winkel2.setAlbums(List.of(
                    new Album(null, "2", "Dance Off", LocalDate.of(2024, 5, 20))
            ));

            winkelRepository.saveAll(List.of(winkel1, winkel2));
        }
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
        winkel.setAlbums(request.getAlbums().stream()
                .map(this::mapToAlbumEntity)
                .collect(Collectors.toList()));
        return winkel;
    }

    private Album mapToAlbumEntity(AlbumRequest request) {
        return new Album(null, request.getArtistId(), request.getName(), LocalDate.now()); // Provide a default date
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
