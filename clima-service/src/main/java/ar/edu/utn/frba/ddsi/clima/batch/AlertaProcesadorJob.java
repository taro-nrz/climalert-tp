package ar.edu.utn.frba.ddsi.clima.batch;

import ar.edu.utn.frba.ddsi.clima.models.repositories.RegistroClimaRepositories;
import ar.edu.utn.frba.ddsi.clima.services.AlertaService;
import ar.edu.utn.frba.ddsi.clima.services.NotificacionEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AlertaProcesadorJob {
  private final RegistroClimaRepositories climaRepositories;
  private final AlertaService alertaService;
  private final NotificacionEmailService notificador;

  public void ejecutar() {
    System.out.println(">>> AlertaProcesadorJob ejecutando");
    var ultimoRegistro = climaRepositories.obtenerUltimo();

    if(ultimoRegistro.isEmpty()) {
      System.out.println(">>> Repositorio vacío, saliendo");
      return;
    }
    if(ultimoRegistro.get().isFueAlertado()) {
      System.out.println(">>> Ya fue alertado, saliendo");
      return;
    }

    boolean enAlerta = alertaService.estaEnAlerta(ultimoRegistro.get());
    System.out.println(">>> estaEnAlerta: " + enAlerta + " | temp: " + ultimoRegistro.get().getTemperaturaC() + " | humedad: " + ultimoRegistro.get().getHumedad());

    if(enAlerta) {
      System.out.println(">>> Enviando alerta...");
      notificador.enviarAlerta(ultimoRegistro.get());
      ultimoRegistro.get().setFueAlertado(true);
      System.out.println(">>> Alerta enviada");
    }
  }
}
