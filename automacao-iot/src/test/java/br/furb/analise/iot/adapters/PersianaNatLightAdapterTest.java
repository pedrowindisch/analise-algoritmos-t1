package br.furb.analise.iot.adapters;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.furb.analise.algoritmos.PersianaNatLight;
import org.junit.jupiter.api.Test;

class PersianaNatLightAdapterTest {

    @Test
    void deveFecharEAbrirRespeitandoOrdemDoHardware() {
        PersianaNatLightAdapter adapter = new PersianaNatLightAdapter(new PersianaNatLight());
        adapter.fechar();
        assertFalse(adapter.estaAberta());
        adapter.abrir();
        assertTrue(adapter.estaAberta());
        adapter.fechar();
        assertFalse(adapter.estaAberta());
    }

    @Test
    void abrirQuandoJaAbertaDevePermanecerAberta() {
        PersianaNatLight adaptee = new PersianaNatLight();
        PersianaNatLightAdapter adapter = new PersianaNatLightAdapter(adaptee);
        assertTrue(adapter.estaAberta());
        adapter.abrir();
        assertTrue(adapter.estaAberta());
        assertTrue(adaptee.estaPalhetaAberta());
        assertTrue(adaptee.estaPalhetaErguida());
    }

    @Test
    void fecharQuandoJaFechadaDevePermanecerFechada() {
        PersianaNatLightAdapter adapter = new PersianaNatLightAdapter(new PersianaNatLight());
        adapter.fechar();
        adapter.fechar();
        assertFalse(adapter.estaAberta());
    }

    @Test
    void estadoAbertoExigePalhetaAbertaEErguida() {
        PersianaNatLight adaptee = new PersianaNatLight();
        PersianaNatLightAdapter adapter = new PersianaNatLightAdapter(adaptee);
        adaptee.descerPalheta();
        assertFalse(adapter.estaAberta());
        assertTrue(adaptee.estaPalhetaAberta());
        assertFalse(adaptee.estaPalhetaErguida());
    }

    @Test
    void deveRejeitarAdapteeNulo() {
        assertThrows(NullPointerException.class, () -> new PersianaNatLightAdapter(null));
    }
}
