package br.furb.analise.iot.adapters;

import br.furb.analise.algoritmos.PersianaSolarius;
import br.furb.analise.iot.interfaces.IPersiana;
import java.util.Objects;

public final class PersianaSolariusAdapter implements IPersiana {
    private final PersianaSolarius adaptee;

    public PersianaSolariusAdapter(PersianaSolarius adaptee) {
        this.adaptee = Objects.requireNonNull(adaptee, "adaptee nao pode ser nulo");
    }

    @Override
    public void abrir() {
        adaptee.subirPersiana();
    }

    @Override
    public void fechar() {
        adaptee.descerPersiana();
    }

    @Override
    public boolean estaAberta() {
        return adaptee.estaAberta();
    }
}
