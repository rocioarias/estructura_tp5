package colecciones.arbol;

import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;


/**
 * Una implementación de {@code Diccionario} mediante nodos encadenados que preserva,
 * las propiedades de ABB y ademas mantiene el arbol balanceado, es decir,
 * las alturas de los subárboles izquierdo y derecho de cada nodo difieren como máximo en uno.
 * @param <T>  Tipo del valor asociado a los nodos del árbol, debe ser posible definir un orden total para los mismos.
 * @see NodoBinario
 */
public class Avl<T> implements Diccionario<T> {

    private NodoBinario<T> raiz;

    /**
     * Comparador usado para mantener el orden en un ABB, o null.
     */
    private final Comparator<? super T> comparador;


    /**
     * Construye un nuevo árbol vacío ordenado acorde al comparador dado.
     *
     * @param comparador define una forma de comparar los valores insertados en el arbol.
     */
    public Avl(Comparator<? super T> comparador) {
        this.raiz = null;
        this.comparador = comparador;
    }

    /**
     * Construye un nuevo árbol con un elemento en la raiz, ordenado acorde al comparador dado.
     *
     * @param comparador define una forma de comparar los valores insertados en el arbol.
     * @param valor de la raiz del nuevo arbol si no es null.
     */
    public Avl(Comparator<? super T> comparador, T valor) {
        this.raiz.setValor(valor);
        this.comparador = comparador;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertar( T elem ) {
       NodoBinario <T> aux = new NodoBinario<>(elem, null, null);
       if (raiz == null){
            raiz = aux;
       }
       else{
            if((raiz.getValor()).compareTo(elem) > 0){
                //significa que debe de ir a la derecha
            }
            else if ((raiz.getValor()).compareTo(elem) < 0){
                //va para la izquierda
            }
            else if ((raiz.getValor()).compareTo(elem) == 0){
                //el elemento ya existe dentro de la lista, asi que solo return
                return;
            }
       }
    }


    /**
     * {@inheritDoc}
     */
    public boolean pertenece(T elem) {
        NodoBinario<T> aux = raiz;
		while (aux != null) {
			if (aux.getValor().compareTo(data) == 0)
				return true;
			else if (aux.getValor().compareTo(data) > 0)
				aux = aux.getIzquierdo();
			else
				aux = aux.getDerecho();
		}
		return false;
	}


    /**
     * {@inheritDoc}
     */
    @Override
    public void borrar(T elem) {
        throw new UnsupportedOperationException("TODO: implementar");
    }

    /**{@inheritDoc}*/
    @Override
    public void vaciar() {
        raiz.setDerecho(null);
        raiz.setIzquierdo(null);
        raiz.setAltura(0);
        this.raiz = null;
    }

    /**{@inheritDoc}*/
    @Override
    public T raiz() {
        return raiz();
    }

    /**{@inheritDoc}*/
    @Override
    public Avl<T> subArbolIzquierdo() {
        throw new UnsupportedOperationException("TODO: implementar");
    }

    /**{@inheritDoc}*/
    @Override
    public Avl<T> subArbolDerecho() {
        throw new UnsupportedOperationException("TODO: implementar");
    }

    /**{@inheritDoc}*/
    @Override
    public int elementos() {
        throw new UnsupportedOperationException("TODO: implementar");
    }

    /**{@inheritDoc}*/
    @Override
    public int altura() {
        return raiz.getAltura();
    }

    /**{@inheritDoc}*/
    @Override
    public boolean esVacio() {
        return (altura(raiz) == 0); 
    }

    /**{@inheritDoc}*/
    @Override
    public T mayorValor(){
        NodoBinario<T> aux = raiz;
		if (aux == null){
			return null;
        }
		while (aux.getDerecho() != null){
            local = local.getDerecho();
        }
		return aux.getValor();
	}

    /**{@inheritDoc}*/
    @Override
    public T menorValor() {
        NodoBinario<T> aux = raiz;
		if (aux == null){
			return null;
        }
		while (aux.getIzquierdo() != null) {
			aux = aux.getIzquierdo();
		}
		return aux.getValor();
	}

    /**{@inheritDoc}*/
    @Override
    public T sucesor(T elem) {
        throw new UnsupportedOperationException("TODO: implementar");
    }

    /**{@inheritDoc}*/
    @Override
    public T predecesor(T elem) {
        throw new UnsupportedOperationException("TODO: implementar");
    }

    /**
     * obtiene el balance del arbol, es decir, la diferencia de altura de sus subarboles.
     * @return diferencia de altura de los subarboles.
     */
    public int balance(){
        int R = (raiz.getDerecho()).getAltura();
		int L = (raiz.getIzquierdo()).getAltura();
		return (L-R);
	}

    /**{@inheritDoc}*/
    @Override
    public boolean repOK() {
        throw new UnsupportedOperationException("TODO: implementar");
    }

    /**{@inheritDoc}*/
    @Override
    public String toString() {
        throw new UnsupportedOperationException("TODO: implementar");
    }

    /**{@inheritDoc}*/
    @Override
    public boolean equals(Object other) {
        throw new UnsupportedOperationException("TODO: implementar");
    }

    /**{@inheritDoc}*/
    @Override
    public List<T> aLista() {
        return aLista(Orden.INORDER);
    }

    /**{@inheritDoc}*/
    @Override
    public List<T> aLista(Orden orden) {
        List<T> elementos = new LinkedList<>();
        switch (orden) {
            case INORDER : return aListaInOrder(raiz, elementos);
            case PREORDER : return aListaPreOrder(raiz, elementos);
            case POSTORDER : return aListaPostOrder(raiz, elementos);
        }
        return elementos;
    }

    /* (non-Javadoc)
     * Este método toma un nodo (que puede ser null), una lista de elementos (que no puede ser null)
     * y va llenando la lista con los elementos del árbol según un recorrido in order.
     * Si bien el prefil está pensando para una implementación recursiva, puede probar con una implementación iterativa.
     */
    private List<T> aListaInOrder(NodoBinario<T> raiz, List<T> elementos) {
        throw new UnsupportedOperationException("TODO: implementar");
    }

    /* (non-Javadoc)
     * Este método toma un nodo (que puede ser null), una lista de elementos (que no puede ser null)
     * y va llenando la lista con los elementos del árbol según un recorrido pre order.
     * Si bien el prefil está pensando para una implementación recursiva, puede probar con una implementación iterativa.
     */
    private List<T> aListaPreOrder(NodoBinario<T> raiz, List<T> elementos) {
        throw new UnsupportedOperationException("TODO: implementar");
    }

    /* (non-Javadoc)
     * Este método toma un nodo (que puede ser null), una lista de elementos (que no puede ser null)
     * y va llenando la lista con los elementos del árbol según un recorrido post order.
     * Si bien el prefil está pensando para una implementación recursiva, puede probar con una implementación iterativa.
     */
    private List<T> aListaPostOrder(NodoBinario<T> raiz, List<T> elementos) {
        throw new UnsupportedOperationException("TODO: implementar");
    }

}
