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
    public void insertar( T elem ) throws ClassCastException, NullPointerException, IllegalStateException {
        NodoBinario <T> nuevoNodo = new NodoBinario <T> (elem, null, null);
        NodoBinario <T> raizAux = raiz;
        boolean salir = false;
        boolean irDerecha = false;
        if (raizAux == null){
            this.raiz = nuevoNodo;
        }
        else{
            if(pertenece(elem)){
                return;
            }
            else{
                while (!salir){
                    if(this.compararDato(nuevoNodo.getValor(), raizAux.getValor()) > 0){
                        if(raiz.getDerecho() != null){
                            raizAux = raizAux.getDerecho();
                        }
                        else{
                            salir = true;
                            der = true;
                        }       
                    }
                    else{
                        if (raizAux.getIzquierdo() != null){
                            raizAux = raizAux.getIzquierdo();
                        }
                        else{
                            salir = true;
                        }
                    }
                }

                if(der == true){
                    raizAux.setDerecho(nuevoNodo);
                }
                else{
                    raizAux.setIzquierdo(nuevoNodo);
                }
                while(equilibrado(raiz) < 0){
                    raizAux = padre(raizAux);
                
                    if(raizAux.getDerecho()==null){
                        altDer = 0;
                    }else{
                        altDer = raizAux.getDerecho().getAltura();
                    }
                    
                    if(raizAux.getIzquierdo()==null){
                        altIzq = 0;
                    }else{
                        altIzq = raizAux.getIzquierdo().getAltura();
                    }
                    
                    NodoBinario<T> cambiar = estructurar(raizAux);
                    NodoBinario<T> superior = padre(raizAux);
        
                    //si los nodos modificados tenian un padre anteriormente
                    if(compararDato(superior.getValor(), raizAux.getValor())!=0){
                        if(superior.getIzquierdo()!=null && compararDato(superior.getIzquierdo().getValor(), raizAux.getValor())==0){
                            superior.setIzquierdo(cambiar);		
                        }
                        else if(superior.getDerecho()!=null && compararDato(superior.getDerecho().getValor(), raizAux.getValor())==0){
                            superior.setDerecho(cambiar);
                        }
                    }else{
                        this.raiz = cambiar;
                    }
                }

            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean pertenece(T elem) {
        NodoBinario <T> raizTmp = raiz;

        if(raizTmp == null){
            return false;
        }
        
        int valor = compararDato(elem, raizTmp.getValor());
        
        if(valor == 0){
            return true;
        }

        while(raizTmp.getDerecho()!=null || raizTmp.getIzquierdo()!=null){
            valor = compararDato(elem, raizTmp.getValor());
	    	if(valor > 0){
	    		if(raizTmp.getDerecho()!=null){   		
	    			raizTmp = raizTmp.getDerecho();
	    		}
                else{
	    			return false;
	    		}
	    	}
            else if(valor < 0){	
	    		if(raizTmp.getIzquierdo()!=null){   
		    		raizTmp = raizTmp.getIzquierdo();
	    		}
                else{
	    			return false;
	    		}
	    	}
	    	
	    	if(valor == 0){
	    		return true;
	    	}
    	}
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void borrar(T elem){
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

//------------------------------------------------------------------------------------------------------

    private NodoBinario<T> estructurar(NodoBinario<T> nodo){
		if(balanceoGeneral(nodo.getDerecho(), nodo.getIzquierdo())==2){
			if(balanceoGeneral(nodo.getDerecho().getDerecho(), nodo.getDerecho().getIzquierdo())==1  || balanceoGeneral(nodo.getDerecho().getDerecho(), nodo.getDerecho().getIzquierdo()) == 0){
				nodo = rotacionSimpleIzquierda(nodo);
			}
			
			else if(balanceoGeneral(nodo.getDerecho().getDerecho(), nodo.getDerecho().getIzquierdo())==-1){
				nodo = rotacionCompuestaDerecha(nodo);
			}
		}
		else if(balanceoGeneral(nodo.getDerecho(), nodo.getIzquierdo())==-2){
			if(balanceoGeneral(nodo.getIzquierdo().getDerecho(), nodo.getIzquierdo().getIzquierdo())==-1 || balanceoGeneral(nodo.getDerecho().getDerecho(), nodo.getDerecho().getIzquierdo())==0){
				nodo = rotacionSimpleDerecha(nodo);
			}
			
			else if(balanceoGeneral(nodo.getIzquierdo().getDerecho(), nodo.getIzquierdo().getIzquierdo())==1){
				nodo = rotacionCompuestaIzquierda(nodo);
			}
		}

		return nodo;	
    }

    //----------------------- Rotaciones ------------------------------

    private NodoBinario<T> rotacionSimpleDerecha(NodoBinario<T> nodo){
    	NodoBinario<T> nodoTmp = nodo;
    	nodo = nodoTmp.getIzquierdo();

		nodoTmp.setIzquierdo(nodo.getDerecho());
		nodo.setDerecho(nodoTmp);
		return nodo;
    }

    private NodoBinario<T> rotacionSimpleIzquierda(NodoBinario<T> nodo){
		NodoBinario<T> nodoTmp = nodo;
		
    	nodo = nodoTmp.getDerecho(); 
		nodoTmp.setDerecho(nodo.getIzquierdo());

		nodo.setIzquierdo(nodoTmp);

		return nodo;
    }

    private NodoBinario<T> rotacionCompuestaIzquierda(NodoBinario<T> nodo){
    	NodoBinario<T> nodoTmp = nodo; 

        nodoTmp = rotacionSimpleIzquierda(nodoTmp.getIzquierdo());
        
		nodo.setIzquierdo(nodoTmp); 
		nodoTmp = rotacionSimpleDerecha(nodo); 
		return nodoTmp;
    }

    private NodoBinario<T> rotacionCompuestaDerecha(NodoBinario<T> nodo){
    	NodoBinario<T> nodoTmp = nodo;
    	
        nodoTmp = rotacionSimpleDerecha(nodoTmp.getDerecho());
	
		nodo.setDerecho(nodoTmp);

		nodoTmp = rotacionSimpleIzquierda(nodo);

		return nodoTmp;
    }
 //-----------------------------------------------------------------------------------------------------------------------
    private int equilibrado(NodoBinario<T> n){
    int hIzq = 0;
    int hDer = 0;
    
    if(n==null){
        return 0;
    }
    hIzq = equilibrado(n.getIzquierdo());
    	
    	if(hIzq < 0){
    		return hIzq;
    	}
    	
    	hDer = equilibrado(n.getDerecho());
    	
    	if(hDer <0){
    		return hDer;
    	}

    	if(Math.abs(hIzq - hDer) > 1){
    		return -1;
    	}

    	return Math.max(hIzq, hDer) + 1;
	}
//-----------------------------------------------------------------------
private NodoBinario<T> padre(NodoBinario<T> nodo){
    NodoBinario<T> raizTmp = raiz;
    Stack<NodoBinario<T>> pila = new Stack<NodoBinario<T>>();
    pila.push(raizTmp);	
    while(raizTmp.getDerecho()!=null || raizTmp.getIzquierdo()!=null){
        if(this.compararDato(nodo.getValor(), raizTmp.getValor())>0){
            if(raizTmp.getDerecho()!=null){   	
                raizTmp = raizTmp.getDerecho();
            }
        }
        else if(this.compararDato(nodo.getValor(), raizTmp.getValor())<0){	
            if(raizTmp.getIzquierdo()!=null){   
                raizTmp = raizTmp.getIzquierdo();
            }
        }
        if(this.compararDato(nodo.getValor(), raizTmp.getValor())==0){
            return pila.pop();
        }

        pila.push(raizTmp);	
    }
    return pila.pop();
}
private int compararDato(T t1, T t2){
    if(this.comparador==null){
        return ((Comparable)t1).compareTo(t2);
    }else{
        return this.comparador.compare(t1,t2);
    }
}

}
