package fact.it.artiestservice.controller;


import fact.it.artiestservice.model.Artiest;
import fact.it.artiestservice.service.ArtiestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/artiest")
@RequiredArgsConstructor
public class ArtiestController {
    private final ArtiestService artiestService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Artiest> getAllArtiests(){
        return artiestService.getArtiests();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Artiest> getArtiestById(@PathVariable Long id) {
        return artiestService.getArtiestById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void addArtiest(@RequestBody Artiest artiest) {
        artiestService.addArtiest(artiest);
    }
}
