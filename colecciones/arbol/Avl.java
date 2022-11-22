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
       raiz = insAux(raiz, elem);
       int balanceado = balance();
       switch (balanceado) {
		case 2:
			raiz = rotarIzquierda(raiz);
			break;
		case -2:
			raiz = rotarDerecha(raiz);
			break;
		}
    }

    public NodoBinario <T> insAux (NodoBinario <T> nodo, T elem){
        if (nodo == null){
            return (new NodoBinario<T>(elem, null, null));
        }

        if (this.compararDato(nodo.getValor(),elem) > 0){
            nodo = new NodoBinario <T> (nodo.getValor(), insAux(nodo.getIzquierdo(), elem), nodo.getDerecho());
        }
        if (this.compararDato(nodo.getValor(),elem) < 0){
            nodo = new NodoBinario <T> (nodo.getValor(), nodo.getIzquierdo(), insAux(nodo.getDerecho(), elem));
        }

        int balanceado = (nodo.getIzquierdo().getAltura()) - (nodo.getDerecho().getAltura());

        switch (balanceado) {
            case 2:
                nodo = rotarIzquierda(nodo);
                break;
            case -2:
                nodo = rotarDerecha(nodo);
                break;
        }
        return nodo;
    }


    /**
     * {@inheritDoc}
     */
    public boolean pertenece(T elem) {
        NodoBinario<T> aux = raiz;
		while (aux != null) {
			if (this.compararDato(aux.getValor(),elem) == 0)
				return true;
			else if (this.compararDato(aux.getValor(),elem) > 0)
				aux = aux.getIzquierdo();
			else
				aux = aux.getDerecho();
		}
		return false;
	}

    private NodoBinario<T> rotarDerecha(NodoBinario<T> nodo) {
		NodoBinario<T> q = nodo;
		NodoBinario<T> p = q.getIzquierdo();
		NodoBinario<T> c = q.getDerecho();
		NodoBinario<T> a = p.getIzquierdo();
		NodoBinario<T> b = p.getDerecho();
		q = new NodoBinario<T>(q.getValor(), b, c);
		p = new NodoBinario<T>(p.getValor(), a, q);
		return p;
    }

	private NodoBinario<T> rotarIzquierda (NodoBinario<T> nodo) {
		NodoBinario<T> q = nodo;
		NodoBinario<T> p = q.getDerecho();
		NodoBinario<T> c = q.getIzquierdo();
		NodoBinario<T> a = p.getIzquierdo();
		NodoBinario<T> b = p.getDerecho();
		q = new NodoBinario<T>(q.getValor(), c, a);
		p = new NodoBinario<T>(p.getValor(), q, b);
		return p;
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
        return raiz.getValor();
    }

    /**{@inheritDoc}*/
    @Override
    public Avl<T> subArbolIzquierdo() {
        NodoBinario <T> nuevoArbol = new NodoBinario <T> ((raiz.getIzquierdo()).getValor(), (raiz.getIzquierdo()).getIzquierdo(), (raiz.getIzquierdo()).getDerecho());
        Avl <T> subIzquierdo = new Avl <T> (comparador);
        subIzquierdo.raiz = nuevoArbol;
        return subIzquierdo; 
    }

    /**{@inheritDoc}*/
    @Override
    public Avl<T> subArbolDerecho() {
        NodoBinario <T> nuevoArbol = new NodoBinario <T> ((raiz.getDerecho()).getValor(), (raiz.getDerecho()).getIzquierdo(), (raiz.getDerecho()).getDerecho());
        Avl <T> subDerecho = new Avl <T> (comparador);
        subDerecho.raiz = nuevoArbol;
        return subDerecho;
    }

    /**{@inheritDoc}*/
    @Override
    public int elementos() {
        List<T> elementos = new LinkedList<>();
        return (aListaInOrder(raiz, elementos).size());
    }

    /**{@inheritDoc}*/
    @Override
    public int altura() {
        return raiz.getAltura();
    }

    /**{@inheritDoc}*/
    @Override
    public boolean esVacio() {
        return (altura() == 0); 
    }

    /**{@inheritDoc}*/
    @Override
    public T mayorValor(){
        NodoBinario<T> aux = raiz;
		if (aux == null){
			return null;
        }
		while (aux.getDerecho() != null){
            aux = aux.getDerecho();
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
        List<T> elementos = new LinkedList<>();
        elementos = aListaInOrder(raiz, elementos);
        return elementos.get(elementos.indexOf(elem)+1);
    }

    /**{@inheritDoc}*/
    @Override
    public T predecesor(T elem) {
        List<T> elementos = new LinkedList<>();
        elementos = aListaInOrder(raiz, elementos);
        return elementos.get(elementos.indexOf(elem)-1);
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
        if(raiz == null){
           elementos.add(null);
        }else{
            aListaInOrder(raiz.getIzquierdo(), elementos);
            elementos.add(raiz.getValor());
            aListaInOrder(raiz.getDerecho(), elementos);
        }
        return elementos;
    }

    /* (non-Javadoc)
     * Este método toma un nodo (que puede ser null), una lista de elementos (que no puede ser null)
     * y va llenando la lista con los elementos del árbol según un recorrido pre order.
     * Si bien el prefil está pensando para una implementación recursiva, puede probar con una implementación iterativa.
     */
    private List<T> aListaPreOrder(NodoBinario<T> raiz, List<T> elementos) {
        if(raiz == null){
            elementos.add(null);
         }else{
            elementos.add(raiz.getValor());
            aListaPreOrder(raiz.getIzquierdo(), elementos);
            aListaPreOrder(raiz.getDerecho(), elementos);
         }
         return elementos;
    }

    /* (non-Javadoc)
     * Este método toma un nodo (que puede ser null), una lista de elementos (que no puede ser null)
     * y va llenando la lista con los elementos del árbol según un recorrido post order.
     * Si bien el prefil está pensando para una implementación recursiva, puede probar con una implementación iterativa.
     */
    private List<T> aListaPostOrder(NodoBinario<T> raiz, List<T> elementos) {
        if(raiz == null){
            elementos.add(null);
         }else{
            aListaPostOrder(raiz.getIzquierdo(), elementos);
            aListaPostOrder(raiz.getDerecho(), elementos);
            elementos.add(raiz.getValor());
         }
         return elementos;
    }

    private int compararDato(T t1, T t2){
    	if(this.comparador==null){
    		return ((Comparable)t1).compareTo(t2);
    	}else{
    		return this.comparador.compare(t1,t2);
    	}
    }


}
