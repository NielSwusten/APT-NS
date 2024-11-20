package fact.it.artiestservice.controller;

import fact.it.artiestservice.dto.ArtiestRequest;
import fact.it.artiestservice.dto.ArtiestResponse;
import fact.it.artiestservice.service.ArtiestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/artiest")
@RequiredArgsConstructor
public class ArtiestController {

    private  ArtiestService artiestService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createArtiest
            (@RequestBody ArtiestRequest artiestRequest) {
        artiestService.createArtiest(artiestRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ArtiestResponse> getAllArtiestsBySkuCode
            (@RequestParam List<String> skuCode) {
        return artiestService.getAllArtiestsBySkuCode(skuCode);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ArtiestResponse> getAllArtiesten() {
        return artiestService.getAllArtiests();
    }
}

