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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // Correct format

            List<Artiest> artiestList = List.of(
                    new Artiest(null, "billy", "the one and only", LocalDate.parse("2001-02-01", formatter)),
                    new Artiest(null, "jason", "The only singer in town", LocalDate.parse("1993-11-14", formatter)),
                    new Artiest(null, "todd hill", "lost in space", LocalDate.parse("1989-07-05", formatter)),
                    new Artiest(null, "bernie", "too old to sing", LocalDate.parse("1952-02-18", formatter)) // No trailing comma
            );

            artiestRepository.saveAll(artiestList);
        }
    }


    public List<Artiest> getArtiests(){
        return artiestRepository.findAll();
    }

    public Optional<Artiest> getArtiestById(Long id) {
        return artiestRepository.findById(id);
    }


    public Artiest addArtiest(Artiest artiest) {
        return artiestRepository.save(artiest);
    }
}
