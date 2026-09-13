package br.furb.analise.iot.adapters;

import br.furb.analise.algoritmos.ArCondicionadoGellaKaza;
import br.furb.analise.iot.interfaces.IArCondicionado;
import java.util.Objects;

public final class ArCondicionadoGellaKazaAdapter implements IArCondicionado {
    public static final int TEMPERATURA_MINIMA = 15;
    public static final int TEMPERATURA_MAXIMA = 35;

    private final ArCondicionadoGellaKaza adaptee;

    public ArCondicionadoGellaKazaAdapter(ArCondicionadoGellaKaza adaptee) {
        this.adaptee = Objects.requireNonNull(adaptee, "adaptee nao pode ser nulo");
    }

    @Override
    public void ligar() {
        adaptee.ativar();
    }

    @Override
    public void desligar() {
        adaptee.desativar();
    }

    @Override
    public void aumentarTemperatura() {
        adaptee.aumentarTemperatura();
    }

    @Override
    public void diminuirTemperatura() {
        adaptee.diminuirTemperatura();
    }

    @Override
    public void definirTemperatura(int temperatura) {
        if (temperatura < TEMPERATURA_MINIMA || temperatura > TEMPERATURA_MAXIMA) {
            throw new IllegalArgumentException(
                "Temperatura " + temperatura + " fora da faixa suportada (" + TEMPERATURA_MINIMA + " a " + TEMPERATURA_MAXIMA + ")");
        }

        while (adaptee.getTemperatura() < temperatura) {
            adaptee.aumentarTemperatura();
        }
        while (adaptee.getTemperatura() > temperatura) {
            adaptee.diminuirTemperatura();
        }
    }

    @Override
    public int getTemperatura() {
        return adaptee.getTemperatura();
    }

    @Override
    public boolean estaLigado() {
        return adaptee.estaLigado();
    }
}
