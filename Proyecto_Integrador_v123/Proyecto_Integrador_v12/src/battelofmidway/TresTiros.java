package battelofmidway;

public class TresTiros extends PowerUp {

    private static final String IMAGENES[] = {
                                            "recursos\\PowerUps\\Tri.gif", // 0
                                           }; 

    public TresTiros() {
        super(IMAGENES, 1, 0);
        this.tipo = 0;
        this.puntos = 200;
        this.segundosExtra = 20000;
        this.desaparicion = 500000;
        this.visible = false;
    }
    
    
}
