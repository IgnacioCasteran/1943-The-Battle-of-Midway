package battelofmidway;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class Heroe extends Personaje{
    private boolean enPiso = false;
    
    public static final double DEFAULT_VELOCITY = 4;

    public static final int DIRECCION_DERECHA = 0;
    public static final int DIRECCION_IZQUIERDA = 1;

    public final static int ESTADO_QUIETO = -1;
    public final static int ESTADO_MOVIENDO = 0;
    public final static int ESTADO_ATACANDO = 2;
    public final static int ESTADO_MURIENDO = 5;
    public final static int ESTADO_MUERTO = 6;

    
    private int direccionActual = DIRECCION_DERECHA;
    private int estadoActual;
    private int estadoAnterior;



    
    private int energia;

    private Arma arma = null;
    private AtaqueEspecial ataque = null;
    
    private boolean rafaga=false;
    private boolean powerup=false;
    private int vidaR=15; //vida de refuerzos
    
    private boolean invulnerable = false;

    private static String IMAGENES[] = {"recursos\\Heroe\\Avion.png", 
                                        "recursos\\Heroe\\AvionIzq1.png", 
                                        "recursos\\Heroe\\AvionDer1.png",
                                        "recursos\\Heroe\\Refuerzo.png",
                                        "recursos\\Heroe\\RefuerzoIzq.png", 
                                        "recursos\\Heroe\\RefuerzoDer.png",
                                        "recursos\\Heroe\\Muerte.GIF"}; 
    
    
    
    
    private Escenario esc;
    private BattleOfMidway ai;
    
    private boolean Refuerzos = false;

    public Heroe(Escenario esc, int energia){
        super(IMAGENES, 7, 0);
        this.esc = esc;
        this.estadoActual = ESTADO_QUIETO;
        this.energia = energia;
        this.visible = true;
     
    }


    private boolean movimientoBloqueado(){
        return (
        		(this.estadoActual == ESTADO_MURIENDO) ||  (this.estadoActual == ESTADO_MUERTO) 
        		);
    }
    
    public void quieto() {
        if(!(movimientoBloqueado())){
            if(this.estadoActual != ESTADO_ATACANDO){
                this.estadoActual = ESTADO_QUIETO;
            }
        }        
    }
    
    public void left() {
        if(!(movimientoBloqueado())){
            //this.velocityX = (hada == null) ? -DEFAULT_VELOCITY: -DEFAULT_VELOCITY-4; //-4.0;
            this.posX = this.posX-5;
            if((this.estadoActual != ESTADO_MOVIENDO) || (this.direccionActual != DIRECCION_IZQUIERDA)){
                cambiarImagen(2);
            }
            this.direccionActual = DIRECCION_IZQUIERDA;
            this.estadoActual = ESTADO_MOVIENDO;

        }
    }
     
    
    public void right(){
        if(!(movimientoBloqueado())){
            //this.velocityX = (hada == null) ? DEFAULT_VELOCITY: DEFAULT_VELOCITY+4;
        	this.posX = this.posX+5;
            if((this.estadoActual != ESTADO_MOVIENDO) || (this.direccionActual != DIRECCION_DERECHA)){
                cambiarImagen(1);
            }
            this.direccionActual = DIRECCION_DERECHA;
            this.estadoActual = ESTADO_MOVIENDO;
        }
    }
    
    public void up(){
        if(!(movimientoBloqueado())){
            this.posY = this.posY-5;
            if((this.estadoActual != ESTADO_MOVIENDO) || (this.direccionActual != DIRECCION_DERECHA)){
                cambiarImagen(0);
            }
        }
    }
    
    public boolean getPowerup() {
    	return powerup;
    }
    
    public void setPowerup() {
    	powerup=true;
    }
    
    public void notPowerup() {
    	powerup=false;
    	rafaga=false;
    }
    
    public void down(){
        if(!(movimientoBloqueado())){
            this.posY = this.posY+5;
            if((this.estadoActual != ESTADO_MOVIENDO) || (this.direccionActual != DIRECCION_DERECHA)){
                cambiarImagen(0);
            }
        }
    }
    
    private int contadorM = 0;
    @Override
    public void update(double delta) {
        
        
        switch(estadoActual){
            case ESTADO_MURIENDO:
                    //arma = null;
                    if(contadorM > 38 )
                        this.estadoActual = ESTADO_MUERTO;
                    else 
                    	contadorM++;
                        
                        
                break;
            case ESTADO_ATACANDO:
            case ESTADO_MOVIENDO:
            case ESTADO_QUIETO:

                /* Rebota contra los margenes X del mundo */
                if((this.posX+ (this.getWidth())) > Escenario.ANCHO_MUNDO){
                    this.posX = (int) 480 - (this.getWidth())+5;
                }
                /* Rebota contra la X=0 del mundo */
                if((this.posX) < 0){
                    this.posX = 0;
                }
                
                if(this.posY > esc.getBajo()-110){
                    this.posY = esc.getBajo()-110;  
                    /*QUE NO SE PIERDA ABAJO*/
                } 
                
                if(this.posY < esc.getAlto()+90){
                   this.posY = esc.getAlto()+90;      
                    /*QUE NO SE PIERDA ARRIBA*/
                } 
    
        }
        if(this.estadoActual != ESTADO_ATACANDO){
            this.estadoAnterior = this.estadoActual;
        }
    }
    
    @Override
    public void display(Graphics2D g2){
        AffineTransform transform = new AffineTransform();
        AffineTransform old = g2.getTransform();
        int numImg = -1;
        int numImg2 = 3;
        switch(estadoActual){
            case ESTADO_QUIETO:
            case ESTADO_ATACANDO:
            	 numImg =  0;
            	break;
            case ESTADO_MOVIENDO:
            	
            	if(this.estadoActual== ESTADO_QUIETO) {
            		 numImg =  0;
            		 numImg2 = 3;
            	}else if(this.direccionActual == DIRECCION_DERECHA){
                    numImg = 2;
                    numImg2 = 5;
                } else {
                    numImg = 1;
                    numImg2 = 4;
                }
                break;

            case ESTADO_MURIENDO:
                numImg = 6; 
                break;

        	}
        
        if(Refuerzos){
            this.width = sizeImg[numImg][0];
            this.height = sizeImg[numImg][1];
            g2.drawImage(imagenes[numImg],(int) this.posX,(int) this.posY, this.width, this.height, null);
            
           g2.drawImage(imagenes[numImg2],(int) this.posX-42,(int) this.posY+5, this.width-15, this.height-15, null);
           g2.drawImage(imagenes[numImg2],(int) this.posX+62,(int) this.posY+5, this.width-15, this.height-15, null);
        }
        else {
            this.width = sizeImg[numImg][0];
            this.height = sizeImg[numImg][1];
            g2.drawImage(imagenes[numImg],(int) this.posX,(int) this.posY, this.width, this.height, null);

        }
        g2.setTransform(old);
    }
    
    public void atacar(){
        if(!(movimientoBloqueado())){  
        	
        	if(!Refuerzos || arma.getTMunicion() == 1) {
            	arma.disparar((int) posX+width/2, (int) posY); //DISPARO
        	}else {
        		arma.disparar((int) posX+width/2-38, (int) posY-5); //DISPARO
        		arma.disparar((int) posX+width/2+30, (int) posY-5); //DISPARO
        	}
        }
    }
    
    public boolean getRafaga() {
    	return rafaga;
    }
    
    public void setRafaga() {
    	rafaga = true;
    }
    
    public void quitRafaga() {
    	rafaga = false;
    }
    
    @Override
    public Rectangle getBordes(){
        return new Rectangle((int) posX, (int) posY, width, height);
    }
    
    public Rectangle getBordesR(){
        return new Rectangle((int) posX-42, (int) posY+5, width-15, height+15);
    }
    public Rectangle getBordesR2(){
        return new Rectangle((int) posX+62, (int) posY+5, width-15, height+15);
    }
 

    public int getEstadoActual() {
        return estadoActual;
    }
    

    public void setEstadoActual(int estadoActual) {
        this.estadoActual = estadoActual;
        
    }


    public int getDireccionActual() {
        return direccionActual;
    }

    public void setDireccionActual(int direccionActual) {
        this.direccionActual = direccionActual;
    }
    
    public void setArma(Arma arma){
        this.arma = arma;
    }
    
    public void setAtaque(AtaqueEspecial ataque){
        this.ataque = ataque;
    }
    
    public AtaqueEspecial getAtaque(){
        return ataque;
    }
    
    public void atacarE() {
    	if(this.energia > 30) {
    	ataque.activar();
    	this.energia = energia-25;}
    }
    
    public void setRefuerzos() {
    	Refuerzos = true;
    }
    
    public void ChocoRefuezo() {
    	vidaR--;
    	if(vidaR<2) {
    		vidaR=3;
    		Refuerzos=false;
    	}
    	
    }

    
    public boolean getRefuerzos(){
        return Refuerzos;
    }
    
    public Arma getArma(){
        return arma;
    }
    
    

    public boolean isInvulnerable() {
        return invulnerable;
    }

    public void setInvulnerable(boolean invulnerable) {
        this.invulnerable = invulnerable;
    }
    
    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }
    
 
}
