
package battelofmidway;

public class Enemigo extends Personaje {

	protected int puntos;

	public Enemigo(String direccion){
            super(direccion);
	}
        
	public Enemigo(String direcciones[], int cantidadImagenes, int numeroImagen){
            super(direcciones, cantidadImagenes, numeroImagen);
	}
        
}
