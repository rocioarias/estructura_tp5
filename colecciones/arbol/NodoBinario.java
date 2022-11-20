package colecciones.arbol;

/**
 * Clase auxiliar para diccionarios implementados con nodos encadenados.
 */
public class NodoBinario<T>  {
    private T valor;
    private NodoBinario<T> izquierdo;
    private NodoBinario<T> derecho;
    private int altura;


    NodoBinario(T valor, NodoBinario<T> izquierdo, NodoBinario<T> derecho) {
       this.valor = valor;
       this.izquierdo = izquierdo;
       this.derecho = derecho;
       if (izquierdo == null && derecho == null){
            setAltura(0);
       }
       else if (izquierdo.altura >= derecho.altura){
            setAltura(izquierdo.altura);
       }
       else if (derecho.altura > izquierdo.altura){
            setAltura(derecho.altura);
       }
    }

    public NodoBinario(){
        setAltura(0);
        setDerecho(null);
        setIzquierdo(null);
        setValor(null);
    }

    public NodoBinario(T valor){
        setAltura(0);
        setValor(valor);
        setDerecho(null);
        setIzquierdo(null);
    }


    T getValor() {
        return valor;
    }

    void setValor(T nuevoValor) {
        this.valor = nuevoValor;
    }

    NodoBinario<T> getIzquierdo() {
        return izquierdo;
    }

    void setIzquierdo(NodoBinario<T> nuevoIzquierdo) {
        this.izquierdo = nuevoIzquierdo;
    }

    NodoBinario<T> getDerecho() {
        return derecho;
    }

    void setDerecho(NodoBinario<T> nuevoDerecho) {
        this.derecho = nuevoDerecho;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }


}
