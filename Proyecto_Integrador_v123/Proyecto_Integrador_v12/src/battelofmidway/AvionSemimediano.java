package battelofmidway;

import java.awt.Graphics2D;

public class AvionSemimediano extends Enemigo {
	private static final String DIRECCIONES[] = {"recursos\\Enemigos\\avion-semimediano-v2.png",
		    "recursos\\Animaciones\\exp_mediana_bucle.gif",
		    "recursos\\Enemigos\\img_transparente_5x5.png",
		    "recursos\\Enemigos\\avion-semimediano-v2-izquierda.png",
		    "recursos\\Enemigos\\avion-semimediano-v2-derecha.png",
		    "recursos\\Enemigos\\avion-semimediano-v2-algo-izquierda.png",
		    "recursos\\Enemigos\\avion-semimediano-v2-algo-derecha.png",
		    };
			
			private int contadorMultiple;
			private boolean enMovimiento = false;
			private int tiempoExplosion = 0;
			private double posX2 = posX;
			private Arma arma= null;
			private int vida = 15;
			
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
		    
			
			public AvionSemimediano(double centerX, double centerY, double radius, double angleOffset, boolean sentidoHorario) {
		        super(DIRECCIONES, 7, 0);
		        this.visible = false;
		        contadorMultiple = 0;
		        this.puntos = 15;
		        
		        this.centerX = centerX;
		        this.centerY = centerY;
		        this.radius = radius;
		        this.angleOffset = angleOffset;
		        this.sentidoHorario = sentidoHorario;
		        this.arma = new Arma(Arma.MUNICION_ENEMIGO_SIMPLE);
		    }
			
			
		    public void movimiento(double posJX, double posJY) {
		        if (enMovimiento) {
		        	double sentido = sentidoHorario ? 1.0 : -1.0;
		            double angle = (contadorMultiple * Math.PI / 180 * sentido) + angleOffset;  // Aplicar el desplazamiento angular
		            centerY = centerY - 3;

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
		            	//ystem.out.println("algo izquierda" + contadorMultiple);
		            } else if (posX2 >= posX) {
		            	cambiarImagen(3);
		            	posX2 = posX;
		            	//System.out.println("izquierda" + contadorMultiple);
		            } else if (posX2 < posX && (contadorMultiple <= 60 || (contadorMultiple >= 200 && contadorMultiple <= 240) || contadorMultiple >= 300)){
		            	cambiarImagen(6);
		            	posX2 = posX;
		            	//	System.out.println("algo derecha" + contadorMultiple);
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
		            if (contadorMultiple % 80 == 0)
		            	arma.dispararE((int)posX,(int) posY,(int) posJX,(int) posJY);
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
			            g.drawImage(imagen, (int) posX, (int) posY, sizeImg[0][0], sizeImg[0][1], null);
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
}
