package br.furb.analise.iot.adapters;

import br.furb.analise.algoritmos.LampadaPhellipes;
import br.furb.analise.iot.interfaces.ILampada;
import java.util.Objects;

public final class LampadaPhellipesAdapter implements ILampada {
    private static final int INTENSIDADE_MAXIMA = 100;
    private static final int INTENSIDADE_MINIMA = 0;

    private final LampadaPhellipes adaptee;

    public LampadaPhellipesAdapter(LampadaPhellipes adaptee) {
        this.adaptee = Objects.requireNonNull(adaptee, "adaptee nao pode ser nulo");
    }

    @Override
    public void ligar() {
        adaptee.setIntensidade(INTENSIDADE_MAXIMA);
    }

    @Override
    public void desligar() {
        adaptee.setIntensidade(INTENSIDADE_MINIMA);
    }

    @Override
    public boolean estaLigada() {
        return adaptee.getIntensidade() > 0;
    }
}
