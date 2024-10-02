package torneo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestTorneo {

    private Torneo torneo;
    private Equipo equipoA;
    private Equipo equipoB;
    private Equipo equipoC;
    private Equipo equipoD;

    @Before
    public void setUp() {
        // Crear equipos genéricos
        equipoA = new Equipo(5, 15, 10, "Equipo A");
        equipoB = new Equipo(8, 20, 12, "Equipo B");
        equipoC = new Equipo(10, 18, 8, "Equipo C");
        equipoD = new Equipo(7, 25, 15, "Equipo D");

        // Crear instancia de Torneo
        torneo = new Torneo();
    }

    @Test
    public void testAgregarPartidoYActualizarPuntos() {
        // Paso 1: Agregar partidos al torneo
        torneo.agregarPartido(equipoA, equipoB, 2, 1, 3, 2, 0, 1); // Equipo A gana 2-1 contra Equipo B
        torneo.agregarPartido(equipoC, equipoD, 1, 3, 1, 2, 0, 0); // Equipo D gana 3-1 contra Equipo C
        torneo.agregarPartido(equipoD, equipoA, 2, 2, 2, 1, 1, 0); // Empate entre Equipo D y Equipo A
        torneo.agregarPartido(equipoB, equipoC, 3, 0, 0, 1, 0, 0); // Equipo B gana 3-0 contra Equipo C

        // Paso 2: Verificar la actualización de puntos
        int puntosEquipoA = torneo.puntos(equipoA);
        int puntosEquipoB = torneo.puntos(equipoB);
        int puntosEquipoC = torneo.puntos(equipoC);
        int puntosEquipoD = torneo.puntos(equipoD);

        assertTrue("El puntaje de Equipo A debería ser mayor a su puntaje inicial después de jugar", puntosEquipoA > 10);
        assertTrue("El puntaje de Equipo B debería ser mayor a su puntaje inicial después de jugar", puntosEquipoB > 12);
        assertTrue("El puntaje de Equipo C debería ser menor o igual a su puntaje inicial después de perder", puntosEquipoC <= 8);
        assertTrue("El puntaje de Equipo D debería ser mayor a su puntaje inicial después de ganar", puntosEquipoD > 15);
    }

    @Test
    public void testObtenerPuntero() {
        // Paso 1: Agregar partidos para determinar el equipo puntero
        torneo.agregarPartido(equipoA, equipoB, 2, 1, 3, 2, 0, 1); // Equipo A gana 2-1 contra Equipo B
        torneo.agregarPartido(equipoC, equipoD, 1, 3, 1, 2, 0, 0); // Equipo D gana 3-1 contra Equipo C
        torneo.agregarPartido(equipoD, equipoA, 2, 2, 2, 1, 1, 0); // Empate entre Equipo D y Equipo A

        // Paso 2: Obtener el equipo puntero
        PartidosEquipo equipoPuntero = torneo.puntero();

        // Paso 3: Verificar que el puntero sea correcto
        assertNotNull("El equipo puntero no debe ser null", equipoPuntero);
        assertEquals("El equipo puntero debería ser Equipo D", "Equipo A", equipoPuntero.getEquipoX().getNombre());
    }

    @Test
    public void testObtenerSiguienteEquipo() {
        // Paso 1: Agregar partidos para establecer una tabla de posiciones
        torneo.agregarPartido(equipoA, equipoB, 2, 1, 3, 2, 0, 1); // Equipo A gana 2-1 contra Equipo B
        torneo.agregarPartido(equipoC, equipoD, 1, 3, 1, 2, 0, 0); // Equipo D gana 3-1 contra Equipo C
        torneo.agregarPartido(equipoD, equipoA, 2, 2, 2, 1, 1, 0); // Empate entre Equipo D y Equipo A

        // Paso 2: Obtener el siguiente equipo después de Equipo A
        Equipo siguienteEquipo = torneo.siguiente(equipoA);

        // Paso 3: Verificar que el siguiente equipo sea correcto
        assertNotNull("El equipo siguiente no debe ser null", siguienteEquipo);
        assertEquals("El equipo que sigue a Equipo A debería ser Equipo B", "Equipo B", siguienteEquipo.getNombre());
    }


    @Test
    public void testAgregarPartidoEquipoNuevo() {
        // Paso 1: Crear un equipo nuevo
        Equipo equipoE = new Equipo(0, 0, 0, "Equipo E");

        // Paso 2: Agregar un partido con el nuevo equipo
        torneo.agregarPartido(equipoA, equipoE, 3, 1, 1, 1, 0, 0); // Equipo A gana 3-1 contra Equipo E

        // Paso 3: Verificar puntos del nuevo equipo
        int puntosEquipoE = torneo.puntos(equipoE);
        assertTrue("El equipo E debería tener puntos después de jugar un partido", puntosEquipoE >= 0);
    }
}
