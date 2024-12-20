package fact.it.artiestservice.service;

import fact.it.artiestservice.dto.ArtiestRequest;
import fact.it.artiestservice.dto.ArtiestResponse;
import fact.it.artiestservice.model.Artiest;
import fact.it.artiestservice.repository.ArtiestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArtiestServiceUnitTests {

    @InjectMocks
    private ArtiestService artiestService;

    @Mock
    private ArtiestRepository artiestRepository;

    private Artiest artiest;

    @BeforeEach
    public void setUp() {
        artiest = new Artiest("1", "Billy", "The one and only", LocalDate.of(2001, 2, 1));
    }

    @Test
    public void testGetArtiests() {
        // Arrange
        Artiest artiest2 = new Artiest("2", "Jason", "The only singer in town", LocalDate.of(1993, 11, 14));
        when(artiestRepository.findAll()).thenReturn(List.of(artiest, artiest2));

        // Act
        List<ArtiestResponse> artiestResponses = artiestService.getArtiests();

        // Assert
        assertEquals(2, artiestResponses.size());
        assertEquals("Billy", artiestResponses.get(0).getName());
        assertEquals("Jason", artiestResponses.get(1).getName());
        verify(artiestRepository, times(1)).findAll();
    }

    @Test
    public void testGetArtiestById() {
        // Arrange
        when(artiestRepository.findById("1")).thenReturn(Optional.of(artiest));

        // Act
        Optional<ArtiestResponse> result = artiestService.getArtiestById("1");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Billy", result.get().getName());
        verify(artiestRepository, times(1)).findById("1");
    }

    @Test
    public void testGetArtiestById_NotFound() {
        // Arrange
        when(artiestRepository.findById("999")).thenReturn(Optional.empty());

        // Act
        Optional<ArtiestResponse> result = artiestService.getArtiestById("999");

        // Assert
        assertFalse(result.isPresent());
        verify(artiestRepository, times(1)).findById("999");
    }

    @Test
    public void testAddArtiest() {
        // Arrange
        ArtiestRequest request = new ArtiestRequest("Todd", "Lost in space", LocalDate.of(1989, 7, 5));
        Artiest savedArtiest = new Artiest("2", "Todd", "Lost in space", LocalDate.of(1989, 7, 5));
        when(artiestRepository.save(any(Artiest.class))).thenReturn(savedArtiest);

        // Act
        ArtiestResponse response = artiestService.addArtiest(request);

        // Assert
        assertEquals("Todd", response.getName());
        assertEquals("Lost in space", response.getDescription());
        assertEquals("2", response.getId());
        verify(artiestRepository, times(1)).save(any(Artiest.class));
    }
}
