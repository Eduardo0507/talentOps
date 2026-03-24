import java.math.BigDecimal;
import java.util.Optional;

class Transaccion {
    BigDecimal monto;
    String moneda;
    Transaccion(BigDecimal monto, String moneda) { this.monto = monto; this.moneda = moneda; }
}

class TasaDeCambio {
    BigDecimal valor;
    TasaDeCambio(BigDecimal valor) { this.valor = valor; }
}

@FunctionalInterface
interface ConversorMoneda {
    BigDecimal convertir(Transaccion t, TasaDeCambio tasa);
}

    public class extension {
        public static void main(String[] args) {
            
            ConversorMoneda conversor = (t, tasa) -> {
                if (t == null || tasa == null || t.monto == null || tasa.valor == null) {
                    return BigDecimal.ZERO;
                }
                return t.monto.multiply(tasa.valor);
            };

            Transaccion miPago = new Transaccion(new BigDecimal("100"), "USD");
            TasaDeCambio tasaDOP = new TasaDeCambio(new BigDecimal("58.50"));

            BigDecimal resultado = conversor.convertir(miPago, tasaDOP);
            System.out.println("Monto convertido: " + resultado);

            BigDecimal resultadoNulo = conversor.convertir(null, tasaDOP);
            System.out.println("Monto con nulo: " + resultadoNulo); 
        }
    }


