package torneo;

import torneo.*;
import colecciones.arbol.Avl;
import colecciones.arbol.Diccionario;
import colecciones.arbol.Diccionario.Orden;

import java.util.Set;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("unchecked")
public class Torneo {
    Set<Equipo> equipos = new HashSet<Equipo>();
    myComparator versus = new myComparator();
    Diccionario<PartidosEquipo> posiciones = new Avl<PartidosEquipo>(versus);
    HashMap<Equipo, Integer> puntosMapa = new HashMap<>();
    
    /**
     * Dado un equipo {@code e}, retorna el equipo siguiente (el que le sigue en cantidad de puntos) en la tabla de posiciones.
     * Esta operacion debe realizarse en O(log n).
     * @param e equipo del que se quiere calcular cual es el que le sigue segun la tabla de posiciones del torneo.
     * @return Equipo siguiente segun la tabla de posiciones del torneo, si hay mas de un equipo con partidos jugados.
     */
    public Equipo siguiente(Equipo e){
        PartidosEquipo pe = posiciones.sucesor(new PartidosEquipo(e, null, 0, 0, 0, 0, 0, 0));
        return (pe == null) ? null : pe.getEquipoX();
    }

    /**
     * Registra en la tabla de posiciones los valores asociados a los equipos que jugaron un partido del torneo.
     * Incluye agregar todos los datos del partido necesarios para el calculo de los puntajes segun el reglamento del torneo.
     * Esta operacion debe realizarse en O(log n).
     * @param eLocal Equipo Local del partido.
     * @param eVisitante Equipo Visitante del partido.
     * @param golesEL goles a favor del equipo Local.
     * @param golesEV goles a favor del equipo Visitante.
     * @param amarillasEL suma de amonestaciones/expulsiones recibidas por jugadores del equipo Local.
     * @param amarillasEV suma de amonestaciones/expulsiones recibidas por jugadores del equipo Visitante.
     * @param rojasEL suma de amonestaciones/expulsiones recibidas por jugadores del equipo Local.
     * @param rojasEV suma de amonestaciones/expulsiones recibidas por jugadores del equipo Visitante.

     */
    public void agregarPartido(Equipo eLocal, Equipo eVisitante, int golesEL, int golesEV, int amarillasEL, int amarillasEV, int rojasEL, int rojasEV){
        PartidosEquipo pe = new PartidosEquipo(eLocal, eVisitante, golesEL, golesEV, amarillasEL, amarillasEV, rojasEL, rojasEV);
        posiciones.insertar(pe);
        puntosMapa.put(eLocal, eLocal.getPuntos());
        puntosMapa.put(eVisitante, eVisitante.getPuntos());
    }

    /**
     * Retorna el equipo con mayor puntaje de la tabla de posiciones y todos los valores en el torneo asociados a ese equipo.
     * Esta operacion debe realizarse en O(1).
     * @return datos de los puntajes asociados a los partidos del equipo con mas puntos en la tabla de posiciones.
     */
    public PartidosEquipo puntero(){
        return posiciones.raiz();
    }

    /**
     * Dado un equipo {@code e} retorna los puntos que tiene ese equipo segun la tabla de posiciones.
     * Esta operacion debe realizarse en O(log n).
     * @param e equipo del que se quiere extraer los puntos acumulados en el torneo.
     * @return los puntos que tiene el equipo {@code e} segun la tabla de posiciones.
     */
    public int puntos(Equipo e){
        return puntosMapa.getOrDefault(e, null);
    }


    static class myComparator implements Comparator<PartidosEquipo> {
        @Override
        public int compare(PartidosEquipo pe1, PartidosEquipo pe2) {
            int puntosDiff = pe1.getEquipoX().getPuntos() - pe2.getEquipoX().getPuntos();
            if (puntosDiff != 0) {
                return -puntosDiff; // Orden descendente de puntos
            }

            int diffGoles = (pe1.getGolesX() - pe1.getGolesY()) - (pe2.getGolesX() - pe2.getGolesY());
            if (diffGoles != 0) {
                return -diffGoles; 
            }

            int golesAFavorDiff = pe1.getGolesX() - pe2.getGolesX();
            if (golesAFavorDiff != 0) {
                return -golesAFavorDiff; 
            }

            int fairPlayDiff = calcularPuntajeFairPlay(pe1) - calcularPuntajeFairPlay(pe2);
            return fairPlayDiff; 
        }

        private int calcularPuntajeFairPlay(PartidosEquipo pe) {
            return (pe.getAmarillasX() + pe.getAmarillasY()) + 3 * (pe.getRojasX() + pe.getRojasY());
        }
    }
    
}
