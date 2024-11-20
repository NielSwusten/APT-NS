package fact.it.artiestservice.service;

import fact.it.artiestservice.*;
import fact.it.artiestservice.dto.ArtiestRequest;
import fact.it.artiestservice.dto.ArtiestResponse;
import fact.it.artiestservice.model.Artiest;
import fact.it.artiestservice.repository.ArtiestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtiestService {

    private final ArtiestRepository artiestRepository;

    public ArtiestService(ArtiestRepository artiestRepository) {
        this.artiestRepository = artiestRepository;
    }

    public void createArtiest(ArtiestRequest artiestRequest){
        Artiest artiest = Artiest.builder()
                .skuCode(artiestRequest.getSkuCode())
                .name(artiestRequest.getName())
                .description(artiestRequest.getDescription())
                .dateOfBirth(artiestRequest.getDateOfBirth())
                .build();

        artiestRepository.save(artiest);
    }

    public List<ArtiestResponse> getAllArtiests() {
        List<Artiest> artiests = artiestRepository.findAll();

        return artiests.stream().map(this::mapToArtiestResponse).toList();
    }

    public List<ArtiestResponse> getAllArtiestsBySkuCode(List<String> skuCode) {
        List<Artiest> artiests = artiestRepository.findBySkuCodeIn(skuCode);

        return artiests.stream().map(this::mapToArtiestResponse).toList();
    }

    private ArtiestResponse mapToArtiestResponse(Artiest artiest) {
        return ArtiestResponse.builder()
                .id(artiest.getId())
                .skuCode(artiest.getSkuCode())
                .name(artiest.getName())
                .description(artiest.getDescription())
                .dateOfBirth(artiest.getDateOfBirth())
                .build();
    }

}
