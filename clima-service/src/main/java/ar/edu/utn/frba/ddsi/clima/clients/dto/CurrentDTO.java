package ar.edu.utn.frba.ddsi.clima.clients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CurrentDTO {
  @JsonProperty("temp_c")
  double tempC;
  int humidity;
  @JsonProperty("feelslike_c")
  double feelsLikeC;
  @JsonProperty("wind_kph")
  double windKph;
  ConditionDTO condition;
}
