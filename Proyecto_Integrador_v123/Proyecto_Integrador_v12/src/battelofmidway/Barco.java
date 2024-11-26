package battelofmidway;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Barco extends Enemigo {

    private static final String DIRECCIONES[] = {
                                                 "recursos\\Enemigos\\CañonBarcoSimple.png",
                                                 "recursos\\Enemigos\\Barco.png",
                                                 "recursos\\Enemigos\\ExploCañonInfinit.gif"};
    private int contadorMultiple;
    private boolean enMovimiento = false;
    private Arma arma=null;
    
    
    //VIDAS TORRETAS
    private int vidaT1 = 6;
    private int vidaT2 = 6;
    private int vidaT3 = 6;
    private int vidaT4 = 6;
    private int vidaT5 = 6;
    private int contT = 0;
    
    public Barco() {
        super(DIRECCIONES, 3, 0);
        this.visible = false;
        contadorMultiple = 0;
        this.puntos = 500;
        this.arma = new Arma(Arma.MUNICION_ENEMIGO_SIMPLE);
    }
    
    public Arma getarma() {
    	return arma;
    }
    
    public void movimiento(double posJX, double posJY){
        if(enMovimiento){
        	posY -= 1.8;
            if(contadorMultiple < 60){
                contadorMultiple+=1;
            } else {
                contadorMultiple = 0;
                if(viveT(1))
                arma.dispararE((int)posX+5,(int) posY,(int) posJX,(int) posJY);
                
                if(viveT(2))
                arma.dispararE((int)posX+5,(int) posY+150,(int) posJX,(int) posJY);
                
                if(viveT(3))
                arma.dispararE((int)posX+5,(int) posY+210,(int) posJX,(int) posJY);
                
                if(viveT(4))
                arma.dispararE((int)posX+5,(int) posY+240,(int) posJX,(int) posJY);
                
                if(viveT(5))
                arma.dispararE((int)posX+5,(int) posY+280,(int) posJX,(int) posJY);
            }
        }
    }
    
    public void display(Graphics2D g,double posJX, double posJY,int alto, int bajo,boolean pausa){	
        if(this.visible){
        	
        	g.drawImage(imagenes[1], (int)posX,(int)posY, imagenes[1].getWidth(null), imagenes[1].getHeight(null), null);  //IMAGEN DE BARCO
        	
            if(!(fueChocado)){
                movimiento(posJX,posJY);
                if(!pausa)
                	arma.display(g,alto,bajo);
            } else {
                if(this.contadorMultiple < 5){
                    posY-=3;
                } else {
                    if(posY < 486){
                        posY+=4;
                    } else {
                        this.visible = false;
                    }
                }
                if(contadorMultiple < 200){
                    contadorMultiple+=1;
                } else {
                    this.visible = false;
                }
            }
            
            //ROTACION 
            int deltaX = (int) (posJX - posX+6);
            int deltaY = (int) (posJY - posY+16);
            double anguloEnRadianes = Math.atan2(deltaY, deltaX); 
            
            //CAÑON 1
            if(viveT(1)) {
            AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+6, (int) posY+16);
            rotacion.rotate(anguloEnRadianes, imagen.getWidth(null) / 2, imagen.getHeight(null) / 2);
            g.drawImage(imagen, rotacion, null);
            }else if(vidaT1 > -25) {
            	vidaT1--;
    			g.drawImage(imagenes[2], (int)posX+2,(int)posY+16, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);         
            }// 
             
            //CAÑON 2
            if(viveT(2)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+6, (int) posY+150);
            rotacion.rotate(anguloEnRadianes, imagen.getWidth(null) / 2, imagen.getHeight(null) / 2);
            g.drawImage(imagen, rotacion, null);
            }else if(vidaT2 > -25) {
            	vidaT2--;
    			g.drawImage(imagenes[2], (int)posX+2,(int)posY+150, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }//
            
          //CAÑON 3
            if(viveT(3)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+6, (int) posY+210);
            rotacion.rotate(anguloEnRadianes, imagen.getWidth(null) / 2, imagen.getHeight(null) / 2);
            g.drawImage(imagen, rotacion, null);
            }else if(vidaT3 > -25) {
            	vidaT3--;
    			g.drawImage(imagenes[2], (int)posX+2,(int)posY+210, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }//
            
          //CAÑON 4
            if(viveT(4)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+6, (int) posY+240);
            rotacion.rotate(anguloEnRadianes, imagen.getWidth(null) / 2, imagen.getHeight(null) / 2);
            g.drawImage(imagen, rotacion, null);
            }else if(vidaT4 > -25) {
            	vidaT4--;
    			g.drawImage(imagenes[2], (int)posX+2,(int)posY+240, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }//
            
          //CAÑON 5
            if(viveT(5)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+6, (int) posY+280);
            rotacion.rotate(anguloEnRadianes, imagen.getWidth(null) / 2, imagen.getHeight(null) / 2);
            g.drawImage(imagen, rotacion, null);
            }else if(vidaT5 > -25) {
            	vidaT5--;
    			g.drawImage(imagenes[2], (int)posX+2,(int)posY+280, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }//
            
           
        }
    }

    public boolean isEnMovimiento() {
        return enMovimiento;
    }
    
    	
    	public boolean viveT(int t) {
    		switch(t) {
    		case 1:
    			if(vidaT1 > 0)
    				return true;
    			else return false;
    		case 2:
    			if(vidaT2 > 0)
    				return true;
    			else return false;
    		case 3:
    			if(vidaT3 > 0)
    				return true;
    			else return false;
    		case 4:
    			if(vidaT4 > 0)
    				return true;
    			else return false;
    		case 5:
    			if(vidaT5 > 0)
    				return true;
    			else return false;
    			
    		}
    		return false;
    	}
    	
    	public void quitarVidaT(int t) {
    		switch(t) {
    		case 1:
    			vidaT1--;
    			break;
    		case 2:
    			vidaT2--;
    			break;
    		case 3:
    			vidaT3--;
    			break;
    		case 4:
    			vidaT4--;
    			break;
    		case 5:
    			vidaT5--;
    			break;	
    		}
    	}
    	
    	public Rectangle getBordesT(int t) {
    		switch(t) {
    		case 1:
    			return new Rectangle((int)posX+6 , (int)posY+16, imagen.getWidth(null), imagen.getHeight(null));
    		case 2:
    			return new Rectangle((int)posX+6 , (int)posY+150, imagen.getWidth(null), imagen.getHeight(null));
    		case 3:
    			return new Rectangle((int)posX+6 , (int)posY+210, imagen.getWidth(null), imagen.getHeight(null));
    		case 4:
    			return new Rectangle((int)posX+6 , (int)posY+240, imagen.getWidth(null), imagen.getHeight(null));
    		case 5:
    			return	new Rectangle((int)posX+6 , (int)posY+280, imagen.getWidth(null), imagen.getHeight(null)); 	
    		}
    		
    		return new Rectangle((int)posX+6 , (int)posY+280, imagen.getWidth(null), imagen.getHeight(null)); 	
    	}
    
    	public Rectangle getBordesT1(){
    		return new Rectangle((int)posX+6 , (int)posY+16, imagen.getWidth(null), imagen.getHeight(null));
    	}
    	
    	public Rectangle getBordesT2(){
    		return new Rectangle((int)posX+6 , (int)posY+150, imagen.getWidth(null), imagen.getHeight(null));
    	}
    	
    	public Rectangle getBordesT3(){
    		return new Rectangle((int)posX+6 , (int)posY+210, imagen.getWidth(null), imagen.getHeight(null));
    	}
    	
    	public Rectangle getBordesT4(){
    		return new Rectangle((int)posX+6 , (int)posY+240, imagen.getWidth(null), imagen.getHeight(null));
    	}
    	
    	
    	public Rectangle getBordesT5(){
    		return	new Rectangle((int)posX+6 , (int)posY+280, imagen.getWidth(null), imagen.getHeight(null)); 
    	}
    

    public void setEnMovimiento(boolean enMovimiento) {
        this.enMovimiento = enMovimiento;
    }
    
    @Override
    public void setFueChocado(boolean fueChocado){
        this.fueChocado = fueChocado;
        this.contadorMultiple = 0;
        cambiarImagen(0);
    }
}

