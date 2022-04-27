package reactivespringboot.demo.batching;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class VaccineProvider {

    @Autowired
    private VaccineService vaccineService;

    public Flux<Vaccine> provideVaccines(){
        return vaccineService.getVaccines().map(this::delivered);
    }

    private Vaccine delivered(Vaccine vaccine){
        vaccine.setDelivered(true);
        return vaccine;
    }
}
