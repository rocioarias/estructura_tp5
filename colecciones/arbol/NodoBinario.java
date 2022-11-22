package colecciones.arbol;

/**
 * Clase auxiliar para diccionarios implementados con nodos encadenados.
 */
public class NodoBinario<T>  {
    private T valor;
    private NodoBinario<T> izquierdo;
    private NodoBinario<T> derecho;
    private int altura;


    public NodoBinario(T valor, NodoBinario<T> izquierdo, NodoBinario<T> derecho) {
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


    public T getValor() {
        return valor;
    }

    public void setValor(T nuevoValor) {
        this.valor = nuevoValor;
    }

    public NodoBinario<T> getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoBinario<T> nuevoIzquierdo) {
        this.izquierdo = nuevoIzquierdo;
        if(altura < nuevoIzquierdo.getAltura()){
            this.altura = nuevoIzquierdo.getAltura();
        }
    }

    public NodoBinario<T> getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoBinario<T> nuevoDerecho) {
        this.derecho = nuevoDerecho;
        if(altura < nuevoDerecho.getAltura()){
            this.altura = nuevoDerecho.getAltura();
        }
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }


}
