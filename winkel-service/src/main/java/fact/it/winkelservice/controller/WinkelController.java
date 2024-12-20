package fact.it.winkelservice.controller;

import fact.it.winkelservice.dto.*;
import fact.it.winkelservice.service.WinkelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/winkel")
@RequiredArgsConstructor
public class WinkelController {
    private final WinkelService winkelService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<WinkelResponse> getAllWinkels() {
        return winkelService.getWinkels();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<WinkelResponse> getWinkelById(@PathVariable Long id) {
        return winkelService.getWinkelById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WinkelResponse addWinkel(@RequestBody WinkelRequest winkelRequest) {
        return winkelService.addWinkel(winkelRequest);
    }
}
