import colecciones.*;
import colecciones.arbol.Avl;
import torneo.*;

public class Main{

public static void main(String[] args) {
    Avl arbol = new Avl<>(null);
    Equipo river = new Equipo();
    Equipo boca = new Equipo();
    Equipo platense = new Equipo();
    Equipo colon = new Equipo();

    Torneo Apertura = new Torneo();

    Apertura.agregarPartido(boca, river, 1, 2, 1, 1, 0, 0);
    Apertura.calcularPuntos(boca, 1, 2, 1, 0);
    Apertura.puntero();








}
}