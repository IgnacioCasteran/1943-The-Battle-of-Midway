package battelofmidway;
import java.util.Random;

public class Pow extends PowerUp {
	
	private  int cont = 0;
	private boolean pow = true;
	
    private static final String IMAGENES[] = {
            "recursos\\PowerUps\\P0.PNG",
            "recursos\\PowerUps\\P1.PNG",
            "recursos\\PowerUps\\P2.PNG",
            "recursos\\PowerUps\\P3.PNG",
            "recursos\\PowerUps\\Auto.GIF",
            "recursos\\PowerUps\\Tri.GIF",
            "recursos\\PowerUps\\ShotGun.GIF",// 0
           }; 

	public Pow() {
	super(IMAGENES, 7, 0);
	this.tipo = 0;
	this.puntos = 200;
	this.segundosExtra = 20000;
	this.desaparicion = 500000;;
	this.visible = false;
	}
	
	public boolean isPow() {
		return pow;
	}
	
	public void fuedisparado() {
		if(cont < 2) {
			cont++;
			cambiarImagen(cont);
		}
		else if(cont == 2) {
			tipo = generarNumeroAleatorio()+3;
			 cambiarImagen(tipo);
			 pow=false;
			 cont++;
		}else if(cont == 3) {
			cambiarImagen(cont);
			cont = -1;
			pow=true;
		}
		
	}
	
	public static int generarNumeroAleatorio() {
        Random random = new Random();
        return random.nextInt(3) + 1;
    }
	
	public int getTipo() {
		return tipo;
	}

}