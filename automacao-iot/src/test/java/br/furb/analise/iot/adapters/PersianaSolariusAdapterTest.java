package br.furb.analise.iot.adapters;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.furb.analise.algoritmos.PersianaSolarius;
import org.junit.jupiter.api.Test;

class PersianaSolariusAdapterTest {

    @Test
    void deveAbrirEFechar() {
        PersianaSolariusAdapter adapter = new PersianaSolariusAdapter(new PersianaSolarius());
        adapter.fechar();
        assertFalse(adapter.estaAberta());
        adapter.abrir();
        assertTrue(adapter.estaAberta());
        adapter.fechar();
        assertFalse(adapter.estaAberta());
    }

    @Test
    void abrirIdempotenteMantemAberta() {
        PersianaSolariusAdapter adapter = new PersianaSolariusAdapter(new PersianaSolarius());
        adapter.abrir();
        adapter.abrir();
        assertTrue(adapter.estaAberta());
    }

    @Test
    void deveRejeitarAdapteeNulo() {
        assertThrows(NullPointerException.class, () -> new PersianaSolariusAdapter(null));
    }
}
