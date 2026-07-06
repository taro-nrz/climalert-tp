package ar.edu.utn.frba.ddsi.clima.batch;

import ar.edu.utn.frba.ddsi.clima.clients.WeatherApiClient;
import ar.edu.utn.frba.ddsi.clima.models.entities.RegistroClima;
import ar.edu.utn.frba.ddsi.clima.models.repositories.RegistroClimaRepositories;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ClimaFetchJob {
  private final RegistroClimaRepositories climaRepositories;
  private final WeatherApiClient climaClient;

  public void ejecutar() {
    System.out.println(">>> ClimaFetchJob ejecutando");
    var response = climaClient.obtenerClimaActual();

    RegistroClima registro = new RegistroClima();
    registro.setFechaHora(LocalDateTime.now());
    registro.setUbicacion(response.getLocation().getName());
    registro.setTemperaturaC(response.getCurrent().getTempC());
    registro.setHumedad(response.getCurrent().getHumidity());
    registro.setVientoKph(response.getCurrent().getWindKph());
    registro.setCondicion(response.getCurrent().getCondition().getText());

    climaRepositories.guardar(registro);
  }
}
