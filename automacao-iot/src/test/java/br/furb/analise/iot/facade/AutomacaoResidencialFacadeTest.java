package br.furb.analise.iot.facade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.furb.analise.algoritmos.ArCondicionadoGellaKaza;
import br.furb.analise.algoritmos.ArCondicionadoVentoBaumn;
import br.furb.analise.algoritmos.LampadaPhellipes;
import br.furb.analise.algoritmos.LampadaShoyuMi;
import br.furb.analise.algoritmos.PersianaNatLight;
import br.furb.analise.algoritmos.PersianaSolarius;
import br.furb.analise.iot.adapters.ArCondicionadoGellaKazaAdapter;
import br.furb.analise.iot.adapters.ArCondicionadoVentoBaumnAdapter;
import br.furb.analise.iot.adapters.LampadaPhellipesAdapter;
import br.furb.analise.iot.adapters.LampadaShoyuMiAdapter;
import br.furb.analise.iot.adapters.PersianaNatLightAdapter;
import br.furb.analise.iot.adapters.PersianaSolariusAdapter;
import br.furb.analise.iot.interfaces.IArCondicionado;
import br.furb.analise.iot.interfaces.ILampada;
import br.furb.analise.iot.interfaces.IPersiana;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AutomacaoResidencialFacadeTest {

    private AutomacaoResidencialFacade facade;
    private List<ILampada> lampadas;
    private List<IPersiana> persianas;
    private List<IArCondicionado> ares;

    @BeforeEach
    void setUp() {
        facade = new AutomacaoResidencialFacade();
        ILampada lampada1 = new LampadaShoyuMiAdapter(new LampadaShoyuMi());
        ILampada lampada2 = new LampadaPhellipesAdapter(new LampadaPhellipes());
        IPersiana persiana1 = new PersianaSolariusAdapter(new PersianaSolarius());
        IPersiana persiana2 = new PersianaNatLightAdapter(new PersianaNatLight());
        IArCondicionado ar1 = new ArCondicionadoVentoBaumnAdapter(new ArCondicionadoVentoBaumn());
        IArCondicionado ar2 = new ArCondicionadoGellaKazaAdapter(new ArCondicionadoGellaKaza());
        facade.adicionarLampada(lampada1);
        facade.adicionarLampada(lampada2);
        facade.adicionarPersiana(persiana1);
        facade.adicionarPersiana(persiana2);
        facade.adicionarArCondicionado(ar1);
        facade.adicionarArCondicionado(ar2);
        lampadas = facade.getLampadas();
        persianas = facade.getPersianas();
        ares = facade.getAresCondicionados();
    }

    @Test
    void modoTrabalhoDeveLigarAbrirEAjustarPara25Graus() {
        facade.modoTrabalho();
        for (ILampada lampada : lampadas) {
            assertTrue(lampada.estaLigada());
        }
        for (IPersiana persiana : persianas) {
            assertTrue(persiana.estaAberta());
        }
        for (IArCondicionado ar : ares) {
            assertTrue(ar.estaLigado());
            assertEquals(25, ar.getTemperatura());
        }
    }

    @Test
    void modoSonoDeveDesligarEFechar() {
        facade.modoTrabalho();
        facade.modoSono();
        for (ILampada lampada : lampadas) {
            assertFalse(lampada.estaLigada());
        }
        for (IPersiana persiana : persianas) {
            assertFalse(persiana.estaAberta());
        }
        for (IArCondicionado ar : ares) {
            assertFalse(ar.estaLigado());
        }
    }

    @Test
    void modosDevemSerIdempotentes() {
        facade.modoTrabalho();
        facade.modoTrabalho();
        for (IArCondicionado ar : ares) {
            assertEquals(25, ar.getTemperatura());
            assertTrue(ar.estaLigado());
        }
        facade.modoSono();
        facade.modoSono();
        for (ILampada lampada : lampadas) {
            assertFalse(lampada.estaLigada());
        }
        for (IPersiana persiana : persianas) {
            assertFalse(persiana.estaAberta());
        }
    }

    @Test
    void deveRejeitarAdicaoNula() {
        assertThrows(NullPointerException.class, () -> facade.adicionarLampada(null));
        assertThrows(NullPointerException.class, () -> facade.adicionarPersiana(null));
        assertThrows(NullPointerException.class, () -> facade.adicionarArCondicionado(null));
    }
}
