package ar.edu.utn.frba.ddsi.clima.batch;

import ar.edu.utn.frba.ddsi.clima.models.repositories.RegistroClimaRepositories;
import ar.edu.utn.frba.ddsi.clima.services.AlertaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AlertaProcesadorJob {
  private final RegistroClimaRepositories climaRepositories;
  private final AlertaService alertaService;

  public boolean ejecutar() {
    var ultimoRegistro = climaRepositories.obtenerUltimo();

    if(ultimoRegistro.isEmpty() || ultimoRegistro.get().isFueAlertado()) {
      return false;
    }
    //TODO Falta setearlo en true (hacerlo luego de enviar el mail)
    return alertaService.estaEnAlerta(ultimoRegistro.get());
  }
}
