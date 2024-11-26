package battelofmidway;

import java.awt.Graphics2D;
import java.awt.Window;

public class AvionMediano extends Enemigo {
	private static final String DIRECCIONES[] = {"recursos\\Enemigos\\avion-mediano-v2.png",
    "recursos\\Animaciones\\exp_mediana_bucle.gif",
    "recursos\\Enemigos\\img_transparente_5x5.png",
    "recursos\\Enemigos\\avion_mediano_v2_izquierda.png",
    "recursos\\Enemigos\\avion_mediano_v2_derecha.png",
    "recursos\\Enemigos\\avion_mediano_v2_algo_izquierda.png",
    "recursos\\Enemigos\\avion_mediano_v2_algo_derecha.png",
    "recursos\\Enemigos\\avion-mediano-v2-bajo-1.png",
    "recursos\\Enemigos\\avion-mediano-v2-bajo-2.png"
    };
	
	private int contadorMultiple;
	private int contadorInvulnerable = 0;
	private boolean invulnerable = false;
	private boolean enMovimiento = false;
	private int tiempoExplosion = 0;
	private double posX2 = posX;
	private Arma arma = null;
	private int vida = 25;
	private double centerX;
    private double centerY;
    private double radius;
    private double angleOffset; // Desplazamiento angular para cada objeto
    private boolean sentidoHorario;

    public void quitarVidaA() {
    	vida--;
    	if(vida ==0) 
    		setFueChocado(true);
    }
    
    
	public AvionMediano(double centerX, double centerY, double radius, double angleOffset, boolean sentidoHorario) {
        super(DIRECCIONES, 9, 0);
        this.visible = false;
        contadorMultiple = 0;
        this.puntos = 30;
        
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.angleOffset = angleOffset;
        this.sentidoHorario = sentidoHorario;
        this.arma = new Arma(Arma.MUNICION_ENEMIGO_TRI);
    }
	

//	@Override
//    public void movimiento() {
//        if (enMovimiento) {
//            double centerX = Window.WIDTH / 2;  // Punto central en el eje X
//            double centerY = Window.HEIGHT / 2;   // Punto central en el eje Y
//            double radius = 250;                  // Radio del círculo
//
//            double angle = contadorMultiple * Math.PI / 180;  // Convertir a radianes
//
//            // Calcular las nuevas coordenadas X e Y basadas en el ángulo y el radio
//            posX = centerX + radius * Math.cos(angle);
//            posY = centerY + radius * Math.sin(angle);
//
//            contadorMultiple++;  // Incrementar el contador
//            if (contadorMultiple >= 360) {
//                contadorMultiple = 0;  // Reiniciar el contador al alcanzar 360 grados
//            }
//        }
//    }
	
	
    public void movimiento(double posJX, double posJY) {
        if (enMovimiento) {
        	double sentido = sentidoHorario ? 1.0 : -1.0;
            double angle = (contadorMultiple * Math.PI / 180 * sentido) + angleOffset;  // Aplicar el desplazamiento angular
            centerY = centerY - 2.3;

            // Calcular las nuevas coordenadas X e Y basadas en el ángulo y el radio
            posX = centerX + radius * Math.cos(angle);
            posY = centerY + radius * Math.sin(angle);
            
            
            
            if (contadorMultiple <= 20 || (contadorMultiple >= 160 && contadorMultiple <= 200) || contadorMultiple >= 340) {
            	cambiarImagen(0);
            	posX2 = posX;
            	//System.out.println("frente" + contadorMultiple);
            }
            else if (posX2 >= posX && (contadorMultiple <= 60 || (contadorMultiple >= 120 && contadorMultiple <= 160) || contadorMultiple >= 300)) {
            	cambiarImagen(5);
            	posX2 = posX;
            	//System.out.println("algo izquierda" + contadorMultiple);  	
            } else if (posX2 >= posX) {
            	cambiarImagen(3);
            	posX2 = posX;
            	//System.out.println("izquierda" + contadorMultiple);
            } else if (posX2 < posX && (contadorMultiple <= 60 || (contadorMultiple >= 200 && contadorMultiple <= 240) || contadorMultiple >= 300)){
            	cambiarImagen(6);
            	posX2 = posX;
            	//System.out.println("algo derecha" + contadorMultiple);
            }
            else if (posX2 < posX){
            	cambiarImagen(4);
            	posX2 = posX;
            	//System.out.println("derecha" + contadorMultiple);
            }
           
            contadorMultiple++;  // Incrementar el contador
            if (contadorMultiple >= 360) {
                contadorMultiple = 0;  // Reiniciar el contador al alcanzar 360 grados
            }
            if (contadorMultiple % 60 == 0 && !this.isInvulnerable())
            	arma.dispararE((int)posX,(int) posY,(int) posJX,(int) posJY);
        }
        	contadorInvulnerable++;
        	//System.out.println(contadorInvulnerable);
        	if(contadorInvulnerable > 280)
            	hacerInvulnerable();
        	if(contadorInvulnerable > 360) {
        		contadorInvulnerable = 0;
        		setInvulnerable(false);
        	}
        		
        
    }
	
	public Arma getarma() {
		return arma;
		}

	    public void display(Graphics2D g,double posJX, double posJY,int alto, int bajo, boolean pausa){
	        if(this.visible){
	            if(!(fueChocado)){
	                movimiento(posJX,posJY);
	                if(!pausa)                              
	                	arma.display(g,alto,bajo);
	            }  else {
	                    this.visible = false;
	                    tiempoExplosion++;
	                    if(tiempoExplosion > 20)
	                    	cambiarImagen(2);
	            }
	            g.drawImage(imagen, (int) posX, (int) posY,sizeImg[0][0]*3/2, (int)sizeImg[0][1]*3/2, null);
	        }
	    }
	   
	private void hacerInvulnerable() {
		setInvulnerable(true);
		
		if (contadorInvulnerable < 300) {
			cambiarImagen(7);
			//System.out.println("imagen 7");
		}
		else if (contadorInvulnerable < 340) {
			cambiarImagen(8);
			//System.out.println("imagen 8");
		}
		else if (contadorInvulnerable < 360) {
			cambiarImagen(7);
			//System.out.println("imagen 7");
		}
		
	}

	public boolean isEnMovimiento() {
		return enMovimiento;
	}

	public void setEnMovimiento(boolean enMovimiento) {
		this.enMovimiento = enMovimiento;
	}

	@Override
	public void setFueChocado(boolean fueChocado){
		this.fueChocado = fueChocado;
		this.contadorMultiple = 0;
		cambiarImagen(1);
	}
	
	public boolean isInvulnerable() {
		return invulnerable;
	}

	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
	}
}


