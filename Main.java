import colecciones.*;
import colecciones.arbol.Avl;
import torneo.*;

public class Main{

        public static void main(String[] args) {
            Equipo boca = new Equipo(0, 0, 0, "Boca Juniors");
            Equipo river = new Equipo(0, 0, 0, "River Plate");
            Equipo flamengo = new Equipo(0, 0, 0, "Flamengo");
            Equipo palmeiras = new Equipo(0, 0, 0, "Palmeiras");

            Torneo torneo = new Torneo();

    
            // Caso de prueba 1: Boca vs River (Boca gana)
            torneo.agregarPartido(boca, river, 1, 0, 2, 1, 0, 0);
            assert torneo.puntos(boca) == 3 : "Error: Boca debería tener 3 puntos.";
            assert torneo.puntos(river) == 0 : "Error: River debería tener 0 puntos.";
    
            // Caso de prueba 2: Flamengo vs Palmeiras (Flamengo gana)
            torneo.agregarPartido(flamengo, palmeiras, 2, 1, 1, 2, 0, 1);
            assert torneo.puntos(flamengo) == 3 : "Error: Flamengo debería tener 3 puntos.";
            assert torneo.puntos(palmeiras) == 0 : "Error: Palmeiras debería tener 0 puntos.";
    
            // Caso de prueba 3: Boca vs Palmeiras (Empate)
            torneo.agregarPartido(boca, palmeiras, 0, 0, 1, 1, 0, 0);
            assert torneo.puntos(boca) == 4 : "Error: Boca debería tener 4 puntos.";
            assert torneo.puntos(palmeiras) == 1 : "Error: Palmeiras debería tener 1 punto.";
    
            // Caso de prueba 4: River vs Flamengo (Flamengo gana)
            torneo.agregarPartido(river, flamengo, 1, 3, 2, 1, 0, 0);
            assert torneo.puntos(river) == 0 : "Error: River debería tener 0 puntos.";
            assert torneo.puntos(flamengo) == 6 : "Error: Flamengo debería tener 6 puntos.";
    
            // Caso de prueba 5: Palmeiras vs River (River gana)
            torneo.agregarPartido(palmeiras, river, 0, 1, 1, 3, 0, 1);
            assert torneo.puntos(palmeiras) == 1 : "Error: Palmeiras debería tener 1 punto.";
            assert torneo.puntos(river) == 3 : "Error: River debería tener 3 puntos.";
    
            // Caso de prueba 6: Verificar puntero del torneo
            PartidosEquipo puntero = torneo.puntero();
            assert puntero.getEquipoX().getNombre().equals("Flamengo") : "Error: El puntero debería ser Flamengo.";
    
            // Caso de prueba 7: Verificar equipo siguiente
            Equipo siguiente = torneo.siguiente(flamengo);
            assert siguiente != null && siguiente.getNombre().equals("Boca Juniors") : "Error: El equipo siguiente a Flamengo debería ser Boca Juniors.";
    
        }
    
    
}