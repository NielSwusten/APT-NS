package fact.it.winkelservice.controller;

import fact.it.winkelservice.dto.WinkelResponse;
import fact.it.winkelservice.service.WinkelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/winkel")
@RequiredArgsConstructor
public class WinkelController {

    private final WinkelService winkelService;

    // http://localhost:8082/api/winkel?skuCode=tube6in&skuCode=beam10ft
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<WinkelResponse> isInStock
    (@RequestParam List<String> skuCode) {
        return winkelService.isInStock(skuCode);
    }
}