package fact.it.artiestservice.service;

import fact.it.artiestservice.dto.ArtiestRequest;
import fact.it.artiestservice.dto.ArtiestResponse;
import fact.it.artiestservice.model.Artiest;
import fact.it.artiestservice.repository.ArtiestRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtiestService {

    private final ArtiestRepository artiestRepository;

    @PostConstruct
    public void loadData() {
        if (artiestRepository.count() <= 0) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            List<Artiest> artiestList = List.of(
                    new Artiest(1L, "billy", "the one and only", LocalDate.parse("2001-02-01", formatter)),
                    new Artiest(2L, "jason", "The only singer in town", LocalDate.parse("1993-11-14", formatter)),
                    new Artiest(3L, "todd hill", "lost in space", LocalDate.parse("1989-07-05", formatter)),
                    new Artiest(4L, "bernie", "too old to sing", LocalDate.parse("1952-02-18", formatter))
            );

            artiestRepository.saveAll(artiestList);
        }
    }

    public List<ArtiestResponse> getArtiests() {
        return artiestRepository.findAll().stream()
                .map(this::mapToArtiestResponse)
                .collect(Collectors.toList());
    }

    public Optional<ArtiestResponse> getArtiestById(Long id) {
        return artiestRepository.findById(id).map(this::mapToArtiestResponse);
    }

    public ArtiestResponse addArtiest(ArtiestRequest artiestRequest) {
        Artiest artiest = mapToArtiestEntity(artiestRequest);
        artiest.setId(generateNewId()); // Generate a new numeric ID
        Artiest savedArtiest = artiestRepository.save(artiest);
        return mapToArtiestResponse(savedArtiest);
    }

    private Long generateNewId() {
        return artiestRepository.count() + 1; // Simple ID generation strategy
    }

    private Artiest mapToArtiestEntity(ArtiestRequest request) {
        return new Artiest(null, request.getName(), request.getDescription(), request.getDateOfBirth());
    }

    private ArtiestResponse mapToArtiestResponse(Artiest artiest) {
        return new ArtiestResponse(
                artiest.getId(),
                artiest.getName(),
                artiest.getDescription(),
                artiest.getDateOfBirth()
        );
    }
}
