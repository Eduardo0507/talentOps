import java.math.BigDecimal;
import java.util.function.Consumer;
import java.util.function.Predicate;

class Transaccion {
    private String id;
    private BigDecimal monto;

    public Transaccion(String id, BigDecimal monto) {
        this.id = id;
        this.monto = monto;
    }

    public String getId() { return id; }
    public BigDecimal getMonto() { return monto; }
}

public class predicateConsumer {
    public static void main(String[] args) {

        Transaccion transaccionActual = new Transaccion("TX-995", new BigDecimal("15000.00"));
        BigDecimal limiteAlerta = new BigDecimal("10000.00");

        Predicate<Transaccion> requiereRevision = t -> 
            t.getMonto().compareTo(limiteAlerta) > 0;

        Consumer<Transaccion> logSeguridad = t -> 
            System.out.println("[SEGURIDAD] Transacción registrada: ID " + t.getId() + " - Monto: $" + t.getMonto());

        Consumer<Transaccion> enviarCorreo = t -> 
            System.out.println("[EMAIL] Enviando alerta al Oficial de Cumplimiento por la transacción: " + t.getId());

        Consumer<Transaccion> pipelineAlerta = logSeguridad.andThen(enviarCorreo);

        if (requiereRevision.test(transaccionActual)) {
            pipelineAlerta.accept(transaccionActual);
        } else {
            System.out.println("Transacción dentro de los parámetros normales.");
        }
    }
}