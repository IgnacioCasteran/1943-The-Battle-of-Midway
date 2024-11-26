package battelofmidway;

import static battelofmidway.Configuracion.leerConfiguracion;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BattleOfMidway extends JGame {
    private Escenario esc;
    
    private static final String etapas[] = {"recursos\\Etapas\\fondo.png", 
                                "recursos\\Etapas\\Logo.png",
                                "recursos\\Etapas\\Mapa1.png",
                                "recursos\\Etapas\\PortaAviones1.png",
                                "recursos\\Etapas\\PortaAviones2.png",
                                "recursos\\Etapas\\Mar.png",
                                "recursos\\Etapas\\Cumplida1.png",
                                "recursos\\Etapas\\Cumplida3.png",
    							"recursos\\Etapas\\PortaAviones3.png",
    							"recursos\\Etapas\\IntermedioFondo.png"};
    private int contB=0;
    private int contC=0;
    
    private static final String AnimacionAvionM[] = {"recursos\\AnimacionMenu\\avion1.png",
    		"recursos\\AnimacionMenu\\avion2.png",
    		"recursos\\AnimacionMenu\\avion3.png",
    		"recursos\\AnimacionMenu\\avion4.png",
    		"recursos\\AnimacionMenu\\avion5.png",
    		"recursos\\AnimacionMenu\\avion6.png",
    		"recursos\\AnimacionMenu\\avion7.png",
    		"recursos\\AnimacionMenu\\avion8.png"};
    
    private static final String RectanguloVida[] = {"recursos\\Vida\\Negro.png",
    												"recursos\\Vida\\Vida0.png"};
    
    private int contA=0;
    
    private static final String AnimacionAvionI[] = {"recursos\\AnimacionMenu\\EnemigosDisparan\\0.png",
    		"recursos\\AnimacionMenu\\Despegue\\0.png",
    		"recursos\\AnimacionMenu\\Despegue\\explo0.gif",
    		"recursos\\AnimacionMenu\\Despegue\\1.png",
    		"recursos\\AnimacionMenu\\Despegue\\2.png",
    		"recursos\\AnimacionMenu\\Despegue\\3.png",
    		"recursos\\AnimacionMenu\\Despegue\\4.png",
    		"recursos\\AnimacionMenu\\Despegue\\5.png",
    		"recursos\\AnimacionMenu\\Despegue\\6.png",
    		"recursos\\AnimacionMenu\\Despegue\\7.png",
    		"recursos\\AnimacionMenu\\Despegue\\8.png",
    		"recursos\\AnimacionMenu\\Despegue\\9.png",
    		"recursos\\AnimacionMenu\\Despegue\\10.png",
    		"recursos\\AnimacionMenu\\Despegue\\11.png",
    		"recursos\\AnimacionMenu\\Despegue\\12.png",
    		"recursos\\AnimacionMenu\\Despegue\\13.png",
    		"recursos\\AnimacionMenu\\Despegue\\14.png",
    		"recursos\\AnimacionMenu\\Despegue\\15.png",
    		"recursos\\AnimacionMenu\\Despegue\\16.png",
    		"recursos\\AnimacionMenu\\Despegue\\17.png",
    		"recursos\\AnimacionMenu\\Despegue\\18.png",
    		};

    
    
    private Image VidaNegro;
    private Image Vida;
     
    private Image animacion_1;
    private Image animacion_2;
    private Image animacion_3;
    private Image animacion_4;
    private Image animacion_5;
    private Image animacion_6;
    private Image animacion_7;
    private Image animacion_8;
    
    private Image animacionI_0;//ENEMIGO
    private Image animacionI_1;//JUGADOR
    private Image animacionI_2;//EXPLOCIONES 


    private Image AnimacionAvionI1;
    private Image AnimacionAvionI2;
    private Image AnimacionAvionI3;
    private Image AnimacionAvionI4;
    private Image AnimacionAvionI5;
    private Image AnimacionAvionI6;
    private Image AnimacionAvionI7;
    private Image AnimacionAvionI8;
    private Image AnimacionAvionI9;
    private Image AnimacionAvionI10;
    private Image AnimacionAvionI11;
    private Image AnimacionAvionI12;
    private Image AnimacionAvionI13;
    private Image AnimacionAvionI14;
    private Image AnimacionAvionI15;
    private Image AnimacionAvionI16;
    private Image AnimacionAvionI17;
    private Image AnimacionAvionI18;
    
    

    private Image imagen_etapa;
    private Image imagen_etapa2;
    private Image imagen_etapa3;
    private Image imagen_etapa4;
    private Image imagen_etapa5;
    private Image cumplida1;
    private Image cumplida3;
 

    private int etapaJuego; 
    Keyboard keyboard = this.getKeyboard();
    
    public final static int ETAPA_MENU = 0;
    public final static int ETAPA_JUEGO = 1;
    public final static int ETAPA_INTERMEDIO = 2;
    public final static int ETAPA_INTERMEDIOB = 6;
    public final static int ETAPA_WIN = 5;
    public final static int ETAPA_GAMEOVER = 3;
    public final static int ETAPA_CONTACTO = 7;
    public final static int ETAPA_WINNER = 8;
    public final static int ETAPA_REPEAT = 9;
    public final static int ETAPA_RANKING = 4;
    
    
    private int p_musica;
    private boolean p_csonido;
    private int p_sonido;
    private int p_pausar;
    private int p_iniciar;
    private int  p_izquierda;
    private int p_derecha;
    private int p_saltar;
    private int p_lanzar;
    
    private int p_subir;
    private int p_bajar;
    private int p_especial;
    
    
    private boolean pausa = false;
   private boolean musica = false;
    
    private byte contMusic = 0;
    
    private float tiempoPower = 0;
    private float tiempo = 1200000; // ESTA PROGRMADO PARA QUE SOLO DURE ESTO CADA PARTIDA, 60 MIL ES UN MINUTDO
    
    
    public BattleOfMidway(String nombreJugador) {
        // call game constructor
        super("1943 Battle Of Midway ", 480, 640);
        esc = new Escenario(this, getJugador());
        this.etapaJuego = ETAPA_RANKING;
        
        contadorMultiple = 0;
        jugador.setNombre(nombreJugador);
        
        
    

    }

    @Override
    public void gameStartup() {
        Toolkit toolkit = Toolkit.getDefaultToolkit(); // SIRVE PARA CARGAR IMAGENES
        
       
        imagen_etapa = toolkit.getImage(etapas[0]);
        imagen_etapa2 = toolkit.getImage(etapas[1]);
        imagen_etapa3 = toolkit.getImage(etapas[9]);
        imagen_etapa4 = toolkit.getImage(etapas[3]);
        imagen_etapa5 = toolkit.getImage(etapas[4]);
        cumplida1 = toolkit.getImage(etapas[6]);
        cumplida3 = toolkit.getImage(etapas[7]);
        
  
      
        ////////////////ANIMACION AVION ENTRADA
        
        animacion_1 = toolkit.getImage(AnimacionAvionM[0]);
        animacion_2 = toolkit.getImage(AnimacionAvionM[1]);
        animacion_3 = toolkit.getImage(AnimacionAvionM[2]);
        animacion_4 = toolkit.getImage(AnimacionAvionM[3]);
        animacion_5 = toolkit.getImage(AnimacionAvionM[4]);
        animacion_6 = toolkit.getImage(AnimacionAvionM[5]);
        animacion_7 = toolkit.getImage(AnimacionAvionM[6]);
        animacion_8 = toolkit.getImage(AnimacionAvionM[7]);
        
        /////////////////ANIMACION INTERLUDE
        
        animacionI_0 = toolkit.getImage(AnimacionAvionI[0]);
        animacionI_2 = toolkit.getImage(AnimacionAvionI[2]);
        
        
        AnimacionAvionI1 = toolkit.getImage(AnimacionAvionI[1]);
        AnimacionAvionI2 = toolkit.getImage(AnimacionAvionI[3]);
        AnimacionAvionI3 = toolkit.getImage(AnimacionAvionI[4]);
        AnimacionAvionI4 = toolkit.getImage(AnimacionAvionI[5]);
        AnimacionAvionI5 = toolkit.getImage(AnimacionAvionI[6]);
        AnimacionAvionI6 = toolkit.getImage(AnimacionAvionI[7]);
        AnimacionAvionI7 = toolkit.getImage(AnimacionAvionI[8]);
        AnimacionAvionI8 = toolkit.getImage(AnimacionAvionI[9]);
        AnimacionAvionI9 = toolkit.getImage(AnimacionAvionI[10]);
        AnimacionAvionI10= toolkit.getImage(AnimacionAvionI[11]);
        AnimacionAvionI11= toolkit.getImage(AnimacionAvionI[12]);
        AnimacionAvionI12= toolkit.getImage(AnimacionAvionI[13]);
        AnimacionAvionI13= toolkit.getImage(AnimacionAvionI[14]);
        AnimacionAvionI14= toolkit.getImage(AnimacionAvionI[15]);
        AnimacionAvionI15= toolkit.getImage(AnimacionAvionI[16]);
        AnimacionAvionI16= toolkit.getImage(AnimacionAvionI[17]);
        AnimacionAvionI17= toolkit.getImage(AnimacionAvionI[18]);
        AnimacionAvionI18= toolkit.getImage(AnimacionAvionI[19]);


        ///////////////////// PARA DIBUJAR VIDA
        
        VidaNegro = toolkit.getImage(RectanguloVida[0]);
        Vida = toolkit.getImage(RectanguloVida[1]);
        
        ////////////////////
        
        esc.getHeroe().quieto();
        
        appProperties = leerConfiguracion();
        p_csonido = Boolean.valueOf(appProperties.getProperty("sonidoChoice"));
        admin_sound.setact_sonido(p_csonido);
        
        //contP = 60;
        
    }
    
    @Override
    public void gameUpdate(double delta) {
        appProperties = leerConfiguracion();
        
        p_sonido =(char)(appProperties.getProperty("teclaSonido").charAt(0)-32);
        p_musica =(char)(appProperties.getProperty("teclaMusica").charAt(0)-32);
        p_pausar =(int)(appProperties.getProperty("Pausa").charAt(0));
        p_iniciar=(char)(appProperties.getProperty("teclaIniciar").charAt(0)-59);
       
        
        
        if("Izquierda".equals(appProperties.getProperty("Izquierda")) ){ //Caso de "<"
            p_izquierda=37;
        }else{
            p_izquierda=(int)(appProperties.getProperty("Izquierda").charAt(0));
        }
        
        
        if("Derecha".equals(appProperties.getProperty("Derecha"))){ //Caso de ">"
            p_derecha=39;
        }else{
            p_derecha=(int)(appProperties.getProperty("Derecha").charAt(0));
        }
        
        if("Arriba".equals(appProperties.getProperty("Arriba"))){ 
        	 p_subir=38;
        }else{
            p_subir=(int)(appProperties.getProperty("Arriba").charAt(0));
        }
        
        if("Abajo".equals(appProperties.getProperty("Abajo"))){ 
       	 p_bajar=40;
        }else{
           p_bajar=(int)(appProperties.getProperty("Abajo").charAt(0));
        }
       
          
        p_lanzar=(int)(appProperties.getProperty("Disparar").charAt(0));
        p_especial=(int)(appProperties.getProperty("Ataque_Especial").charAt(0));
        
        
        if(keyboard.isKeyPressed(p_izquierda) && etapaJuego == ETAPA_JUEGO && !pausa){
            esc.getHeroe().left();
        }
        if(keyboard.isKeyPressed(p_derecha) && etapaJuego == ETAPA_JUEGO && !pausa){
            esc.getHeroe().right();
        }  
        
        if(keyboard.isKeyPressed(p_subir) && etapaJuego == ETAPA_JUEGO && !pausa){
        	 esc.getHeroe().up();
        }
        
        if(keyboard.isKeyPressed(p_bajar) && etapaJuego == ETAPA_JUEGO && !pausa){
        	esc.getHeroe().down();
        }
        

        

        
        // check the list of key events for a pressed escape key
        LinkedList < KeyEvent > keyEvents = keyboard.getEvents();
        for(KeyEvent event: keyEvents){
        	
        	if(esc.getHeroe().getRafaga()) {
        		if((event.getID() == KeyEvent.KEY_PRESSED) && (event.getKeyCode() == p_lanzar) && etapaJuego == ETAPA_JUEGO && !pausa){
                esc.getHeroe().atacar();
                admin_sound.playSonido("disparo_normal", true);
               }
        	}else{
        		if((event.getID() == KeyEvent.KEY_RELEASED) && (event.getKeyCode() == p_lanzar) && etapaJuego == ETAPA_JUEGO && !pausa){
                    esc.getHeroe().atacar();
                    admin_sound.playSonido("disparo_normal", true);
                 }
        	}
        		
        	if((event.getID() == KeyEvent.KEY_RELEASED) && (event.getKeyCode() == p_especial) && etapaJuego == ETAPA_JUEGO && !pausa){
                esc.getHeroe().atacarE();
             }

            	
            if((event.getID() == KeyEvent.KEY_RELEASED) && etapaJuego == ETAPA_JUEGO){
                    esc.getHeroe().quieto();                
            } else if((event.getID() == KeyEvent.KEY_PRESSED) && (event.getKeyCode() == KeyEvent.VK_ESCAPE)){
                stop();  
            } else if((event.getID() == KeyEvent.KEY_PRESSED) && (event.getKeyCode() == p_sonido)){
                admin_sound.setact_sonido(!admin_sound.getact_sonido());
            } else if((event.getID() == KeyEvent.KEY_PRESSED) && (event.getKeyCode() == p_musica)){
                admin_sound.setact_musica(this.etapaJuego, esc.getNumeroMapa(), !admin_sound.getact_musica());
            } else if((event.getID() == KeyEvent.KEY_PRESSED) && (event.getKeyCode() == p_pausar)){
                pausa = !pausa; 
                admin_sound.setact_sonido(!admin_sound.getact_sonido());
                admin_sound.setact_musica(this.etapaJuego, esc.getNumeroMapa(), !admin_sound.getact_musica());
            }
        }
        
        if (!pausa){ esc.getHeroe().update(delta);}

        
    
    }
    
    public boolean getPausa(){
        return this.pausa;
    }
    

    
    private void drawMenu(Graphics2D g){
    	
        g.drawImage(imagen_etapa, 8, 7, imagen_etapa.getWidth(null), imagen_etapa.getHeight(null), null);
          
       //////////////////////////////////// ANIMACION AVION 
        
        if(contA < 40) {
        	if(contA < 5)
        	g.drawImage( animacion_1 , 270, 160, animacion_1.getWidth(null), animacion_1.getHeight(null), null);
        	else if(contA < 10)
        	g.drawImage( animacion_2 , 340, 160, animacion_2.getWidth(null), animacion_2.getHeight(null), null);
        	else if(contA < 15)
        	g.drawImage( animacion_3 , 430, 160, animacion_3.getWidth(null), animacion_3.getHeight(null), null);
        	else if(contA < 20)
        	g.drawImage( animacion_4 , 450, 230, animacion_2.getWidth(null), animacion_4.getHeight(null), null);
        	else if(contA < 25)
        	g.drawImage( animacion_5 , 430, 290, animacion_5.getWidth(null), animacion_5.getHeight(null), null);
        	else if(contA < 30)
        	g.drawImage( animacion_6 , 300, 340, animacion_6.getWidth(null), animacion_6.getHeight(null), null);
        	else if(contA < 35)
        	g.drawImage( animacion_7 , 260, 390, animacion_7.getWidth(null), animacion_7.getHeight(null), null);
        	else if(contA <40)
        	g.drawImage( animacion_8 ,85, 250, animacion_8.getWidth(null), animacion_8.getHeight(null), null);
        
        	contA++;
        }else {
        	g.drawImage( animacion_8 ,20, 250, animacion_8.getWidth(null), animacion_8.getHeight(null), null);
        	drawText(g, "PRESS START", 135, 400, 40, Color.WHITE, Color.RED);
        }
        
        ////////////////////////////////////////////
        
        

        g.drawImage(imagen_etapa2, 50, 140, imagen_etapa2.getWidth(null)*3/4, imagen_etapa2.getHeight(null)*3/4, null);
        
          
        drawText(g, "1 PLAYER", 120, 65, 25, Color.WHITE, Color.BLACK);
        drawText(g, "HI-SCORE", 280, 65, 25, Color.WHITE, Color.RED);
        drawText(g, "9999999", 278, 90, 25, Color.WHITE, Color.RED);
        drawText(g, "0000000", 122, 90, 25, Color.WHITE, Color.BLACK);
        
        
        
    }
    
    private int dezplazamiento=0;

	private int contadorMultiple;
    private void drawGame(Graphics2D g){
        if(esc.getHeroe().getEstadoActual() != 6)
        {
        	if(esc.cambioNivel()) {
        		dezplazamiento=0;
        		esc.setcambionivel();
        	}
        	
        	
            
            if(!pausa && !esc.getHeroe().getAtaque().activado()) {
            	dezplazamiento=dezplazamiento+3;
            }
            
            g.translate(0,dezplazamiento);
            
            esc.display(g,dezplazamiento);
            
            
            drawText(g, "1 PLAYER", 120, 65-dezplazamiento, 25, Color.WHITE, Color.BLACK);
            drawText(g, "HI-SCORE", 280, 65-dezplazamiento, 25, Color.WHITE, Color.RED);
            drawText(g, "9999999", 278, 90-dezplazamiento, 25, Color.WHITE, Color.RED);
            drawText(g, esc.getJugador().getMPuntos("0"), 122, 90-dezplazamiento, 25, Color.WHITE, Color.BLACK);

            
            if(esc.getHeroe().getPowerup()) {
            	drawTimePower(g);
            	if(!pausa)
            		tiempoPower = decrementTime(tiempoPower);
            }
            
            if(!pausa) {
            tiempo = decrementTime(tiempo); // CONTROLA SI SE TERMINA EL JUEGO POR TIEMPO
            
            if (tiempo % 3000 == 0) {// LE QUITA 1 PUNTOS DE VIDA AL HEROE CADA 3 SEGUNDOS           
                esc.getHeroe().setEnergia(esc.getHeroe().getEnergia()-1);
            }
            }
            
            
            	
        } else {
            this.etapaJuego = ETAPA_GAMEOVER;
            esc = new Escenario(this, getJugador());
            dezplazamiento=0;
            tiempoPower=0;
            tiempo = 1200000;
        }
        
        if(esc.getHeroe().getEnergia() < 30 && tiempo % 500 == 0 ) {
        	admin_sound.playSonido("poca_vida", true);
        }
        
        // DIBUJAR VIDA
        g.drawImage(VidaNegro, 15, 610-dezplazamiento, VidaNegro.getWidth(null), VidaNegro.getHeight(null), null);   
        double porcentaje = esc.getHeroe().getEnergia() / 100.0; 
        int srcWidth = (int) (Vida.getWidth(null) * porcentaje);  // Ancho de la región de origen en función del porcentaje
        int srcHeight = Vida.getHeight(null);  // Alto completo de la imagen
        g.drawImage(Vida, 
                18, 612-dezplazamiento,                    		// Coordenadas de destino
                10 + srcWidth, 610 + srcHeight-dezplazamiento,	 // Coordenadas finales de destino
                0, 0,                                            		// Coordenadas de origen
                srcWidth, srcHeight,                           		  // Ancho y alto de la región de origen
                null);
        
        
        
    }
    
    private void drawTimePower(Graphics2D g){
        if(esc.getHeroe().getEstadoActual() != Heroe.ESTADO_MURIENDO || esc.getHeroe().getEstadoActual() != Heroe.ESTADO_MUERTO && 
        		etapaJuego == ETAPA_JUEGO){
            if(tiempoPower > 0 && esc.getEnergiaHeroe()>=0){
            	
                String tiempoP = Integer.toString((int) (tiempoPower/1000));
                drawText(g, tiempoP,230, 630-dezplazamiento, 25, Color.RED, Color.WHITE);
                drawText(g, esc.getHeroe().getArma().getNomMunicion(), 21, 602-dezplazamiento, 25, Color.RED, Color.WHITE);
                
            }else if(tiempoPower==0){
            	esc.getHeroe().notPowerup();
            	esc.getHeroe().getArma().setMunicion(0);
            }
        }
    }
    
    
    private void drawGameOver(Graphics2D g){
        g.setBackground(Color.black);

        drawText(g, "GAME OVER", 85, 350, 60, Color.RED, Color.WHITE);
        drawText(g, "PRESS ENTER", 180, 400, 25, Color.RED, Color.WHITE);
    }
    
    private void drawContacto(Graphics2D g){
        g.setBackground(Color.black);
        
        if(contC  < 200) {
        drawText(g, "CONTACTO VISUAL CON", 95, 300, 30, Color.WHITE, Color.BLACK);
        drawText(g, "FUERZAS SUPERFICIALES CONFIRMADAS", 25, 360, 25, Color.WHITE, Color.BLACK);
        drawText(g, "COMENZANDO ATAQUE..", 95, 420, 30, Color.WHITE, Color.RED);
        contC++;
        }else {
        	contC = 0;
        	this.etapaJuego = ETAPA_JUEGO;
        }
        musica=false;
    }
    
    private void drawRepeat(Graphics2D g){
        g.setBackground(Color.black);
        
        if(contC  < 200) {
        drawText(g, "FALLO DESTRUCCION", 110, 300, 30, Color.WHITE, Color.BLACK);
        drawText(g, "REPITIENDO MISION..", 130, 360, 25, Color.WHITE, Color.BLACK);
        contC++;
        }else {
        	contC = 0;
        	this.etapaJuego = ETAPA_JUEGO;
        }
    }
    
    private void drawWin(Graphics2D g){
    	
        g.drawImage(cumplida1, 8, 7, cumplida1.getWidth(null), cumplida1.getHeight(null), null);
  
       //////////////////////////////////// ANIMACION AVION 
        
        if(contA < 40) {
        	if(contA < 5)
        	g.drawImage( animacion_1 , 270, 160, animacion_1.getWidth(null), animacion_1.getHeight(null), null);
        	else if(contA < 10)
        	g.drawImage( animacion_2 , 340, 160, animacion_2.getWidth(null), animacion_2.getHeight(null), null);
        	else if(contA < 15)
        	g.drawImage( animacion_3 , 430, 160, animacion_3.getWidth(null), animacion_3.getHeight(null), null);
        	else if(contA < 20)
        	g.drawImage( animacion_4 , 450, 230, animacion_2.getWidth(null), animacion_4.getHeight(null), null);
        	else if(contA < 25)
        	g.drawImage( animacion_5 , 430, 290, animacion_5.getWidth(null), animacion_5.getHeight(null), null);
        	else if(contA < 30)
        	g.drawImage( animacion_6 , 300, 340, animacion_6.getWidth(null), animacion_6.getHeight(null), null);
        	else if(contA < 35)
        	g.drawImage( animacion_7 , 260, 390, animacion_7.getWidth(null), animacion_7.getHeight(null), null);
        	else if(contA <40)
        	g.drawImage( animacion_8 ,85, 250, animacion_8.getWidth(null), animacion_8.getHeight(null), null);
        
        	contA++;
        }else
        	g.drawImage( animacion_8 ,20, 250, animacion_8.getWidth(null), animacion_8.getHeight(null), null);
        
        ////////////////////////////////////////////
        
        
          
        drawText(g, "1 PLAYER", 120, 65, 25, Color.WHITE, Color.BLACK);
        drawText(g, "HI-SCORE", 280, 65, 25, Color.WHITE, Color.RED);
        drawText(g, "9999999", 278, 90, 25, Color.WHITE, Color.RED);
        drawText(g, esc.getJugador().getMPuntos("0"), 122, 90, 25, Color.WHITE, Color.BLACK);
        
        drawText(g, "MISION COMPLETADA", 120, 320, 30, Color.WHITE, Color.BLACK);
        if(!musica) {
        admin_sound.playSonido("Win", admin_sound.getact_musica());
        musica = true;
        }
      
 
        
    }
    
    private void drawWinner(Graphics2D g){
    	

        g.drawImage(cumplida3, 8, 7, cumplida1.getWidth(null), cumplida1.getHeight(null), null);
      
        drawText(g, "1 PLAYER", 120, 65, 25, Color.WHITE, Color.BLACK);
        drawText(g, "HI-SCORE", 280, 65, 25, Color.WHITE, Color.RED);
        drawText(g, "9999999", 278, 90, 25, Color.WHITE, Color.RED);
        drawText(g, esc.getJugador().getMPuntos("0"), 122, 90, 25, Color.WHITE, Color.BLACK);
        
        drawText(g, "JUEGO COMPLETADO", 45, 400, 45, Color.WHITE, Color.BLACK);
        
        esc = new Escenario(this, getJugador());
        dezplazamiento=0;
        tiempoPower=0;
        if(!musica) {
            admin_sound.playSonido("Win", admin_sound.getact_musica());
            musica = true;
            }
        
    }
  
    private void drawIntermediate(Graphics2D g){
   
    	 g.translate(4,contB);
    	 g.drawImage(imagen_etapa3, 3, -imagen_etapa3.getHeight(null)+640, imagen_etapa3.getWidth(null), imagen_etapa3.getHeight(null), null); //FONDO
    	 contB = contB + 4; 
    	 
    	 if(contB < 300) {
    	 	g.drawImage(imagen_etapa4, 200, 120-contB, imagen_etapa4.getWidth(null)*2, imagen_etapa4.getHeight(null)*2, null); //BARCO   	    	    	 
   	 	} 
    	 else if (contB > 300 && contB < 700){     
    		 
    		g.drawImage(imagen_etapa5, 200, 120-contB, imagen_etapa5.getWidth(null)*2, imagen_etapa5.getHeight(null)*2, null); 
    		
    		g.drawImage(animacionI_2, 200, 220-contB, animacionI_2.getWidth(null)/7, animacionI_2.getHeight(null)/7, null);
    		g.drawImage(animacionI_2, 210, 270-contB, animacionI_2.getWidth(null)/7, animacionI_2.getHeight(null)/7, null);
    		g.drawImage(animacionI_2, 205, 290-contB, animacionI_2.getWidth(null)/7, animacionI_2.getHeight(null)/7, null);
    		g.drawImage(animacionI_2, 220, 310-contB, animacionI_2.getWidth(null)/7, animacionI_2.getHeight(null)/7, null);
    		g.drawImage(animacionI_2, 200, 350-contB, animacionI_2.getWidth(null)/7, animacionI_2.getHeight(null)/7, null);
    		g.drawImage(animacionI_2, 250, 390-contB, animacionI_2.getWidth(null)/4, animacionI_2.getHeight(null)/4, null);
    		g.drawImage(animacionI_2, 260, 410-contB, animacionI_2.getWidth(null)/7, animacionI_2.getHeight(null)/7, null);
    		g.drawImage(animacionI_2, 200, 450-contB, animacionI_2.getWidth(null)/4, animacionI_2.getHeight(null)/4, null);
    	 }else if (contB > 700 && contB < 800) {
    		 g.drawImage(imagen_etapa5, 200, -580, imagen_etapa5.getWidth(null)*2, imagen_etapa5.getHeight(null)*2, null);
    		 g.drawImage(AnimacionAvionI1, 220, 350-contB, AnimacionAvionI1.getWidth(null)*2, AnimacionAvionI1.getHeight(null)*2, null);
    		
    	 
    	 }
    	 
    	 g.drawImage(animacionI_0, 160, 20, animacionI_0.getWidth(null), animacionI_0.getHeight(null), null);
    	 g.drawImage(animacionI_0, 220, 60, animacionI_0.getWidth(null), animacionI_0.getHeight(null), null);
	 	 g.drawImage(animacionI_0, 280, 20, animacionI_0.getWidth(null), animacionI_0.getHeight(null), null);
	 	 g.drawImage(animacionI_0, 100, -20, animacionI_0.getWidth(null), animacionI_0.getHeight(null), null);
	 	 g.drawImage(animacionI_0, 340, -20, animacionI_0.getWidth(null), animacionI_0.getHeight(null), null);
    	 
	 	
	 	 if(contB >800  && contB < 900) {
    		 g.drawImage(imagen_etapa5, 200, -580, imagen_etapa5.getWidth(null)*2, imagen_etapa5.getHeight(null)*2, null);
    		 g.drawImage(AnimacionAvionI2, 220, 350-contB, AnimacionAvionI2.getWidth(null)*2, AnimacionAvionI2.getHeight(null)*2, null);
    	 }else if (contB > 900 && contB < 920) {
    		 g.drawImage(imagen_etapa5, 200, -580, imagen_etapa5.getWidth(null)*2, imagen_etapa5.getHeight(null)*2, null);
    		 g.drawImage(AnimacionAvionI3, 220, 350-contB, AnimacionAvionI3.getWidth(null)*2, AnimacionAvionI3.getHeight(null)*2, null);
    	 }else if (contB > 920 && contB < 1020) {
    		 g.drawImage(imagen_etapa5, 200, -580, imagen_etapa5.getWidth(null)*2, imagen_etapa5.getHeight(null)*2, null);
    		 g.drawImage(AnimacionAvionI4, 220, 350-contB, AnimacionAvionI4.getWidth(null)*2, AnimacionAvionI4.getHeight(null)*2, null);
    	 }else if (contB > 1020 && contB < 1040) {
    		 g.drawImage(imagen_etapa5, 200, -580, imagen_etapa5.getWidth(null)*2, imagen_etapa5.getHeight(null)*2, null);
    		 g.drawImage(AnimacionAvionI5, 220, 350-contB, AnimacionAvionI5.getWidth(null)*2, AnimacionAvionI5.getHeight(null)*2, null);
    	 }else if (contB > 1040 && contB < 1060) {
    		 g.drawImage(AnimacionAvionI6, 220, 350-contB, AnimacionAvionI6.getWidth(null)*2, AnimacionAvionI6.getHeight(null)*2, null);
    	 }
    	 else if (contB > 1060 && contB < 1080) {
    		 g.drawImage(AnimacionAvionI7, 220, 350-contB, AnimacionAvionI7.getWidth(null)*2, AnimacionAvionI7.getHeight(null)*2, null);
    	 }
    	 else if (contB > 1080 && contB < 1100) {
    		 g.drawImage(AnimacionAvionI7, 220, 350-contB, AnimacionAvionI7.getWidth(null)*2, AnimacionAvionI7.getHeight(null)*2, null);
    	 }
    	 else if (contB > 1100 && contB < 1120) {
    		 g.drawImage(AnimacionAvionI8, 220, 350-contB, AnimacionAvionI8.getWidth(null)*2, AnimacionAvionI8.getHeight(null)*2, null);
    	 }
    	 else if (contB > 1120 && contB < 1140) {
    		 g.drawImage(AnimacionAvionI10, 220, 350-contB, AnimacionAvionI9.getWidth(null)*2, AnimacionAvionI9.getHeight(null)*2, null);
    	 }
    	 else if (contB > 1160 && contB < 1180) {
    		 g.drawImage(AnimacionAvionI10, 220, 350-contB, AnimacionAvionI10.getWidth(null)*2, AnimacionAvionI10.getHeight(null)*2, null);
    	 }
    	 else if (contB > 1180 && contB < 1200) {
    		 g.drawImage(AnimacionAvionI11, 220, 350-contB, AnimacionAvionI11.getWidth(null)*2, AnimacionAvionI11.getHeight(null)*2, null);
    	 }
    	 else if (contB > 1200 && contB < 1220) {
    		 g.drawImage(AnimacionAvionI12, 220, 350-contB, AnimacionAvionI12.getWidth(null)*2, AnimacionAvionI12.getHeight(null)*2, null);
    	 }
    	 else if (contB > 1220 && contB < 1240) {
    		 g.drawImage(AnimacionAvionI13, 220, 350-contB, AnimacionAvionI13.getWidth(null)*2, AnimacionAvionI13.getHeight(null)*2, null);
    	 }
    	 else if (contB > 1240 && contB < 1260) {
    		 g.drawImage(AnimacionAvionI14, 220, 350-contB, AnimacionAvionI14.getWidth(null)*2, AnimacionAvionI14.getHeight(null)*2, null);
    	 }
    	 else if (contB > 1260 && contB < 1280) {
    		 g.drawImage(AnimacionAvionI15, 220, 350-contB, AnimacionAvionI15.getWidth(null)*2, AnimacionAvionI15.getHeight(null)*2, null);
    	 }
    	 else if (contB > 1280 && contB < 1300) {
    		 g.drawImage(AnimacionAvionI16, 220, 350-contB, AnimacionAvionI16.getWidth(null)*2, AnimacionAvionI16.getHeight(null)*2, null);
    	 }
    	 else if (contB > 1300 && contB < 1320) {
    		 g.drawImage(AnimacionAvionI17, 220, 350-contB, AnimacionAvionI17.getWidth(null)*2, AnimacionAvionI17.getHeight(null)*2, null);
    	 }
    	 else if (contB > 1320) {  		 
    		 g.drawImage(AnimacionAvionI18, 220, 350-contB, AnimacionAvionI18.getWidth(null)*2, AnimacionAvionI18.getHeight(null)*2, null);
    	 }
	 	 
	 	if(contB > 1450) {
	 		this.etapaJuego = ETAPA_JUEGO;
	 		contB=700;
	 	}
	 	


    } 
    
    private void drawIntermediateB(Graphics2D g){
     if(contB<700)
    	 contB=700;
   	 g.translate(4,contB);
   	 g.drawImage(imagen_etapa3, 3, -imagen_etapa3.getHeight(null)+640, imagen_etapa3.getWidth(null), imagen_etapa3.getHeight(null), null); //FONDO
   	 contB = contB + 2; 
   	 	if (contB > 700 && contB < 800) {
   		 g.drawImage(imagen_etapa5, 200, -580, imagen_etapa5.getWidth(null)*2, imagen_etapa5.getHeight(null)*2, null);
   		 g.drawImage(AnimacionAvionI1, 220, 350-contB, AnimacionAvionI1.getWidth(null)*2, AnimacionAvionI1.getHeight(null)*2, null);
   		}
   	 
	 	
	 	 if(contB >800  && contB < 900) {
   		 g.drawImage(imagen_etapa5, 200, -580, imagen_etapa5.getWidth(null)*2, imagen_etapa5.getHeight(null)*2, null);
   		 g.drawImage(AnimacionAvionI2, 220, 350-contB, AnimacionAvionI2.getWidth(null)*2, AnimacionAvionI2.getHeight(null)*2, null);
   	 }else if (contB > 900 && contB < 920) {
   		 g.drawImage(imagen_etapa5, 200, -580, imagen_etapa5.getWidth(null)*2, imagen_etapa5.getHeight(null)*2, null);
   		 g.drawImage(AnimacionAvionI3, 220, 350-contB, AnimacionAvionI3.getWidth(null)*2, AnimacionAvionI3.getHeight(null)*2, null);
   	 }else if (contB > 920 && contB < 1020) {
   		 g.drawImage(imagen_etapa5, 200, -580, imagen_etapa5.getWidth(null)*2, imagen_etapa5.getHeight(null)*2, null);
   		 g.drawImage(AnimacionAvionI4, 220, 350-contB, AnimacionAvionI4.getWidth(null)*2, AnimacionAvionI4.getHeight(null)*2, null);
   	 }else if (contB > 1020 && contB < 1040) {
   		 g.drawImage(imagen_etapa5, 200, -580, imagen_etapa5.getWidth(null)*2, imagen_etapa5.getHeight(null)*2, null);
   		 g.drawImage(AnimacionAvionI5, 220, 350-contB, AnimacionAvionI5.getWidth(null)*2, AnimacionAvionI5.getHeight(null)*2, null);
   	 }else if (contB > 1040 && contB < 1060) {
   		 g.drawImage(AnimacionAvionI6, 220, 350-contB, AnimacionAvionI6.getWidth(null)*2, AnimacionAvionI6.getHeight(null)*2, null);
   	 }
   	 else if (contB > 1060 && contB < 1080) {
   		 g.drawImage(AnimacionAvionI7, 220, 350-contB, AnimacionAvionI7.getWidth(null)*2, AnimacionAvionI7.getHeight(null)*2, null);
   	 }
   	 else if (contB > 1080 && contB < 1100) {
   		 g.drawImage(AnimacionAvionI7, 220, 350-contB, AnimacionAvionI7.getWidth(null)*2, AnimacionAvionI7.getHeight(null)*2, null);
   	 }
   	 else if (contB > 1100 && contB < 1120) {
   		 g.drawImage(AnimacionAvionI8, 220, 350-contB, AnimacionAvionI8.getWidth(null)*2, AnimacionAvionI8.getHeight(null)*2, null);
   	 }
   	 else if (contB > 1120 && contB < 1140) {
   		 g.drawImage(AnimacionAvionI10, 220, 350-contB, AnimacionAvionI9.getWidth(null)*2, AnimacionAvionI9.getHeight(null)*2, null);
   	 }
   	 else if (contB > 1160 && contB < 1180) {
   		 g.drawImage(AnimacionAvionI10, 220, 350-contB, AnimacionAvionI10.getWidth(null)*2, AnimacionAvionI10.getHeight(null)*2, null);
   	 }
   	 else if (contB > 1180 && contB < 1200) {
   		 g.drawImage(AnimacionAvionI11, 220, 350-contB, AnimacionAvionI11.getWidth(null)*2, AnimacionAvionI11.getHeight(null)*2, null);
   	 }
   	 else if (contB > 1200 && contB < 1220) {
   		 g.drawImage(AnimacionAvionI12, 220, 350-contB, AnimacionAvionI12.getWidth(null)*2, AnimacionAvionI12.getHeight(null)*2, null);
   	 }
   	 else if (contB > 1220 && contB < 1240) {
   		 g.drawImage(AnimacionAvionI13, 220, 350-contB, AnimacionAvionI13.getWidth(null)*2, AnimacionAvionI13.getHeight(null)*2, null);
   	 }
   	 else if (contB > 1240 && contB < 1260) {
   		 g.drawImage(AnimacionAvionI14, 220, 350-contB, AnimacionAvionI14.getWidth(null)*2, AnimacionAvionI14.getHeight(null)*2, null);
   	 }
   	 else if (contB > 1260 && contB < 1280) {
   		 g.drawImage(AnimacionAvionI15, 220, 350-contB, AnimacionAvionI15.getWidth(null)*2, AnimacionAvionI15.getHeight(null)*2, null);
   	 }
   	 else if (contB > 1280 && contB < 1300) {
   		 g.drawImage(AnimacionAvionI16, 220, 350-contB, AnimacionAvionI16.getWidth(null)*2, AnimacionAvionI16.getHeight(null)*2, null);
   	 }
   	 else if (contB > 1300 && contB < 1320) {
   		 g.drawImage(AnimacionAvionI17, 220, 350-contB, AnimacionAvionI17.getWidth(null)*2, AnimacionAvionI17.getHeight(null)*2, null);
   	 }
   	 else if (contB > 1320) {  		 
   		 g.drawImage(AnimacionAvionI18, 220, 350-contB, AnimacionAvionI18.getWidth(null)*2, AnimacionAvionI18.getHeight(null)*2, null);
   	 }
	 	 
	 	if(contB > 1450) {
	 		this.etapaJuego = ETAPA_JUEGO;
	 		contB= 0;
	 	}


   }

    @Override
    public void gameDraw(Graphics2D g){
        if(keyboard.isKeyPressed(KeyEvent.VK_ENTER)){
            switch(this.etapaJuego){
                case ETAPA_MENU:
                     admin_sound.playSonido("Music", admin_sound.getact_musica());
                     musica=true;
                	this.etapaJuego = ETAPA_INTERMEDIO;
                    break;
                case ETAPA_INTERMEDIO:
                case ETAPA_INTERMEDIOB:
                	this.etapaJuego = ETAPA_JUEGO;
        	 		contB= 0;
                	break;
                case ETAPA_GAMEOVER:
                    this.etapaJuego = ETAPA_RANKING;
                    ranking.cargar(jugador.getNombre(), jugador.getPuntajeJugador());
                    ranking.ordenar();
                    jugador.setPuntajeJugador(0);
                    break;
                case ETAPA_RANKING:
                    this.etapaJuego = ETAPA_MENU;
                    esc = new Escenario(this,jugador);
                    contA=0;
                    break;
                case ETAPA_WIN:
                	admin_sound.pararMusica("Win");
                	musica=false;
                	if(esc.getNumeroMapa() == 2)
                		this.etapaJuego = ETAPA_INTERMEDIOB;
                	else
                		this.etapaJuego = ETAPA_WINNER;
                	break;
                case ETAPA_WINNER:
                	this.etapaJuego = ETAPA_RANKING;
                    ranking.cargar(jugador.getNombre(), jugador.getPuntajeJugador());
                    ranking.ordenar();
                    jugador.setPuntajeJugador(0);
                    break;
            }
            keyboard.releaseKey(KeyEvent.VK_ENTER);
        }
        switch(this.etapaJuego){
            case ETAPA_MENU:
                drawMenu(g);
                this.contMusic++; //Ver
                this.contadorMultiple = 0;
                break;
            case ETAPA_JUEGO:
            	if(!musica) {
            		admin_sound.playSonido("Music", admin_sound.getact_musica());
            		musica=true;
            	}	
                drawGame(g);
                this.contadorMultiple = 0;
                break;
            case ETAPA_GAMEOVER:
            	admin_sound.pararMusica("Music");
                drawGameOver(g);
                break;
            case ETAPA_CONTACTO:
            	admin_sound.pararMusica("Music");
                drawContacto(g);
                break;
            case ETAPA_REPEAT:
            	admin_sound.pararMusica("Music");
            	musica=false;
                drawRepeat(g);
                break;
            case ETAPA_INTERMEDIO:
                drawIntermediate(g);
                break;
            case ETAPA_INTERMEDIOB:
            	if(!musica) {
            		admin_sound.playSonido("Music", admin_sound.getact_musica());
            		musica=true;
            	}
                drawIntermediateB(g);
                break;
            case ETAPA_WIN:
                drawWin(g);
                break;
            case ETAPA_WINNER:
                drawWinner(g);
                break;
            case ETAPA_RANKING:
            	ranking.ordenar();
            	admin_sound.pararMusica("Music");
                ranking.display(g);
                break;
            default:
                break;
        }
    }
    
    public void quitMusic() {
    	musica=false;
    }
    
    private void drawText(Graphics2D g2, String text, int posX, int posY, int size, Color color, Color colorb){
        Font f = new Font("Lazer Game Zone", Font.ITALIC, size);
                
        g2.setFont(f);
        g2.setColor(colorb);
        g2.drawString(text, posX+3, posY+2);
        g2.setColor(color);
        g2.drawString(text, posX, posY);
    }
    


    
    
    /////////////////////////////////////////////////////////////////////////
    
    @Override
    public void gameShutdown() {
       //Log.info(getClass().getSimpleName(), "Shutting down game");
    }

 

    public Escenario getEsc() {
        return esc;
    }
    
    public int getEtapaJuego() {
        return etapaJuego;
    }

    public void setEtapaJuego(int etapaJuego) {
        this.etapaJuego = etapaJuego;
    }

    public int getTiempo() {
        return (int) tiempoPower;
    }
    
    public void setTiempo(int tiempo) {
        this.tiempoPower = tiempo;
    }
    
    public void incrementTime(int segundos){
    	
            tiempoPower = tiempoPower + segundos;
 
    }
    
    public float decrementTime(float tiempoPower2){
        return tiempoPower2-=25;
    }
 
    
    public static void main(String[] args) {

    	BattleOfMidway game = new BattleOfMidway(Configuracion.leerConfiguracion().getProperty("nombreJugador"));
        Thread t = new Thread() {
            @Override
            public void run() {
                game.run(1.5 / 60.0);
            }
        };
        t.start();
    }
}