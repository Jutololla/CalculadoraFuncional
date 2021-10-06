package main;

import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;


public class Main {
    static IntBinaryOperator suma = (a, b) -> a + b;
    static IntBinaryOperator resta = (a, b) -> a - b;
    static IntBinaryOperator mult = (a, b) -> {
        if (b < 0 && a > 0) {
            int c = a;
            a = b;
            b = c;
        }
        if (b < 0 && a < 0) {
            a = -a;
        }
        int finalA = a;
        return IntStream.range(0, Math.abs(b) + 1)
                .reduce((acumulado, number) -> {

                            return suma.applyAsInt(finalA, acumulado);
                        }
                ).getAsInt();
    };

    static IntBinaryOperator div = (a, b) -> {
        return IntStream.range(0, Math.abs(a) + 1)
                .reduce((acumulado, number) -> {
                            if (resta.applyAsInt(number,mult.applyAsInt(b,suma.applyAsInt(acumulado,1))) == 0) {
                                acumulado=suma.applyAsInt(acumulado,1);
                            }
                            return acumulado;
                        }
                ).getAsInt();
    };

    public static void main(String[] args) {
        System.out.println(suma.applyAsInt(3, 2));
        System.out.println(resta.applyAsInt(3, 2));
        System.out.println(mult.applyAsInt(-5, -10));
        System.out.println(div.applyAsInt(36, 5));
    }

}
