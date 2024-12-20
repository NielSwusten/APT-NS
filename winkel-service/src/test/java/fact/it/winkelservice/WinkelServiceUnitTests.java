package fact.it.winkelservice.service;

import fact.it.winkelservice.dto.WinkelRequest;
import fact.it.winkelservice.dto.WinkelResponse;
import fact.it.winkelservice.model.Winkel;
import fact.it.winkelservice.repository.WinkelRepository;
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
public class WinkelServiceUnitTests {

    @InjectMocks
    private WinkelService winkelService;

    @Mock
    private WinkelRepository winkelRepository;

    private Winkel winkel;

    @BeforeEach
    public void setUp() {
        winkel = new Winkel(1L, "Mediamarkt", 1L);
    }

    @Test
    public void testGetWinkels() {
        // Arrange
        Winkel winkel2 = new Winkel(2L, "eBay", 2L);
        when(winkelRepository.findAll()).thenReturn(List.of(winkel, winkel2));

        // Act
        List<WinkelResponse> winkelResponses = winkelService.getWinkels();

        // Assert
        assertEquals(2, winkelResponses.size());
        assertEquals("Mediamarkt", winkelResponses.get(0).getName());
        assertEquals("eBay", winkelResponses.get(1).getName());
        verify(winkelRepository, times(1)).findAll();
    }

    @Test
    public void testGetWinkelById() {
        // Arrange
        when(winkelRepository.findById(1L)).thenReturn(Optional.of(winkel));

        // Act
        Optional<WinkelResponse> result = winkelService.getWinkelById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Mediamarkt", result.get().getName());
        verify(winkelRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetWinkelById_NotFound() {
        // Arrange
        when(winkelRepository.findById(999L)).thenReturn(Optional.empty());

        // Act
        Optional<WinkelResponse> result = winkelService.getWinkelById(999L);

        // Assert
        assertFalse(result.isPresent());
        verify(winkelRepository, times(1)).findById(999L);
    }

    @Test
    public void testAddWinkel() {
        // Arrange
        WinkelRequest request = new WinkelRequest("Amazon", 3L);
        Winkel savedWinkel = new Winkel(3L, "Amazon", 3L);
        when(winkelRepository.save(any(Winkel.class))).thenReturn(savedWinkel);

        // Act
        WinkelResponse response = winkelService.addWinkel(request);

        // Assert
        assertEquals("Amazon", response.getName());
        assertEquals(3L, response.getAlbumId());
        assertEquals(3L, response.getId());
        verify(winkelRepository, times(1)).save(any(Winkel.class));
    }

    @Test
    public void testClearAllData() {
        // Act
        winkelService.clearAllData();

        // Assert
        verify(winkelRepository, times(1)).deleteAll();
    }
}
