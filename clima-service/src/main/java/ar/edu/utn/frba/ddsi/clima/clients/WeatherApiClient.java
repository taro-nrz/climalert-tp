package ar.edu.utn.frba.ddsi.clima.clients;

import ar.edu.utn.frba.ddsi.clima.clients.dto.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class WeatherApiClient {

  private final String apiKey;
  private final RestClient restClient;
  private final String ubicacion;

  public WeatherApiClient (@Value("${weather.api.base-url}") String baseUrl,
                           @Value("${weather.api.key}") String apiKey,
                           @Value("${weather.api.ubicacion}") String ubicacion){

    this.apiKey = apiKey;
    this.ubicacion = ubicacion;
    this.restClient = RestClient.builder().baseUrl(baseUrl).build();
  }


  public WeatherApiResponse obtenerClimaActual() {
    return restClient.get()
        .uri("/current.json?key=" + apiKey + "&q=" + ubicacion)
        .retrieve()
        .body(WeatherApiResponse.class);
  }
}
