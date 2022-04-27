package reactivespringboot.demo.batching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class VaccineController  {

    @Autowired
    private VaccineService vaccineService;

    @GetMapping("/vaccines")
    public Flux<Vaccine> getVaccines(){
        return vaccineService.getVaccines();
    }

}
