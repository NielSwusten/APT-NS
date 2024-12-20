package fact.it.albumservice;

import fact.it.albumservice.dto.AlbumRequest;
import fact.it.albumservice.dto.AlbumResponse;
import fact.it.albumservice.model.Album;
import fact.it.albumservice.repository.AlbumRepository;
import fact.it.albumservice.service.AlbumService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlbumServiceUnitTests {

    @InjectMocks
    private AlbumService albumService;

    @Mock
    private AlbumRepository albumRepository;

    @Test
    public void testGetAllAlbums() {
        // Arrange
        Album album1 = new Album(1L, "67436f443474646a729fac18", "Break", LocalDate.of(2006, 10, 1));
        Album album2 = new Album(2L, "67436f443474646a729fac19", "The One", LocalDate.of(1993, 1, 15));
        when(albumRepository.findAll()).thenReturn(List.of(album1, album2));

        // Act
        List<AlbumResponse> albumResponses = albumService.getAllAlbums();

        // Assert
        assertEquals(2, albumResponses.size());
        assertEquals("Break", albumResponses.get(0).getAlbumName());
        assertEquals("The One", albumResponses.get(1).getAlbumName());
        verify(albumRepository, times(1)).findAll();
    }

    @Test
    public void testGetAlbumById() {
        // Arrange
        Album album = new Album(1L, "67436f443474646a729fac18", "Break", LocalDate.of(2006, 10, 1));
        when(albumRepository.findById(1L)).thenReturn(Optional.of(album));

        // Act
        Optional<AlbumResponse> albumResponse = albumService.getAlbumById(1L);

        // Assert
        assertTrue(albumResponse.isPresent());
        assertEquals("Break", albumResponse.get().getAlbumName());
        verify(albumRepository, times(1)).findById(1L);
    }

    @Test
    public void testSaveAlbum() {
        // Arrange
        AlbumRequest albumRequest = new AlbumRequest("67436f443474646a729fac18", "Break", LocalDate.of(2006, 10, 1));
        Album album = new Album(null, "67436f443474646a729fac18", "Break", LocalDate.of(2006, 10, 1));
        Album savedAlbum = new Album(1L, "67436f443474646a729fac18", "Break", LocalDate.of(2006, 10, 1));

        // Use any() to avoid strict stubbing error
        when(albumRepository.save(any(Album.class))).thenReturn(savedAlbum);

        // Act
        AlbumResponse albumResponse = albumService.saveAlbum(albumRequest);

        // Assert
        assertEquals("Break", albumResponse.getAlbumName());
        assertEquals(1L, albumResponse.getId());
        verify(albumRepository, times(1)).save(any(Album.class));
    }

    @Test
    public void testUpdateAlbum() {
        // Arrange
        Album existingAlbum = new Album(1L, "67436f443474646a729fac18", "Old Name", LocalDate.of(2000, 1, 1));
        AlbumRequest albumRequest = new AlbumRequest("67436f443474646a729fac18", "New Name", LocalDate.of(2023, 12, 1));
        Album updatedAlbum = new Album(1L, "67436f443474646a729fac18", "New Name", LocalDate.of(2023, 12, 1));

        when(albumRepository.findById(1L)).thenReturn(Optional.of(existingAlbum));
        when(albumRepository.save(existingAlbum)).thenReturn(updatedAlbum);

        // Act
        AlbumResponse albumResponse = albumService.updateAlbum(1L, albumRequest);

        // Assert
        assertEquals("New Name", albumResponse.getAlbumName());
        assertEquals(LocalDate.of(2023, 12, 1), albumResponse.getReleaseDate());
        verify(albumRepository, times(1)).findById(1L);
        verify(albumRepository, times(1)).save(existingAlbum);
    }

    @Test
    public void testDeleteAlbum() {
        // Arrange
        Long albumId = 1L;
        doNothing().when(albumRepository).deleteById(albumId);

        // Act
        albumService.deleteAlbum(albumId);

        // Assert
        verify(albumRepository, times(1)).deleteById(albumId);
    }
}
//test