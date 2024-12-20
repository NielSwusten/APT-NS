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
import org.junit.jupiter.api.extension.ExtendWith;

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
        artiest = new Artiest(1L, "John Doe", 1L);
    }

    @Test
    public void testGetArtiesten() {
        // Arrange
        Artiest artiest2 = new Artiest(2L, "Jane Doe", 2L);
        when(artiestRepository.findAll()).thenReturn(List.of(artiest, artiest2));

        // Act
        List<ArtiestResponse> artiestResponses = artiestService.getArtiesten();

        // Assert
        assertEquals(2, artiestResponses.size());
        assertEquals("John Doe", artiestResponses.get(0).getName());
        assertEquals("Jane Doe", artiestResponses.get(1).getName());
        verify(artiestRepository, times(1)).findAll();
    }

    @Test
    public void testGetArtiestById() {
        // Arrange
        when(artiestRepository.findById(1L)).thenReturn(Optional.of(artiest));

        // Act
        Optional<ArtiestResponse> result = artiestService.getArtiestById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
        verify(artiestRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetArtiestById_NotFound() {
        // Arrange
        when(artiestRepository.findById(999L)).thenReturn(Optional.empty());

        // Act
        Optional<ArtiestResponse> result = artiestService.getArtiestById(999L);

        // Assert
        assertFalse(result.isPresent());
        verify(artiestRepository, times(1)).findById(999L);
    }

    @Test
    public void testAddArtiest() {
        // Arrange
        ArtiestRequest request = new ArtiestRequest("Ariana Grande", 3L);
        Artiest savedArtiest = new Artiest(3L, "Ariana Grande", 3L);
        when(artiestRepository.save(any(Artiest.class))).thenReturn(savedArtiest);

        // Act
        ArtiestResponse response = artiestService.addArtiest(request);

        // Assert
        assertEquals("Ariana Grande", response.getName());
        assertEquals(3L, response.getAlbumId());
        assertEquals(3L, response.getId());
        verify(artiestRepository, times(1)).save(any(Artiest.class));
    }

    @Test
    public void testClearAllData() {
        // Act
        artiestService.clearAllData();

        // Assert
        verify(artiestRepository, times(1)).deleteAll();
    }
}
