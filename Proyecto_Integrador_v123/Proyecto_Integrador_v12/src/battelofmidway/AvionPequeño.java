package battelofmidway;

import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;

public class AvionPequeño extends Enemigo{
	private static final String DIRECCIONES[] = {"recursos\\Enemigos\\avion-pequeño-v2.png",
			"recursos\\Animaciones\\exp_pequeña_bucle.gif",
			"recursos\\Enemigos\\img_transparente_5x5.png",
			"recursos\\Enemigos\\avion-pequeño-otro-derecha.png",
			"recursos\\Enemigos\\avion-pequeño-otro-derecha-abajo-1.png",
			"recursos\\Enemigos\\avion-pequeño-otro-derecha-abajo-2.png",
			"recursos\\Enemigos\\avion-pequeño-otro-derecha-abajo-3.png",
			"recursos\\Enemigos\\avion-pequeño-otro-abajo.png",
			"recursos\\Enemigos\\avion-pequeño-otro-girando-arriba-1.png",
			"recursos\\Enemigos\\avion-pequeño-otro-girando-arriba-2.png",
			"recursos\\Enemigos\\avion-pequeño-otro-girando-arriba-3.png",
			"recursos\\Enemigos\\avion-pequeño-otro-girado-arriba.png",
			"recursos\\Enemigos\\avion-pequeño-otro-rotando-arriba-1.png",
			"recursos\\Enemigos\\avion-pequeño-otro-rotando-arriba-2.png",
			"recursos\\Enemigos\\avion-pequeño-otro-rotando-arriba-3.png",
			"recursos\\Enemigos\\avion-pequeño-otro-arriba.png",
			"recursos\\Enemigos\\avion-pequeño-otro-izquierda.png",
			"recursos\\Enemigos\\avion-pequeño-otro-izquierda-abajo-1.png",
			"recursos\\Enemigos\\avion-pequeño-otro-izquierda-abajo-2.png",
	"recursos\\Enemigos\\avion-pequeño-otro-izquierda-abajo-3.png"};
	private int contadorMultiple;
	private boolean enMovimiento = false;
	private int tiempoExplosion = 0;
	private boolean llegoAbajo = false;
	private boolean llegoDerecha = false;
	private int mov;
	private Arma arma= null;


	public AvionPequeño(int mov) {
		super(DIRECCIONES, 20, 0);
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
	//int bordeSuperior = screenInsets.top;


	public void movimiento(double posJX, double posJY){
		if(enMovimiento){
			switch(mov) {
			case 1: { 
				if(contadorMultiple < 60) {
					cambiarImagen(7);
					posY += 3;
				}
				else if(contadorMultiple < 70) {
					cambiarImagen(7);
					posY+= 2;
				}
				else if(contadorMultiple < 80) {
					cambiarImagen(8);
					posY+= 1;
				}
				else if(contadorMultiple < 90) {
					cambiarImagen(9);
					posY-= 1;
				}
				else if(contadorMultiple < 90) {
					cambiarImagen(10);
					posY-= 1;
					
				}
				else if(contadorMultiple < 100) {
					cambiarImagen(10);
					posY-= 2;
				}
				else if(contadorMultiple < 100) {
					cambiarImagen(11);
					posY-= 3;
				}
				else if(contadorMultiple < 110) {
					cambiarImagen(12);
					posY-= 3;
				}
//				else if(contadorMultiple < 120) {
//					cambiarImagen(14);
//					posY-= 3;
//				}
				else if(contadorMultiple < 240) {
					cambiarImagen(15);
					posY-= 6;
				}
				if(contadorMultiple >= 240)
					contadorMultiple = 0;				
				if (contadorMultiple < 240)
				contadorMultiple++;
				//System.out.println("AvionPequeñoOtro:" + contadorMultiple);
				if(contadorMultiple == 10)
					arma.dispararE((int)posX,(int) posY,(int) posJX,(int) posJY);
				break;
			}
			case 2: {
				if(contadorMultiple < 70) {
					cambiarImagen(3);
					posX+=5;
				}
				else if(contadorMultiple < 80) {
					cambiarImagen(4);
					posX+= 4;
					posY++;
				}
				else if(contadorMultiple < 90) {
					cambiarImagen(5);
					posX+= 3;
					posY+= 2;
				}
				else if(contadorMultiple < 100) {
					cambiarImagen(6);
					posX+= 2;
					posY+= 4;
				}
				else if(contadorMultiple < 180) {
					cambiarImagen(7);
					posY+= 4;
				}
				contadorMultiple++;
				if(contadorMultiple == 10)
					arma.dispararE((int)posX,(int) posY,(int) posJX,(int) posJY);
				break;
			}
			case 3: {
				if(contadorMultiple < 60) {
					cambiarImagen(7);
					posY += 3;
				}
				else if(contadorMultiple < 70) {
					cambiarImagen(7);
					posY+= 2;
				}
				else if(contadorMultiple < 80) {
					cambiarImagen(8);
					posY+= 1;
				}
				else if(contadorMultiple < 240) {
					cambiarImagen(7);
					posY+= 2;
				}
				
				if(contadorMultiple >= 240)
					contadorMultiple = 0;				
				if (contadorMultiple < 240)
				contadorMultiple++;
				if(contadorMultiple == 10)
					arma.dispararE((int)posX,(int) posY,(int) posJX,(int) posJY);
				//System.out.println("AvionPequeñoOtro:" + contadorMultiple);
				break;
			}
			case 4: {
				if(contadorMultiple < 70) {
					cambiarImagen(16);
					posX-=4;
				}
				else if(contadorMultiple < 80) {
					cambiarImagen(17);
					posX-= 3;
					posY++;
				}
				else if(contadorMultiple < 90) {
					cambiarImagen(18);
					posX-= 2;
					posY+= 2;
				}
				else if(contadorMultiple < 100) {
					cambiarImagen(19);
					posX-= 1;
					posY+= 4;
				}
				else if(contadorMultiple < 180) {
					cambiarImagen(7);
					posY+= 4;
				}
				contadorMultiple++;
				if(contadorMultiple == 10)
					arma.dispararE((int)posX,(int) posY,(int) posJX,(int) posJY);
				break;
			}
			}
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
