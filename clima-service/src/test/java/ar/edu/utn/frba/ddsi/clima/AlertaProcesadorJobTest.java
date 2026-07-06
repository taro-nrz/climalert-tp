package ar.edu.utn.frba.ddsi.clima;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.utn.frba.ddsi.clima.batch.AlertaProcesadorJob;
import ar.edu.utn.frba.ddsi.clima.models.entities.RegistroClima;
import ar.edu.utn.frba.ddsi.clima.models.repositories.RegistroClimaRepositories;
import ar.edu.utn.frba.ddsi.clima.services.AlertaService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public final class AlertaProcesadorJobTest {

  @Mock
  private RegistroClimaRepositories climaRepositories;

  @Mock
  private AlertaService alertaService;

  @InjectMocks
  private AlertaProcesadorJob climaProcesarAlerta;

  @Test
  public void repositorioVacio() {
    when(climaRepositories.obtenerUltimo()).thenReturn(Optional.empty());

    climaProcesarAlerta.ejecutar();

    verify(alertaService, never()).estaEnAlerta(any());

  }

  @Test
  public void registroYaAlertado() {
    RegistroClima registro = new RegistroClima();
    registro.setFueAlertado(true);

    when(climaRepositories.obtenerUltimo()).thenReturn(Optional.of(registro));

    climaProcesarAlerta.ejecutar();

    verify(alertaService, never()).estaEnAlerta(any());
  }

  @Test
  public void registroEnAlerta() {
    RegistroClima registro = new RegistroClima();
    registro.setFueAlertado(false);

    when(climaRepositories.obtenerUltimo()).thenReturn(Optional.of(registro));
    when(alertaService.estaEnAlerta(registro)).thenReturn(true);

    climaProcesarAlerta.ejecutar();

    verify(alertaService).estaEnAlerta(registro);
  }


}
