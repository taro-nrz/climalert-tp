package ar.edu.utn.frba.ddsi.clima.clients.dto;

import lombok.Data;

@Data
public class WeatherApiResponse {
  LocationDTO location;
  CurrentDTO current;
}
