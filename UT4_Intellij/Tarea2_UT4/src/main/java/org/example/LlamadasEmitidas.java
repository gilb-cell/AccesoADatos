package org.example;

public class LlamadasEmitidas implements java.io.Serializable {

    private int codigoLlamada;
    private int numeroLlamado;
    private int duracionLlamada;
    private float importeLlamada;


    public LlamadasEmitidas(int codigoLlamada, int numeroLlamado,
                           int duracionLlamada, float importeLlamada) {
        this.codigoLlamada = codigoLlamada;
        this.numeroLlamado = numeroLlamado;
        this.duracionLlamada = duracionLlamada;
        this.importeLlamada = importeLlamada;
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

}
