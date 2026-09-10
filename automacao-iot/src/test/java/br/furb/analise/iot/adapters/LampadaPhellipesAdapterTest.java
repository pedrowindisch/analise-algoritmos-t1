package br.furb.analise.iot.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.furb.analise.algoritmos.LampadaPhellipes;
import org.junit.jupiter.api.Test;

class LampadaPhellipesAdapterTest {

    @Test
    void deveIniciarDesligada() {
        LampadaPhellipesAdapter adapter = new LampadaPhellipesAdapter(new LampadaPhellipes());
        assertFalse(adapter.estaLigada());
        assertEquals(0, getIntensidade(adapter));
    }

    @Test
    void ligarDeveAjustarIntensidadePara100() {
        LampadaPhellipes adaptee = new LampadaPhellipes();
        LampadaPhellipesAdapter adapter = new LampadaPhellipesAdapter(adaptee);
        adapter.ligar();
        assertTrue(adapter.estaLigada());
        assertEquals(100, adaptee.getIntensidade());
    }

    @Test
    void desligarDeveAjustarIntensidadePara0() {
        LampadaPhellipes adaptee = new LampadaPhellipes();
        LampadaPhellipesAdapter adapter = new LampadaPhellipesAdapter(adaptee);
        adapter.ligar();
        adapter.desligar();
        assertFalse(adapter.estaLigada());
        assertEquals(0, adaptee.getIntensidade());
    }

    @Test
    void intensidadeIntermediariaDeveSerConsideradaLigada() {
        LampadaPhellipes adaptee = new LampadaPhellipes();
        adaptee.setIntensidade(50);
        LampadaPhellipesAdapter adapter = new LampadaPhellipesAdapter(adaptee);
        assertTrue(adapter.estaLigada());
    }

    @Test
    void deveRejeitarAdapteeNulo() {
        assertThrows(NullPointerException.class, () -> new LampadaPhellipesAdapter(null));
    }

    private int getIntensidade(LampadaPhellipesAdapter adapter) {
        adapter.desligar();
        return adapter.estaLigada() ? 100 : 0;
    }
}
