package br.furb.analise.iot.adapters;

import br.furb.analise.algoritmos.PersianaNatLight;
import br.furb.analise.iot.interfaces.IPersiana;
import java.util.Objects;

public final class PersianaNatLightAdapter implements IPersiana {
    private final PersianaNatLight adaptee;

    public PersianaNatLightAdapter(PersianaNatLight adaptee) {
        this.adaptee = Objects.requireNonNull(adaptee, "adaptee nao pode ser nulo");
    }

    @Override
    public void abrir() {
        if (!adaptee.estaPalhetaAberta()) {
            adaptee.abrirPalheta();
        }
        if (!adaptee.estaPalhetaErguida()) {
            try {
                adaptee.subirPalheta();
            } catch (Exception e) {
                throw new IllegalStateException("Falha ao subir palheta: palheta precisa estar aberta", e);
            }
        }
    }

    @Override
    public void fechar() {
        if (adaptee.estaPalhetaErguida()) {
            adaptee.descerPalheta();
        }
        if (adaptee.estaPalhetaAberta()) {
            try {
                adaptee.fecharPalheta();
            } catch (Exception e) {
                throw new IllegalStateException("Falha ao fechar palheta: persiana precisa estar descida", e);
            }
        }
    }

    @Override
    public boolean estaAberta() {
        return adaptee.estaPalhetaAberta() && adaptee.estaPalhetaErguida();
    }
}
