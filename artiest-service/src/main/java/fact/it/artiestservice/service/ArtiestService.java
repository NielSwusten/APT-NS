package fact.it.artiestservice.service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import fact.it.artiestservice.model.Artiest;
import fact.it.artiestservice.repository.ArtiestRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtiestService {

    private final ArtiestRepository artiestRepository;

    @PostConstruct
    public void loadData() {
        if (artiestRepository.count() <= 0) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");  // Adjusted to your format

                List<Artiest> artiestList = List.of(

                        new Artiest(null, "billy", "the one and only", LocalDate.parse("2001-2-1", formatter)),
                        new Artiest(null, "jason", "The only singer in town", LocalDate.parse("1993-14-11", formatter)),
                        new Artiest(null, "todd hill", "lost in space", LocalDate.parse("1989-7-5", formatter)),
                        new Artiest(null, "bernie", "to old to sing", LocalDate.parse("1952-18-2", formatter)),
                );

                artiestRepository.saveAll(artiestList);

        }
    }

    public List<Artiest> getArtiests(){
        return artiestRepository.findAll();
    }

    public Optional<Artiest> getArtiestById(String id) {
        return artiestRepository.findById(id);
    }


    public Artiest addArtiest(Artiest artiest) {
        return artiestRepository.save(artiest);
    }
}
