package torneo;

import torneo.Equipo;

public class PartidosEquipo {
	
	private Equipo equipoX;
	private Equipo equipoY;
	private int golesX;
	private int golesY;
	private int amarillasX;
	private int amarillasY;
	private int rojasX;
	private int rojasY;

	public PartidosEquipo() {
		this.equipoX = null;
		this.equipoY = null;
		this.golesX = 0;
		this.golesY = 0;
		this.amarillasX = 0;
		this.amarillasY = 0;
		this.rojasX = 0;
		this.rojasY = 0;
	}

	public PartidosEquipo(Equipo equipoX, Equipo equipoY, int golesX, int golesY, int amarillasX, int amarillasY, int rojasX, int rojasY) {
		this.equipoX = equipoX;
		this.equipoY = equipoY;
		this.golesX = golesX;
		this.golesY = golesY;
		this.amarillasX = amarillasX;
		this.amarillasY = amarillasY;
		this.rojasX = rojasX;
		this.rojasY = rojasY;
		setEquiposPuntajes();
	}

	public Equipo getEquipoX() {
		return equipoX;
	}

	public void setEquipoX(Equipo equipoX) {
		this.equipoX = equipoX;
	}

	public Equipo getEquipoY() {
		return equipoY;
	}

	public void setEquipoY(Equipo equipoY) {
		this.equipoY = equipoY;
	}

	public int getGolesX() {
		return golesX;
	}

	public void setGolesX(int golesX) {
		this.golesX = golesX;
	}

	public int getGolesY() {
		return golesY;
	}

	public void setGolesY(int golesY) {
		this.golesY = golesY;
	}

	public int getAmarillasX() {
		return amarillasX;
	}

	public void setAmarillasX(int amarillasX) {
		this.amarillasX = amarillasX;
	}

	public int getAmarillasY() {
		return amarillasY;
	}

	public void setAmarillasY(int amarillasY) {
		this.amarillasY = amarillasY;
	}

	public int getRojasX() {
		return rojasX;
	}

	public void setRojasX(int rojasX) {
		this.rojasX = rojasX;
	}

	public int getRojasY() {
		return rojasY;
	}

	public void setRojasY(int rojasY) {
		this.rojasY = rojasY;
	}

	// Esto es en base a los resultados de los partidos, se actualiza en cada partido.
	private void setEquiposPuntajes(){
		if(golesX > golesY){
			equipoX.setPuntos(equipoX.getPuntos() + 3);
		}else if(golesX < golesY){
			equipoY.setPuntos(equipoY.getPuntos() + 3);
		}else{
			equipoX.setPuntos(equipoX.getPuntos() + 1);
			equipoY.setPuntos(equipoY.getPuntos() + 1);
		}
	}
	
}
