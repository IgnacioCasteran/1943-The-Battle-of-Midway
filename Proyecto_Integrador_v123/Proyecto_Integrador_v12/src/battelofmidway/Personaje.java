
package battelofmidway;

public class Personaje extends ObjetoGrafico implements ObjetoMovible{
    

	/*protected Object nivel_energia;
	protected Object cant_vida;
        public abstract void mover();
	public void restar_vida() {
	}*/

    public Personaje(String direccion) {
        super(direccion);
    }
    
    public Personaje(String direcciones[], int cantidadImagenes, int numeroImagen) {
        super(direcciones, cantidadImagenes, numeroImagen);
    }
    

	/*protected Object nivel_energia;
	protected Object cant_vida;
        public abstract void mover();
	public void restar_vida() {
	}*/

    @Override
    public void update(double delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void movimiento() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}