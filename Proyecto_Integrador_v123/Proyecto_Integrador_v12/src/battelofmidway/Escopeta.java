
 package battelofmidway;


public class Escopeta extends PowerUp {

    private static final String IMAGENES[] = {
                                            "recursos\\PowerUps\\ShotGun.gif", // 0
                                           }; 

    public Escopeta() {
        super(IMAGENES, 1, 0);
        this.tipo = 0;
        this.puntos = 100;
        this.segundosExtra = 20000;
        this.desaparicion = 500000;;
        this.visible = false;
    }
    
    
}

