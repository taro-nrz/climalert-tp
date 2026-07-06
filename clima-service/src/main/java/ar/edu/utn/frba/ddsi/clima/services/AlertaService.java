package ar.edu.utn.frba.ddsi.clima.services;

import ar.edu.utn.frba.ddsi.clima.models.entities.RegistroClima;
import org.springframework.stereotype.Service;

@Service
public class AlertaService {
  private static final double TEMPERATURA_MAXIMA = 35.0;
  private static final int HUMEDAD_MAXIMA = 60;

  public boolean estaEnAlerta(RegistroClima registro) {
    return registro.getHumedad() > HUMEDAD_MAXIMA && registro.getTemperaturaC() > TEMPERATURA_MAXIMA;
  }
}
