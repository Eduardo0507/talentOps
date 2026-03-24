package week1.day1;
import java.math.BigDecimal;

interface CalculadorComision {
    BigDecimal calcular(BigDecimal monto);
}

public class lambdasJava {
    public static void main(String[] args) {
        CalculadorComision digital = monto -> monto.multiply(new BigDecimal("0.005"));
        CalculadorComision sucursal = monto -> monto.multiply(new BigDecimal("0.020"));

        BigDecimal pago = new BigDecimal("1000");

        System.out.print("CANAL DIGITAL: ");
        procesarPago(pago, digital);

        System.out.print("SUCURSAL: ");
        procesarPago(pago, sucursal);
    }

    public static void procesarPago(BigDecimal monto, CalculadorComision calculador) {
        BigDecimal comision = calculador.calcular(monto);
        System.out.println("Monto final con comision: " + monto.add(comision));
    }
}