package fact.it.albumservice.controller;

import fact.it.albumservice.dto.AlbumRequest;
import fact.it.albumservice.dto.AlbumResponse;
import fact.it.albumservice.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/album")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;

    // Get all albums
    @GetMapping
    public List<AlbumResponse> getAllAlbums() {
        return albumService.getAllAlbums();
    }

    // Get an album by ID
    @GetMapping("/{id}")
    public ResponseEntity<AlbumResponse> getAlbumById(@PathVariable Long id) {
        return albumService.getAlbumById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new album
    @PostMapping
    public AlbumResponse createAlbum(@RequestBody AlbumRequest albumRequest) {
        return albumService.saveAlbum(albumRequest);
    }

    // Update an existing album
    @PutMapping("/{id}")
    public ResponseEntity<AlbumResponse> updateAlbum(@PathVariable Long id, @RequestBody AlbumRequest albumRequest) {
        try {
            AlbumResponse albumResponse = albumService.updateAlbum(id, albumRequest);
            return ResponseEntity.ok(albumResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an album
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }
}