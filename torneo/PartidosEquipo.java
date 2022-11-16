package torneo;

public class PartidosEquipo{
    
    class Nodo {
        private Equipo dato;
        private Nodo izquierda, derecha;

        public Nodo(Equipo dato) {
            this.dato = dato;
            this.izquierda = this.derecha = null;
        }
    }
    
}
