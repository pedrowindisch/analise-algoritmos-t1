package br.furb.analise.iot.adapters;

import br.furb.analise.algoritmos.LampadaShoyuMi;
import br.furb.analise.iot.interfaces.ILampada;
import java.util.Objects;

public final class LampadaShoyuMiAdapter implements ILampada {
    private final LampadaShoyuMi adaptee;

    public LampadaShoyuMiAdapter(LampadaShoyuMi adaptee) {
        this.adaptee = Objects.requireNonNull(adaptee, "adaptee nao pode ser nulo");
    }

    @Override
    public void ligar() {
        adaptee.ligar();
    }

    @Override
    public void desligar() {
        adaptee.desligar();
    }

    @Override
    public boolean estaLigada() {
        return adaptee.estaLigada();
    }
}
