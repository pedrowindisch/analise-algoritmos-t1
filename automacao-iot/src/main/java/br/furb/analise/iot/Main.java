package br.furb.analise.iot;

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
import br.furb.analise.iot.facade.AutomacaoResidencialFacade;
import br.furb.analise.iot.interfaces.IArCondicionado;
import br.furb.analise.iot.interfaces.ILampada;
import br.furb.analise.iot.interfaces.IPersiana;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Main {
    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    private Main() {
    }

    public static void main(String[] args) {
        LOG.info("Automacao Residencial IoT: inicio da demonstracao");

        ILampada lampadaSala = new LampadaShoyuMiAdapter(new LampadaShoyuMi());
        ILampada lampadaQuarto = new LampadaPhellipesAdapter(new LampadaPhellipes());
        IPersiana persianaSala = new PersianaSolariusAdapter(new PersianaSolarius());
        IPersiana persianaQuarto = new PersianaNatLightAdapter(new PersianaNatLight());
        IArCondicionado arSala = new ArCondicionadoVentoBaumnAdapter(new ArCondicionadoVentoBaumn());
        IArCondicionado arQuarto = new ArCondicionadoGellaKazaAdapter(new ArCondicionadoGellaKaza());

        AutomacaoResidencialFacade facade = new AutomacaoResidencialFacade();
        facade.adicionarLampada(lampadaSala);
        facade.adicionarLampada(lampadaQuarto);
        facade.adicionarPersiana(persianaSala);
        facade.adicionarPersiana(persianaQuarto);
        facade.adicionarArCondicionado(arSala);
        facade.adicionarArCondicionado(arQuarto);

        logarEstado("estado inicial", lampadaSala, lampadaQuarto, persianaSala, persianaQuarto, arSala, arQuarto);

        facade.modoTrabalho();
        logarEstado("modo trabalho", lampadaSala, lampadaQuarto, persianaSala, persianaQuarto, arSala, arQuarto);

        facade.modoSono();
        logarEstado("modo sono", lampadaSala, lampadaQuarto, persianaSala, persianaQuarto, arSala, arQuarto);

        LOG.info("Automacao Residencial IoT: fim da demonstracao");
    }

    private static void logarEstado(String etapa, ILampada lampadaSala, ILampada lampadaQuarto,
            IPersiana persianaSala, IPersiana persianaQuarto,
            IArCondicionado arSala, IArCondicionado arQuarto) {
        LOG.log(Level.INFO, "Etapa: {0}", etapa);
        logarLampada("sala", lampadaSala);
        logarLampada("quarto", lampadaQuarto);
        logarPersiana("sala", persianaSala);
        logarPersiana("quarto", persianaQuarto);
        logarArCondicionado("sala", arSala);
        logarArCondicionado("quarto", arQuarto);
    }

    private static void logarLampada(String ambiente, ILampada lampada) {
        LOG.log(Level.INFO, "Lampada, ambiente={0}: ligada={1}", new Object[] { ambiente, lampada.estaLigada() });
    }

    private static void logarPersiana(String ambiente, IPersiana persiana) {
        LOG.log(Level.INFO, "Persiana, ambiente={0}: aberta={1}", new Object[] { ambiente, persiana.estaAberta() });
    }

    private static void logarArCondicionado(String ambiente, IArCondicionado ar) {
        LOG.log(Level.INFO, "Ar-condicionado, ambiente={0}: ligado={1}, temperatura={2}C",
                new Object[] { ambiente, ar.estaLigado(), ar.getTemperatura() });
    }
}
