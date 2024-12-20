package fact.it.albumservice.service;

import fact.it.albumservice.dto.AlbumRequest;
import fact.it.albumservice.dto.AlbumResponse;
import fact.it.albumservice.model.Album;
import fact.it.albumservice.repository.AlbumRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AlbumService {

    private final AlbumRepository albumRepository;

    public void clearAllData() {
        winkelRepository.deleteAll();
    }

    @PostConstruct
    public void loadData() {
        clearAllData(); // Ensure all old data is removed before loading new data

        if (albumRepository.count() == 0) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            List<Album> albumList = List.of(
                    new Album(null, "67436f443474646a729fac18", "Break", LocalDate.parse("2006-10-01", formatter)),
                    new Album(null, "67436f443474646a729fac19", "The one", LocalDate.parse("1993-01-15", formatter)),
                    new Album(null, "67436f443474646a729fac18", "final count", LocalDate.parse("1989-05-06", formatter)),
                    new Album(null, "67436f443474646a729fac19", "dusk til dawn", LocalDate.parse("1999-09-01", formatter)),
                    new Album(null, "67436f443474646a729fac1b", "breakout", LocalDate.parse("2005-08-12", formatter)),
                    new Album(null, "67436f443474646a729fac18", "dance off", LocalDate.parse("1999-10-10", formatter)),
                    new Album(null, "67436f443474646a729fac1b", "fall off", LocalDate.parse("1999-08-02", formatter))
            );

            albumRepository.saveAll(albumList);
        }
    }

    // Get all albums
    public List<AlbumResponse> getAllAlbums() {
        return albumRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Get a single album by ID
    public Optional<AlbumResponse> getAlbumById(Long id) {
        return albumRepository.findById(id).map(this::mapToDto);
    }

    // Create a new album
    public AlbumResponse saveAlbum(AlbumRequest albumRequest) {
        Album album = mapToEntity(albumRequest);
        Album savedAlbum = albumRepository.save(album);
        return mapToDto(savedAlbum);
    }

    // Update an existing album
    public AlbumResponse updateAlbum(Long id, AlbumRequest albumRequest) {
        return albumRepository.findById(id).map(album -> {
            album.setAlbumName(albumRequest.getAlbumName());
            album.setReleaseDate(albumRequest.getReleaseDate());
            album.setArtiestId(albumRequest.getArtiestId());

            Album updatedAlbum = albumRepository.save(album);
            return mapToDto(updatedAlbum);
        }).orElseThrow(() -> new RuntimeException("Album not found"));
    }

    // Delete an album by ID
    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }

    // Helper methods
    private AlbumResponse mapToDto(Album album) {
        return AlbumResponse.builder()
                .id(album.getId())
                .artiestId(album.getArtiestId())
                .albumName(album.getAlbumName())
                .releaseDate(album.getReleaseDate())
                .build();
    }

    private Album mapToEntity(AlbumRequest albumRequest) {
        return new Album(null, albumRequest.getArtiestId(), albumRequest.getAlbumName(), albumRequest.getReleaseDate());
    }
}