package br.furb.analise.iot.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.furb.analise.algoritmos.ArCondicionadoVentoBaumn;
import org.junit.jupiter.api.Test;

class ArCondicionadoVentoBaumnAdapterTest {

    @Test
    void deveIniciarDesligadoA24Graus() {
        ArCondicionadoVentoBaumnAdapter adapter = new ArCondicionadoVentoBaumnAdapter(new ArCondicionadoVentoBaumn());
        assertFalse(adapter.estaLigado());
        assertEquals(24, adapter.getTemperatura());
    }

    @Test
    void deveLigarEDefinirTemperatura() {
        ArCondicionadoVentoBaumnAdapter adapter = new ArCondicionadoVentoBaumnAdapter(new ArCondicionadoVentoBaumn());
        adapter.ligar();
        assertTrue(adapter.estaLigado());
        adapter.definirTemperatura(25);
        assertEquals(25, adapter.getTemperatura());
    }

    @Test
    void definirTemperaturaDeveLigarAutomaticamenteSeDesligado() {
        ArCondicionadoVentoBaumnAdapter adapter = new ArCondicionadoVentoBaumnAdapter(new ArCondicionadoVentoBaumn());
        adapter.definirTemperatura(22);
        assertTrue(adapter.estaLigado());
        assertEquals(22, adapter.getTemperatura());
    }

    @Test
    void deveAumentarEDiminuirTemperatura() {
        ArCondicionadoVentoBaumnAdapter adapter = new ArCondicionadoVentoBaumnAdapter(new ArCondicionadoVentoBaumn());
        adapter.ligar();
        adapter.definirTemperatura(24);
        adapter.aumentarTemperatura();
        assertEquals(25, adapter.getTemperatura());
        adapter.diminuirTemperatura();
        assertEquals(24, adapter.getTemperatura());
    }

    @Test
    void deveDesligar() {
        ArCondicionadoVentoBaumnAdapter adapter = new ArCondicionadoVentoBaumnAdapter(new ArCondicionadoVentoBaumn());
        adapter.ligar();
        adapter.desligar();
        assertFalse(adapter.estaLigado());
    }

    @Test
    void deveRejeitarTemperaturaForaDosLimites() {
        ArCondicionadoVentoBaumnAdapter adapter = new ArCondicionadoVentoBaumnAdapter(new ArCondicionadoVentoBaumn());
        assertThrows(IllegalArgumentException.class, () -> adapter.definirTemperatura(14));
        assertThrows(IllegalArgumentException.class, () -> adapter.definirTemperatura(36));
        assertThrows(IllegalArgumentException.class, () -> adapter.definirTemperatura(0));
    }

    @Test
    void deveAceitarLimitesExtremos() {
        ArCondicionadoVentoBaumnAdapter adapter = new ArCondicionadoVentoBaumnAdapter(new ArCondicionadoVentoBaumn());
        adapter.definirTemperatura(15);
        assertEquals(15, adapter.getTemperatura());
        adapter.definirTemperatura(35);
        assertEquals(35, adapter.getTemperatura());
    }

    @Test
    void deveRejeitarAdapteeNulo() {
        assertThrows(NullPointerException.class, () -> new ArCondicionadoVentoBaumnAdapter(null));
    }
}
