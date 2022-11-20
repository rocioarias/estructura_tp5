package torneo;

import torneo.*;

import colecciones.arbol.Diccionario;

import java.util.Set;

public class Torneo {
    Set<Equipo> equipos;
    Diccionario<PartidosEquipo> posiciones;
    /**
     * Dado un equipo {@code e}, retorna el equipo siguiente (el que le sigue en cantidad de puntos) en la tabla de posiciones.
     * Esta operacion debe realizarse en O(log n).
     * @param e equipo del que se quiere calcular cual es el que le sigue segun la tabla de posiciones del torneo.
     * @return Equipo siguiente segun la tabla de posiciones del torneo, si hay mas de un equipo con partidos jugados.
     */
    public Equipo siguiente(Equipo e){
        throw new UnsupportedOperationException("Debe implementar este método");
        //aca hay que usar el hashmap je
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
        
        // Tendriamos que buscar si el equipo está en el arbol, si no está crearles el tipo (partidos equipo) y
        // ahi poder setearlos como está abajo, sino hay que solamente actualizar goles, tarjetas y eso.
        // donde equipoLocal = buscarEquipoenarbol
        // equipovisitante lo mismo.
        PartidosEquipo equipoLocal = new PartidosEquipo(eLocal, golesEL, amarillasEL, rojasEL, 0);
        PartidosEquipo equipoVisitante = new PartidosEquipo(eVisitante, golesEV, amarillasEV, rojasEV, 0);
        //---------------------------------------------------------------------------------------------------------
            if(golesEL > golesEV){
                equipoLocal.setPuntos(equipoLocal.getPuntos() + 3);
            }
            else if (golesEV > golesEL){
                equipoVisitante.setPuntos(equipoVisitante.getPuntos() + 3);
            }
            else{
                equipoLocal.setPuntos(equipoLocal.getPuntos() + 1);
                equipoVisitante.setPuntos(equipoVisitante.getPuntos() + 1);
            }
        
        if (equipoVisitante.getPuntos() == equipoLocal.getPuntos()){
            //aca se haria la diferencia por goles que tenga cada uno registrado en el torneo, sino 

            if(equipoVisitante.getPuntos() == equipoVisitante.getPuntos()){
                //aca se haria el tema de amarillas y rojas acumuladas en el partido
            }
        }

        //despues acá se agregarian los equipos al arbol (de ser nuevos elementos), sino actualizar los puntos de cada uno, eso con un
        //avl automaticamente se va a rotar y mover creo yo, hay que seguir con el tipo avl
        
    }

    /**
     * Retorna el equipo con mayor puntaje de la tabla de posiciones y todos los valores en el torneo asociados a ese equipo.
     * Esta operacion debe realizarse en O(1).
     * @return datos de los puntajes asociados a los partidos del equipo con mas puntos en la tabla de posiciones.
     */
    public PartidosEquipo puntero(){
        return ((posiciones.raiz()).getPuntos());
    }

    /**
     * Dado un equipo {@code e} retorna los puntos que tiene ese equipo segun la tabla de posiciones.
     * Esta operacion debe realizarse en O(log n).
     * @param e equipo del que se quiere extraer los puntos acumulados en el torneo.
     * @return los puntos que tiene el equipo {@code e} segun la tabla de posiciones.
     */
    public int puntos(Equipo e){
        throw new UnsupportedOperationException("Debe implementar este método");
        //acá tambien hay que usarlo al hashmap xd
    }

}
