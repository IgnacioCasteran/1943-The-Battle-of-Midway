
package battelofmidway;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Arma extends ObjetoGrafico implements ObjetoMovible{
    private static final String MUNICION[] = {
    		"recursos\\Municion\\Estandar.png", 
    		"recursos\\Municion\\3tiros0.png" ,
    		"recursos\\Municion\\3tiros1.png" ,
    		"recursos\\Municion\\3tiros2.png",
    		"recursos\\Municion\\Escopeta0.png",
    		"recursos\\Municion\\Escopeta1.png",
    		"recursos\\Municion\\Escopeta00.png",
    		"recursos\\Municion\\Escopeta01.png",
    		"recursos\\Municion\\Misil.png",
    		"recursos\\Municion\\Yamato3.png",
    		"recursos\\Municion\\Yamato1.png",
    		"recursos\\Municion\\Yamato2.gif",};

    private int contadorMovimiento1 = 0;
    private int Municion = 0;  

    public final static int MUNICION_ESTANDAR = 0;
    public final static int MUNICION_TRI = 1;
    public final static int MUNICION_SHOTGUN = 2;
    public final static int MUNICION_AUTO = 3;
    public final static int MUNICION_SHOTGUN_MEJORADO = 4;
    public final static int MUNICION_SHELL = 5;
    public final static int MUNICION_ENEMIGO_SIMPLE = 6;
    public final static int MUNICION_ENEMIGO_TRI = 7;
    public final static int MUNICION_ENEMIGO_BOS = 8;
    public final static int MUNICION_ENEMIGO_FOGONEO = 9;
    public final static int MUNICION_ENEMIGO_TRI_BOS = 10;
    
    public final static int LIMITE_SHOTGUN = 12;

    

    private boolean pausa = false;
    private boolean chocoC = false;
    private boolean chocoD = false;
    private boolean chocoI = false;
    private boolean chocoEnem = false;
    
    private boolean chocoDD = false;
    private boolean chocoII = false;
    private int sprai = 0;
    private int dsprai = 0;
    private int cont= 0;
    
    
    private ArrayList<Posdisp> disparosE = new ArrayList<Posdisp>();
    private ArrayList<Posdisp> disparosEnemigos = new ArrayList<Posdisp>();
    private ArrayList<Posdisp> disparosEnemigosB = new ArrayList<Posdisp>();
    private ArrayList<Posdisp> disparosEnemigosF = new ArrayList<Posdisp>();

    
    //GUARDAR MUNICION DE ESCOPETA SIMPLE
    private ArrayList<Posdisp> disparos = new ArrayList<Posdisp>();
    private ArrayList<Posdisp> disparosD = new ArrayList<Posdisp>();
    private ArrayList<Posdisp> disparosI = new ArrayList<Posdisp>();
    private ArrayList<Posdisp> disparosDD = new ArrayList<Posdisp>();
    private ArrayList<Posdisp> disparosII = new ArrayList<Posdisp>();
  //GUARDAR MUNICION DE ESCOPETA SIMPLE
    
    //GUARDAR MUNICION DE TRI
    private ArrayList<Posdisp> disparosT = new ArrayList<Posdisp>();
    private ArrayList<Posdisp> disparosDT = new ArrayList<Posdisp>();
    private ArrayList<Posdisp> disparosIT = new ArrayList<Posdisp>();
  //GUARDAR MUNICION DE TRI
    
    //GUARDAR MUNICION DE ESCOPETA MEJORADA
    private ArrayList<Posdisp> disparosM = new ArrayList<Posdisp>();
    private ArrayList<Posdisp> disparosDM = new ArrayList<Posdisp>();
    private ArrayList<Posdisp> disparosIM = new ArrayList<Posdisp>();
    private ArrayList<Posdisp> disparosDDM = new ArrayList<Posdisp>();
    private ArrayList<Posdisp> disparosIIM = new ArrayList<Posdisp>();
    //GUARDAR MUNICION DE ESCOPETA MEJORADA
    
    
    public Arma(int m) {
        super(MUNICION, 12, 0);
        this.visible = false;
        this.Municion = m;
    }
    

    public void display(Graphics2D g2, int alto,int bajo) {
    		if(!pausa) {
	        		if(!disparosE.isEmpty() )
	        			Estandar(g2,alto,bajo);
	        		
	        		if(!disparosT.isEmpty() || !disparosDT.isEmpty() || !disparosIT.isEmpty()) {
	        			if(Municion == MUNICION_TRI)
	        				Tri(g2,alto,bajo);
	        			else if(Municion == MUNICION_ENEMIGO_TRI)
	        				TriEnemigo(g2,alto,bajo);
	        			else
	        				TriEnemigoB(g2,alto,bajo);
	        		}
	        		
	        		if(!disparos.isEmpty())
	        			ShotGun(g2,alto,bajo);
	        		
	        		if(!disparosM.isEmpty())
	        			ShotGunM(g2,alto,bajo);
	        		
	        		if(!disparosEnemigos.isEmpty())
	        			Enemigos(g2,alto,bajo);
	        		
	        		if(!disparosEnemigosB.isEmpty())
	        			EnemigosB(g2,alto,bajo);
	        		
	        		if(!disparosEnemigosF.isEmpty())
	        			EnemigosF(g2,alto,bajo);
    		}
    }
    
    public void Estandar(Graphics2D g2,int alto,int bajo) {
    	AffineTransform old = g2.getTransform();
        
        this.visible = false;
        dsprai=0;
        
        if(Municion == MUNICION_AUTO)
        	sprai=0;
        else if(Municion == MUNICION_SHELL) {
        	sprai=8;
        	dsprai=-7;
        }
        else
        	sprai=1;
        
        this.cambiarImagen(sprai);
        
        
        for(int i=0 ; i<disparosE.size() ; i++) {       	
                if(disparosE.get(i).getPosY() < alto || chocoC){
                    //contadorMovimiento1 = 0;
                    disparosE.remove(i);
                    
                    chocoC=false;
                } else {
                    g2.drawImage(this.imagen,(int) disparosE.get(i).getPosX()-10+dsprai,(int) disparosE.get(i).getPosY(), this.imagen.getWidth(null)*2, this.imagen.getHeight(null)*2,null);
                 
                    	disparosE.get(i).setPosY(disparosE.get(i).getPosY()-20);
                    

                }            
        }       
        g2.setTransform(old);
    }

    public void Tri(Graphics2D g2,int alto,int bajo) {
    	AffineTransform old = g2.getTransform();
        
        this.visible = false;
        this.cambiarImagen(1);
            
        for(int i=0 ; i<disparosT.size() ; i++) {   
                if(disparosT.get(i).getPosY() < alto || chocoC ){
                    contadorMovimiento1 = 0;
                    disparosT.remove(i);
                    
                   chocoC=false;
                } else {
                	//DISPARO CENTRAL         
	                	this.cambiarImagen(1);
	                    g2.drawImage(this.imagen,(int) disparosT.get(i).getPosX()-10,(int) disparosT.get(i).getPosY(), this.imagen.getWidth(null)*2, this.imagen.getHeight(null)*2,null);
	                    	disparosT.get(i).setPosY(disparosT.get(i).getPosY()-20);
 
                }    
        	}
        
        //DISPARO DERECHO
        for(int i=0 ; i<disparosDT.size() ; i++) {   
            if(disparosDT.get(i).getPosY() < alto || chocoD){
                disparosDT.remove(i);
                
                chocoD=false;
            } else {  
            	this.cambiarImagen(2);
                g2.drawImage(this.imagen,(int) disparosDT.get(i).getPosX(),(int) disparosDT.get(i).getPosY(), this.imagen.getWidth(null)*2, this.imagen.getHeight(null)*2,null);
             
            	disparosDT.get(i).setPosY(disparosDT.get(i).getPosY()-20);
            	disparosDT.get(i).setPosX(disparosDT.get(i).getPosX()-10);
            
           }
        }
        
        //DISPARO IZQUIEDO
        for(int i=0 ; i<disparosIT.size() ; i++) {   
            if(disparosIT.get(i).getPosY() < alto || chocoI){
                disparosIT.remove(i);
                
                chocoI=false;
            } else {  
            	this.cambiarImagen(3);
            	g2.drawImage(this.imagen,(int) disparosIT.get(i).getPosX(),(int) disparosIT.get(i).getPosY(), this.imagen.getWidth(null)*2, this.imagen.getHeight(null)*2,null);

             	disparosIT.get(i).setPosY(disparosIT.get(i).getPosY()-20);
              	disparosIT.get(i).setPosX(disparosIT.get(i).getPosX()+10);
            
           }
        }

            
        g2.setTransform(old);
    }
    
    public void TriEnemigo(Graphics2D g2,int alto,int bajo) {
    	AffineTransform old = g2.getTransform();
        
        this.visible = false;
        this.cambiarImagen(7);
            
        for(int i=0 ; i<disparosT.size() ; i++) {   
                if(disparosT.get(i).getPosY() < alto || chocoC ){
                    contadorMovimiento1 = 0;
                    disparosT.remove(i);
                    
                   chocoC=false;
                } else {
                	//DISPARO CENTRAL         
	                    g2.drawImage(this.imagen,(int) disparosT.get(i).getPosX()-10,(int) disparosT.get(i).getPosY(), this.imagen.getWidth(null)*2, this.imagen.getHeight(null)*2,null);
	                    	disparosT.get(i).setPosY(disparosT.get(i).getPosY()+5);
 
                }    
        	}
        
        //DISPARO DERECHO
        for(int i=0 ; i<disparosDT.size() ; i++) {   
            if(disparosDT.get(i).getPosY() < alto || chocoD){
                disparosDT.remove(i);
                
                chocoD=false;
            } else {  
                g2.drawImage(this.imagen,(int) disparosDT.get(i).getPosX(),(int) disparosDT.get(i).getPosY(), this.imagen.getWidth(null)*2, this.imagen.getHeight(null)*2,null);
             
            	disparosDT.get(i).setPosY(disparosDT.get(i).getPosY()+5);
            	disparosDT.get(i).setPosX(disparosDT.get(i).getPosX()-2);
            
           }
        }
        
        //DISPARO IZQUIERDO
        for(int i=0 ; i<disparosIT.size() ; i++) {   
            if(disparosIT.get(i).getPosY() < alto || chocoI){
                disparosIT.remove(i);
                
                chocoI=false;
            } else {  
            	g2.drawImage(this.imagen,(int) disparosIT.get(i).getPosX(),(int) disparosIT.get(i).getPosY(), this.imagen.getWidth(null)*2, this.imagen.getHeight(null)*2,null);

             	disparosIT.get(i).setPosY(disparosIT.get(i).getPosY()+5);
              	disparosIT.get(i).setPosX(disparosIT.get(i).getPosX()+2);
            
           }
        }

            
        g2.setTransform(old);
    }
    
    public void TriEnemigoB(Graphics2D g2,int alto,int bajo) {
    	AffineTransform old = g2.getTransform();
        
        this.visible = false;
        this.cambiarImagen(10);
            
        for(int i=0 ; i<disparosT.size() ; i++) {   
                if(disparosT.get(i).getPosY() < alto || chocoC ){
                    contadorMovimiento1 = 0;
                    disparosT.remove(i);
                    
                   chocoC=false;
                } else {
                	//DISPARO CENTRAL         
	                    g2.drawImage(this.imagen,(int) disparosT.get(i).getPosX()-10,(int) disparosT.get(i).getPosY(), this.imagen.getWidth(null)*2, this.imagen.getHeight(null)*2,null);
	                    	disparosT.get(i).setPosY(disparosT.get(i).getPosY()+5);
 
                }    
        	}
        
        //DISPARO DERECHO
        for(int i=0 ; i<disparosDT.size() ; i++) {   
            if(disparosDT.get(i).getPosY() < alto || chocoD){
                disparosDT.remove(i);
                
                chocoD=false;
            } else {  
                g2.drawImage(this.imagen,(int) disparosDT.get(i).getPosX(),(int) disparosDT.get(i).getPosY(), this.imagen.getWidth(null)*2, this.imagen.getHeight(null)*2,null);
             
            	disparosDT.get(i).setPosY(disparosDT.get(i).getPosY()+5);
            	disparosDT.get(i).setPosX(disparosDT.get(i).getPosX()-2);
            
           }
        }
        
        //DISPARO IZQUIERDO
        for(int i=0 ; i<disparosIT.size() ; i++) {   
            if(disparosIT.get(i).getPosY() < alto || chocoI){
                disparosIT.remove(i);
                
                chocoI=false;
            } else {  
            	g2.drawImage(this.imagen,(int) disparosIT.get(i).getPosX(),(int) disparosIT.get(i).getPosY(), this.imagen.getWidth(null)*2, this.imagen.getHeight(null)*2,null);

             	disparosIT.get(i).setPosY(disparosIT.get(i).getPosY()+5);
              	disparosIT.get(i).setPosX(disparosIT.get(i).getPosX()+2);
            
           }
        }

            
        g2.setTransform(old);
    }

    public void ShotGun(Graphics2D g2,int alto,int bajo) {
    	AffineTransform old = g2.getTransform();
        
        this.visible = false;
        //this.cambiarImagen();
        
      
            
        for(int i=0 ; i<disparos.size() ; i++) {   
                if( disparos.get(i).getCont() > LIMITE_SHOTGUN || chocoC || disparos.get(i).getPosY() < alto ){
                    disparos.remove(i);
                    
                   chocoC=false;
                 
                } else {
                	//DISPARO CENTRAL         
	                	this.cambiarImagen(6);
	                	if(disparos.get(i).getCont() < LIMITE_SHOTGUN) {
	                		g2.drawImage(this.imagen,(int) disparos.get(i).getPosX()-10,(int) disparos.get(i).getPosY(), this.imagen.getWidth(null)*2, this.imagen.getHeight(null)*2,null);
	                		disparos.get(i).setPosY(disparos.get(i).getPosY()-25);
	                		disparos.get(i).sumarCont();
	                	}                                                   
                }    
        	}
        
        //DISPARO DERECHO
        for(int i=0 ; i<disparosD.size() ; i++) {   
            if(disparosD.get(i).getCont() > LIMITE_SHOTGUN || disparosD.get(i).getPosY() < alto || chocoD){
                disparosD.remove(i);
                
                chocoD=false;
            } else {  
            	this.cambiarImagen(7);
            	if(disparosD.get(i).getCont() < LIMITE_SHOTGUN) {
                g2.drawImage(this.imagen,(int) disparosD.get(i).getPosX(),(int) disparosD.get(i).getPosY(), this.imagen.getWidth(null)*2, this.imagen.getHeight(null)*2,null);

            	disparosD.get(i).setPosY(disparosD.get(i).getPosY()-20);
            	disparosD.get(i).setPosX(disparosD.get(i).getPosX()-5);
            	disparosD.get(i).sumarCont();
            	}
           }
        }
        
        //DISPARO IZQUIEDO
        for(int i=0 ; i<disparosI.size() ; i++) {   
            if(disparosI.get(i).getCont() > LIMITE_SHOTGUN || disparosI.get(i).getPosY() < alto || chocoI){

                disparosI.remove(i);
                
                chocoI=false;
            } else {  
            	//this.cambiarImagen(7);
            	if(disparosI.get(i).getCont() < LIMITE_SHOTGUN) {
            	g2.drawImage(this.imagen,(int) disparosI.get(i).getPosX(),(int) disparosI.get(i).getPosY(), this.imagen.getWidth(null)*2, this.imagen.getHeight(null)*2,null);
             	disparosI.get(i).setPosY(disparosI.get(i).getPosY()-20);
              	disparosI.get(i).setPosX(disparosI.get(i).getPosX()+5);
              	disparosI.get(i).sumarCont();
            	}
           }
        }
        
        //DISPARO DERECHO2
        for(int i=0 ; i<disparosDD.size() ; i++) {   
            if(disparosDD.get(i).getCont() > LIMITE_SHOTGUN || disparosDD.get(i).getPosY() < alto || chocoDD){
                disparosDD.remove(i);
                
                chocoDD=false;
            } else {  
            	if(disparosDD.get(i).getCont() < LIMITE_SHOTGUN) {
                g2.drawImage(this.imagen,(int) disparosDD.get(i).getPosX(),(int) disparosDD.get(i).getPosY(), this.imagen.getWidth(null)*2, this.imagen.getHeight(null)*2,null);

            	disparosDD.get(i).setPosY(disparosDD.get(i).getPosY()-15); //VELOCIDAD
            	disparosDD.get(i).setPosX(disparosDD.get(i).getPosX()-15);
            	disparosDD.get(i).sumarCont();
            	}
            
           }
        }
        
        //DISPARO IZQUIERDO2
        for(int i=0 ; i<disparosII.size() ; i++) {   
            if(disparosII.get(i).getCont() > LIMITE_SHOTGUN || disparosII.get(i).getPosY() < alto || chocoII){
                contadorMovimiento1 = 0;
                disparosII.remove(i);
                
                chocoII=false;
            } else {  
            	if(disparosII.get(i).getCont() < LIMITE_SHOTGUN) {
            	g2.drawImage(this.imagen,(int) disparosII.get(i).getPosX(),(int) disparosII.get(i).getPosY(), this.imagen.getWidth(null)*2, this.imagen.getHeight(null)*2,null);
             	disparosII.get(i).setPosY(disparosII.get(i).getPosY()-15);
              	disparosII.get(i).setPosX(disparosII.get(i).getPosX()+15);
              	disparosII.get(i).sumarCont();
            	}
            
           }
        }
        
       
       // contadorMovimiento1+=1;
        g2.setTransform(old);
        
    }
    
    public void ShotGunM(Graphics2D g2,int alto,int bajo) {
    	AffineTransform old = g2.getTransform();
        
        this.visible = false;
        //this.cambiarImagen();
        
      
            
        for(int i=0 ; i<disparosM.size() ; i++) {   
                if( chocoC || disparosM.get(i).getPosY() < alto ){
                    disparosM.remove(i);
                    
                   chocoC=false;
                 
                } else {
                	//DISPARO CENTRAL         
	                	this.cambiarImagen(4);
	                		g2.drawImage(this.imagen,(int) disparosM.get(i).getPosX()-10,(int) disparosM.get(i).getPosY(), this.imagen.getWidth(null)*2, this.imagen.getHeight(null)*2,null);
	                		disparosM.get(i).setPosY(disparosM.get(i).getPosY()-25);    	                                                 
                }    
        	}
        
        //DISPARO DERECHO
        for(int i=0 ; i<disparosDM.size() ; i++) {   
            if(disparosDM.get(i).getPosY() < alto || chocoD){
                disparosDM.remove(i);
                
                chocoD=false;
            } else {  
            	this.cambiarImagen(5);
 
                g2.drawImage(this.imagen,(int) disparosDM.get(i).getPosX(),(int) disparosDM.get(i).getPosY(), this.imagen.getWidth(null)*2, this.imagen.getHeight(null)*2,null);

            	disparosDM.get(i).setPosY(disparosDM.get(i).getPosY()-20);
            	disparosDM.get(i).setPosX(disparosDM.get(i).getPosX()-5);	
           }
        }
        
        //DISPARO IZQUIEDO
        for(int i=0 ; i<disparosIM.size() ; i++) {   
            if(disparosIM.get(i).getPosY() < alto || chocoI){

                disparosIM.remove(i);
                
                chocoI=false;
            } else {  
            	g2.drawImage(this.imagen,(int) disparosIM.get(i).getPosX(),(int) disparosIM.get(i).getPosY(), this.imagen.getWidth(null)*2, this.imagen.getHeight(null)*2,null);
             	disparosIM.get(i).setPosY(disparosIM.get(i).getPosY()-20);
              	disparosIM.get(i).setPosX(disparosIM.get(i).getPosX()+5);

           }
        }
        
        //DISPARO DERECHO2
        for(int i=0 ; i<disparosDDM.size() ; i++) {   
            if(disparosDDM.get(i).getPosY() < alto || chocoDD){
                disparosDDM.remove(i);
                
                chocoDD=false;
            } else {  
            	
                g2.drawImage(this.imagen,(int) disparosDDM.get(i).getPosX(),(int) disparosDDM.get(i).getPosY(), this.imagen.getWidth(null)*2, this.imagen.getHeight(null)*2,null);

            	disparosDDM.get(i).setPosY(disparosDDM.get(i).getPosY()-15); //VELOCIDAD
            	disparosDDM.get(i).setPosX(disparosDDM.get(i).getPosX()-15);
           }
        }
        
        //DISPARO IZQUIERDO2
        for(int i=0 ; i<disparosIIM.size() ; i++) {   
            if(disparosIIM.get(i).getPosY() < alto || chocoII){
                disparosIIM.remove(i);
                
                chocoII=false;
            } else {  
            	g2.drawImage(this.imagen,(int) disparosIIM.get(i).getPosX(),(int) disparosIIM.get(i).getPosY(), this.imagen.getWidth(null)*2, this.imagen.getHeight(null)*2,null);
             	disparosIIM.get(i).setPosY(disparosIIM.get(i).getPosY()-15);
              	disparosIIM.get(i).setPosX(disparosIIM.get(i).getPosX()+15);    	
            
           }
        }
        
       
       // contadorMovimiento1+=1;
        g2.setTransform(old);
        
    }
    
    
    public void Enemigos(Graphics2D g2,int alto,int bajo) {
    	AffineTransform old = g2.getTransform();
        
        this.visible = false;
        
        this.cambiarImagen(7);
        
        
        for(int i=0 ; i<disparosEnemigos.size() ; i++) {       	
                if(disparosEnemigos.get(i).getPosY() > bajo || chocoEnem || disparosEnemigos.get(i).getPosY() < alto){
                    //contadorMovimiento1 = 0;
                    disparosEnemigos.remove(i);
                    //
                    chocoEnem=false;
                } else {
                    g2.drawImage(this.imagen,(int) disparosEnemigos.get(i).getPosX()-10,(int) disparosEnemigos.get(i).getPosY(), this.imagen.getWidth(null)*2, this.imagen.getHeight(null)*2,null);
                 
                   
                    
                    disparosEnemigos.get(i).setPosX((int) (disparosEnemigos.get(i).getPosX()+disparosEnemigos.get(i).getPasoX()));
                    disparosEnemigos.get(i).setPosY((int) (disparosEnemigos.get(i).getPosY()+disparosEnemigos.get(i).getPasoY()));
                    
                }            
        }       
        g2.setTransform(old);
    }
    
    public void EnemigosB(Graphics2D g2,int alto,int bajo) {
    	AffineTransform old = g2.getTransform();
        
        this.visible = false;
        
        this.cambiarImagen(9);
        
        
        for(int i=0 ; i<disparosEnemigosB.size() ; i++) {   		
                if(disparosEnemigosB.get(i).getPosY() > bajo || chocoEnem || disparosEnemigosB.get(i).getPosY() < alto){
                    //contadorMovimiento1 = 0;
                    disparosEnemigosB.remove(i);
                    //
                    chocoEnem=false;
                } else {
                    g2.drawImage(this.imagen,(int) disparosEnemigosB.get(i).getPosX()-10,(int) disparosEnemigosB.get(i).getPosY(), this.imagen.getWidth(null)*2, this.imagen.getHeight(null)*2,null);
                  
                    disparosEnemigosB.get(i).setPosX((int) (disparosEnemigosB.get(i).getPosX()+disparosEnemigosB.get(i).getPasoX()));
                    disparosEnemigosB.get(i).setPosY((int) (disparosEnemigosB.get(i).getPosY()+disparosEnemigosB.get(i).getPasoY()));
                        
                }            
        }       
        g2.setTransform(old);
    }
    
    public void EnemigosF(Graphics2D g2,int alto,int bajo) {
    	AffineTransform old = g2.getTransform();
        
        this.visible = false;

        this.cambiarImagen(10);
        
        
        for(int i=0 ; i<disparosEnemigosF.size() ; i++) {   		
                if(disparosEnemigosF.get(i).getPosY() > bajo || chocoEnem || disparosEnemigosF.get(i).getPosY() < alto  || disparosEnemigosF.get(i).getCont() > 50){
                    //contadorMovimiento1 = 0;
                    disparosEnemigosF.remove(i);
                    
                    chocoEnem=false;
                } else {
                	
                	if( disparosEnemigosF.get(i).getCont() < 30) {
                    g2.drawImage(this.imagen,(int) disparosEnemigosF.get(i).getPosX()-10,(int) disparosEnemigosF.get(i).getPosY(), this.imagen.getWidth(null)*2, this.imagen.getHeight(null)*2,null);
                    disparosEnemigosF.get(i).setPosX((int) (disparosEnemigosF.get(i).getPosX()+disparosEnemigosF.get(i).getPasoX()));
                    disparosEnemigosF.get(i).setPosY((int) (disparosEnemigosF.get(i).getPosY()+disparosEnemigosF.get(i).getPasoY()));
                    }else{
                    	this.cambiarImagen(11);
                    	g2.drawImage(this.imagen,(int) disparosEnemigosF.get(i).getPosX()-10,(int) disparosEnemigosF.get(i).getPosY(), this.imagen.getWidth(null)*2, this.imagen.getHeight(null)*2,null);
                    	disparosEnemigosF.get(i).setPosX((int) (disparosEnemigosF.get(i).getPosX()-disparosEnemigosF.get(i).getPasoX()));
                        disparosEnemigosF.get(i).setPosY((int) (disparosEnemigosF.get(i).getPosY()-disparosEnemigosF.get(i).getPasoY()));
                    }
                	disparosEnemigosF.get(i).sumarCont();
                }            
        }       
        g2.setTransform(old);
    }
    
    //PARA BORRAR LAS MUNICIONES QUE IMPACTAN
    public void chocoC() {
    	chocoC = true;
    }
    
    public void chocoEnem() {
    	chocoEnem = true;
    }
    
    public void chocoD() {
    	chocoD = true;
    }
    
    public void chocoI() {
    	chocoI = true;
    }
    
    public void chocoDD() {
    	chocoDD = true;
    }
    
    public void chocoII() {
    	chocoII = true;
    }

    //PARA BORRAR LAS MUNICIONES QUE IMPACTAN
    
    public void dispararE(int posXDisparo, int posYDisparo, int dirX, int dirY) {
    	switch(Municion) {
    		case MUNICION_ENEMIGO_SIMPLE:
    			Posdisp dispE = new Posdisp(posXDisparo,posYDisparo,dirX,dirY, 5);
    	    	disparosEnemigos.add(dispE);
    	    	break;
    		case MUNICION_ENEMIGO_BOS:
    			dispE = new Posdisp(posXDisparo,posYDisparo,dirX,dirY,5);
    	    	disparosEnemigosB.add(dispE);
    	    	break;
    		case MUNICION_ENEMIGO_FOGONEO:
    			dispE = new Posdisp(posXDisparo,posYDisparo,dirX-150,dirY-80, 5);
    	    	disparosEnemigosF.add(dispE);
    	    	
    	    	dispE = new Posdisp(posXDisparo,posYDisparo,dirX,dirY,5);
    	    	disparosEnemigosF.add(dispE);
    	    	
    	    	dispE = new Posdisp(posXDisparo,posYDisparo,dirX+150,dirY-80,5);
    	    	disparosEnemigosF.add(dispE);
    	    	break;
    		case MUNICION_ENEMIGO_TRI:
    		case MUNICION_ENEMIGO_TRI_BOS:
    			Posdisp disp  = new Posdisp(posXDisparo,posYDisparo);
	    		disparosT.add(disp);
	    		
	    		Posdisp dispD  = new Posdisp(posXDisparo-30,posYDisparo);
	    		disparosDT.add(dispD);
	    		
	    		Posdisp dispI  = new Posdisp(posXDisparo+10,posYDisparo);
	    		disparosIT.add(dispI);
    			break;	
    	}

    }
    
    public void disparar(int posXDisparo, int posYDisparo) { 
    	switch(this.Municion) {
    		case(MUNICION_ESTANDAR):
	    		
		    		Posdisp disp  = new Posdisp(posXDisparo,posYDisparo);
		    		disparosE.add(disp);
	    		
    		break;
    		
    		case(MUNICION_SHELL):
	    		
    			if(disparosE.size() < 6) {
	    		disp  = new Posdisp(posXDisparo,posYDisparo);
	    		disparosE.add(disp);
	    		disp  = new Posdisp(posXDisparo,posYDisparo+3);
	    		disparosE.add(disp);
    			}
	    		break;
		
    		case(MUNICION_TRI):

	    			disp  = new Posdisp(posXDisparo,posYDisparo);
		    		disparosT.add(disp);
		    		
		    		Posdisp dispD  = new Posdisp(posXDisparo-30,posYDisparo);
		    		disparosDT.add(dispD);
		    		
		    		Posdisp dispI  = new Posdisp(posXDisparo+10,posYDisparo);
		    		disparosIT.add(dispI);
		            

    		break;
    		case(MUNICION_SHOTGUN):
    				disp  = new Posdisp(posXDisparo,posYDisparo);
		    		disparos.add(disp);
		    		
		    		dispD= new Posdisp(posXDisparo-30,posYDisparo);
		    		disparosD.add(dispD);
		    		
		    		dispI  = new Posdisp(posXDisparo+10,posYDisparo);
		    		disparosI.add(dispI);
		    		
		    		Posdisp dispDD  = new Posdisp(posXDisparo-30,posYDisparo);
		    		disparosDD.add(dispDD);
		    		
		    		Posdisp dispII  = new Posdisp(posXDisparo+10,posYDisparo);
		    		disparosII.add(dispII);
    	
    		break;
    		case(MUNICION_AUTO):

		    		disp  = new Posdisp(posXDisparo,posYDisparo);
		    		disparosE.add(disp);
		    		disp  = new Posdisp(posXDisparo,posYDisparo-80);
		    		disparosE.add(disp);
	    		
    		break;
    		case(MUNICION_SHOTGUN_MEJORADO):
    			disp  = new Posdisp(posXDisparo,posYDisparo);
    		disparosM.add(disp);
    		
    		dispD= new Posdisp(posXDisparo-30,posYDisparo);
    		disparosDM.add(dispD);
    		
    		dispI  = new Posdisp(posXDisparo+10,posYDisparo);
    		disparosIM.add(dispI);
    		
    		dispDD  = new Posdisp(posXDisparo-30,posYDisparo);
    		disparosDDM.add(dispDD);
    		
    		dispII  = new Posdisp(posXDisparo+10,posYDisparo);
    		disparosIIM.add(dispII);
    		
    		break;
    	}
    }
    
    //PARA COLISIONES DE CADA MUNICION DISPARADA
    
    public ArrayList<Rectangle> getMunicionT(){
    	ArrayList<Rectangle> rMunicion = new ArrayList<Rectangle>();
    	for(int i=0 ; i<disparosT.size() ; i++) {
    		rMunicion.add(new Rectangle(disparosT.get(i).getPosX(), disparosT.get(i).getPosY(), imagen.getWidth(null), imagen.getHeight(null)));
    	}

        return rMunicion;
    }
    
    public ArrayList<Rectangle> getMunicion(){
    	ArrayList<Rectangle> rMunicion = new ArrayList<Rectangle>();
    	for(int i=0 ; i<disparos.size() ; i++) {
    		rMunicion.add(new Rectangle(disparos.get(i).getPosX(), disparos.get(i).getPosY(), imagen.getWidth(null), imagen.getHeight(null)));
    	}

        return rMunicion;
    }
    
    public ArrayList<Rectangle> getMunicionM(){
    	ArrayList<Rectangle> rMunicion = new ArrayList<Rectangle>();
    	for(int i=0 ; i<disparosM.size() ; i++) {
    		rMunicion.add(new Rectangle(disparosM.get(i).getPosX(), disparosM.get(i).getPosY(), imagen.getWidth(null), imagen.getHeight(null)));
    	}

        return rMunicion;
    }
    
    public ArrayList<Rectangle> getMunicionE(){
    	ArrayList<Rectangle> rMunicion = new ArrayList<Rectangle>();
    	for(int i=0 ; i<disparosE.size() ; i++) {
    		rMunicion.add(new Rectangle(disparosE.get(i).getPosX(), disparosE.get(i).getPosY(), imagen.getWidth(null), imagen.getHeight(null)));
    	}

        return rMunicion;
    }
    
    public ArrayList<Rectangle> getMunicionEnemigo(){
    	ArrayList<Rectangle> rMunicion = new ArrayList<Rectangle>();
    	for(int i=0 ; i<disparosEnemigos.size() ; i++) {
    		rMunicion.add(new Rectangle(disparosEnemigos.get(i).getPosX(), disparosEnemigos.get(i).getPosY(), imagen.getWidth(null), imagen.getHeight(null)));
    	}
    	
    	for(int i=0 ; i<disparosEnemigosB.size() ; i++) {
    		rMunicion.add(new Rectangle(disparosEnemigosB.get(i).getPosX(), disparosEnemigosB.get(i).getPosY(), imagen.getWidth(null), imagen.getHeight(null)));
    	}
    	
    	for(int i=0 ; i<disparosEnemigosF.size() ; i++) {
    		rMunicion.add(new Rectangle(disparosEnemigosF.get(i).getPosX(), disparosEnemigosF.get(i).getPosY(), imagen.getWidth(null), imagen.getHeight(null)));
    	}


        return rMunicion;
    }
    
    public ArrayList<Rectangle> getMunicionD(){
    	ArrayList<Rectangle> rMunicion = new ArrayList<Rectangle>();
    	for(int i=0 ; i<disparosD.size() ; i++) {
    		rMunicion.add(new Rectangle(disparosD.get(i).getPosX(), disparosD.get(i).getPosY(), imagen.getWidth(null), imagen.getHeight(null)));
    	}

        return rMunicion;
    }
    
    public ArrayList<Rectangle> getMunicionDM(){
    	ArrayList<Rectangle> rMunicion = new ArrayList<Rectangle>();
    	for(int i=0 ; i<disparosDM.size() ; i++) {
    		rMunicion.add(new Rectangle(disparosDM.get(i).getPosX(), disparosDM.get(i).getPosY(), imagen.getWidth(null), imagen.getHeight(null)));
    	}

        return rMunicion;
    }
    
    public ArrayList<Rectangle> getMunicionDT(){
    	ArrayList<Rectangle> rMunicion = new ArrayList<Rectangle>();
    	for(int i=0 ; i<disparosDT.size() ; i++) {
    		rMunicion.add(new Rectangle(disparosDT.get(i).getPosX(), disparosDT.get(i).getPosY(), imagen.getWidth(null), imagen.getHeight(null)));
    	}

        return rMunicion;
    }
    
    public ArrayList<Rectangle> getMunicionDD(){
    	ArrayList<Rectangle> rMunicion = new ArrayList<Rectangle>();
    	for(int i=0 ; i<disparosDD.size() ; i++) {
    		rMunicion.add(new Rectangle(disparosDD.get(i).getPosX(), disparosDD.get(i).getPosY(), imagen.getWidth(null), imagen.getHeight(null)));
    		//rMunicion.add(new Rectangle(disparos.get(i).getPer1X(), disparos.get(i).getPer1Y(), imagen.getWidth(null), imagen.getHeight(null)));
    		//rMunicion.add(new Rectangle(disparos.get(i).getPer2X(), disparos.get(i).getPer2Y(), imagen.getWidth(null), imagen.getHeight(null)));
    	}

        return rMunicion;
    }
    
    public ArrayList<Rectangle> getMunicionDDM(){
    	ArrayList<Rectangle> rMunicion = new ArrayList<Rectangle>();
    	for(int i=0 ; i<disparosDDM.size() ; i++) {
    		rMunicion.add(new Rectangle(disparosDDM.get(i).getPosX(), disparosDDM.get(i).getPosY(), imagen.getWidth(null), imagen.getHeight(null)));
    	}

        return rMunicion;
    }
    
    public ArrayList<Rectangle> getMunicionI(){
    	ArrayList<Rectangle> rMunicion = new ArrayList<Rectangle>();
    	for(int i=0 ; i<disparosI.size() ; i++) {
    		rMunicion.add(new Rectangle(disparosI.get(i).getPosX(), disparosI.get(i).getPosY(), imagen.getWidth(null), imagen.getHeight(null)));
    	}

        return rMunicion;
    }
    
    public ArrayList<Rectangle> getMunicionIM(){
    	ArrayList<Rectangle> rMunicion = new ArrayList<Rectangle>();
    	for(int i=0 ; i<disparosIM.size() ; i++) {
    		rMunicion.add(new Rectangle(disparosIM.get(i).getPosX(), disparosIM.get(i).getPosY(), imagen.getWidth(null), imagen.getHeight(null)));
    	}

        return rMunicion;
    }
    
    public ArrayList<Rectangle> getMunicionIT(){
    	ArrayList<Rectangle> rMunicion = new ArrayList<Rectangle>();
    	for(int i=0 ; i<disparosIT.size() ; i++) {
    		rMunicion.add(new Rectangle(disparosIT.get(i).getPosX(), disparosIT.get(i).getPosY(), imagen.getWidth(null), imagen.getHeight(null)));
    	}

        return rMunicion;
    }
       
    public ArrayList<Rectangle> getMunicionII(){
    	ArrayList<Rectangle> rMunicion = new ArrayList<Rectangle>();
    	for(int i=0 ; i<disparosII.size() ; i++) {
    		rMunicion.add(new Rectangle(disparosII.get(i).getPosX(), disparosII.get(i).getPosY(), imagen.getWidth(null), imagen.getHeight(null)));
    	}

        return rMunicion;
    }
    
    public ArrayList<Rectangle> getMunicionIIM(){
    	ArrayList<Rectangle> rMunicion = new ArrayList<Rectangle>();
    	for(int i=0 ; i<disparosIIM.size() ; i++) {
    		rMunicion.add(new Rectangle(disparosIIM.get(i).getPosX(), disparosIIM.get(i).getPosY(), imagen.getWidth(null), imagen.getHeight(null)));
    	}

        return rMunicion;
    }
    
  //PARA COLISIONES DE CADA MUNICION DISPARADA
    
    public int getTMunicion() {
    	return Municion;
    }
    
    public void  setMunicion(int m) {
    	this.Municion = m;
    }
    public void setPausa(boolean b){
        pausa = b;
    }

    public String getNomMunicion() {
    	String nombre = null;
    	switch(Municion) {
    		case(MUNICION_SHOTGUN):
    			nombre = "SHOTGUN"; 
    		break;
    		case(MUNICION_SHOTGUN_MEJORADO):
    			nombre = "SHOTGUN"; 
    		break;
    		case(MUNICION_TRI):
    			nombre = "THREE-WAY"; 
    		break;
    		case(MUNICION_AUTO):
    			nombre = "AUTO"; 
    		break;
    		case(MUNICION_SHELL):
    			nombre = "SHELL"; 
    		break;
    	}
		return nombre;
    	
    	
    }


	@Override
	public void update(double delta) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void movimiento() {
		// TODO Auto-generated method stub
		
	}
}