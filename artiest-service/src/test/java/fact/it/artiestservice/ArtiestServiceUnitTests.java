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
        artiest = new Artiest("1", "John Doe", "A description", LocalDate.of(1990, 1, 1));  // Correct constructor
    }

    @Test
    public void testGetArtiesten() {
        // Arrange
        Artiest artiest2 = new Artiest("2", "Jane Doe", "Another description", LocalDate.of(1985, 5, 15));
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
        when(artiestRepository.findById("1")).thenReturn(Optional.of(artiest));

        // Act
        Optional<ArtiestResponse> result = artiestService.getArtiestById("1");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
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
        ArtiestRequest request = new ArtiestRequest("Ariana Grande", "Singer", LocalDate.of(1993, 6, 26));
        Artiest savedArtiest = new Artiest("3", "Ariana Grande", "Singer", LocalDate.of(1993, 6, 26));
        when(artiestRepository.save(any(Artiest.class))).thenReturn(savedArtiest);

        // Act
        ArtiestResponse response = artiestService.addArtiest(request);

        // Assert
        assertEquals("Ariana Grande", response.getName());
        assertEquals("Singer", response.getDescription());
        assertEquals("3", response.getId());  // Corrected to match the id type
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
