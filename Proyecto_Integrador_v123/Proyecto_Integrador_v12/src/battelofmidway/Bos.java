package battelofmidway;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Bos extends Enemigo {

    private static final String DIRECCIONES[] = {
                                                 "recursos\\Enemigos\\Cañon1Bos2.png",
                                                 "recursos\\Enemigos\\Bos1.png",
                                                 "recursos\\Enemigos\\ExploCañonInfinit.gif",
                                                 "recursos\\Enemigos\\CañonFogoneo.png",
                                                 "recursos\\Enemigos\\explo0.gif"};
    private int contadorMultiple;
    private boolean enMovimiento = false;
    private Arma arma=null;
    private Arma arma2=null;
    
    
    //VIDAS TORRETAS
    private int vidaT1 = 6;
    private int vidaT2 = 6;
    private int vidaT3 = 6;
    private int vidaT4 = 6;
    private int vidaT5 = 6;
    //VIDAS FOGONEO
    private int vidaF1 = 3;
    private int vidaF2 = 3;
    private int vidaF3 = 3;
    private int vidaF4 = 3;
    
    private int contD = 0;
    
    public Bos() {
        super(DIRECCIONES, 5, 0);
        this.visible = false;
        contadorMultiple = 0;
        this.puntos = 500;
        this.arma = new Arma(Arma.MUNICION_ENEMIGO_BOS);
        this.arma2 = new Arma(Arma.MUNICION_ENEMIGO_FOGONEO);
    }
    
    public Arma getarma() {
    	return arma;
    }
    
    public Arma getarma2() {
    	return arma2;
    }
    
    private boolean der=true;
    
    public void movimiento(double posJX, double posJY){
        if(enMovimiento){
        	posY -= 1.8; 
        	
        	
        	if(posX < 300 && der) {
        		posX+= 1.2;
        	}else {
        		posX-=1.2;
        		der=false;
        		if(posX <20)
        			der=true;
        	}
        	
        	
            if(contadorMultiple < 45){
                contadorMultiple+=1;

            } else {
                contadorMultiple = 0; 
                //DISPAROS CAÑONES
                if(viveT(1))
                arma.dispararE((int)posX+42,(int) posY+110,(int) posJX,(int) posJY);
                
                if(viveT(2))
                arma.dispararE((int)posX+42,(int) posY+130,(int) posJX,(int) posJY);
                
                if(viveT(3))
                arma.dispararE((int)posX+42,(int) posY+170,(int) posJX,(int) posJY);
                
                if(viveT(4))
                arma.dispararE((int)posX+42,(int) posY+440,(int) posJX,(int) posJY);
                
                if(viveT(5))
                arma.dispararE((int)posX+42,(int) posY+490,(int) posJX,(int) posJY);
                
                
                //DISPAROS FOGONEO
                if(viveF(1))
                arma2.dispararE((int)posX+78,(int) posY+330,(int) posJX,(int) posJY);
                
                if(viveF(2))
                arma2.dispararE((int)posX-8,(int) posY+330,(int) posJX,(int) posJY);
                
                if(viveF(3))
                arma2.dispararE((int)posX-8,(int) posY+252,(int) posJX,(int) posJY);
                
                if(viveF(4))
                arma2.dispararE((int)posX+78,(int) posY+252,(int) posJX,(int) posJY);
                
            }
        }
    }
    
    public void display(Graphics2D g,double posJX, double posJY,int alto, int bajo, boolean pausa){	
        if(this.visible){
        	
        	g.drawImage(imagenes[1], (int)posX,(int)posY, imagenes[1].getWidth(null)*2, imagenes[1].getHeight(null)*2, null);  //IMAGEN DE BARCO
        	
            if(!(fueChocado)){
                movimiento(posJX,posJY);
                if(!pausa) {
                arma.display(g,alto,bajo);
                arma2.display(g,alto,bajo);
                }
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
            int deltaX = (int) (posJX - posX+40);
            int deltaY = (int) (posJY - posY-110);
            double anguloEnRadianes = Math.atan2(deltaY, deltaX); 
            
            //CAÑON 1
            if(viveT(1)) {
            AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+40, (int) posY+110);
            rotacion.rotate(anguloEnRadianes, imagen.getWidth(null) / 2, imagen.getHeight(null) / 2);
            g.drawImage(imagen, rotacion, null);
            }else{
    			g.drawImage(imagenes[2], (int)posX+30,(int)posY+90, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);         
            }// 
             
            //CAÑON 2
            if(viveT(2)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+40, (int) posY+130);
            rotacion.rotate(anguloEnRadianes, imagen.getWidth(null) / 2, imagen.getHeight(null) / 2);
            g.drawImage(imagen, rotacion, null);
            }else{
    			g.drawImage(imagenes[2], (int)posX+34,(int)posY+120, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }//
            
          //CAÑON 3
            if(viveT(3)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+40, (int) posY+170);
            rotacion.rotate(anguloEnRadianes, imagen.getWidth(null) / 2, imagen.getHeight(null) / 2);
            g.drawImage(imagen, rotacion, null);
            }else{
    			g.drawImage(imagenes[2], (int)posX+20,(int)posY+150, imagenes[2].getWidth(null)+40, imagenes[2].getHeight(null)+10, null);
            }//
            
            
           anguloEnRadianes = Math.atan2(deltaY-370, deltaX); 
          //CAÑON 4
            if(viveT(4)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+40, (int) posY+440);
            rotacion.rotate(anguloEnRadianes, imagen.getWidth(null) / 2, imagen.getHeight(null) / 2);
            g.drawImage(imagen, rotacion, null);
            }else{
    			g.drawImage(imagenes[2], (int)posX+30,(int)posY+420, imagenes[2].getWidth(null)+20, imagenes[2].getHeight(null)+20, null);
            }//
            
          //CAÑON 5
            if(viveT(5)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+40, (int) posY+490);
            rotacion.rotate(anguloEnRadianes, imagen.getWidth(null) / 2, imagen.getHeight(null) / 2);
            g.drawImage(imagen, rotacion, null);
            }else{
    			g.drawImage(imagenes[2], (int)posX+30,(int)posY+470, imagenes[2].getWidth(null)+20, imagenes[2].getHeight(null)+20, null);
            }
            
            
            
            //
            
            //FOGONEOS
            
            if(viveF(1)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+78, (int) posY+330);
            rotacion.rotate(anguloEnRadianes, imagenes[3].getWidth(null) / 2, imagenes[3].getHeight(null) / 2);
            g.drawImage(imagenes[3], rotacion, null);
            }else{
    			g.drawImage(imagenes[2], (int)posX+78,(int)posY+318, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }
            
            //FOGONEO 2
            if(viveF(2)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX-8, (int) posY+330);
            rotacion.rotate(anguloEnRadianes, imagenes[3].getWidth(null) / 2, imagenes[3].getHeight(null) / 2);
            g.drawImage(imagenes[3], rotacion, null);
            }else{
    			g.drawImage(imagenes[2], (int)posX-8,(int)posY+318, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }
            
            //FOGONEO 3
            if(viveF(3)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX-8, (int) posY+252);
            rotacion.rotate(anguloEnRadianes, imagenes[3].getWidth(null) / 2, imagenes[3].getHeight(null) / 2);
            g.drawImage(imagenes[3], rotacion, null);
            }else {
    			g.drawImage(imagenes[2], (int)posX-8,(int)posY+242, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }
            
          //FOGONEO 4
            if(viveF(4)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+78, (int) posY+252);
            rotacion.rotate(anguloEnRadianes, imagenes[3].getWidth(null) / 2, imagenes[3].getHeight(null) / 2);
            g.drawImage(imagenes[3], rotacion, null);
            }else {
    			g.drawImage(imagenes[2], (int)posX+78,(int)posY+242, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }
            
            //SI EL BARCO ESTA DESTRUIDO DIBUJA EXPLOCIONES 
            if(contD == 9){
            	g.drawImage(imagenes[4], (int)posX+20,(int)posY+180, imagenes[4].getWidth(null)/4, imagenes[4].getHeight(null)/4, null);
            	g.drawImage(imagenes[4], (int)posX+20,(int)posY+270, imagenes[4].getWidth(null)/3, imagenes[4].getHeight(null)/3, null);
            	g.drawImage(imagenes[4], (int)posX+20,(int)posY+350, imagenes[4].getWidth(null)/4, imagenes[4].getHeight(null)/4, null);
            }
            	
            
            //
           
        }
    }

    public boolean isEnMovimiento() {
        return enMovimiento;
    }
    
    public boolean Destruido() {
    	if(contD > 6)
    		return true;
    	else
    		return false;
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
    	
    	public boolean viveF(int t) {
    		switch(t) {
    		case 1:
    			if(vidaF1 > 0)
    				return true;
    			else return false;
    		case 2:
    			if(vidaF2 > 0)
    				return true;
    			else return false;
    		case 3:
    			if(vidaF3 > 0)
    				return true;
    			else return false;
    		case 4:
    			if(vidaF4 > 0)
    				return true;
    			else return false;
    			
    		}
    		return false;
    	}
    	
    	public void quitarVidaT(int t) {
    		switch(t) {
    		case 1:
    			vidaT1--;
    			if(vidaT1 == 0)
    				contD++;
    			break;
    		case 2:
    			vidaT2--;
    			if(vidaT2 == 0)
    				contD++;
    			break;
    		case 3:
    			vidaT3--;
    			if(vidaT3 == 0)
    				contD++;
    			break;
    		case 4:
    			vidaT4--;
    			if(vidaT4 == 0)
    				contD++;
    			break;
    		case 5:
    			vidaT5--;
    			if(vidaT5 == 0)
    				contD++;
    			break;	
    		}
    	}
    	
    	public void quitarVidaF(int t) {
    		switch(t) {
    		case 1:
    			vidaF1--;
    			if(vidaF1 == 0)
    				contD++;
    			break;
    		case 2:
    			vidaF2--;
    			if(vidaF2 == 0)
    				contD++;
    			break;
    		case 3:
    			vidaF3--;
    			if(vidaF3 == 0)
    				contD++;
    			break;
    		case 4:
    			vidaF4--;
    			if(vidaF4 == 0)
    				contD++;
    			break;
    		}
    	}
    	
    	public Rectangle getBordesT(int t) {
    		switch(t) {
    		case 1:
    			return new Rectangle((int)posX+40 , (int)posY+110, imagen.getWidth(null), imagen.getHeight(null));
    		case 2:
    			return new Rectangle((int)posX+40 , (int)posY+130, imagen.getWidth(null), imagen.getHeight(null));
    		case 3:
    			return new Rectangle((int)posX+40 , (int)posY+170, imagen.getWidth(null), imagen.getHeight(null));
    		case 4:
    			return new Rectangle((int)posX+40 , (int)posY+440, imagen.getWidth(null), imagen.getHeight(null));
    		case 5:
    			return	new Rectangle((int)posX+40 , (int)posY+490, imagen.getWidth(null), imagen.getHeight(null)); 	
    		}
    		
    		return null; 	
    	}
    	
    	public Rectangle getBordesF(int t) {
    		switch(t) {
    		case 1:
    			return new Rectangle((int)posX+78 , (int)posY+330, imagen.getWidth(null), imagen.getHeight(null));
    		case 2:
    			return new Rectangle((int)posX-8 , (int)posY+330, imagen.getWidth(null), imagen.getHeight(null));
    		case 3:
    			return new Rectangle((int)posX-8 , (int)posY+252, imagen.getWidth(null), imagen.getHeight(null));
    		case 4:
    			return new Rectangle((int)posX+78 , (int)posY+252, imagen.getWidth(null), imagen.getHeight(null));	
    		}
    		
    		return null; 	
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
