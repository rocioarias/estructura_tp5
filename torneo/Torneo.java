package torneo;

import torneo.*;
import colecciones.arbol.Avl;
import colecciones.arbol.Diccionario;
import colecciones.arbol.Diccionario.Orden;

import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Clase Torneo que representa un torneo de fútbol donde se pueden gestionar equipos, partidos y sus posiciones en una tabla.
 */
@SuppressWarnings("unchecked")
public class Torneo {
    Set<Equipo> equipos = new HashSet<Equipo>();
    Comparation versus = new Comparation();
    Diccionario<PartidosEquipo> posiciones = new Avl<PartidosEquipo>(versus);
    HashMap<Equipo, Integer> puntosMapa = new HashMap<>();

    /**
     * Dado un equipo {@code e}, retorna el equipo siguiente (el que le sigue en cantidad de puntos) en la tabla de posiciones.
     * Esta operación debe realizarse en O(log n).
     *
     * @param e equipo del que se quiere calcular cuál es el que le sigue según la tabla de posiciones del torneo.
     * @return Equipo siguiente según la tabla de posiciones del torneo, si hay más de un equipo con partidos jugados.
     */
    public Equipo siguiente(Equipo e) {
        PartidosEquipo pe = posiciones.sucesor(new PartidosEquipo(e, null, 0, 0, 0, 0, 0, 0));
        return (pe == null) ? null : pe.getEquipoX();
    }

    /**
     * Registra en la tabla de posiciones los valores asociados a los equipos que jugaron un partido del torneo.
     * Incluye agregar todos los datos del partido necesarios para el cálculo de los puntajes según el reglamento del torneo.
     * Esta operación debe realizarse en O(log n).
     *
     * @param eLocal      Equipo Local del partido.
     * @param eVisitante  Equipo Visitante del partido.
     * @param golesEL     Goles a favor del equipo Local.
     * @param golesEV     Goles a favor del equipo Visitante.
     * @param amarillasEL Número de tarjetas amarillas recibidas por el equipo Local.
     * @param amarillasEV Número de tarjetas amarillas recibidas por el equipo Visitante.
     * @param rojasEL     Número de tarjetas rojas recibidas por el equipo Local.
     * @param rojasEV     Número de tarjetas rojas recibidas por el equipo Visitante.
     */
    public void agregarPartido(Equipo eLocal, Equipo eVisitante, int golesEL, int golesEV, int amarillasEL, int amarillasEV, int rojasEL, int rojasEV) {
        PartidosEquipo pe = new PartidosEquipo(eLocal, eVisitante, golesEL, golesEV, amarillasEL, amarillasEV, rojasEL, rojasEV);

        // Actualizar los puntos de los equipos según el resultado del partido
        if (golesEL > golesEV) {
            eLocal.setPuntos(eLocal.getPuntos() + 3);
        } else if (golesEL < golesEV) {
            eVisitante.setPuntos(eVisitante.getPuntos() + 3);
        } else {
            eLocal.setPuntos(eLocal.getPuntos() + 1);
            eVisitante.setPuntos(eVisitante.getPuntos() + 1);
        }



        if (amarillasEL > 0) {
            eLocal.setPuntos(eLocal.getPuntos() - amarillasEL);
            if (rojasEL > 0) {
                eLocal.setPuntos(eLocal.getPuntos() - 4);
            }
        }
        if (amarillasEL > 1) {
            eLocal.setPuntos(eLocal.getPuntos() - 2);
        }



        if(amarillasEV > 0){
            eVisitante.setPuntos(eVisitante.getPuntos() - (amarillasEV));
            if (rojasEV > 0) {
                eVisitante.setPuntos(eVisitante.getPuntos() - 4);
            }
        }

        if (amarillasEV > 1) {
            eVisitante.setPuntos(eVisitante.getPuntos() - 2);
        }

        if(rojasEV > 0){
            eVisitante.setPuntos(eVisitante.getPuntos() - (rojasEV * 4));
        }

        if(rojasEL > 0){
            eLocal.setPuntos(eLocal.getPuntos() - (rojasEL * 4));
        }

        

        // Insertar el partido en la estructura de posiciones
        posiciones.insertar(pe);

        // Actualizar los puntos en el mapa de puntos
        puntosMapa.put(eLocal, eLocal.getPuntos());
        puntosMapa.put(eVisitante, eVisitante.getPuntos());
    }

    /**
     * Retorna el equipo con mayor puntaje de la tabla de posiciones y todos los valores en el torneo asociados a ese equipo.
     * Esta operación debe realizarse en O(1).
     *
     * @return Datos de los puntajes asociados a los partidos del equipo con más puntos en la tabla de posiciones.
     */
    public PartidosEquipo puntero() {
        return posiciones.raiz();
    }

    /**
     * Dado un equipo {@code e} retorna los puntos que tiene ese equipo según la tabla de posiciones.
     * Esta operación debe realizarse en O(log n).
     *
     * @param e equipo del que se quiere extraer los puntos acumulados en el torneo.
     * @return Los puntos que tiene el equipo {@code e} según la tabla de posiciones.
     */
    public int puntos(Equipo e) {
        return puntosMapa.getOrDefault(e, 0);
    }


    static class Comparation implements Comparator<PartidosEquipo> {

        @Override
        public int compare(PartidosEquipo uno, PartidosEquipo dos) {
            int puntosUno = uno.getEquipoX().getPuntos();
            int puntosDos = dos.getEquipoX().getPuntos();
            
            if (puntosUno != puntosDos) {
                return puntosDos - puntosUno; 
            }
        
            //Instancia de fairplay
            int golesUno = uno.getGolesX() + uno.getGolesY();
            int golesDos = dos.getGolesX() + dos.getGolesY();
        
            if (golesUno != golesDos) {
                return golesUno - golesDos;
            }

            int fairPlayUno = (uno.getAmarillasX() + uno.getRojasX());
            int fairPlayDos = (dos.getAmarillasX() + dos.getRojasX());
        
            if (fairPlayUno != fairPlayDos) {
                return fairPlayUno - fairPlayDos;
            }
        
        
            int comparacionEquipos = uno.getEquipoX().getNombre().compareTo(dos.getEquipoX().getNombre());
            if (comparacionEquipos != 0) {
                return comparacionEquipos;
            }
            comparacionEquipos = uno.getEquipoY().getNombre().compareTo(dos.getEquipoY().getNombre());
            if (comparacionEquipos != 0) {
                return comparacionEquipos;
            }
        
            return 0;
        }
        
    

}

}
