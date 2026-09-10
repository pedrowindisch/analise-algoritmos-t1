package br.furb.analise.iot.facade;

import br.furb.analise.iot.interfaces.IArCondicionado;
import br.furb.analise.iot.interfaces.ILampada;
import br.furb.analise.iot.interfaces.IPersiana;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class AutomacaoResidencialFacade {
    private static final int TEMPERATURA_MODO_TRABALHO = 25;

    private final List<ILampada> lampadas = new ArrayList<>();
    private final List<IPersiana> persianas = new ArrayList<>();
    private final List<IArCondicionado> aresCondicionados = new ArrayList<>();

    public void adicionarLampada(ILampada lampada) {
        lampadas.add(Objects.requireNonNull(lampada, "lampada nao pode ser nula"));
    }

    public void adicionarPersiana(IPersiana persiana) {
        persianas.add(Objects.requireNonNull(persiana, "persiana nao pode ser nula"));
    }

    public void adicionarArCondicionado(IArCondicionado arCondicionado) {
        aresCondicionados.add(Objects.requireNonNull(arCondicionado, "arCondicionado nao pode ser nulo"));
    }

    public void modoTrabalho() {
        for (ILampada lampada : lampadas) {
            lampada.ligar();
        }
        for (IPersiana persiana : persianas) {
            persiana.abrir();
        }
        for (IArCondicionado ar : aresCondicionados) {
            ar.ligar();
            ar.definirTemperatura(TEMPERATURA_MODO_TRABALHO);
        }
    }

    public void modoSono() {
        for (ILampada lampada : lampadas) {
            lampada.desligar();
        }
        for (IPersiana persiana : persianas) {
            persiana.fechar();
        }
        for (IArCondicionado ar : aresCondicionados) {
            ar.desligar();
        }
    }

    public List<ILampada> getLampadas() {
        return Collections.unmodifiableList(lampadas);
    }

    public List<IPersiana> getPersianas() {
        return Collections.unmodifiableList(persianas);
    }

    public List<IArCondicionado> getAresCondicionados() {
        return Collections.unmodifiableList(aresCondicionados);
    }
}
