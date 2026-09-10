package br.furb.analise.iot.adapters;

import br.furb.analise.algoritmos.ArCondicionadoGellaKaza;
import br.furb.analise.iot.interfaces.IArCondicionado;
import java.util.Objects;

public final class ArCondicionadoGellaKazaAdapter implements IArCondicionado {
    public static final int TEMPERATURA_MINIMA = 15;
    public static final int TEMPERATURA_MAXIMA = 35;
    private static final int GUARDA_ITERACOES_EXTRA = 5;

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
            throw new IllegalArgumentException("Temperatura deve ser entre 15 e 35");
        }
        if (!adaptee.estaLigado()) {
            adaptee.ativar();
        }
        int maxIteracoes = Math.abs(temperatura - adaptee.getTemperatura()) + GUARDA_ITERACOES_EXTRA;
        int iteracoes = 0;
        while (adaptee.getTemperatura() != temperatura) {
            if (iteracoes >= maxIteracoes) {
                throw new IllegalStateException(
                        "Nao foi possivel atingir a temperatura alvo " + temperatura + ": limite do hardware alcancado");
            }
            try {
                if (adaptee.getTemperatura() < temperatura) {
                    adaptee.aumentarTemperatura();
                } else {
                    adaptee.diminuirTemperatura();
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalStateException(
                        "Nao foi possivel atingir a temperatura alvo " + temperatura + ": limite do hardware alcancado", e);
            }
            iteracoes++;
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
