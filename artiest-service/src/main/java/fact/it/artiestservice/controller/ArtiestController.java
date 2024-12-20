package fact.it.artiestservice.controller;

import fact.it.artiestservice.dto.ArtiestRequest;
import fact.it.artiestservice.dto.ArtiestResponse;
import fact.it.artiestservice.service.ArtiestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artiest")
@RequiredArgsConstructor
public class ArtiestController {
    private final ArtiestService artiestService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ArtiestResponse> getAllArtiests() {
        return artiestService.getArtiests();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ArtiestResponse> getArtiestById(@PathVariable String id) {
        return artiestService.getArtiestById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArtiestResponse addArtiest(@RequestBody ArtiestRequest artiestRequest) {
        return artiestService.addArtiest(artiestRequest);
    }
}
