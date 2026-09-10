package br.furb.analise.iot.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.furb.analise.algoritmos.ArCondicionadoGellaKaza;
import org.junit.jupiter.api.Test;

class ArCondicionadoGellaKazaAdapterTest {

    @Test
    void deveIniciarDesligadoA28Graus() {
        ArCondicionadoGellaKazaAdapter adapter = new ArCondicionadoGellaKazaAdapter(new ArCondicionadoGellaKaza());
        assertFalse(adapter.estaLigado());
        assertEquals(28, adapter.getTemperatura());
    }

    @Test
    void ligarDeveAtivarEDesligarDeveDesativar() {
        ArCondicionadoGellaKazaAdapter adapter = new ArCondicionadoGellaKazaAdapter(new ArCondicionadoGellaKaza());
        adapter.ligar();
        assertTrue(adapter.estaLigado());
        adapter.desligar();
        assertFalse(adapter.estaLigado());
    }

    @Test
    void definirTemperaturaDeveAjustarIterativamenteParaBaixo() {
        ArCondicionadoGellaKazaAdapter adapter = new ArCondicionadoGellaKazaAdapter(new ArCondicionadoGellaKaza());
        adapter.definirTemperatura(25);
        assertEquals(25, adapter.getTemperatura());
        assertTrue(adapter.estaLigado());
    }

    @Test
    void definirTemperaturaDeveAjustarIterativamenteParaCima() {
        ArCondicionadoGellaKazaAdapter adapter = new ArCondicionadoGellaKazaAdapter(new ArCondicionadoGellaKaza());
        adapter.definirTemperatura(20);
        assertEquals(20, adapter.getTemperatura());
        adapter.definirTemperatura(30);
        assertEquals(30, adapter.getTemperatura());
    }

    @Test
    void definirMesmaTemperaturaNaoDeveAlterarEstado() {
        ArCondicionadoGellaKazaAdapter adapter = new ArCondicionadoGellaKazaAdapter(new ArCondicionadoGellaKaza());
        adapter.definirTemperatura(28);
        assertEquals(28, adapter.getTemperatura());
    }

    @Test
    void deveAumentarEDiminuirDeUmEmUm() {
        ArCondicionadoGellaKazaAdapter adapter = new ArCondicionadoGellaKazaAdapter(new ArCondicionadoGellaKaza());
        adapter.ligar();
        adapter.aumentarTemperatura();
        assertEquals(29, adapter.getTemperatura());
        adapter.diminuirTemperatura();
        assertEquals(28, adapter.getTemperatura());
    }

    @Test
    void deveRejeitarTemperaturaForaDosLimitesSemLoopInfinito() {
        ArCondicionadoGellaKazaAdapter adapter = new ArCondicionadoGellaKazaAdapter(new ArCondicionadoGellaKaza());
        assertThrows(IllegalArgumentException.class, () -> adapter.definirTemperatura(14));
        assertThrows(IllegalArgumentException.class, () -> adapter.definirTemperatura(36));
        assertEquals(28, adapter.getTemperatura());
    }

    @Test
    void deveRejeitarAdapteeNulo() {
        assertThrows(NullPointerException.class, () -> new ArCondicionadoGellaKazaAdapter(null));
    }
}
