package org.example;


/**
 * GILBERTO
 */



public class LlamadasEmitida implements java.io.Serializable {


    // ESTA CLASE ES EL POJO

    private int codigoLlamada;
    private int numeroLlamado;
    private int duracionLlamada;
    private float importeLlamada;
    private int simLlamante;

    public LlamadasEmitida() {}

    public LlamadasEmitida(int codigoLlamada, int numeroLlamado,
                           int duracionLlamada, float importeLlamada,  int simLlamante) {
        this.codigoLlamada = codigoLlamada;
        this.numeroLlamado = numeroLlamado;
        this.duracionLlamada = duracionLlamada;
        this.importeLlamada = importeLlamada;
        this.simLlamante = simLlamante;
    }
    public int getCodigoLlamada() {
        return this.codigoLlamada;
    }
    public void setCodigoLlamada(int codigoLlamada) {
        this.codigoLlamada = codigoLlamada;
    }
    public int getNumeroLlamado() {
        return this.numeroLlamado;
    }
    public void setNumeroLlamado(int numeroLlamado) {
        this.numeroLlamado = numeroLlamado;
    }
    public int getDuracionLlamada() {
        return this.duracionLlamada;
    }
    public void setDuracionLlamada(int duracionLlamada) {
        this.duracionLlamada = duracionLlamada;
    }
    public float getImporteLlamada() {
        return this.importeLlamada;
    }
    public void setImporteLlamada(float importeLlamada) {
        this.importeLlamada = importeLlamada;
    }

    public int getsimLlamante() {

        return this.simLlamante;
    }
    public void setSimLlamante(int simLlamante) {
        this.simLlamante = simLlamante;
    }

}
