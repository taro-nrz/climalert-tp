package ar.edu.utn.frba.ddsi.clima.services;

import ar.edu.utn.frba.ddsi.clima.models.entities.RegistroClima;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificacionEmailService {
  private final SendGrid sendGrid;
  private final List<String> destinatarios = List.of(
      "admin@clima.com",
      "emergencias@clima.com",
      "meteorologia@clima.com"
  );


  public NotificacionEmailService(@Value("${sendgrid.api.key}") String apikey) {
    this.sendGrid = new SendGrid(apikey);
  }

  public void enviarAlerta(RegistroClima registro) {
    for(String destino : destinatarios) {

      Email from = new Email("lnarizzano@frba.utn.edu.ar");
      Email to = new Email(destino);
      String subject = "ALERTA CLIMATICA";
      Content content = new Content("text/plain", construirCuerpo(registro));

      Mail mail = new Mail(from, subject, to, content);

      Request request = new Request();
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");

      try {
      request.setBody(mail.build());
      sendGrid.api(request);
      } catch (IOException e) {
        throw new RuntimeException("Error al enviar email", e);
      }

    }

  }

  private String construirCuerpo(RegistroClima registro) {
    return """
        ALERTA CLIMÁTICA DETECTADA

        Ubicación:         %s
        Fecha y hora:      %s
        Temperatura:       %.1f°C
        Humedad:           %d%%
        Sensación térmica: %.1f°C
        Viento:            %.1f km/h
        Condición:         %s
        """.formatted(
        registro.getUbicacion(),
        registro.getFechaHora(),
        registro.getTemperaturaC(),
        registro.getHumedad(),
        registro.getSensacionTermicaC(),
        registro.getVientoKph(),
        registro.getCondicion()
    );
  }

}
