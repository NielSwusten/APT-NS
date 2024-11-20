package fact.it.winkelservice.service;


import fact.it.winkelservice.model.Winkel;
import fact.it.winkelservice.repository.WinkelRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WinkelService {

    private final WinkelRepository winkelRepository;

    @PostConstruct
    public void loadData() {
        if (winkelRepository.count() <= 0) {

            List<Winkel> winkelList = List.of(

                    new Winkel(null, "mediamarkt"),
                    new Winkel(null, "ebay"),
                    );

            winkelRepository.saveAll(winkelList);

        }
    }

    public List<Winkel> getWinkels(){
        return winkelRepository.findAll();
    }

    public Optional<Winkel> getWinkelById(String id) {
        return winkelRepository.findById(id);
    }


    public Winkel addWinkel(Winkel winkel) {
        return winkelRepository.save(winkel);
    }
}
