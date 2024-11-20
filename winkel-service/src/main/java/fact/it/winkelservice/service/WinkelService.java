package fact.it.winkelservice.service;

import fact.it.winkelservice.dto.WinkelResponse;
import fact.it.winkelservice.model.Winkel;
import fact.it.winkelservice.repository.WinkelRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WinkelService {

    private final WinkelRepository winkelRepository;

    @PostConstruct
    @Transactional
    public void loadData() {
        System.out.println(winkelRepository.count());
        if(winkelRepository.count() <= 0){
            Winkel winkel = new Winkel();
            winkel.setSkuCode("tube6in");
            winkel.setName("Tube 6 Inch");
            winkel.setQuantity(100);

            Winkel winkel1 = new Winkel();
            winkel1.setSkuCode("beam10ft");
            winkel1.setName("Beam 10 Feet");
            winkel1.setQuantity(0);

            winkelRepository.save(winkel);
            winkelRepository.save(winkel1);
        }
    }


    @Transactional(readOnly = true)
    public List<WinkelResponse> isInStock(List<String> skuCode) {

        return winkelRepository.findBySkuCodeIn(skuCode).stream()
                .map(winkel ->
                        WinkelResponse.builder()
                                .skuCode(winkel.getSkuCode())
                                .build()
                ).toList();
    }
}
