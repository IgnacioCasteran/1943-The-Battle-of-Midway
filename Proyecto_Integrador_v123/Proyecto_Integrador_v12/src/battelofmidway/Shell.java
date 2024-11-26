package battelofmidway;

public class Shell extends PowerUp {
	
    private static final String IMAGENES[] = {
            "recursos\\PowerUps\\Misil.GIF",
           }; 

	public Shell() {
	super(IMAGENES, 1, 0);
	this.tipo = 0;
	this.puntos = 200;
	this.segundosExtra = 20000;
	this.desaparicion = 500000;;
	this.visible = false;
	}
	
}