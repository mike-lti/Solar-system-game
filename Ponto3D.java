/**
 * As instancias desta classe representam pontos no espaco
 * tridimensional
 * 
 * @author minunes
 */
public class Ponto3D {
	private int x, y, z;
	/**
	 * Inicializa os atributos do novo ponto 3D
	 * @param x Abcissa
	 * @param y Ordenada
	 * @param z Cota 
	 */
	public Ponto3D(int x, int y, int z) {
		this.x = x; this.y = y ; this.z = z;
	}
	
	/**
	 * A abcissa deste ponto
	 */
	public int x() {
		return this.x;
	}
	
	/**
	 * A ordenada deste ponto
	 */
	public int y() {
		return this.y;
	}
	
	/**
	 * A cota deste ponto
	 */
	public int z() {
		return this.z;
	}
	
	/**
	 * A distancia deste ponto a outro
	 * @param p O outro ponto
	 * @return A distancia entre este ponto e p
	 * @requires p != null
	 */
	public double distancia(Ponto3D p) {
		return Math.sqrt(
				Math.pow(p.x() - this.x,2) +
				Math.pow(p.y() - this.y,2) +
				Math.pow(p.z() - this.z,2)
				);				
	}
	
	/**
	 * Representacao textual deste ponto
	 */
	public String toString() {
		return "(" + this.x + "," + this.y + "," + this.z + ")";
	}
	
	/**
	 * Este ponto e' igual a outro?
	 * @param other O objeto para comparacao
	 * @return true se other e' instanceof de Ponto3D e se as coordenadas
	 *         deste ponto sao iguais 'as de other
	 */
	public boolean equals(Object other) {
		if(other == null) {
			return false;
		}
		if(other == this) {
			return true;
		}
		if(!(other instanceof Ponto3D)) {
			return false;
		}
		Ponto3D p = (Ponto3D) other;
		return equalsPonto3D(p);
	}
	
	private boolean equalsPonto3D(Ponto3D p) {
		return this.x() == p.x() && this.y() == p.y() && this.z() == p.z();
	}

}
