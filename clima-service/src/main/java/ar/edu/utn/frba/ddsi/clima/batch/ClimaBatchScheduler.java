package ar.edu.utn.frba.ddsi.clima.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClimaBatchScheduler {
  private final ClimaFetchJob jobClima;
  private final AlertaProcesadorJob jobAlertaClima;

  @Scheduled(cron = "0 */5 * * * *")
  public void obtenerDatosClima() {
    jobClima.ejecutar();
  }

  @Scheduled(cron = "0 * * * * *")
  public void procesarAlertas() {
    jobAlertaClima.ejecutar();
  }

}


