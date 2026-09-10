package br.furb.analise.iot.adapters;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.furb.analise.algoritmos.LampadaShoyuMi;
import org.junit.jupiter.api.Test;

class LampadaShoyuMiAdapterTest {

    @Test
    void deveLigarEDesligar() {
        LampadaShoyuMiAdapter adapter = new LampadaShoyuMiAdapter(new LampadaShoyuMi());
        assertFalse(adapter.estaLigada());
        adapter.ligar();
        assertTrue(adapter.estaLigada());
        adapter.desligar();
        assertFalse(adapter.estaLigada());
    }

    @Test
    void deveReligarAposDesligar() {
        LampadaShoyuMiAdapter adapter = new LampadaShoyuMiAdapter(new LampadaShoyuMi());
        adapter.ligar();
        adapter.desligar();
        adapter.ligar();
        assertTrue(adapter.estaLigada());
    }

    @Test
    void deveRejeitarAdapteeNulo() {
        assertThrows(NullPointerException.class, () -> new LampadaShoyuMiAdapter(null));
    }
}
