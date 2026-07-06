package ar.edu.utn.frba.ddsi.clima.models.repositories;

import ar.edu.utn.frba.ddsi.clima.models.entities.RegistroClima;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class RegistroClimaRepositories {
  private final List<RegistroClima> registros = new ArrayList<>();

  public void guardar(RegistroClima registro) {
    registros.add(registro);
  }

  public Optional<RegistroClima> obtenerUltimo() {
    if(registros.isEmpty()){
      return Optional.empty();
    }
    return Optional.of(registros.getLast());

  }

}
