import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AlbumService {

    private final AlbumRepository albumRepository;

    @PostConstruct
    public void loadData() {
        if (albumRepository.count() == 0) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-d-M");  // Updated pattern for single digit month/day

            // Use LocalDate.parse to parse the release date string
            List<Album> albumList = List.of(
                    new Album(null, "2", "Break", LocalDate.parse("2006-21-10", formatter)),
                    new Album(null, "1", "The one", LocalDate.parse("1993-14-11", formatter)),
                    new Album(null, "1", "final count", LocalDate.parse("1989-5-5", formatter)),
                    new Album(null, "3", "dusk til dawn", LocalDate.parse("1999-8-1", formatter)),
                    new Album(null, "3", "breakout", LocalDate.parse("2005-8-12", formatter)),
                    new Album(null, "2", "dance off", LocalDate.parse("1999-18-10", formatter)),
                    new Album(null, "4", "fall off", LocalDate.parse("1999-18-2", formatter))
            );

            albumRepository.saveAll(albumList);
        }
    }

    // Get all albums
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    // Get a single album by ID
    public Optional<Album> getAlbumById(Long id) {
        return albumRepository.findById(id);
    }

    // Create a new album
    public Album saveAlbum(Album album) {
        return albumRepository.save(album);
    }

    // Update an existing album
    public Album updateAlbum(Long id, Album updatedAlbum) {
        return albumRepository.findById(id).map(album -> {
            album.setAlbumName(updatedAlbum.getAlbumName());
            album.setReleaseDate(updatedAlbum.getReleaseDate());
            album.setArtiestId(updatedAlbum.getArtiestId());

            return albumRepository.save(album);
        }).orElseThrow(() -> new RuntimeException("Album not found"));
    }

    // Delete an album by ID
    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }
}
