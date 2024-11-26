package battelofmidway;

import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;

public class AvionKamikaze extends Enemigo{
	private static final String DIRECCIONES[] = {"recursos\\Enemigos\\avion-pequeño-v2.png",
	"recursos\\Animaciones\\exp_pequeña_bucle.gif",
	"recursos\\Enemigos\\img_transparente_5x5.png",
	"recursos\\Enemigos\\avion_pequeño_v2_izquierda.png",
	"recursos\\Enemigos\\avion_pequeño_v2_derecha.png",
	"recursos\\Enemigos\\avion-pequeño-v2-derecha.png",
	"recursos\\Enemigos\\avion-pequeño-v2-izquierda.png",
	"recursos\\Enemigos\\avion-pequeño-v2-izquierda-rojo.png"};
	private int contadorMultiple;
	private boolean enMovimiento = false;
	private int tiempoExplosion = 0;
	private boolean llegoAbajo = false;
	private boolean llegoDerecha = false;
	private int mov;
	boolean fuecontado=false;
	private Arma arma= null;

	public AvionKamikaze(int mov) {
		super(DIRECCIONES, 8, 0);
		this.visible = false;
		contadorMultiple = 0;
		this.puntos = 100;
		this.mov = mov;
		this.arma = new Arma(Arma.MUNICION_ENEMIGO_SIMPLE);
	}
	
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice gd = ge.getDefaultScreenDevice();

    Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(gd.getDefaultConfiguration());
    int screenHeight = gd.getDisplayMode().getHeight() - screenInsets.bottom;

    int bordeInferior = screenHeight - sizeImg[0][1];
    int bordeDerecho = gd.getDisplayMode().getWidth();
    int bordeIzquierdo = -sizeImg[0][0];
    
    boolean fueContado() {
    	return fuecontado;
    }
	
    
    public void movimiento(double posJX, double posJY){
    	if(enMovimiento){
    		switch(mov) {
    		case 1: { 
    			if(!llegoAbajo)
    				posY += 5;
    			else {
    				cambiarImagen(4);
    				posY-= 12;
    				posX+= 1;
    			}
    			if(posY >= bordeInferior) {
    				llegoAbajo = true;
    			}
    			break;
    		}
    		case 2: {
    			if(!llegoAbajo)
    				posY += 5;
    			else {
    				cambiarImagen(3);
    				posY-= 12;
    				posX-= 1;
    			}
    			if(posY >= bordeInferior) {
    				llegoAbajo = true;
    			}
    			break;
    		}
    		case 3: {
    			if(!llegoDerecha) {
    				cambiarImagen(5);
    				posX += 4;
    				//System.out.println("erterterter");
    			}
    			else {
    				cambiarImagen(6);
    				//System.out.println("afdsfasdf");
    				posX-= 4;
    			}
    			if(posX >= bordeDerecho) {
    				llegoDerecha = true;
    			}
    			break;
    		}
    		case 4: {
    			cambiarImagen(7);
    			posX -= 3;
    			break;
    		}
    		}
    	}
    }
    
    public Arma getarma() {
		return arma;
		}

    public static String[] getDirecciones() {
    	return DIRECCIONES;
    }


	public int getContadorMultiple() {
		return contadorMultiple;
	}


	public int getTiempoExplosion() {
		return tiempoExplosion;
	}


	public boolean isLlegoAbajo() {
		return llegoAbajo;
	}


	public boolean isLlegoDerecha() {
		return llegoDerecha;
	}


	public int getMov() {
		return mov;
	}


	public GraphicsEnvironment getGe() {
		return ge;
	}


	public GraphicsDevice getGd() {
		return gd;
	}


	public Insets getScreenInsets() {
		return screenInsets;
	}


	public int getScreenHeight() {
		return screenHeight;
	}


	public int getBordeInferior() {
		return bordeInferior;
	}


	public int getBordeDerecho() {
		return bordeDerecho;
	}


	public int getBordeIzquierdo() {
		return bordeIzquierdo;
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
		fuecontado=true;
		cambiarImagen(1);
	}
}
