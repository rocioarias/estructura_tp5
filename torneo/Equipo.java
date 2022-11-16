package torneo;

public class Equipo {
    
    private char nombre;
    private int golesContra;
    private int golesAFavor;
    private int puntos;
    
    public Equipo(int gC, int gF, int puntos, char nom) {
        this.golesContra = gC;
        this.golesAFavor = gF;
        this.nombre = nom;
        this.puntos = puntos;
    }

    public Equipo() {
        this.golesContra = 0;
        this.golesAFavor = 0;
        this.puntos = 0;
        this.nombre = "";
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getGolesContra() {
        return golesContra;
    }
    
    public char getNombre() {
        return nombre;
    }

    public void setNombre (char nombre) {
        this.nombre = nombre;
    }

    public void setGolesContra(int golesContra) {
        this.golesContra = golesContra;
    }

    public int getGolesAFavor() {
        return golesAFavor;
    }

    public void setGolesAFavor(int golesAFavor) {
        this.golesAFavor = golesAFavor;
    }
   
}
