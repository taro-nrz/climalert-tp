package ar.edu.utn.frba.ddsi.clima;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.utn.frba.ddsi.clima.batch.AlertaProcesadorJob;
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

}
