package battelofmidway;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Yamato extends Enemigo {

    private static final String DIRECCIONES[] = {
                                                 "recursos\\Enemigos\\Cañon1Bos2.png",
                                                 "recursos\\Enemigos\\Yamato.png",
                                                 "recursos\\Enemigos\\ExploCañonInfinit.gif",
                                                 "recursos\\Enemigos\\CañonFogoneo.png",
                                                 "recursos\\Enemigos\\explo0.gif",
                                                 "recursos\\Enemigos\\CañonBoss1.png",
                                                 "recursos\\Enemigos\\CañonYamato.png",
                                                 "recursos\\Enemigos\\CañonYamato2.png",};
    private int contadorMultiple;
    private boolean enMovimiento = false;
    private Arma arma=null;
    private Arma arma2=null;
    private Arma arma3=null;
    
    
    //VIDAS TORRETAS
    private int vidaT1 = 6;
    private int vidaT2 = 6;
    private int vidaT3 = 6;
    private int vidaT4 = 6;
    private int vidaT5 = 6;
    private int vidaT6 = 6;
    private int vidaT7 = 6;
    private int vidaT8 = 6;
    private int vidaT9 = 6;
    private int vidaT10 = 6;
    private int vidaT11= 6;
    private int vidaT12= 6;
    private int vidaT13= 6;
    private int vidaT14= 6;

    //VIDAS FOGONEO
    private int vidaF1 = 3;
    private int vidaF2 = 3;
    private int vidaF3 = 3;
    private int vidaF4 = 3;
    private int vidaF5 = 3;
    private int vidaF6 = 3;
    private int vidaF7 = 3;
    private int vidaF8 = 3;
    private int vidaF9 = 3;
    private int vidaF10 = 3;
    private int vidaF11= 3;
    private int vidaF12= 3;
    
    //VIDAS TORRETA TRIPLE
    private int vidaTT1 = 3;
    private int vidaTT2 = 3;
    private int vidaTT3= 3;
    
    //VIDAS CAÑON TRIPLE
    private int vidaCT1 = 3;
    private int vidaCT2 = 3;

    
    private int contD = 0;
    
    public Yamato() {
        super(DIRECCIONES, 8, 0);
        this.visible = false;
        contadorMultiple = 0;
        this.puntos = 500;
        this.arma = new Arma(Arma.MUNICION_ENEMIGO_SIMPLE);
        this.arma2 = new Arma(Arma.MUNICION_ENEMIGO_FOGONEO);
        this.arma3 = new Arma(Arma.MUNICION_ENEMIGO_TRI_BOS);
    }
    
    public Arma getarma() {
    	return arma;
    }
    
    public Arma getarma2() {
    	return arma2;
    }
    
    public Arma getarma3() {
    	return arma3;
    }
    
    private boolean der=true;
    
    public void movimiento(double posJX, double posJY){
        if(enMovimiento){
        	posY -= 2.5; 
        	
        	
        	if(posX < 300 && der) {
        		posX+= 1.2;
        	}else {
        		posX-=1.2;
        		der=false;
        		if(posX <20)
        			der=true;
        	}
        	
        	
            if(contadorMultiple < 160){
                contadorMultiple+=1;

            } else {
                contadorMultiple = 0; 
                //DISPAROS CAÑONES
                if(viveT(1))
                arma.dispararE((int)posX+130,(int) posY+610,(int) posJX,(int) posJY);
                
                if(viveT(2))
                arma.dispararE((int)posX+20,(int) posY+610,(int) posJX,(int) posJY);
                
                if(viveT(3))
                arma.dispararE((int)posX+170,(int) posY+610,(int) posJX,(int) posJY);
                
                if(viveT(4))
                arma.dispararE((int)posX-10,(int) posY+610,(int) posJX,(int) posJY);
                
                if(viveT(5))
                arma.dispararE((int)posX-10,(int) posY+585,(int) posJX,(int) posJY);
                
                if(viveT(6))
                    arma.dispararE((int)posX+165,(int) posY+585,(int) posJX,(int) posJY);
                if(viveT(7))
                    arma.dispararE((int)posX-10,(int) posY+555,(int) posJX,(int) posJY);
                if(viveT(8))
                    arma.dispararE((int)posX+165,(int) posY+555,(int) posJX,(int) posJY);
                if(viveT(9))
                    arma.dispararE((int)posX-10,(int) posY+525,(int) posJX,(int) posJY);
                if(viveT(10))
                    arma.dispararE((int)posX+165,(int) posY+525,(int) posJX,(int) posJY);
                if(viveT(11))
                    arma.dispararE((int)posX+22,(int) posY+535,(int) posJX,(int) posJY);
                if(viveT(12))
                    arma.dispararE((int)posX+130,(int) posY+535,(int) posJX,(int) posJY);
                if(viveT(13))
                    arma.dispararE((int)posX+22,(int) posY+565,(int) posJX,(int) posJY);
                if(viveT(14))
                    arma.dispararE((int)posX+130,(int) posY+565,(int) posJX,(int) posJY);
                
                
                //DISPAROS FOGONEO
                if(viveF(1))
                arma2.dispararE((int)posX+12,(int) posY+905,(int) posJX,(int) posJY);
                
                if(viveF(2))
                arma2.dispararE((int)posX+135,(int) posY+905,(int) posJX,(int) posJY);
                
                if(viveF(3))
                arma2.dispararE((int)posX,(int) posY+880,(int) posJX,(int) posJY);
                
                if(viveF(4))
                arma2.dispararE((int)posX+145,(int) posY+880,(int) posJX,(int) posJY);
                if(viveF(5))
                    arma2.dispararE((int)posX+135,(int) posY+685,(int) posJX,(int) posJY);
                if(viveF(6))
                    arma2.dispararE((int)posX+10,(int) posY+685,(int) posJX,(int) posJY);
                if(viveF(7))
                    arma2.dispararE((int)posX+105,(int) posY+645,(int) posJX,(int) posJY);
                if(viveF(8))
                    arma2.dispararE((int)posX+40,(int) posY+645,(int) posJX,(int) posJY);
                if(viveF(9))
                    arma2.dispararE((int)posX+140,(int) posY+625,(int) posJX,(int) posJY);
                if(viveF(10))
                    arma2.dispararE((int)posX+3,(int) posY+625,(int) posJX,(int) posJY);
                if(viveF(11))
                    arma2.dispararE((int)posX+130,(int) posY+500,(int) posJX,(int) posJY);
                if(viveF(12))
                    arma2.dispararE((int)posX+13,(int) posY+500,(int) posJX,(int) posJY);
                
                
              //DISPAROS TT
                if(viveTT(1))
                arma3.dispararE((int)posX+63,(int) posY+745,(int) posJX,(int) posJY);
                
                if(viveTT(2))
                arma3.dispararE((int)posX+63,(int) posY+344,(int) posJX,(int) posJY);
                
                if(viveTT(3))
                arma3.dispararE((int)posX+63,(int) posY+244,(int) posJX,(int) posJY);
                
                //DISPAROS CT
                if(viveCT(1))
                arma3.dispararE((int)posX+68,(int) posY+455,(int) posJX,(int) posJY);
                
                if(viveCT(2))
                arma3.dispararE((int)posX+68,(int) posY+700,(int) posJX,(int) posJY);
                
             
                
            }
        }
    }
    
    public void display(Graphics2D g,double posJX, double posJY,int alto, int bajo){	
        if(this.visible){
        	
        	g.drawImage(imagenes[1], (int)posX,(int)posY, imagenes[1].getWidth(null)*2, imagenes[1].getHeight(null)*2, null);  //IMAGEN DE BARCO
        	
            if(!(fueChocado)){
              movimiento(posJX,posJY);
                arma.display(g,alto,bajo);
                arma2.display(g,alto,bajo);
                arma3.display(g,alto,bajo);
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
            AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+130, (int) posY+610);
            rotacion.rotate(anguloEnRadianes, imagen.getWidth(null) / 2, imagen.getHeight(null) / 2);
            g.drawImage(imagen, rotacion, null);
            }else{
    			g.drawImage(imagenes[2], (int)posX+130,(int)posY+610, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);         
            }// 
             
            //CAÑON 2
            if(viveT(2)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+20, (int) posY+610);
            rotacion.rotate(anguloEnRadianes, imagen.getWidth(null) / 2, imagen.getHeight(null) / 2);
            g.drawImage(imagen, rotacion, null);
            }else{
    			g.drawImage(imagenes[2], (int)posX+20,(int)posY+610, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }//
            
          //CAÑON 3
            if(viveT(3)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+170, (int) posY+610);
            rotacion.rotate(anguloEnRadianes, imagen.getWidth(null) / 2, imagen.getHeight(null) / 2);
            g.drawImage(imagenes[5], rotacion, null);
            }else{
    			g.drawImage(imagenes[2], (int)posX+170,(int)posY+610, imagenes[2].getWidth(null)+40, imagenes[2].getHeight(null)+10, null);
            }//
            
            
           //anguloEnRadianes = Math.atan2(deltaY-370, deltaX); 
          //CAÑON 4
            if(viveT(4)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX-10, (int) posY+610);
            rotacion.rotate(anguloEnRadianes, imagen.getWidth(null) / 2, imagen.getHeight(null) / 2);
            g.drawImage(imagenes[5], rotacion, null);
            }else{
    			g.drawImage(imagenes[2], (int)posX-10,(int)posY+610, imagenes[2].getWidth(null)+20, imagenes[2].getHeight(null)+20, null);
            }//
            
          //CAÑON 5
            if(viveT(5)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX-10, (int) posY+585);
            rotacion.rotate(anguloEnRadianes, imagen.getWidth(null) / 2, imagen.getHeight(null) / 2);
            g.drawImage(imagenes[5], rotacion, null);
            }else{
    			g.drawImage(imagenes[2], (int)posX+-10,(int)posY+585, imagenes[2].getWidth(null)+20, imagenes[2].getHeight(null)+20, null);
            }
            
            //CAÑON 6
            if(viveT(6)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+165, (int) posY+585);
            rotacion.rotate(anguloEnRadianes, imagen.getWidth(null) / 2, imagen.getHeight(null) / 2);
            g.drawImage(imagenes[5], rotacion, null);
            }else{
    			g.drawImage(imagenes[2], (int)posX+165,(int)posY+585, imagenes[2].getWidth(null)+20, imagenes[2].getHeight(null)+20, null);
            }
            
            //CAÑON 7
            if(viveT(7)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX-10, (int) posY+555);
            rotacion.rotate(anguloEnRadianes, imagen.getWidth(null) / 2, imagen.getHeight(null) / 2);
            g.drawImage(imagenes[5], rotacion, null);
            }else{
    			g.drawImage(imagenes[2], (int)posX-10,(int)posY+555, imagenes[2].getWidth(null)+20, imagenes[2].getHeight(null)+20, null);
            }
            
            //CAÑON 8
            if(viveT(8)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+165, (int) posY+555);
            rotacion.rotate(anguloEnRadianes, imagen.getWidth(null) / 2, imagen.getHeight(null) / 2);
            g.drawImage(imagenes[5], rotacion, null);
            }else{
    			g.drawImage(imagenes[2], (int)posX+165,(int)posY+555, imagenes[2].getWidth(null)+20, imagenes[2].getHeight(null)+20, null);
            }
            
            //CAÑON 9
            if(viveT(9)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX-10, (int) posY+525);
            rotacion.rotate(anguloEnRadianes, imagen.getWidth(null) / 2, imagen.getHeight(null) / 2);
            g.drawImage(imagenes[5], rotacion, null);
            }else{
    			g.drawImage(imagenes[2], (int)posX-10,(int)posY+525, imagenes[2].getWidth(null)+20, imagenes[2].getHeight(null)+20, null);
            }
            
            //CAÑON 10
            if(viveT(10)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+165, (int) posY+525);
            rotacion.rotate(anguloEnRadianes, imagen.getWidth(null) / 2, imagen.getHeight(null) / 2);
            g.drawImage(imagenes[5], rotacion, null);
            }else{
    			g.drawImage(imagenes[2], (int)posX+165,(int)posY+525, imagenes[2].getWidth(null)+20, imagenes[2].getHeight(null)+20, null);
            }
            
            //CAÑON 11
            if(viveT(11)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+22, (int) posY+535);
            rotacion.rotate(anguloEnRadianes, imagen.getWidth(null) / 2, imagen.getHeight(null) / 2);
            g.drawImage(imagen, rotacion, null);
            }else{
    			g.drawImage(imagenes[2], (int)posX+22,(int)posY+535, imagenes[2].getWidth(null)+20, imagenes[2].getHeight(null)+20, null);
            }
            
            //CAÑON 12
            if(viveT(12)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+130, (int) posY+535);
            rotacion.rotate(anguloEnRadianes, imagen.getWidth(null) / 2, imagen.getHeight(null) / 2);
            g.drawImage(imagen, rotacion, null);
            }else{
    			g.drawImage(imagenes[2], (int)posX+130,(int)posY+535, imagenes[2].getWidth(null)+20, imagenes[2].getHeight(null)+20, null);
            }
            
            //CAÑON 13
            if(viveT(13)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+22, (int) posY+565);
            rotacion.rotate(anguloEnRadianes, imagen.getWidth(null) / 2, imagen.getHeight(null) / 2);
            g.drawImage(imagen, rotacion, null);
            }else{
    			g.drawImage(imagenes[2], (int)posX+22,(int)posY+565, imagenes[2].getWidth(null)+20, imagenes[2].getHeight(null)+20, null);
            }
            
            //CAÑON 14
            if(viveT(14)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+130, (int) posY+565);
            rotacion.rotate(anguloEnRadianes, imagen.getWidth(null) / 2, imagen.getHeight(null) / 2);
            g.drawImage(imagen, rotacion, null);
            }else{
    			g.drawImage(imagenes[2], (int)posX+130,(int)posY+565, imagenes[2].getWidth(null)+20, imagenes[2].getHeight(null)+20, null);
            }
            
            //////
            
            //FOGONEOS
            
            if(viveF(1)) {
            AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+12, (int) posY+905);
            rotacion.rotate(anguloEnRadianes, imagenes[3].getWidth(null) / 2, imagenes[3].getHeight(null) / 2);
            g.drawImage(imagenes[3], rotacion, null);
            }else{
    			g.drawImage(imagenes[2], (int)posX+12,(int)posY+905, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }
            
            //FOGONEO 2
            if(viveF(2)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+135, (int) posY+905);
            rotacion.rotate(anguloEnRadianes, imagenes[3].getWidth(null) / 2, imagenes[3].getHeight(null) / 2);
            g.drawImage(imagenes[3], rotacion, null);
            }else{
    			g.drawImage(imagenes[2], (int)posX+135,(int)posY+905, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }
            
            //FOGONEO 3
            if(viveF(3)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX, (int) posY+880);
            rotacion.rotate(anguloEnRadianes, imagenes[3].getWidth(null) / 2, imagenes[3].getHeight(null) / 2);
            g.drawImage(imagenes[3], rotacion, null);
            }else {
    			g.drawImage(imagenes[2], (int)posX,(int)posY+880, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }
            
          //FOGONEO 4
            if(viveF(4)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+145, (int) posY+880);
            rotacion.rotate(anguloEnRadianes, imagenes[3].getWidth(null) / 2, imagenes[3].getHeight(null) / 2);
            g.drawImage(imagenes[3], rotacion, null);
            }else {
    			g.drawImage(imagenes[2], (int)posX+145,(int)posY+880, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }
            
            //FOGONEO 5
            if(viveF(5)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+135, (int) posY+685);
            rotacion.rotate(anguloEnRadianes, imagenes[3].getWidth(null) / 2, imagenes[3].getHeight(null) / 2);
            g.drawImage(imagenes[3], rotacion, null);
            }else {
    			g.drawImage(imagenes[2], (int)posX+135,(int)posY+685, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }
            
            //FOGONEO 6
            if(viveF(6)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+10, (int) posY+685);
            rotacion.rotate(anguloEnRadianes, imagenes[3].getWidth(null) / 2, imagenes[3].getHeight(null) / 2);
            g.drawImage(imagenes[3], rotacion, null);
            }else {
    			g.drawImage(imagenes[2], (int)posX+10,(int)posY+685, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }
            
          //FOGONEO 7
            if(viveF(7)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+105, (int) posY+645);
            rotacion.rotate(anguloEnRadianes, imagenes[3].getWidth(null) / 2, imagenes[3].getHeight(null) / 2);
            g.drawImage(imagenes[3], rotacion, null);
            }else {
    			g.drawImage(imagenes[2], (int)posX+105,(int)posY+645, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }
            
            //FOGONEO 8
            if(viveF(8)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+40, (int) posY+645);
            rotacion.rotate(anguloEnRadianes, imagenes[3].getWidth(null) / 2, imagenes[3].getHeight(null) / 2);
            g.drawImage(imagenes[3], rotacion, null);
            }else {
    			g.drawImage(imagenes[2], (int)posX+40,(int)posY+645, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }
            
            //FOGONEO 9
            if(viveF(9)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+140, (int) posY+625);
            rotacion.rotate(anguloEnRadianes, imagenes[3].getWidth(null) / 2, imagenes[3].getHeight(null) / 2);
            g.drawImage(imagenes[3], rotacion, null);
            }else {
    			g.drawImage(imagenes[2], (int)posX+140,(int)posY+625, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }
            
            //FOGONEO 10
            if(viveF(10)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+3, (int) posY+625);
            rotacion.rotate(anguloEnRadianes, imagenes[3].getWidth(null) / 2, imagenes[3].getHeight(null) / 2);
            g.drawImage(imagenes[3], rotacion, null);
            }else {
    			g.drawImage(imagenes[2], (int)posX+3,(int)posY+625, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }
            
            //FOGONEO 11
            if(viveF(11)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+130, (int) posY+500);
            rotacion.rotate(anguloEnRadianes, imagenes[3].getWidth(null) / 2, imagenes[3].getHeight(null) / 2);
            g.drawImage(imagenes[3], rotacion, null);
            }else {
    			g.drawImage(imagenes[2], (int)posX+130,(int)posY+500, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }
            
            //FOGONEO 12
            if(viveF(12)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+13, (int) posY+500);
            rotacion.rotate(anguloEnRadianes, imagenes[3].getWidth(null) / 2, imagenes[3].getHeight(null) / 2);
            g.drawImage(imagenes[3], rotacion, null);
            }else {
    			g.drawImage(imagenes[2], (int)posX+13,(int)posY+500, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }
            
            //TORRETA TRIPLE 1
            
            if(viveTT(1)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+63, (int) posY+745);
            rotacion.rotate(anguloEnRadianes, imagenes[6].getWidth(null)/2, imagenes[6].getHeight(null)/2);
            g.drawImage(imagenes[6], rotacion, null);
            }else {
    			g.drawImage(imagenes[2], (int)posX+63,(int)posY+725, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }
            
          //TORRETA TRIPLE 2
            if(viveTT(2)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+63, (int) posY+344);
            rotacion.rotate(anguloEnRadianes, imagenes[6].getWidth(null)/2, imagenes[6].getHeight(null)/2);
            g.drawImage(imagenes[6], rotacion, null);
            }else {
    			g.drawImage(imagenes[2], (int)posX+63,(int)posY+344, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }
            
            //TORRETA TRIPLE 3
            if(viveTT(3)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+63, (int) posY+244);
            rotacion.rotate(anguloEnRadianes, imagenes[6].getWidth(null)/2, imagenes[6].getHeight(null)/2);
            g.drawImage(imagenes[6], rotacion, null);
            }else {
    			g.drawImage(imagenes[2], (int)posX+63,(int)posY+344, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }
            
            //CAÑONES TRIPLES 1
            if(viveCT(1)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+68, (int) posY+455);
            rotacion.rotate(anguloEnRadianes, imagenes[7].getWidth(null)/2, imagenes[7].getHeight(null)/2);
            g.drawImage(imagenes[7], rotacion, null);
            }else {
    			g.drawImage(imagenes[2], (int)posX+68,(int)posY+455, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }
            
            //CAÑONES TRIPLES 2
            if(viveCT(2)) {
            	AffineTransform rotacion = AffineTransform.getTranslateInstance((int) posX+68, (int) posY+700);
            rotacion.rotate(anguloEnRadianes, imagenes[7].getWidth(null)/2, imagenes[7].getHeight(null)/2);
            g.drawImage(imagenes[7], rotacion, null);
            }else {
    			g.drawImage(imagenes[2], (int)posX+68,(int)posY+700, imagenes[2].getWidth(null)+10, imagenes[2].getHeight(null)+10, null);
            }
            
  
            
            //SI EL BARCO ESTA DESTRUIDO DIBUJA EXPLOCIONES 
            if(contD > 21){
            	g.drawImage(imagenes[4], (int)posX+20,(int)posY+300, imagenes[4].getWidth(null)/3, imagenes[4].getHeight(null)/3, null);
            	g.drawImage(imagenes[4], (int)posX+40,(int)posY+460, imagenes[4].getWidth(null)/3, imagenes[4].getHeight(null)/3, null);
            	g.drawImage(imagenes[4], (int)posX+80,(int)posY+380, imagenes[4].getWidth(null)/3, imagenes[4].getHeight(null)/3, null);
            }
            	
            
            //
           
        }
    }

    public boolean isEnMovimiento() {
        return enMovimiento;
    }
    
    public boolean Destruido() {
    	if(contD > 22)
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
    		case 6:
    			if(vidaT6 > 0)
    				return true;
    			else return false;
    		case 7:
    			if(vidaT7 > 0)
    				return true;
    			else return false;
    		case 8:
    			if(vidaT8 > 0)
    				return true;
    			else return false;
    		case 9:
    			if(vidaT9 > 0)
    				return true;
    			else return false;
    		case 10:
    			if(vidaT10 > 0)
    				return true;
    			else return false;
    		case 11:
    			if(vidaT11 > 0)
    				return true;
    			else return false;
    		case 12:
    			if(vidaT12 > 0)
    				return true;
    			else return false;
    		case 13:
    			if(vidaT13 > 0)
    				return true;
    			else return false;
    		case 14:
    			if(vidaT14 > 0)
    				return true;
    			else return false;
    			
    		}
    		return false;
    	}
    	
    	public boolean viveTT(int t) {
    		switch(t) {
    		case 1:
    			if(vidaTT1 > 0)
    				return true;
    			else return false;
    		case 2:
    			if(vidaTT2 > 0)
    				return true;
    			else return false;
    		case 3:
    			if(vidaTT3 > 0)
    				return true;
    			else return false;
    		}
    		return false;
    	}
    	
    	public boolean viveCT(int t) {
    		switch(t) {
    		case 1:
    			if(vidaCT1 > 0)
    				return true;
    			else return false;
    		case 2:
    			if(vidaCT2 > 0)
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
    			
    		case 5:
    			if(vidaF5 > 0)
    				return true;
    			else return false;
    			
    		case 6:
    			if(vidaF6 > 0)
    				return true;
    			else return false;
    			
    		case 7:
    			if(vidaF7 > 0)
    				return true;
    			else return false;
    			
    		case 8:
    			if(vidaF8 > 0)
    				return true;
    			else return false;
    			
    		case 9:
    			if(vidaF9 > 0)
    				return true;
    			else return false;
    			
    		case 10:
    			if(vidaF10 > 0)
    				return true;
    			else return false;
    			
    		case 11:
    			if(vidaF11 > 0)
    				return true;
    			else return false;
    		case 12:
    			if(vidaF12 > 0)
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
    		case 6:
    			vidaT6--;
    			if(vidaT6 == 0)
    				contD++;
    			break;
    		case 7:
    			vidaT7--;
    			if(vidaT7 == 0)
    				contD++;
    			break;
    		case 8:
    			vidaT8--;
    			if(vidaT8 == 0)
    				contD++;
    			break;
    		case 9:
    			vidaT9--;
    			if(vidaT9 == 0)
    				contD++;
    			break;
    		case 10:
    			vidaT10--;
    			if(vidaT10 == 0)
    				contD++;
    			break;
    		case 11:
    			vidaT11--;
    			if(vidaT11 == 0)
    				contD++;
    			break;
    		case 12:
    			vidaT12--;
    			if(vidaT12 == 0)
    				contD++;
    			break;
    		case 13:
    			vidaT13--;
    			if(vidaT13 == 0)
    				contD++;
    			break;
    		case 14:
    			vidaT14--;
    			if(vidaT14 == 0)
    				contD++;
    			break;
    		}
    	}
    	
    	public void quitarVidaTT(int t) {
    		switch(t) {
    		case 1:
    			vidaTT1--;
    			if(vidaTT1 == 0)
    				contD++;
    			break;
    		case 2:
    			vidaTT2--;
    			if(vidaTT2 == 0)
    				contD++;
    			break;
    		case 3:
    			vidaTT3--;
    			if(vidaTT3 == 0)
    				contD++;
    			break;
    		}
    	}
    	
    	public void quitarVidaCT(int t) {
    		switch(t) {
    		case 1:
    			vidaCT1--;
    			if(vidaCT1 == 0)
    				contD++;
    			break;
    		case 2:
    			vidaCT2--;
    			if(vidaCT2 == 0)
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
    		case 5:
    			vidaF5--;
    			if(vidaF5 == 0)
    				contD++;
    			break;
    		case 6:
    			vidaF6--;
    			if(vidaF6 == 0)
    				contD++;
    			break;
    		case 7:
    			vidaF7--;
    			if(vidaF7 == 0)
    				contD++;
    			break;
    		case 8:
    			vidaF8--;
    			if(vidaF8 == 0)
    				contD++;
    			break;
    		case 9:
    			vidaF9--;
    			if(vidaF9 == 0)
    				contD++;
    			break;
    		case 10:
    			vidaF10--;
    			if(vidaF10 == 0)
    				contD++;
    			break;
    		case 11:
    			vidaF11--;
    			if(vidaF11 == 0)
    				contD++;
    			break;
    		}
    	}
    	
    	
    	
    	public Rectangle getBordesTT(int t) {
    		switch(t) {
    		case 1:
    			return new Rectangle((int)posX+63 , (int)posY+745, imagenes[6].getWidth(null), imagenes[6].getHeight(null));
    		case 2:
    			return new Rectangle((int)posX+63 , (int)posY+344, imagenes[6].getWidth(null), imagenes[6].getHeight(null));
    		case 3:
    			return new Rectangle((int)posX+63 , (int)posY+244, imagenes[6].getWidth(null), imagenes[6].getHeight(null));
    		}
    		return null;
    	}
    	
    	public Rectangle getBordesCT(int t) {
    		switch(t) {
    		case 1:
    			return new Rectangle((int)posX+68 , (int)posY+455, imagenes[7].getWidth(null), imagenes[7].getHeight(null));
    		case 2:
    			return new Rectangle((int)posX+68 , (int)posY+700, imagenes[7].getWidth(null), imagenes[7].getHeight(null));
    		}
    		return null;
    	}
    	
    	
    	
    	
    	public Rectangle getBordesT(int t) {
    		switch(t) {
    		case 1:
    			return new Rectangle((int)posX+130 , (int)posY+610, imagen.getWidth(null), imagen.getHeight(null));
    		case 2:
    			return new Rectangle((int)posX+20 , (int)posY+610, imagen.getWidth(null), imagen.getHeight(null));
    		case 3:
    			return new Rectangle((int)posX+170 , (int)posY+610, imagen.getWidth(null), imagen.getHeight(null));
    		case 4:
    			return new Rectangle((int)posX-10 , (int)posY+610, imagen.getWidth(null), imagen.getHeight(null));
    		case 5:
    			return new Rectangle((int)posX-10 , (int)posY+585, imagen.getWidth(null), imagen.getHeight(null));
    		case 6:
    			return	new Rectangle((int)posX+165, (int)posY+585, imagen.getWidth(null), imagen.getHeight(null)); 
    		case 7:
    			return	new Rectangle((int)posX-10, (int)posY+555, imagen.getWidth(null), imagen.getHeight(null)); 
    		case 8:
    			return	new Rectangle((int)posX+165, (int)posY+555, imagen.getWidth(null), imagen.getHeight(null)); 
    		case 9:
    			return	new Rectangle((int)posX-10, (int)posY+525, imagen.getWidth(null), imagen.getHeight(null)); 
    		case 10:
    			return	new Rectangle((int)posX+165, (int)posY+525, imagen.getWidth(null), imagen.getHeight(null)); 
    		case 11:
    			return	new Rectangle((int)posX+22, (int)posY+535, imagen.getWidth(null), imagen.getHeight(null)); 
    		case 12:
    			return	new Rectangle((int)posX+130, (int)posY+535, imagen.getWidth(null), imagen.getHeight(null)); 
    		case 13:
    			return	new Rectangle((int)posX+22, (int)posY+565, imagen.getWidth(null), imagen.getHeight(null)); 
    		case 14:
    			return	new Rectangle((int)posX+130, (int)posY+565, imagen.getWidth(null), imagen.getHeight(null)); 

			}
    		
    		return null; 	
    	}
    	
    	public Rectangle getBordesF(int t) {
    		switch(t) {
    		case 1:
    			return new Rectangle((int)posX+12 , (int)posY+905, imagen.getWidth(null), imagen.getHeight(null));
    		case 2:
    			return new Rectangle((int)posX+135, (int)posY+905, imagen.getWidth(null), imagen.getHeight(null));
    		case 3:
    			return new Rectangle((int)posX , (int)posY+880, imagen.getWidth(null), imagen.getHeight(null));
    		case 4:
    			return new Rectangle((int)posX+145 , (int)posY+880, imagen.getWidth(null), imagen.getHeight(null));	
    		case 5:
    			return new Rectangle((int)posX+135 , (int)posY+685, imagen.getWidth(null), imagen.getHeight(null));	
    		case 6:
    			return new Rectangle((int)posX+10 , (int)posY+685, imagen.getWidth(null), imagen.getHeight(null));	
    		case 7:
    			return new Rectangle((int)posX+105 , (int)posY+645, imagen.getWidth(null), imagen.getHeight(null));	
    		case 8:
    			return new Rectangle((int)posX+40 , (int)posY+645, imagen.getWidth(null), imagen.getHeight(null));	
    		case 9:
    			return new Rectangle((int)posX+140 , (int)posY+625, imagen.getWidth(null), imagen.getHeight(null));	
    		case 10:
    			return new Rectangle((int)posX+3 , (int)posY+625, imagen.getWidth(null), imagen.getHeight(null));	
    		case 11:
    			return new Rectangle((int)posX+130 , (int)posY+500, imagen.getWidth(null), imagen.getHeight(null));	
    		case 12:
    			return new Rectangle((int)posX+13 , (int)posY+500, imagen.getWidth(null), imagen.getHeight(null));	
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
