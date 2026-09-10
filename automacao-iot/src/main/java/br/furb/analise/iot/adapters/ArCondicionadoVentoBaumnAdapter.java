package br.furb.analise.iot.adapters;

import br.furb.analise.algoritmos.ArCondicionadoVentoBaumn;
import br.furb.analise.iot.interfaces.IArCondicionado;
import java.util.Objects;

public final class ArCondicionadoVentoBaumnAdapter implements IArCondicionado {
    public static final int TEMPERATURA_MINIMA = 15;
    public static final int TEMPERATURA_MAXIMA = 35;

    private final ArCondicionadoVentoBaumn adaptee;
    private boolean ligado;

    public ArCondicionadoVentoBaumnAdapter(ArCondicionadoVentoBaumn adaptee) {
        this.adaptee = Objects.requireNonNull(adaptee, "adaptee nao pode ser nulo");
        this.ligado = false;
    }

    @Override
    public void ligar() {
        adaptee.ligar();
        ligado = true;
    }

    @Override
    public void desligar() {
        adaptee.desligar();
        ligado = false;
    }

    @Override
    public void aumentarTemperatura() {
        definirTemperatura(getTemperatura() + 1);
    }

    @Override
    public void diminuirTemperatura() {
        definirTemperatura(getTemperatura() - 1);
    }

    @Override
    public void definirTemperatura(int temperatura) {
        if (temperatura < TEMPERATURA_MINIMA || temperatura > TEMPERATURA_MAXIMA) {
            throw new IllegalArgumentException("Temperatura deve ser entre 15 e 35");
        }
        if (!ligado) {
            ligar();
        }
        adaptee.definirTemperatura(temperatura);
    }

    @Override
    public int getTemperatura() {
        return adaptee.getTemperatura();
    }

    @Override
    public boolean estaLigado() {
        return ligado;
    }
}
