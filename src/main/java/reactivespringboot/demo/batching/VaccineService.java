package reactivespringboot.demo.batching;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class VaccineService {

    public Flux<Vaccine> getVaccines(){
        return Flux.just(new Vaccine("Pfizer"), new Vaccine("Moderna"), new Vaccine("J&J"), new Vaccine("Synophirm"));
    }
}
