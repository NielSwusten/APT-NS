package fact.it.winkelservice.controller;


import fact.it.winkelservice.model.Winkel;
import fact.it.winkelservice.service.WinkelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/api/winkel")
@RequestMapping("/winkel")

@RequiredArgsConstructor
public class WinkelController {
    private final WinkelService winkelService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Winkel> getAllWinkels(){
        return winkelService.getWinkels();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Winkel> getWinkelById(@PathVariable Long id) {
        return winkelService.getWinkelById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void addWinkel(@RequestBody Winkel winkel) {
        winkelService.addWinkel(winkel);
    }
}
