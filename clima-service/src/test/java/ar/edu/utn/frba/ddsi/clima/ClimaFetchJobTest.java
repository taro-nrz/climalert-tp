package ar.edu.utn.frba.ddsi.clima;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.utn.frba.ddsi.clima.batch.ClimaFetchJob;
import ar.edu.utn.frba.ddsi.clima.clients.WeatherApiClient;
import ar.edu.utn.frba.ddsi.clima.clients.dto.ConditionDTO;
import ar.edu.utn.frba.ddsi.clima.clients.dto.CurrentDTO;
import ar.edu.utn.frba.ddsi.clima.clients.dto.LocationDTO;
import ar.edu.utn.frba.ddsi.clima.clients.dto.WeatherApiResponse;
import ar.edu.utn.frba.ddsi.clima.models.entities.RegistroClima;
import ar.edu.utn.frba.ddsi.clima.models.repositories.RegistroClimaRepositories;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ClimaFetchJobTest {

  @Mock
  private WeatherApiClient weatherApiClient;

  @Mock
  private RegistroClimaRepositories climaRepositories;

  @InjectMocks
  private ClimaFetchJob climaFetchJob;


  @Test
  public void deberiaGuardarRegistroAlEjecutar() {

    ConditionDTO condition = new ConditionDTO();
    condition.setText("soleado");

    CurrentDTO current = new CurrentDTO();
    current.setCondition(condition);
    current.setHumidity(50);
    current.setTempC(30.0);
    current.setWindKph(10.0);

    LocationDTO location = new LocationDTO();
    location.setName("Buenos Aires");

    WeatherApiResponse response = new WeatherApiResponse();
    response.setLocation(location);
    response.setCurrent(current);

    when(weatherApiClient.obtenerClimaActual()).thenReturn(response);

    climaFetchJob.ejecutar();

    verify(climaRepositories).guardar(any(RegistroClima.class));
  }

}
