package ar.edu.utn.frba.ddsi.clima.models.entities;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class RegistroClima {

  private LocalDateTime fechaHora;
  private String ubicacion;
  private double temperaturaC;
  private int humedad;
  private double vientoKph;
  private double sensacionTermicaC;
  private String condicion;
  private boolean fueAlertado = false;
}
