package battelofmidway;

public class Refuerzo extends PowerUp {
	
    private static final String IMAGENES[] = {
            "recursos\\PowerUps\\Compas.GIF"
           }; 

	public Refuerzo() {
	super(IMAGENES, 1, 0);
	this.tipo = 0;
	this.puntos = 200;
	this.segundosExtra = 20000;
	this.desaparicion = 5000;
	this.visible = false;
	}
	
}
