package br.furb.analise.iot.interfaces;

public interface IArCondicionado {
    void ligar();

    void desligar();

    void aumentarTemperatura();

    void diminuirTemperatura();

    void definirTemperatura(int temperatura);

    int getTemperatura();

    boolean estaLigado();
}
