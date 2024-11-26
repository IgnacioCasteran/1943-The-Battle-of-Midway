package battelofmidway;
import java.awt.*;
import java.util.ArrayList;

import adventureisland.AvionKamikaze;
import adventureisland.AvionMediano;
import adventureisland.AvionPequeño;
import adventureisland.AvionSemimediano;
import adventureisland.Pow;

public class Escenario{
    public static final boolean TESTMODE = false;
    
    
    private static final String AREAS[] = {"recursos\\Etapas\\Mapa1.png", "recursos\\Etapas\\IntermedioFondo.png", "recursos\\Etapas\\Mapa2.png"};
    
    
    
    
  

    private Image imagen_fondo;
    
    private Heroe heroe = null;
    private Jugador jugador = null;
    private Arma arma = null;
    private AtaqueEspecial ataqueE = null;
    
    
    
    //POWER UPS
    private Escopeta escopetas[];
    private int cantidadEscopetas = 0;
    
    private TresTiros trestiros[];
    private int cantidadTresTiros = 0;
    
    private Auto autos[];
    private int cantidadAutos = 0;
    
    private Pow pows[];
    private int cantidadPows = 0;
    private boolean algunAvionRojoSinChocar = false; 
    private int powCreado[];
    private double bonusPosX;
    private double bonusPosY;
    
    private ArrayList<Pow> powsAvionesRojos = new ArrayList<Pow>();
    private int powAvionRojo[];
    
    private Estrella estrellas[];
    private int cantidadEstrellas = 0;
    
    private Refuerzo refuerzos[];
    private int cantidadRefuerzos = 0;
    
    private Shell misiles[];
    private int cantidadMisiles = 0;
    
    //
    
    
    private Barco barcos[];
    private int cantidadbarcos = 0;
    
    private Bos boss;
    private Yamato yamato;
    
    private AvionKamikaze avionesKamikazes[];
    private AvionKamikaze avionesKamikazesRojos[][];
    private ArrayList<AvionKamikaze> avionesRojos = new ArrayList<AvionKamikaze>();
    private int cantidadAvionesKamikazes = 0;
    private int cantidadgruposAvionesRojos;
    
    private AvionPequeño avionesPequeños[];
    private int cantidadAvionesPequeños = 0;
    
    private AvionSemimediano avionesSemimedianos[];
    private int cantidadAvionesSemimedianos = 0;
    
    private AvionMediano avionesMedianos[];
    private int cantidadAvionesMedianos = 0;
   
    private BattleOfMidway bm;
 
    
    private int numeroMapa;
    private int energiaHeroe;
    
    public static final int ANCHO_MUNDO=480;
    private int ALTO_MUNDO=0;
    private int BAJO_MUNDO=640;
    private int limite=-6600;
    private boolean cambionivel=false;
    private boolean heroemuerto=false;
    private boolean bossmuerto=false;
    private boolean yamatomuerto=false;
    
  
    
    public Escenario(BattleOfMidway bm, Jugador jugador){
        numeroMapa = 0;
        this.jugador = jugador;
        this.energiaHeroe = 100;
        this.bm = bm;
        generarAreas();
        
        powCreado = new int[3];
        powCreado[0] = 0;
        powCreado[1] = 0;
        powCreado[2] = 0;
    
    }
    
    private void estaMuerto(){
        if(heroe.getEnergia() > 0){
            if(this.heroe.getEstadoActual() == Heroe.ESTADO_MUERTO || heroe.getPosY() < limite){
                // reiniciar personaje
            	
                if(heroe.getPosY() < limite){
                    numeroMapa += 1;
                    cambionivel=true;
                    BAJO_MUNDO=640;
                    ALTO_MUNDO=0;
                    vaciarArea();
                    
                    //this.jugador.setPuntajeJugador(this.jugador.getPuntajeJugador() + bm.getTiempo()/10); //SUMA PUNTOS EN BASE AL TIEMPO RESTANTE
                    
                    
                    switch(numeroMapa) {
                      case 1:
                    	  jugador.setTemp();
                    	  bm.setEtapaJuego(BattleOfMidway.ETAPA_CONTACTO);
                    	break;
                      case 2:
                    	  if(boss.Destruido()) {
                    	  bm.setEtapaJuego(BattleOfMidway.ETAPA_WIN);
                    	  bm.getAdmin_sound().pararMusica("Music");
                    	  bm.quitMusic();
                    	  }
                    	  else {
                    	  numeroMapa=1;
                    	  bm.setEtapaJuego(BattleOfMidway.ETAPA_REPEAT);
                    	  jugador.resetP();
                    	  }
                    	  break;
                      case 3:{
                    	 limite=-12000;
                    	  jugador.setTemp();
                    	  bm.setEtapaJuego(BattleOfMidway.ETAPA_CONTACTO);
                      }
                    	  break;
                      case 4:
                    	  if(yamato.Destruido()) {
                    			  bm.setEtapaJuego(BattleOfMidway.ETAPA_WIN);
                    			  bm.getAdmin_sound().pararMusica("Music");
                            	  bm.quitMusic();
                    	  }
                    	  else {
                    		  numeroMapa--;
                    		  bm.setEtapaJuego(BattleOfMidway.ETAPA_REPEAT);
                    		  jugador.resetP();
                    	  }
                    	  break;
                    }
                    
                    
                }

              
                //bm.getAdmin_sound().playSonido("Area1", false);
                //bm.getAdmin_sound().playSonido("Area2", false);
                generarAreas();
                
                if(numeroMapa == 0){
                    //bm.getAdmin_sound().playSonido("Area1", bm.getAdmin_sound().getact_musica());
                }else if(numeroMapa == 1){
                    //bm.getAdmin_sound().playSonido("Area2", bm.getAdmin_sound().getact_musica());
                }
            }
        } else
        	heroe.setEstadoActual(Heroe.ESTADO_MURIENDO);
    }
    
    public int getAlto() {
    	return ALTO_MUNDO;
    }
    
    public int getBajo() {
    	return BAJO_MUNDO;
    }
    
    public void setcambionivel() {
    	cambionivel=false;
    }
    
    public boolean cambioNivel() {
    	return cambionivel;
    }
    
    public void display(Graphics2D g2, int dezplazamiento) {
        g2.drawImage(imagen_fondo,8 ,1280-imagen_fondo.getHeight(null), imagen_fondo.getWidth(null), imagen_fondo.getHeight(null), null);
        estaMuerto();
        colisiones();
        
      
        
        //System.out.println(ALTO_MUNDO);
      // System.out.println(heroe.getPosY());
       
        
        
        for(int i = 0; i < cantidadEscopetas; i++){
            if(!(escopetas[i].getFueChocado())){
                if(en_Rango((int)heroe.getPosY(), heroe.getWidth(),(int) escopetas[i].getPosY(), 1100)){
                    escopetas[i].setVisible(true);
                }
            }
            escopetas[i].display(g2);
        }
        
        //DIBUJA LOS POWERUPS TRSTIROS
        for(int i = 0; i < cantidadTresTiros; i++){
            if(!(trestiros[i].getFueChocado())){
                if(en_Rango((int)heroe.getPosY(), heroe.getWidth(),(int) trestiros[i].getPosY(), 1100)){
                    trestiros[i].setVisible(true);
                }
            }
            trestiros[i].display(g2);
        }
        
        //DIBUJA LOS POWERUPS AUTO
        for(int i = 0; i < cantidadAutos; i++){
            if(!(autos[i].getFueChocado())){
                if(en_Rango((int)heroe.getPosY(), heroe.getWidth(),(int) autos[i].getPosY(), 1100)){
                    autos[i].setVisible(true);
                }
            }
            autos[i].display(g2);
        }
        
      //DIBUJA LOS POWERUPS POW
        for(int i = 0; i < cantidadPows; i++){
            if(!(pows[i].getFueChocado())){
                if(en_Rango((int)heroe.getPosY(), heroe.getWidth(),(int) pows[i].getPosY(), 1100)){
                    pows[i].setVisible(true);
                }
            }
            pows[i].display(g2);
        }
        
        for (int i = 0; i < powsAvionesRojos.size(); i++) {
            Pow pow = powsAvionesRojos.get(i);
            if (!(pow.getFueChocado())) {
                if (en_Rango((int) heroe.getPosY(), heroe.getWidth(), (int) pow.getPosY(), 1100)) {
                    pow.setVisible(true);
                }
            }
            pow.display(g2);
        }
        
        
      //DIBUJA las estrellas
        for(int i = 0; i < cantidadEstrellas; i++){
            if(!(estrellas[i].getFueChocado())){
                if(en_Rango((int)heroe.getPosY(), heroe.getWidth(),(int) estrellas[i].getPosY(), 1100)){
                    estrellas[i].setVisible(true);
                }
            }
            estrellas[i].display(g2);
        }
        
        //DIBUJA las refuerzos
        for(int i = 0; i < cantidadRefuerzos; i++){
            if(!(refuerzos[i].getFueChocado())){
                if(en_Rango((int)heroe.getPosY(), heroe.getWidth(),(int) refuerzos[i].getPosY(), 1100)){
                    refuerzos[i].setVisible(true);
                }
            }
            refuerzos[i].display(g2);
        }
        
        
      //DIBUJA las SHELL
        for(int i = 0; i < cantidadMisiles; i++){
            if(!(misiles[i].getFueChocado())){
                if(en_Rango((int)heroe.getPosY(), heroe.getWidth(),(int) misiles[i].getPosY(), 1100)){
                    misiles[i].setVisible(true);
                }
            }
            misiles[i].display(g2);
        }

        
        if(numeroMapa == 1 || numeroMapa == 3) {
        for(int i = 0; i < cantidadbarcos; i++){
            if(en_Rango((int)heroe.getPosY(), heroe.getWidth(),(int) barcos[i].getPosY(), 1200)){
                barcos[i].setVisible(true);
                if(en_Rango((int)heroe.getPosX(), heroe.getWidth(),(int) barcos[i].getPosX(), 300)){
                    barcos[i].setEnMovimiento(!bm.getPausa());
                }
            }
            if(!(en_Rango((int)barcos[i].getPosX(), barcos[i].getWidth(),(int) heroe.getPosX(), 640))){
                barcos[i].setVisible(false);
                barcos[i].setEnMovimiento(false);
                

            
            }   
            if(heroe.getAtaque().activado() && en_Rango((int)heroe.getPosY(), heroe.getWidth(),(int) barcos[i].getPosY(), 1000) ) {
            	barcos[i].setEnMovimiento(false);
            	barcos[i].quitarVidaT(1);
            	barcos[i].quitarVidaT(3);
            	barcos[i].quitarVidaT(5);
            	jugador.sumarPuntajeJugador(40);
            }
            barcos[i].display(g2,heroe.getPosX(),heroe.getPosY(),ALTO_MUNDO,BAJO_MUNDO,bm.getPausa());
        }
        
        }
        
        
        //BOSS 1
        if(numeroMapa == 1) {
            boss.setVisible(true);
            if(en_Rango((int)heroe.getPosY(), heroe.getWidth(),(int) boss.getPosY(), 1000)){
            	boss.setEnMovimiento(!bm.getPausa());
            }
            
            if(heroe.getAtaque().activado()  && en_Rango((int)heroe.getPosY(), heroe.getWidth(),(int) boss.getPosY(), 1000) ) {
            	boss.setEnMovimiento(false);
            	boss.quitarVidaF(1);
            	boss.quitarVidaF(4);
            	boss.quitarVidaT(3);
            	boss.quitarVidaT(5);
            	jugador.sumarPuntajeJugador(40);
            }

        boss.display(g2,heroe.getPosX(),heroe.getPosY(),ALTO_MUNDO,BAJO_MUNDO,bm.getPausa());
        if(boss.Destruido() && bossmuerto==false) {
        	bm.getAdmin_sound().playSonido("destruccion_jefe", bm.getAdmin_sound().getact_sonido());
        	bossmuerto=true;
        }
        }
        
        //YAMATO
        if(numeroMapa == 3 ) { 
        	if(en_Rango((int)heroe.getPosY(), heroe.getWidth(),(int) yamato.getPosY(), 1700)){
        		yamato.setVisible(true);
            if(en_Rango((int)heroe.getPosX(), heroe.getWidth(),(int) yamato.getPosX(), 640)){
            	yamato.setEnMovimiento(!bm.getPausa());
            }
        }
        if(!(en_Rango((int)yamato.getPosX(), yamato.getWidth(),(int) heroe.getPosX(), 640))){
        	yamato.setVisible(false);
        	yamato.setEnMovimiento(false);
        }
        
        if(heroe.getAtaque().activado() && en_Rango((int)heroe.getPosY(), heroe.getWidth(),(int) yamato.getPosY(), 1500 )) {
        	yamato.setEnMovimiento(false);
        	yamato.quitarVidaF(1);
        	yamato.quitarVidaF(3);
        	yamato.quitarVidaF(8);
        	yamato.quitarVidaF(6);
        	yamato.quitarVidaT(4);
        	yamato.quitarVidaT(9);
        	yamato.quitarVidaT(1);
        	yamato.quitarVidaT(5);
        	jugador.sumarPuntajeJugador(40);
        }
        yamato.display(g2,heroe.getPosX(),heroe.getPosY(),ALTO_MUNDO,BAJO_MUNDO);
        if(yamato.Destruido() && yamatomuerto==false) {
        	yamatomuerto=true;
        	bm.getAdmin_sound().playSonido("destruccion_jefe", bm.getAdmin_sound().getact_sonido());
        }
        }
        //
        
//AVIONES
        
        for(int i = 0; i < cantidadAvionesKamikazes; i++){
            if(en_Rango((int)heroe.getPosY(), heroe.getWidth(),(int)avionesKamikazes[i].getPosY(), 1000)){
               avionesKamikazes[i].setVisible(true);
                if(en_Rango((int)heroe.getPosY(), heroe.getWidth(),(int)avionesKamikazes[i].getPosY(), 550)){
                	avionesKamikazes[i].setEnMovimiento(!bm.getPausa());
                }
            }
            if(heroe.getAtaque().activado()  && en_Rango((int)heroe.getPosY(), heroe.getWidth(),(int)avionesKamikazes[i].getPosY(), 440) ) {
            	avionesKamikazes[i].setFueChocado(true);
            	jugador.sumarPuntajeJugador(5);
            }
           avionesKamikazes[i].display(g2,heroe.getPosX(),heroe.getPosY(),ALTO_MUNDO,BAJO_MUNDO,bm.getPausa());
        }
        
        
        for (int i = 0; i < avionesKamikazesRojos.length; i++) {
            for (int j = 0; j < avionesKamikazesRojos[i].length; j++) {
                AvionKamikaze avionRojo = avionesKamikazesRojos[i][j];
                
                    avionRojo.setVisible(true);
                    if (en_Rango((int) heroe.getPosY(), heroe.getWidth(), (int) avionRojo.getPosY(), 400)) {
                    	
                        avionRojo.setEnMovimiento(!bm.getPausa());
                    }
                

                if ((int) avionRojo.getPosX() < 50 || (int) avionRojo.getPosX() > 500) { //CUANDO SE VA DE ANCHO SE BORRAN
                    avionRojo.setVisible(false);
                    avionRojo.setEnMovimiento(false);
               }

                avionRojo.display(g2,heroe.getPosX(),heroe.getPosY(),ALTO_MUNDO,BAJO_MUNDO,bm.getPausa());
            }
        }
        
        for(int i = 0; i < cantidadAvionesPequeños; i++){
            if(en_Rango((int)heroe.getPosY(), heroe.getWidth(),(int)avionesPequeños[i].getPosY(), 600)){
               avionesPequeños[i].setVisible(true);
                if(en_Rango((int)heroe.getPosY(), heroe.getWidth(),(int)avionesPequeños[i].getPosY(), 580)){
                	avionesPequeños[i].setEnMovimiento(!bm.getPausa());
                }
            }
            
            if(heroe.getAtaque().activado()  && en_Rango((int)heroe.getPosY(), heroe.getWidth(),(int)avionesPequeños[i].getPosY(), 440) ) {
            	avionesPequeños[i].setFueChocado(true);
            	jugador.sumarPuntajeJugador(2);
            }
           avionesPequeños[i].display(g2,heroe.getPosX(),heroe.getPosY(),ALTO_MUNDO,BAJO_MUNDO,bm.getPausa());
        }
        
        for(int i = 0; i < cantidadAvionesSemimedianos; i++){
        		
        	avionesSemimedianos[i].setEnMovimiento(false);
                avionesSemimedianos[i].setVisible(true);
               if(en_Rango((int)heroe.getPosY(), heroe.getWidth(),(int)avionesSemimedianos[i].getPosY(), 480))
                	avionesSemimedianos[i].setEnMovimiento(!bm.getPausa());
                
            avionesSemimedianos[i].display(g2,heroe.getPosX(),heroe.getPosY(),ALTO_MUNDO,BAJO_MUNDO,bm.getPausa());
        }
        
        for(int i = 0; i < cantidadAvionesMedianos; i++){
            
        	
                avionesMedianos[i].setVisible(true);
                if(en_Rango((int)heroe.getPosX(), heroe.getWidth(),(int) avionesMedianos[i].getPosY(), 300)) {
                	avionesMedianos[i].setEnMovimiento(!bm.getPausa());
                	
                } 
                avionesMedianos[i].setEnMovimiento(!bm.getPausa());
            avionesMedianos[i].display(g2,heroe.getPosX(),heroe.getPosY(),ALTO_MUNDO,BAJO_MUNDO,bm.getPausa());
        }
        
        
        
        if(heroe.getAtaque().activado()) {
        	heroe.getAtaque().display(g2,ALTO_MUNDO,BAJO_MUNDO);
        	ALTO_MUNDO= ALTO_MUNDO + 3;
            BAJO_MUNDO= BAJO_MUNDO + 3;
            if(heroe.getAtaque().getTipo() == AtaqueEspecial.TSUNAMI && heroe.getAtaque().getCont() == 1) {
            	bm.getAdmin_sound().playSonido("tsunami", bm.getAdmin_sound().getact_sonido());
            }else if(heroe.getAtaque().getTipo() == AtaqueEspecial.RELAMPAGO && heroe.getAtaque().getCont() == 1) {
            	bm.getAdmin_sound().playSonido("relampago", bm.getAdmin_sound().getact_sonido());
            }
        }
        
        if(!bm.getPausa() ) {
            ALTO_MUNDO= ALTO_MUNDO - 3;
            BAJO_MUNDO= BAJO_MUNDO - 3;
            arma.display(g2,ALTO_MUNDO,BAJO_MUNDO);
            }
        
        heroe.display(g2);
        //heroe.setInvulnerable(true);// PARA HACER INMORTAL AL JUGADOR 
        
        //PROBAR FUNCIONA A VECES (ES LA EXPLOSION DEL HEROE CUANDO MUERE)
        if(heroe.getEstadoActual() == Heroe.ESTADO_MURIENDO && heroemuerto==false ){
    		heroemuerto=true;
    		bm.getAdmin_sound().playSonido("explosion_muerte", bm.getAdmin_sound().getact_sonido());
    		}  
        
         
    }
    
    public boolean en_Rango(int Obj1_posX, int Obj1_ancho, int Obj2_posX, int rango){ //Cuando heroe se acerca a los objetos
        int aVerificar = (Obj2_posX - (Obj1_posX + Obj1_ancho));
        if(aVerificar < 0){
            aVerificar *= (-1);
        }
        return (aVerificar < rango);
    }
    
    public Heroe getHeroe(){
        return heroe;
    }
    
    public boolean colision(Rectangle r1, Rectangle r2){
        return r1.intersects(r2);
    }
    
    private void generarAreas(){
    	
    	
    	if(numeroMapa==0) {
        	heroe = new Heroe(this,this.energiaHeroe);
            arma = new Arma(Arma.MUNICION_ESTANDAR); 
            ataqueE= new AtaqueEspecial(AtaqueEspecial.RELAMPAGO); 
            heroe.setArma(arma);
            heroe.setAtaque(ataqueE);
    	}
    	
        heroe.setEnergia(energiaHeroe);
        heroe.setPosition(220,350);
        heroe.setEstadoActual(Heroe.ESTADO_QUIETO);
        
       
        
        switch(this.numeroMapa){
            case 0:
                generarArea1();
                break;
            case 1: 
            	heroe.setAtaque(new AtaqueEspecial(AtaqueEspecial.TSUNAMI));
            	generarArea2();
                break;
            case 2: 
            	heroe.setAtaque(new AtaqueEspecial(AtaqueEspecial.RELAMPAGO));
            	generarArea3();
                break;
            case 3: 
            	heroe.setAtaque(new AtaqueEspecial(AtaqueEspecial.TSUNAMI));
            	generarArea4();
                break;
        }
     
    }
      
    private void generarArea1(){ //PRIMER NIVEL AEREO
        setFondo(0);
        this.numeroMapa = 0;
        //heroe.setPosition(3430*2, POSICION_Y_PISO-2*(435-420));
        
      //DEFINICION Y GENERACION DE LAS POWERUPS ESCOPETA
       // this.cantidadEscopetas = 2;
//        escopetas = new Escopeta[this.cantidadEscopetas];
//        escopetas[0] = new Escopeta();
//        escopetas[0].setPosition(50, -300);   
//        
//        escopetas[1] = new Escopeta();
//        escopetas[1].setPosition(50, 350);  

        
      //DEFINICION Y GENERACION DE LAS POWERUPS TRESTIROS
        this.cantidadTresTiros = 3;
        trestiros = new TresTiros[this.cantidadTresTiros];
        trestiros[0] = new TresTiros();
        trestiros[0].setPosition(400, -480);      
        trestiros[1] = new TresTiros();
        trestiros[1].setPosition(400, -390);  
        trestiros[2] = new TresTiros();
        trestiros[2].setPosition(400, -6090);
        
        //DEFINICION Y GENERACION DE LAS POWERUPS AUTO
        this.cantidadAutos = 2;
        autos = new Auto[this.cantidadAutos];
        autos[0] = new Auto();
        autos[0].setPosition(150, -480);  
        autos[1] = new Auto();
        autos[1].setPosition(150, -3780);  
        
      //DEFINICION Y GENERACION DE LAS POWERUPS POW
//        this.cantidadPows = 1;
//        pows = new Pow[this.cantidadPows];
//        pows[0] = new Pow();
//        pows[0].setPosition(250, -530); 
        
        //DEFINICION Y GENERACION DE LAS POWERUPS ESTRELLA
        this.cantidadEstrellas = 4;
        estrellas = new Estrella[this.cantidadEstrellas];
        estrellas[0] = new Estrella();
        estrellas[0].setPosition(390, -1220);
        estrellas[1] = new Estrella();
        estrellas[1].setPosition(390, -2220);
        estrellas[2] = new Estrella();
        estrellas[2].setPosition(300, -4220);
        estrellas[3] = new Estrella();
        estrellas[3].setPosition(300, -5220);
        
        //DEFINICION Y GENERACION DE LAS POWERUPS ESTRELLA
        this.cantidadRefuerzos = 2;
        refuerzos = new Refuerzo[this.cantidadRefuerzos];
        refuerzos[0] = new Refuerzo();
        refuerzos[0].setPosition(100, -1030); 
        refuerzos[1] = new Refuerzo();
        refuerzos[1].setPosition(100, -3030);
        
        
      this.cantidadAvionesKamikazes = 16;
      avionesKamikazes = new AvionKamikaze[cantidadAvionesKamikazes];
      avionesKamikazes[0] = new AvionKamikaze(1);
      avionesKamikazes[0].setPosition(230,-1400);
      avionesKamikazes[1] = new AvionKamikaze(2);
      avionesKamikazes[1].setPosition(230,-1360);
      avionesKamikazes[2] = new AvionKamikaze(1);
      avionesKamikazes[2].setPosition(230,-1320);
      avionesKamikazes[3] = new AvionKamikaze(2);
      avionesKamikazes[3].setPosition(230,-1280);
      avionesKamikazes[4] = new AvionKamikaze(1);
      avionesKamikazes[4].setPosition(250,-4400);
      avionesKamikazes[5] = new AvionKamikaze(2);
      avionesKamikazes[5].setPosition(250,-4440);
      avionesKamikazes[6] = new AvionKamikaze(1);
      avionesKamikazes[6].setPosition(250,-4480);
      avionesKamikazes[7] = new AvionKamikaze(2);
      avionesKamikazes[7].setPosition(250,-4520);
      avionesKamikazes[8] = new AvionKamikaze(1);
      avionesKamikazes[8].setPosition(200,-2000);
      avionesKamikazes[9] = new AvionKamikaze(2);
      avionesKamikazes[9].setPosition(100,-2040);
      avionesKamikazes[10] = new AvionKamikaze(1);
      avionesKamikazes[10].setPosition(100,-3080);
      avionesKamikazes[11] = new AvionKamikaze(2);
      avionesKamikazes[11].setPosition(200,-3120);
      avionesKamikazes[12] = new AvionKamikaze(2);
      avionesKamikazes[12].setPosition(140,-3520);
      avionesKamikazes[13] = new AvionKamikaze(2);
      avionesKamikazes[13].setPosition(270,-3110);
      avionesKamikazes[14] = new AvionKamikaze(2);
      avionesKamikazes[14].setPosition(100,-5800);
      avionesKamikazes[15] = new AvionKamikaze(2);
      avionesKamikazes[15].setPosition(130,-5800);
        
      this.cantidadgruposAvionesRojos = 3;
      avionesKamikazesRojos = new AvionKamikaze[cantidadgruposAvionesRojos][];
      
      avionesKamikazesRojos[0] = new AvionKamikaze[5];
      avionesKamikazesRojos[0][0] = new AvionKamikaze(4);
      avionesKamikazesRojos[0][0].setPosition(490, -600);
      avionesKamikazesRojos[0][1] = new AvionKamikaze(4);
      avionesKamikazesRojos[0][1].setPosition(490, -630);
      avionesKamikazesRojos[0][2] = new AvionKamikaze(4);
      avionesKamikazesRojos[0][2].setPosition(490, -660);
      avionesKamikazesRojos[0][3] = new AvionKamikaze(4);
      avionesKamikazesRojos[0][3].setPosition(490, -690);
      avionesKamikazesRojos[0][4] = new AvionKamikaze(4);
      avionesKamikazesRojos[0][4].setPosition(490, -720);
      
      avionesKamikazesRojos[1] = new AvionKamikaze[5];
      
      avionesKamikazesRojos[1][0] = new AvionKamikaze(4);
      avionesKamikazesRojos[1][0].setPosition(490, -1600);
      avionesKamikazesRojos[1][1] = new AvionKamikaze(4);
      avionesKamikazesRojos[1][1].setPosition(490, -1630);
     avionesKamikazesRojos[1][2] = new AvionKamikaze(4);
     avionesKamikazesRojos[1][2].setPosition(490, -1660);
     avionesKamikazesRojos[1][3] = new AvionKamikaze(4);
      avionesKamikazesRojos[1][3].setPosition(490, -1690);
      avionesKamikazesRojos[1][4] = new AvionKamikaze(4);
      avionesKamikazesRojos[1][4].setPosition(490, -1720);
      
    avionesKamikazesRojos[2] = new AvionKamikaze[5];
      
      avionesKamikazesRojos[2][0] = new AvionKamikaze(4);
      avionesKamikazesRojos[2][0].setPosition(490, -2200);
      avionesKamikazesRojos[2][1] = new AvionKamikaze(4);
      avionesKamikazesRojos[2][1].setPosition(490, -2230);
     avionesKamikazesRojos[2][2] = new AvionKamikaze(4);
     avionesKamikazesRojos[2][2].setPosition(490, -2260);
     avionesKamikazesRojos[2][3] = new AvionKamikaze(4);
      avionesKamikazesRojos[2][3].setPosition(490, -2290);
      avionesKamikazesRojos[2][4] = new AvionKamikaze(4);
      avionesKamikazesRojos[2][4].setPosition(490, -2320);
      
 
      
      this.cantidadAvionesPequeños = 22;
      avionesPequeños = new AvionPequeño[cantidadAvionesPequeños];
      avionesPequeños[0] = new AvionPequeño(1);
      avionesPequeños[0].setPosition(200,-320);
      avionesPequeños[1] = new AvionPequeño(2);
      avionesPequeños[1].setPosition(0,-320);
      avionesPequeños[2] = new AvionPequeño(3);
      avionesPequeños[2].setPosition(280,-320);
      avionesPequeños[3] = new AvionPequeño(4);
      avionesPequeños[3].setPosition(480,-320);
      avionesPequeños[4] = new AvionPequeño(1);
      avionesPequeños[4].setPosition(200,-1820);
      avionesPequeños[5] = new AvionPequeño(1);
      avionesPequeños[5].setPosition(250,-2000);
      avionesPequeños[6] = new AvionPequeño(1);
      avionesPequeños[6].setPosition(200,-2000);
      avionesPequeños[7] = new AvionPequeño(1);
      avionesPequeños[7].setPosition(150,-2000);
      avionesPequeños[8] = new AvionPequeño(1);
      avionesPequeños[8].setPosition(100,-2000);
      avionesPequeños[9] = new AvionPequeño(1);
      avionesPequeños[9].setPosition(200,-4420);
      avionesPequeños[10] = new AvionPequeño(2);
      avionesPequeños[10].setPosition(0,-4500);
      avionesPequeños[11] = new AvionPequeño(3);
      avionesPequeños[11].setPosition(280,-5000);
      avionesPequeños[12] = new AvionPequeño(4);
      avionesPequeños[12].setPosition(480,-5000);
      avionesPequeños[13] = new AvionPequeño(1);
      avionesPequeños[13].setPosition(200,-5000);
      avionesPequeños[14] = new AvionPequeño(1);
      avionesPequeños[14].setPosition(250,-6000);
      avionesPequeños[15] = new AvionPequeño(1);
      avionesPequeños[15].setPosition(200,-6200);
      avionesPequeños[16] = new AvionPequeño(1);
      avionesPequeños[16].setPosition(150,-6200);
      avionesPequeños[17] = new AvionPequeño(1);
      avionesPequeños[17].setPosition(100,-6200);
      avionesPequeños[18] = new AvionPequeño(3);
      avionesPequeños[18].setPosition(280,-5020);
      avionesPequeños[19] = new AvionPequeño(3);
      avionesPequeños[19].setPosition(280,-4080);
      avionesPequeños[20] = new AvionPequeño(3);
      avionesPequeños[20].setPosition(280,-4040);
      avionesPequeños[21] = new AvionPequeño(3);
      avionesPequeños[21].setPosition(280,-4000);
      
      this.cantidadAvionesSemimedianos = 8;
      avionesSemimedianos = new AvionSemimediano[cantidadAvionesSemimedianos];
      avionesSemimedianos[0] = new AvionSemimediano(25,  -1000, 50, 0, false);
      avionesSemimedianos[1] = new AvionSemimediano(200, -900, 40, 0, true);
      avionesSemimedianos[2] = new AvionSemimediano(180, -1300, 100, 0, true);
      avionesSemimedianos[3] = new AvionSemimediano(250, -850, 100, 0, true);
      avionesSemimedianos[4] = new AvionSemimediano(180, -2700, 100, 0, false);
      avionesSemimedianos[5] = new AvionSemimediano(360, -3300, 100, 0, false);
      avionesSemimedianos[6] = new AvionSemimediano(200, -4000, 100, 0, true);
      avionesSemimedianos[7] = new AvionSemimediano(250, -44300, 100, 0, false);
      
      
      this.cantidadAvionesMedianos = 3;
      avionesMedianos = new AvionMediano[cantidadAvionesSemimedianos];
      avionesMedianos[0] = new AvionMediano(110, -450, 110, 0, false);
      avionesMedianos[1] = new AvionMediano(240, -750, 110, 0, false);
      avionesMedianos[2] = new AvionMediano(110, -1000, 105, 0, false);
    
   

    }
    
    private void generarArea2() { //PRIMER ACERCAMIENTO
    	setFondo(1);
        this.numeroMapa = 1;
        //heroe.setPosition(3430*2, POSICION_Y_PISO-2*(435-420));
        
        this.cantidadEstrellas = 2;
        estrellas = new Estrella[this.cantidadEstrellas];
        estrellas[0] = new Estrella();
        estrellas[0].setPosition(390, -1220);
        estrellas[1] = new Estrella();
        estrellas[1].setPosition(350, -4220);
        
        this.cantidadMisiles = 1;
        misiles = new Shell[this.cantidadMisiles];
        misiles[0] = new Shell();
        misiles[0].setPosition(40, -3500);
        
      //DEFINICION Y GENERACION DE LAS POWERUPS ESCOPETA
        this.cantidadEscopetas = 3;
        escopetas = new Escopeta[this.cantidadEscopetas];
        escopetas[0] = new Escopeta();
        escopetas[0].setPosition(-190, -280);   
        
        escopetas[1] = new Escopeta();
        escopetas[1].setPosition(-230, 280);  
        
        escopetas[2] = new Escopeta();
        escopetas[2].setPosition(-4930, 280); 
        
        this.cantidadPows = 1;
        pows = new Pow[this.cantidadPows];
        pows[0] = new Pow();
        pows[0].setPosition(310, -530); 
        
        
      // BARCOS Y BOS NO TOCAR YA ESTA LISTO
        
      this.cantidadbarcos = 2;
      barcos = new Barco[cantidadbarcos];
      barcos[0] = new Barco();
      barcos[0].setPosition(80,-750);
      
      barcos[1] = new Barco();
      barcos[1].setPosition(360,-1400);
      
      this.cantidadAvionesKamikazes = 8;
      avionesKamikazes = new AvionKamikaze[cantidadAvionesKamikazes];
      avionesKamikazes[0] = new AvionKamikaze(1);
      avionesKamikazes[0].setPosition(200,-1000);
      avionesKamikazes[1] = new AvionKamikaze(2);
      avionesKamikazes[1].setPosition(200,-1040);
      avionesKamikazes[2] = new AvionKamikaze(1);
      avionesKamikazes[2].setPosition(200,-1080);
      avionesKamikazes[3] = new AvionKamikaze(2);
      avionesKamikazes[3].setPosition(200,-1120);
      avionesKamikazes[4] = new AvionKamikaze(2);
      avionesKamikazes[4].setPosition(400,-1520);
      avionesKamikazes[5] = new AvionKamikaze(2);
      avionesKamikazes[5].setPosition(200,-1560);
      avionesKamikazes[6] = new AvionKamikaze(2);
      avionesKamikazes[6].setPosition(200,-1600);
      avionesKamikazes[7] = new AvionKamikaze(2);
      avionesKamikazes[7].setPosition(200,-1640);
      
      this.cantidadgruposAvionesRojos = 1;
      avionesKamikazesRojos = new AvionKamikaze[cantidadgruposAvionesRojos][];
      avionesKamikazesRojos[0] = new AvionKamikaze[5];
      avionesKamikazesRojos[0][0] = new AvionKamikaze(4);
      avionesKamikazesRojos[0][0].setPosition(100, -1000);
      avionesKamikazesRojos[0][1] = new AvionKamikaze(4);
      avionesKamikazesRojos[0][1].setPosition(140, 1030);
      avionesKamikazesRojos[0][2] = new AvionKamikaze(4);
      avionesKamikazesRojos[0][2].setPosition(180, 1060);
      avionesKamikazesRojos[0][3] = new AvionKamikaze(4);
      avionesKamikazesRojos[0][3].setPosition(220, 1090);
      avionesKamikazesRojos[0][4] = new AvionKamikaze(4);
      avionesKamikazesRojos[0][4].setPosition(260, 1120);
      
      
      
      this.cantidadAvionesPequeños = 10;
      avionesPequeños = new AvionPequeño[cantidadAvionesPequeños];
      avionesPequeños[0] = new AvionPequeño(1);
      avionesPequeños[0].setPosition(200,-320);
      avionesPequeños[1] = new AvionPequeño(2);
      avionesPequeños[1].setPosition(0,-320);
      avionesPequeños[2] = new AvionPequeño(3);
      avionesPequeños[2].setPosition(280,-320);
      avionesPequeños[3] = new AvionPequeño(4);
      avionesPequeños[3].setPosition(480,-320);
      avionesPequeños[4] = new AvionPequeño(1);
      avionesPequeños[4].setPosition(200,-1820);
      avionesPequeños[5] = new AvionPequeño(1);
      avionesPequeños[5].setPosition(250,-1520);
      avionesPequeños[6] = new AvionPequeño(1);
      avionesPequeños[6].setPosition(250,-4520);
      avionesPequeños[7] = new AvionPequeño(2);
      avionesPequeños[7].setPosition(200,-4520);
      
      avionesPequeños[8] = new AvionPequeño(2);
      avionesPequeños[8].setPosition(250,-3520);
      
      avionesPequeños[9] = new AvionPequeño(2);
      avionesPequeños[9].setPosition(200,-3520);
     
      this.cantidadAvionesSemimedianos = 4;
      avionesSemimedianos = new AvionSemimediano[cantidadAvionesSemimedianos];
      avionesSemimedianos[0] = new AvionSemimediano(25,  -250, 50, 0, false);
      avionesSemimedianos[1] = new AvionSemimediano(200, -500, 30, 0, true);
      avionesSemimedianos[2] = new AvionSemimediano(180, -2380, 80, 0, true);
      avionesSemimedianos[3] = new AvionSemimediano(250, -4000, 150, 0, true);
      //									   VALOR X / VALOR Y DONDE VA A APRECER
      //ANDA JUGANDO CON DIFERENTES RADIOS
      
      
      
      this.cantidadAvionesMedianos = 3;
      avionesMedianos = new AvionMediano[cantidadAvionesSemimedianos];
      avionesMedianos[0] = new AvionMediano(250, -150, 150, 0, false);
      avionesMedianos[1] = new AvionMediano(250, -1050, 150, 0, false);
      avionesMedianos[2] = new AvionMediano(250, -1150, 100, 0, false);
      //	                           VALOR X / VALOR Y DONDE VA A APRECER
      		//ANDA JUGANDO CON DIFERENTES RADIOS
      
      boss = new Bos();
      boss.setPosition(240, -4800);
      
       
       //
    	
    }
    
    private void generarArea3() { // SEGUNDO NIVEL AEREO
    	setFondo(2);
        this.numeroMapa = 2;
        //heroe.setPosition(3430*2, POSICION_Y_PISO-2*(435-420));
        
      //DEFINICION Y GENERACION DE LAS POWERUPS ESCOPETA
        this.cantidadEscopetas = 2;
        escopetas = new Escopeta[this.cantidadEscopetas];
        escopetas[0] = new Escopeta();
        escopetas[0].setPosition(190, -2280);   
        
        escopetas[1] = new Escopeta();
        escopetas[1].setPosition(230, -1280);
        
        this.cantidadEstrellas = 4;
        estrellas = new Estrella[this.cantidadEstrellas];
        estrellas[0] = new Estrella();
        estrellas[0].setPosition(390, -1220);
        estrellas[1] = new Estrella();
        estrellas[1].setPosition(390, -2220);
        estrellas[2] = new Estrella();
        estrellas[2].setPosition(300, -4220);
        estrellas[3] = new Estrella();
        estrellas[3].setPosition(300, -5220);
        
        this.cantidadAutos = 2;
        autos = new Auto[this.cantidadAutos];
        autos[0] = new Auto();
        autos[0].setPosition(150, -480);  
        autos[1] = new Auto();
        autos[1].setPosition(150, -3780);  
        
        
        this.cantidadRefuerzos = 2;
        refuerzos = new Refuerzo[this.cantidadRefuerzos];
        refuerzos[0] = new Refuerzo();
        refuerzos[0].setPosition(100, -1030); 
        refuerzos[1] = new Refuerzo();
        refuerzos[1].setPosition(100, -3151);
        
        this.cantidadAvionesKamikazes = 16;
        avionesKamikazes = new AvionKamikaze[cantidadAvionesKamikazes];
        avionesKamikazes[0] = new AvionKamikaze(1);
        avionesKamikazes[0].setPosition(200,-1000);
        avionesKamikazes[1] = new AvionKamikaze(2);
        avionesKamikazes[1].setPosition(200,-1040);
        avionesKamikazes[2] = new AvionKamikaze(1);
        avionesKamikazes[2].setPosition(200,-1080);
        avionesKamikazes[3] = new AvionKamikaze(2);
        avionesKamikazes[3].setPosition(200,-1120);
        avionesKamikazes[4] = new AvionKamikaze(2);
        avionesKamikazes[4].setPosition(240,-3120);
        avionesKamikazes[5] = new AvionKamikaze(2);
        avionesKamikazes[5].setPosition(270,-3110);
        avionesKamikazes[6] = new AvionKamikaze(2);
        avionesKamikazes[6].setPosition(300,-3100);
        avionesKamikazes[7] = new AvionKamikaze(2);
        avionesKamikazes[7].setPosition(330,-2990);
        avionesKamikazes[8] = new AvionKamikaze(1);
        avionesKamikazes[8].setPosition(200,-2000);
        avionesKamikazes[9] = new AvionKamikaze(2);
        avionesKamikazes[9].setPosition(100,-2040);
        avionesKamikazes[10] = new AvionKamikaze(1);
        avionesKamikazes[10].setPosition(100,-3080);
        avionesKamikazes[11] = new AvionKamikaze(2);
        avionesKamikazes[11].setPosition(200,-3120);
        avionesKamikazes[12] = new AvionKamikaze(2);
        avionesKamikazes[12].setPosition(140,-3520);
        avionesKamikazes[13] = new AvionKamikaze(2);
        avionesKamikazes[13].setPosition(270,-3110);
        avionesKamikazes[14] = new AvionKamikaze(2);
        avionesKamikazes[14].setPosition(100,-5800);
        avionesKamikazes[15] = new AvionKamikaze(2);
        avionesKamikazes[15].setPosition(130,-5800);
        
        this.cantidadAvionesPequeños = 22;
        avionesPequeños = new AvionPequeño[cantidadAvionesPequeños];
        avionesPequeños[0] = new AvionPequeño(1);
        avionesPequeños[0].setPosition(200,-320);
        avionesPequeños[1] = new AvionPequeño(2);
        avionesPequeños[1].setPosition(0,-320);
        avionesPequeños[2] = new AvionPequeño(3);
        avionesPequeños[2].setPosition(280,-320);
        avionesPequeños[3] = new AvionPequeño(4);
        avionesPequeños[3].setPosition(480,-320);
        avionesPequeños[4] = new AvionPequeño(1);
        avionesPequeños[4].setPosition(200,-1820);
        avionesPequeños[5] = new AvionPequeño(1);
        avionesPequeños[5].setPosition(250,-2000);
        avionesPequeños[6] = new AvionPequeño(1);
        avionesPequeños[6].setPosition(200,-2000);
        avionesPequeños[7] = new AvionPequeño(1);
        avionesPequeños[7].setPosition(150,-2000);
        avionesPequeños[8] = new AvionPequeño(2);
        avionesPequeños[8].setPosition(100,-2000);
        avionesPequeños[9] = new AvionPequeño(2);
        avionesPequeños[9].setPosition(200,-4420);
        avionesPequeños[10] = new AvionPequeño(2);
        avionesPequeños[10].setPosition(0,-4500);
        avionesPequeños[11] = new AvionPequeño(3);
        avionesPequeños[11].setPosition(280,-5000);
        avionesPequeños[12] = new AvionPequeño(4);
        avionesPequeños[12].setPosition(480,-5000);
        avionesPequeños[13] = new AvionPequeño(1);
        avionesPequeños[13].setPosition(200,-5000);
        avionesPequeños[14] = new AvionPequeño(1);
        avionesPequeños[14].setPosition(250,-6000);
        avionesPequeños[15] = new AvionPequeño(1);
        avionesPequeños[15].setPosition(200,-5200);
        avionesPequeños[16] = new AvionPequeño(3);
        avionesPequeños[16].setPosition(150,-6200);
        avionesPequeños[17] = new AvionPequeño(1);
        avionesPequeños[17].setPosition(100,-6200);
        avionesPequeños[18] = new AvionPequeño(3);
        avionesPequeños[18].setPosition(280,-5020);
        avionesPequeños[19] = new AvionPequeño(3);
        avionesPequeños[19].setPosition(280,-4080);
        avionesPequeños[20] = new AvionPequeño(3);
        avionesPequeños[20].setPosition(280,-4040);
        avionesPequeños[21] = new AvionPequeño(3);
        avionesPequeños[21].setPosition(280,-4000);
        
        this.cantidadAvionesSemimedianos = 5;
        avionesSemimedianos = new AvionSemimediano[cantidadAvionesSemimedianos];
        avionesSemimedianos[0] = new AvionSemimediano(250, -1080, 50, 0, false);
        avionesSemimedianos[1] = new AvionSemimediano(200, -500, 10, 0, true);
        avionesSemimedianos[2] = new AvionSemimediano(250, -2250, 110, 0, true);
        avionesSemimedianos[3] = new AvionSemimediano(250, -3050, 100, 0, true);
        avionesSemimedianos[4] = new AvionSemimediano(250, -4000, 90, 0, false);
        
        this.cantidadAvionesMedianos = 5;
        avionesMedianos = new AvionMediano[cantidadAvionesSemimedianos];
        avionesMedianos[0] = new AvionMediano(250, -150, 150, 0, false);
        avionesMedianos[1] = new AvionMediano(250, -1050, 150, 0, false);
        avionesMedianos[2] = new AvionMediano(250, -3050, 120, 0, false);
        avionesMedianos[3] = new AvionMediano(250, -4050, 180, 0, false);
        avionesMedianos[4] = new AvionMediano(250, -5050, 50, 0, true);
     
    	
        this.cantidadMisiles = 1;
        misiles = new Shell[this.cantidadMisiles];
        misiles[0] = new Shell();
        misiles[0].setPosition(30, 210); 
        
        this.cantidadgruposAvionesRojos = 3;
        avionesKamikazesRojos = new AvionKamikaze[cantidadgruposAvionesRojos][];
        
        avionesKamikazesRojos[0] = new AvionKamikaze[5];
        avionesKamikazesRojos[0][0] = new AvionKamikaze(4);
        avionesKamikazesRojos[0][0].setPosition(490, -600);
        avionesKamikazesRojos[0][1] = new AvionKamikaze(4);
        avionesKamikazesRojos[0][1].setPosition(490, -630);
        avionesKamikazesRojos[0][2] = new AvionKamikaze(4);
        avionesKamikazesRojos[0][2].setPosition(490, -660);
        avionesKamikazesRojos[0][3] = new AvionKamikaze(4);
        avionesKamikazesRojos[0][3].setPosition(490, -690);
        avionesKamikazesRojos[0][4] = new AvionKamikaze(4);
        avionesKamikazesRojos[0][4].setPosition(490, -720);
        
        avionesKamikazesRojos[1] = new AvionKamikaze[5];
        
        avionesKamikazesRojos[1][0] = new AvionKamikaze(4);
        avionesKamikazesRojos[1][0].setPosition(490, -1600);
        avionesKamikazesRojos[1][1] = new AvionKamikaze(4);
        avionesKamikazesRojos[1][1].setPosition(490, -1630);
       avionesKamikazesRojos[1][2] = new AvionKamikaze(4);
       avionesKamikazesRojos[1][2].setPosition(490, -1660);
       avionesKamikazesRojos[1][3] = new AvionKamikaze(4);
        avionesKamikazesRojos[1][3].setPosition(490, -1690);
        avionesKamikazesRojos[1][4] = new AvionKamikaze(4);
        avionesKamikazesRojos[1][4].setPosition(490, -1720);
        
      avionesKamikazesRojos[2] = new AvionKamikaze[5];
        
        avionesKamikazesRojos[2][0] = new AvionKamikaze(4);
        avionesKamikazesRojos[2][0].setPosition(490, -2200);
        avionesKamikazesRojos[2][1] = new AvionKamikaze(4);
        avionesKamikazesRojos[2][1].setPosition(490, -2230);
       avionesKamikazesRojos[2][2] = new AvionKamikaze(4);
       avionesKamikazesRojos[2][2].setPosition(490, -2260);
       avionesKamikazesRojos[2][3] = new AvionKamikaze(4);
        avionesKamikazesRojos[2][3].setPosition(490, -2290);
        avionesKamikazesRojos[2][4] = new AvionKamikaze(4);
        avionesKamikazesRojos[2][4].setPosition(490, -2320);
        
    }
    
    private void generarArea4() { //SEGUNDO ACERCAMIENTO Y YAMATO
    	setFondo(1);
        this.numeroMapa = 3;
        //heroe.setPosition(3430*2, POSICION_Y_PISO-2*(435-420));
        
      //DEFINICION Y GENERACION DE LAS POWERUPS ESCOPETA
        this.cantidadEscopetas = 2;
        escopetas = new Escopeta[this.cantidadEscopetas];
        escopetas[0] = new Escopeta();
        escopetas[0].setPosition(190, 280);   
        
        escopetas[1] = new Escopeta();
        escopetas[1].setPosition(230, 280);  
        
        this.cantidadEstrellas = 1;
        estrellas = new Estrella[this.cantidadEstrellas];
        estrellas[0] = new Estrella();
        estrellas[0].setPosition(390, -1030); 
        
        this.cantidadgruposAvionesRojos = 2;
        avionesKamikazesRojos = new AvionKamikaze[cantidadgruposAvionesRojos][];
        
        avionesKamikazesRojos[0] = new AvionKamikaze[5];
        avionesKamikazesRojos[0][0] = new AvionKamikaze(4);
        avionesKamikazesRojos[0][0].setPosition(490, -600);
        avionesKamikazesRojos[0][1] = new AvionKamikaze(4);
        avionesKamikazesRojos[0][1].setPosition(490, -630);
        avionesKamikazesRojos[0][2] = new AvionKamikaze(4);
        avionesKamikazesRojos[0][2].setPosition(490, -660);
        avionesKamikazesRojos[0][3] = new AvionKamikaze(4);
        avionesKamikazesRojos[0][3].setPosition(490, -690);
        avionesKamikazesRojos[0][4] = new AvionKamikaze(4);
        avionesKamikazesRojos[0][4].setPosition(490, -720);
        
        avionesKamikazesRojos[1] = new AvionKamikaze[5];
        
        avionesKamikazesRojos[1][0] = new AvionKamikaze(4);
        avionesKamikazesRojos[1][0].setPosition(490, -1600);
        avionesKamikazesRojos[1][1] = new AvionKamikaze(4);
        avionesKamikazesRojos[1][1].setPosition(490, -1630);
       avionesKamikazesRojos[1][2] = new AvionKamikaze(4);
       avionesKamikazesRojos[1][2].setPosition(490, -1660);
       avionesKamikazesRojos[1][3] = new AvionKamikaze(4);
        avionesKamikazesRojos[1][3].setPosition(490, -1690);
        avionesKamikazesRojos[1][4] = new AvionKamikaze(4);
        avionesKamikazesRojos[1][4].setPosition(490, -1720);
        
        
        // BARCOS Y BOS NO TOCAR YA ESTA LISTO
        
        this.cantidadbarcos = 4;
        barcos = new Barco[cantidadbarcos];
        barcos[0] = new Barco();
        barcos[0].setPosition(200,-320);
        
        barcos[1] = new Barco();
        barcos[1].setPosition(360,-1450);
        
        barcos[2] = new Barco();
        barcos[2].setPosition(80,-1880);
        
        barcos[3] = new Barco();
        barcos[3].setPosition(280,-2000);
        
        
        this.cantidadAvionesPequeños = 9;
        avionesPequeños = new AvionPequeño[cantidadAvionesPequeños];
        avionesPequeños[0] = new AvionPequeño(1);
        avionesPequeños[0].setPosition(200,-320);
        avionesPequeños[1] = new AvionPequeño(2);
        avionesPequeños[1].setPosition(0,-820);
        avionesPequeños[2] = new AvionPequeño(3);
        avionesPequeños[2].setPosition(280,-1120);
        avionesPequeños[3] = new AvionPequeño(4);
        avionesPequeños[3].setPosition(480,-1120);
        avionesPequeños[4] = new AvionPequeño(1);
        avionesPequeños[4].setPosition(200,-1820);
        avionesPequeños[5] = new AvionPequeño(1);
        avionesPequeños[5].setPosition(250,-520);
        avionesPequeños[6] = new AvionPequeño(1);
        avionesPequeños[6].setPosition(250,-3100);
        avionesPequeños[7] = new AvionPequeño(1);
        avionesPequeños[7].setPosition(250,-5520);
        avionesPequeños[8] = new AvionPequeño(1);
        avionesPequeños[8].setPosition(200,-5520);
        
        
        this.cantidadAvionesSemimedianos = 3;
        avionesSemimedianos = new AvionSemimediano[cantidadAvionesSemimedianos];
        avionesSemimedianos[0] = new AvionSemimediano(250, -580, 50, 0, false);
        avionesSemimedianos[1] = new AvionSemimediano(250, -2250, 110, 0, true);
        avionesSemimedianos[2] = new AvionSemimediano(250, -4250, 70, 0, true);
        
        
        
        this.cantidadAvionesMedianos = 2;
        avionesMedianos = new AvionMediano[cantidadAvionesSemimedianos];
        avionesMedianos[0] = new AvionMediano(250, -450, 150, 0, false);
        avionesMedianos[1] = new AvionMediano(250, -1250, 100, 0, true);
        
        
        
        yamato = new Yamato();
        yamato.setPosition(240, -2900);
   
        
        //
    	
    }
    
    public void colisiones(){
        if(this.heroe.getEstadoActual() != Heroe.ESTADO_MURIENDO){
        	
        	//CONTROLA SI CHOCO CON EL POWERUP ESCOPETA
            for(int i = 0; i < this.cantidadEscopetas; i++){
                if(escopetas[i].isVisible()){
                    if(this.heroe.getBordes().intersects(escopetas[i].getBordes())){
                    	heroe.quitRafaga();
                        escopetas[i].setFueChocado(true);
                        
                        if(heroe.getArma().getTMunicion() == Arma.MUNICION_SHOTGUN || heroe.getArma().getTMunicion() == Arma.MUNICION_SHOTGUN_MEJORADO) {
                        	heroe.getArma().setMunicion(Arma.MUNICION_SHOTGUN_MEJORADO);
                        	bm.incrementTime(escopetas[i].getSegundosExtra());
                        }else{heroe.getArma().setMunicion(Arma.MUNICION_SHOTGUN);// FUTURO POWER UP
                    		heroe.setPowerup();
                    		heroe.quitRafaga(); // para sacar lo del boton mantenido
                    		bm.incrementTime(escopetas[i].getSegundosExtra());      
                    	}     
                        
                        jugador.sumarPuntajeJugador(escopetas[i].getPuntos());
                        bm.getAdmin_sound().playSonido("power_up", bm.getAdmin_sound().getact_sonido());
                    }
                }
            } 
            
        	//CONTROLA SI CHOCO CON EL POWERUP TRESTIROS
            for(int i = 0; i < this.cantidadTresTiros; i++){
                if(trestiros[i].isVisible()){
                    if(this.heroe.getBordes().intersects(trestiros[i].getBordes())){
                        trestiros[i].setFueChocado(true);
                        heroe.quitRafaga();
                        if(heroe.getArma().getTMunicion() == Arma.MUNICION_TRI) {
                        	bm.incrementTime(trestiros[i].getSegundosExtra());                  
                        }else {																//CONTROLA LO DE SUMAR EL TIEMPO SI YA TIENE EL POWERUP
                        	heroe.getArma().setMunicion(Arma.MUNICION_TRI);// FUTURO POWER UP
                        	heroe.setPowerup();
                        	heroe.quitRafaga(); // para sacar lo del boton mantenido
                        	bm.incrementTime(trestiros[i].getSegundosExtra()); 
                        }
                        
                        jugador.sumarPuntajeJugador(trestiros[i].getPuntos());
                        bm.getAdmin_sound().playSonido("power_up", bm.getAdmin_sound().getact_sonido());
                    }
                }
            } 
            
        	//CONTROLA SI CHOCO CON EL POWERUP MISLES
            for(int i = 0; i < this.cantidadMisiles; i++){
                if(misiles[i].isVisible()){
                    if(this.heroe.getBordes().intersects(misiles[i].getBordes())){
                        misiles[i].setFueChocado(true);
                        
                        if(heroe.getArma().getTMunicion() == Arma.MUNICION_SHELL) {
                        	bm.incrementTime(misiles[i].getSegundosExtra());                  
                        }else {																//CONTROLA LO DE SUMAR EL TIEMPO SI YA TIENE EL POWERUP
                        	heroe.getArma().setMunicion(Arma.MUNICION_SHELL);// FUTURO POWER UP
                        	heroe.setPowerup();
                        	heroe.quitRafaga(); // para sacar lo del boton mantenido
                        	bm.incrementTime(misiles[i].getSegundosExtra()); 
                        	heroe.setRafaga();
                        }
                        
                        jugador.sumarPuntajeJugador(misiles[i].getPuntos());
                        bm.getAdmin_sound().playSonido("power_up", bm.getAdmin_sound().getact_sonido());
                    }
                }
            } 
            
            for(int i = 0; i < this.cantidadRefuerzos; i++){
                if(refuerzos[i].isVisible()){
                    if(this.heroe.getBordes().intersects(refuerzos[i].getBordes())){
                        refuerzos[i].setFueChocado(true);
                        heroe.setRefuerzos();
                        heroe.quitRafaga();
                        if(heroe.getArma().getTMunicion() == Arma.MUNICION_SHELL)
                        	heroe.notPowerup();
                        
                        jugador.sumarPuntajeJugador(refuerzos[i].getPuntos());
                        bm.getAdmin_sound().playSonido("power_up", bm.getAdmin_sound().getact_sonido());
                    }
                }
            } 
            
            for(int i = 0; i < this.cantidadEstrellas; i++){
                if(estrellas[i].isVisible()){
                    if(this.heroe.getBordes().intersects(estrellas[i].getBordes())){
                        estrellas[i].setFueChocado(true);
                        heroe.setEnergia(100);
                        jugador.sumarPuntajeJugador(estrellas[i].getPuntos());
                        bm.getAdmin_sound().playSonido("power_up", bm.getAdmin_sound().getact_sonido());
                    }
                }
            } 
            
        	//CONTROLA SI CHOCO CON EL POWERUP POW
            for(int i = 0; i < this.cantidadPows; i++){
                if(pows[i].isVisible()){
                    if(this.heroe.getBordes().intersects(pows[i].getBordes())){
                        pows[i].setFueChocado(true);
                        
                        if(pows[i].isPow()) {
                        	heroe.setEnergia(heroe.getEnergia() + 10);  //ENERGIA QUE DA EL POW           
                        }else {																//CONTROLA LO DE SUMAR EL TIEMPO SI YA TIENE EL POWERUP
                        	 switch(pows[i].getTipo()){
                        		 case 4:
                        			 heroe.quitRafaga();
                        			 bm.incrementTime(pows[i].getSegundosExtra());
                                     heroe.getArma().setMunicion(Arma.MUNICION_AUTO);// FUTURO POWER UP
                                     jugador.sumarPuntajeJugador(pows[i].getPuntos());
                                     heroe.setPowerup();
                                     bm.getAdmin_sound().playSonido("power_up", bm.getAdmin_sound().getact_sonido());
                        			 break;
                        		 case 5:
                        			 heroe.quitRafaga();
                        			 if(heroe.getArma().getTMunicion() == Arma.MUNICION_TRI) {
                                     	bm.incrementTime(pows[i].getSegundosExtra());                  
                                     }else {																//CONTROLA LO DE SUMAR EL TIEMPO SI YA TIENE EL POWERUP
                                     	heroe.getArma().setMunicion(Arma.MUNICION_TRI);// FUTURO POWER UP
                                     	heroe.setPowerup();
                                     	bm.incrementTime(pows[i].getSegundosExtra()); 
                                     }
                        			 break;
                        		 case 6:
                        			 heroe.quitRafaga();
                        			 if(heroe.getArma().getTMunicion() == Arma.MUNICION_SHOTGUN) {
                                     	heroe.getArma().setMunicion(Arma.MUNICION_SHOTGUN_MEJORADO);
                                     	bm.incrementTime(pows[i].getSegundosExtra());
                                     	
                                     }else{heroe.getArma().setMunicion(Arma.MUNICION_SHOTGUN);// FUTURO POWER UP
                                 		heroe.setPowerup();
                                 		bm.incrementTime(pows[i].getSegundosExtra());      
                                 	}    
                        			 break;
                        	 	}
                        }
                        
                        
                        //jugador.sumarPuntajeJugador(trestiros[i].getPuntos());
                       // bm.getAdmin_sound().playSonido("power_up", bm.getAdmin_sound().getact_sonido());
                    }
                    
                    if(ataqueHeroe(pows[i].getBordes())){
                        pows[i].fuedisparado();
                       
                    }
                }
            }
            
          //CONTROLA SI CHOCO CON EL POWERUP AUTO
            for(int i = 0; i < this.cantidadAutos; i++){
                if(autos[i].isVisible()){
                    if(this.heroe.getBordes().intersects(autos[i].getBordes())){
                        bm.incrementTime(autos[i].getSegundosExtra());
                        heroe.quitRafaga();
                        autos[i].setFueChocado(true);
                        heroe.setPowerup();
                        heroe.getArma().setMunicion(3);// FUTURO POWER UP
                        jugador.sumarPuntajeJugador(autos[i].getPuntos());
                        bm.getAdmin_sound().playSonido("power_up", bm.getAdmin_sound().getact_sonido());
                    }
                }
            } 
                      

            //COLISIONES CON LOS AVIONES
            
            for(int i = 0; i < this.cantidadAvionesKamikazes; i++){
                if(avionesKamikazes[i].isVisible() && (!(avionesKamikazes[i].getFueChocado()))){
                	
                	
                    if(this.heroe.getBordes().intersects(avionesKamikazes[i].getBordes())  && (!(heroe.isInvulnerable()))){
                        if(!this.heroe.getRefuerzos()){
                          //  this.heroe.setEstadoActual(Heroe.ESTADO_MURIENDO);
                            //bm.getAdmin_sound().playSonido("Area1", false);
                            //bm.getAdmin_sound().playSonido("Area2", false);
                            bm.getAdmin_sound().playSonido("explota_aviones", bm.getAdmin_sound().getact_sonido());
                        	heroe.setEnergia(heroe.getEnergia()-15);
                        	avionesKamikazes[i].setFueChocado(true);
                        } else
                        	{
                        	avionesKamikazes[i].setFueChocado(true);
                            heroe.setEnergia(heroe.getEnergia()-15);
                            jugador.sumarPuntajeJugador(avionesKamikazes[i].puntos);
                        	}
                        
                    } else if(this.heroe.getBordes().intersects(avionesKamikazes[i].getBordes()) && (heroe.isInvulnerable())) //CUANDO ES INVENCIBLE 
                    	{
                    	avionesKamikazes[i].setFueChocado(true);
                        jugador.sumarPuntajeJugador(avionesKamikazes[i].puntos);
                        bm.getAdmin_sound().playSonido("explota_aviones", bm.getAdmin_sound().getact_sonido());
                    	}
                    
                    if(this.heroe.getBordesR().intersects(avionesKamikazes[i].getBordes()) || this.heroe.getBordesR2().intersects(avionesKamikazes[i].getBordes()) && heroe.getRefuerzos()) { //CUANDO CHOCA CON LOS REFUERZOS
                    	avionesKamikazes[i].setFueChocado(true);
                    	heroe.ChocoRefuezo();
                    }
                    
                    //CONTROLA EL IMPACTO DE MUNICION CONTRA LOS AVIONES KAMIKAZES
                    if(ataqueHeroe(avionesKamikazes[i].getBordes())){
                    	avionesKamikazes[i].setFueChocado(true);
                        			
                        
                        jugador.sumarPuntajeJugador(avionesKamikazes[i].puntos+3500);
                        bm.getAdmin_sound().playSonido("explota_aviones", bm.getAdmin_sound().getact_sonido());
                    }
                    
                    //CONTROLA LA MUNICION DEL AVION KAMIKAZE CONTRA EL JUGADOR
                    if(ataqueEnemigo(heroe.getBordes(),avionesKamikazes[i].getarma()) && (!(heroe.isInvulnerable()))){
                        //spiders[i].setFueChocado(true);
                        //			
                    	heroe.setEnergia(heroe.getEnergia()-20);
                        bm.getAdmin_sound().playSonido("explota_aviones", bm.getAdmin_sound().getact_sonido());
                    }
                            
                }
            }
            
            
            for (int i = 0; i < avionesKamikazesRojos.length; i++) {
           	 algunAvionRojoSinChocar = false;
           	 //
               for (int j = 0; j < avionesKamikazesRojos[i].length; j++) {
                   AvionKamikaze avionRojo = avionesKamikazesRojos[i][j];

                   if (avionRojo.isVisible() && !avionRojo.getFueChocado()) {
                	   
                	  
                	   
                       if (heroe.getBordes().intersects(avionRojo.getBordes()) && !heroe.isInvulnerable()) {
                           if (!heroe.getRefuerzos()) {
                               heroe.setEnergia(heroe.getEnergia() - 15);
                               avionRojo.setFueChocado(true);
                           } else {
                               avionRojo.setFueChocado(true);
                               heroe.setEnergia(heroe.getEnergia() - 15);
                               jugador.sumarPuntajeJugador(avionRojo.puntos);
                           }
                       } else if (heroe.getBordes().intersects(avionRojo.getBordes()) && heroe.isInvulnerable()) {
                           avionRojo.setFueChocado(true);
                           jugador.sumarPuntajeJugador(avionRojo.puntos);
                           bm.getAdmin_sound().playSonido("explota_aviones", bm.getAdmin_sound().getact_sonido());
                       }

                       if ((heroe.getBordesR().intersects(avionRojo.getBordes()) || heroe.getBordesR2().intersects(avionRojo.getBordes())) && heroe.getRefuerzos()) {
                           avionRojo.setFueChocado(true);
                           heroe.ChocoRefuezo();
                       }

                       // CONTROLA EL IMPACTO DE MUNICIÓN CONTRA LOS AVIONES KAMIKAZES ROJOS
                       if (ataqueHeroe(avionRojo.getBordes())) {
                           avionRojo.setFueChocado(true);
                           
                           jugador.sumarPuntajeJugador(avionRojo.puntos);
                           bm.getAdmin_sound().playSonido("explota_aviones", bm.getAdmin_sound().getact_sonido());
                       }
                       
                       if(avionRojo.fueContado())
                		   powCreado[i]++;
                       
                       // Verificar si algún avión del grupo no ha sido chocado
                   }
                   
                   if (powCreado[i]  == 5 ) {
                       Pow pow1 = new Pow();
                       powsAvionesRojos.add(pow1);
                       pow1.setPosition(avionRojo.getPosX(), avionRojo.getPosY());
                       powCreado[i] = 0;
                   }
               }
               
           }
            
        	//CONTROLA SI CHOCO CON EL POWERUP de los kamicazes
            for(int i = 0; i < this.powsAvionesRojos.size(); i++){
                if(powsAvionesRojos.get(i).isVisible()){
                    if(this.heroe.getBordes().intersects(powsAvionesRojos.get(i).getBordes())){
                    	powsAvionesRojos.get(i).setFueChocado(true);
                        
                        if(powsAvionesRojos.get(i).isPow()) {
                        	heroe.setEnergia(heroe.getEnergia() + 40);  //ENERGIA QUE DA EL POW           
                        }else {																//CONTROLA LO DE SUMAR EL TIEMPO SI YA TIENE EL POWERUP
                        	 switch(powsAvionesRojos.get(i).getTipo()){
                        		 case 4:
                        			 bm.incrementTime(powsAvionesRojos.get(i).getSegundosExtra());
                                     heroe.getArma().setMunicion(Arma.MUNICION_AUTO);// FUTURO POWER UP
                                     jugador.sumarPuntajeJugador(powsAvionesRojos.get(i).getPuntos());
                                     heroe.setPowerup();
                                     bm.getAdmin_sound().playSonido("power_up", bm.getAdmin_sound().getact_sonido());
                        			 break;
                        		 case 5:
                        			 if(heroe.getArma().getTMunicion() == Arma.MUNICION_TRI) {
                                     	bm.incrementTime(powsAvionesRojos.get(i).getSegundosExtra());                  
                                     }else {																//CONTROLA LO DE SUMAR EL TIEMPO SI YA TIENE EL POWERUP
                                     	heroe.getArma().setMunicion(Arma.MUNICION_TRI);// FUTURO POWER UP
                                     	heroe.setPowerup();
                                     	bm.incrementTime(powsAvionesRojos.get(i).getSegundosExtra()); 
                                     }
                        			 break;
                        		 case 6:
                        			 if(heroe.getArma().getTMunicion() == Arma.MUNICION_SHOTGUN) {
                                     	heroe.getArma().setMunicion(Arma.MUNICION_SHOTGUN_MEJORADO);
                                     	bm.incrementTime(powsAvionesRojos.get(i).getSegundosExtra());
                                     	
                                     }else{heroe.getArma().setMunicion(Arma.MUNICION_SHOTGUN);// FUTURO POWER UP
                                 		heroe.setPowerup();
                                 		bm.incrementTime(powsAvionesRojos.get(i).getSegundosExtra());      
                                 	}    
                        			 break;
                        	 	}
                        }
                        
                        
                        //jugador.sumarPuntajeJugador(trestiros[i].getPuntos());
                       // bm.getAdmin_sound().playSonido("power_up", bm.getAdmin_sound().getact_sonido());
                    }
                    
                    if(ataqueHeroe(powsAvionesRojos.get(i).getBordes())){
                    	powsAvionesRojos.get(i).fuedisparado();
                       
                    }
                }
            }
            
            
            
            
            
            for(int i = 0; i < this.cantidadAvionesPequeños; i++){
                if(avionesPequeños[i].isVisible() && (!(avionesPequeños[i].getFueChocado()))){
                	
                	
                    if(this.heroe.getBordes().intersects(avionesPequeños[i].getBordes())  && (!(heroe.isInvulnerable()))){
                        if(!this.heroe.getRefuerzos()){
                            bm.getAdmin_sound().playSonido("explota_aviones", bm.getAdmin_sound().getact_sonido());
                        	heroe.setEnergia(heroe.getEnergia()-10);
                        	avionesPequeños[i].setFueChocado(true);
                        } else
                        	{
                        	avionesPequeños[i].setFueChocado(true);
                            heroe.setEnergia(heroe.getEnergia()-10);
                            jugador.sumarPuntajeJugador(avionesPequeños[i].puntos);
                        	}
                        
                    } else if(this.heroe.getBordes().intersects(avionesPequeños[i].getBordes()) && (heroe.isInvulnerable())) //CUANDO ES INVENCIBLE 
                    	{
                    	avionesPequeños[i].setFueChocado(true);
                        jugador.sumarPuntajeJugador(avionesPequeños[i].puntos);
                        bm.getAdmin_sound().playSonido("explota_aviones", bm.getAdmin_sound().getact_sonido());
                    	}
                    
                    if(this.heroe.getBordesR().intersects(avionesPequeños[i].getBordes()) || this.heroe.getBordesR2().intersects(avionesPequeños[i].getBordes()) && heroe.getRefuerzos()) { //CUANDO CHOCA CON LOS REFUERZOS
                    	avionesPequeños[i].setFueChocado(true);
                    	heroe.ChocoRefuezo();
                    }
                    
                    //CONTROLA EL IMPACTO DE MUNICION CONTRA LOS AVIONES PEQUEÑOS
                    if(ataqueHeroe(avionesPequeños[i].getBordes())){
                    	avionesPequeños[i].setFueChocado(true);
                      			
                        
                        jugador.sumarPuntajeJugador(avionesPequeños[i].puntos);
                        bm.getAdmin_sound().playSonido("explota_aviones", bm.getAdmin_sound().getact_sonido());
                    }
                    
                    //CONTROLA LA MUNICION DEL AVION PEQUEÑO CONTRA EL JUGADOR
                    if(ataqueEnemigo(heroe.getBordes(),avionesPequeños[i].getarma()) && (!(heroe.isInvulnerable()))){		
                    	heroe.setEnergia(heroe.getEnergia()-3);
                        bm.getAdmin_sound().playSonido("recibe_disparo_heroe", bm.getAdmin_sound().getact_sonido());
                    }
                    
                    if(heroe.getRefuerzos() && (ataqueEnemigo(heroe.getBordesR(),avionesPequeños[i].getarma()) || ataqueEnemigo(heroe.getBordesR2(),avionesPequeños[i].getarma())) ) {
                		heroe.ChocoRefuezo(); //SI TIENE REFUERZOS LAS BALAS DE LOS BARCOS LOS PUEDEN MATAR
                	}
                            
                }
            }
            
            
            for(int i = 0; i < this.cantidadAvionesSemimedianos; i++){
                if(avionesSemimedianos[i].isVisible() && (!(avionesSemimedianos[i].getFueChocado()))){
                	
                	
                    if(this.heroe.getBordes().intersects(avionesSemimedianos[i].getBordes())  && (!(heroe.isInvulnerable()))){
                        if(!this.heroe.getRefuerzos()){
                               bm.getAdmin_sound().playSonido("explota_aviones", bm.getAdmin_sound().getact_sonido());
                        	heroe.setEnergia(heroe.getEnergia()-10);
                        	avionesSemimedianos[i].quitarVidaA();
                        } else
                        	{
                        	avionesSemimedianos[i].quitarVidaA();
                            heroe.setEnergia(heroe.getEnergia()-10);
                            jugador.sumarPuntajeJugador(avionesSemimedianos[i].puntos);
                        	}
                        
                    } else if(this.heroe.getBordes().intersects(avionesSemimedianos[i].getBordes()) && (heroe.isInvulnerable())) //CUANDO ES INVENCIBLE 
                    	{
                    	avionesSemimedianos[i].quitarVidaA();
                        jugador.sumarPuntajeJugador(avionesSemimedianos[i].puntos);
                        bm.getAdmin_sound().playSonido("explota_aviones", bm.getAdmin_sound().getact_sonido());
                    	}
                    
                    if(this.heroe.getBordesR().intersects(avionesSemimedianos[i].getBordes()) || this.heroe.getBordesR2().intersects(avionesSemimedianos[i].getBordes()) && heroe.getRefuerzos()) { //CUANDO CHOCA CON LOS REFUERZOS
                    	avionesSemimedianos[i].quitarVidaA();
                    	heroe.ChocoRefuezo();
                    }
                    
                    //CONTROLA EL IMPACTO DE MUNICION CONTRA LOS AVIONES SEMIMEDIANOS
                    if(ataqueHeroe(avionesSemimedianos[i].getBordes())){
                    	avionesSemimedianos[i].quitarVidaA();
                        			
                        
                        jugador.sumarPuntajeJugador(avionesSemimedianos[i].puntos);
                        bm.getAdmin_sound().playSonido("explota_aviones", bm.getAdmin_sound().getact_sonido());
                    }
                    
                    //CONTROLA LA MUNICION DEL AVION SEMIMEDIANO CONTRA EL JUGADOR
                    if(ataqueEnemigo(heroe.getBordes(),avionesSemimedianos[i].getarma())&& (!(heroe.isInvulnerable()))){	
                    	heroe.setEnergia(heroe.getEnergia()-5);
                        bm.getAdmin_sound().playSonido("recibe_disparo_heroe", bm.getAdmin_sound().getact_sonido());
                    }
                    
                    if(heroe.getRefuerzos() && (ataqueEnemigo(heroe.getBordesR(),avionesSemimedianos[i].getarma()) || ataqueEnemigo(heroe.getBordesR2(),avionesSemimedianos[i].getarma())) ) {
                		heroe.ChocoRefuezo(); //SI TIENE REFUERZOS LAS BALAS DE LOS BARCOS LOS PUEDEN MATAR
                	}
                            
                }
            }
            
            
            for(int i = 0; i < this.cantidadAvionesMedianos; i++){
                if(avionesMedianos[i].isVisible() && (!(avionesMedianos[i].getFueChocado())) && (!avionesMedianos[i].isInvulnerable())){
                	
                	
                    if(this.heroe.getBordes().intersects(avionesMedianos[i].getBordes())  && (!(heroe.isInvulnerable()))){
                        if(!this.heroe.getRefuerzos()){
                        	heroe.setEnergia(heroe.getEnergia()-25);
                        	avionesMedianos[i].quitarVidaA();
                        } else
                        	{
                        	avionesMedianos[i].quitarVidaA();
                            heroe.setEnergia(heroe.getEnergia()-25);
                            jugador.sumarPuntajeJugador(avionesMedianos[i].puntos);
                        	}
                        
                    } else if(this.heroe.getBordes().intersects(avionesMedianos[i].getBordes()) && (heroe.isInvulnerable())) //CUANDO ES INVENCIBLE 
                    	{
          
                        jugador.sumarPuntajeJugador(avionesMedianos[i].puntos);
                        bm.getAdmin_sound().playSonido("explota_aviones", bm.getAdmin_sound().getact_sonido());
                    	}
                    
                    if(this.heroe.getBordesR().intersects(avionesMedianos[i].getBordes()) || this.heroe.getBordesR2().intersects(avionesMedianos[i].getBordes()) && heroe.getRefuerzos()) { //CUANDO CHOCA CON LOS REFUERZOS
                    
                    	avionesMedianos[i].quitarVidaA();
                    	heroe.ChocoRefuezo();
                    }
                    
                    //CONTROLA EL IMPACTO DE MUNICION CONTRA LOS AVIONES MEDIANOS
                    if(ataqueHeroe(avionesMedianos[i].getBordes())){
                    	avionesMedianos[i].quitarVidaA();
                        			
                        
                        jugador.sumarPuntajeJugador(avionesMedianos[i].puntos);
                        bm.getAdmin_sound().playSonido("explota_aviones", bm.getAdmin_sound().getact_sonido());
                    }
                    
                    //CONTROLA LA MUNICION DEL AVION MEDIANO CONTRA EL JUGADOR
                    if(ataqueEnemigo(heroe.getBordes(),avionesMedianos[i].getarma())&& (!(heroe.isInvulnerable()))){
                        //spiders[i].setFueChocado(true);
                        //			
                    	heroe.setEnergia(heroe.getEnergia()-8);
                        bm.getAdmin_sound().playSonido("recibe_disparo_heroe", bm.getAdmin_sound().getact_sonido());
                    }
                    
                    if(heroe.getRefuerzos() && (ataqueEnemigo(heroe.getBordesR(),avionesMedianos[i].getarma()) || ataqueEnemigo(heroe.getBordesR2(),avionesMedianos[i].getarma())) ) {
                		heroe.ChocoRefuezo(); //SI TIENE REFUERZOS LAS BALAS DE LOS BARCOS LOS PUEDEN MATAR
                	}
                            
                }
            }
            
            
            
            if(numeroMapa == 1 || numeroMapa == 3) {
            //COLISIONES CON LOS BARCOS
            for(int i = 0; i < this.cantidadbarcos; i++){
                if(barcos[i].isVisible() && (!(barcos[i].getFueChocado()))){                    
                	
                    for(int j = 0; j <= 4; j++){ 
                    	//CONTROLA MUNICION DE HEROE CONTRA TORRETAS
	                    if( barcos[i].viveT(j+1) && ataqueHeroe(barcos[i].getBordesT(j+1)) ){
	                    	barcos[i].quitarVidaT(j+1);
	                    	jugador.sumarPuntajeJugador(20);
	                        bm.getAdmin_sound().playSonido("explota_aviones", bm.getAdmin_sound().getact_sonido());
	                    }
                    }
                    
                    //CONTROLA LA MUNICION DEl BARCO CONTRA EL HEROE
                    if(ataqueEnemigo(heroe.getBordes(),barcos[i].getarma())&& (!(heroe.isInvulnerable()))){			
                    	heroe.setEnergia(heroe.getEnergia()-5);
                        bm.getAdmin_sound().playSonido("explota_aviones", bm.getAdmin_sound().getact_sonido());
                    	
                    	if(heroe.getRefuerzos() && (ataqueEnemigo(heroe.getBordesR(),barcos[i].getarma()) || ataqueEnemigo(heroe.getBordesR2(),barcos[i].getarma())) ) {
                    		heroe.ChocoRefuezo(); //SI TIENE REFUERZOS LAS BALAS DE LOS BARCOS LOS PUEDEN MATAR
                    	}
                    }
                            
                }
            }
            
            }
            
            
            if(numeroMapa == 1 ) {
            //COLISIONES CON EL BOS
            if(boss.isVisible() && (!(boss.getFueChocado()))){                    
            	
            	//CONTROLA MUNICION DE HEROE CONTRA TORRETAS
                for(int j = 0; j <= 4; j++){ 
                	                    if( boss.viveT(j+1) && ataqueHeroe(boss.getBordesT(j+1)) ){
                    	boss.quitarVidaT(j+1);
                    	jugador.sumarPuntajeJugador(30);
                        bm.getAdmin_sound().playSonido("explota_aviones", bm.getAdmin_sound().getact_sonido());
                    }
                }
                
              //CONTROLA MUNICION DE HEROE CONTRA FOGONEO
                for(int j = 0; j <= 3; j++){ 
                	     if( boss.viveF(j+1) && ataqueHeroe(boss.getBordesF(j+1)) ){
                    	boss.quitarVidaF(j+1);
                    	jugador.sumarPuntajeJugador(20);
                        bm.getAdmin_sound().playSonido("explota_aviones", bm.getAdmin_sound().getact_sonido());
                    }
                }
                
                //CONTROLA LA MUNICION DEL BOSS CONTRA EL JUGADOR
                if(ataqueEnemigo(heroe.getBordes(),boss.getarma())&& (!(heroe.isInvulnerable()))){
                	System.out.println("auch");
                	heroe.setEnergia(heroe.getEnergia()-5);
                    bm.getAdmin_sound().playSonido("recibe_disparo_heroe", bm.getAdmin_sound().getact_sonido());
                	
                	if(heroe.getRefuerzos() && (ataqueEnemigo(heroe.getBordesR(),boss.getarma()) || ataqueEnemigo(heroe.getBordesR2(),boss.getarma())) ) {
                		heroe.ChocoRefuezo(); //SI TIENE REFUERZOS LAS BALAS DE LOS BARCOS LOS PUEDEN MATAR
                	}
                }
                
                if(ataqueEnemigo(heroe.getBordes(),boss.getarma2())&& (!(heroe.isInvulnerable()))){			
                	heroe.setEnergia(heroe.getEnergia()-10);
                    bm.getAdmin_sound().playSonido("explota_aviones", bm.getAdmin_sound().getact_sonido());
                	
                	if(heroe.getRefuerzos() && (ataqueEnemigo(heroe.getBordesR(),boss.getarma2()) || ataqueEnemigo(heroe.getBordesR2(),boss.getarma2())) ) {
                		heroe.ChocoRefuezo(); //SI TIENE REFUERZOS LAS BALAS DE LOS BARCOS LOS PUEDEN MATAR
                	}
                }
                          
            }
           }
           //////////////////////////////// 
            
            
            if(numeroMapa == 3) {
          //COLISIONES CON EL yamato
            if(yamato.isVisible() && (!(yamato.getFueChocado()))){                    
            	
            	//CONTROLA MUNICION DE HEROE CONTRA TORRETAS
                for(int j = 0; j <= 13; j++){ 
                	                    if( yamato.viveT(j+1) && ataqueHeroe(yamato.getBordesT(j+1)) ){
                    	yamato.quitarVidaT(j+1);
                    	jugador.sumarPuntajeJugador(30);
                        bm.getAdmin_sound().playSonido("explota_aviones", bm.getAdmin_sound().getact_sonido());
                    }
                }
                
              //CONTROLA MUNICION DE HEROE CONTRA FOGONEO
                for(int j = 0; j <= 11; j++){ 
                	     if( yamato.viveF(j+1) && ataqueHeroe(yamato.getBordesF(j+1)) ){
                    	yamato.quitarVidaF(j+1);
                    	jugador.sumarPuntajeJugador(10);
                        bm.getAdmin_sound().playSonido("explota_aviones", bm.getAdmin_sound().getact_sonido());
                    }
                }
                
                for(int j = 0; j <= 2; j++){ 
           	     if( yamato.viveTT(j+1) && ataqueHeroe(yamato.getBordesTT(j+1)) ){
           	    	 yamato.quitarVidaTT(j+1);
           	    	 jugador.sumarPuntajeJugador(30);
                   bm.getAdmin_sound().playSonido("explota_aviones", bm.getAdmin_sound().getact_sonido());
           	     }
                }
                
                for(int j = 0; j <= 1; j++){ 
              	     if( yamato.viveCT(j+1) && ataqueHeroe(yamato.getBordesCT(j+1)) ){
                  	yamato.quitarVidaCT(j+1);
                  	jugador.sumarPuntajeJugador(30);
                      bm.getAdmin_sound().playSonido("explota_aviones", bm.getAdmin_sound().getact_sonido());
                  }
                 }
                
                //CONTROLA LA MUNICION DEL BOSS CONTRA EL JUGADOR
                if(ataqueEnemigo(heroe.getBordes(),yamato.getarma())&& (!(heroe.isInvulnerable()))){
                	heroe.setEnergia(heroe.getEnergia()-5);
                    bm.getAdmin_sound().playSonido("recibe_disparo_heroe", bm.getAdmin_sound().getact_sonido());
                	
                	if(heroe.getRefuerzos() && (ataqueEnemigo(heroe.getBordesR(),yamato.getarma()) || ataqueEnemigo(heroe.getBordesR2(),yamato.getarma())) ) {
                		heroe.ChocoRefuezo(); //SI TIENE REFUERZOS LAS BALAS DE LOS BARCOS LOS PUEDEN MATAR
                	}
                }
                
                if(ataqueEnemigo(heroe.getBordes(),yamato.getarma2())&& (!(heroe.isInvulnerable()))){			
                	heroe.setEnergia(heroe.getEnergia()-10);
                    bm.getAdmin_sound().playSonido("recibe_disparo_heroe", bm.getAdmin_sound().getact_sonido());
                	
                	if(heroe.getRefuerzos() && (ataqueEnemigo(heroe.getBordesR(),yamato.getarma2()) || ataqueEnemigo(heroe.getBordesR2(),yamato.getarma2())) ) {
                		heroe.ChocoRefuezo(); //SI TIENE REFUERZOS LAS BALAS DE LOS BARCOS LOS PUEDEN MATAR
                	}
                }
                
                if(ataqueEnemigo(heroe.getBordes(),yamato.getarma3())&& (!(heroe.isInvulnerable()))){			
                	heroe.setEnergia(heroe.getEnergia()-15);
                    bm.getAdmin_sound().playSonido("recibe_disparo_heroe", bm.getAdmin_sound().getact_sonido());
                	
                	if(heroe.getRefuerzos() && (ataqueEnemigo(heroe.getBordesR(),yamato.getarma3()) || ataqueEnemigo(heroe.getBordesR2(),yamato.getarma3())) ) {
                		heroe.ChocoRefuezo(); //SI TIENE REFUERZOS LAS BALAS DE LOS BARCOS LOS PUEDEN MATAR
                	}
                }
                
                
                
                        
            }
            }
           //////////////////////////////// 
            
            
        }
    }
   
    public void vaciarArea() {
    	
    	for (int i = 0; i < cantidadTresTiros; i++) {
    	    trestiros[i] = null;
    	}
    	cantidadTresTiros=0;
    	
 
    	
    	for (int i = 0; i < cantidadEstrellas; i++) {
    	    estrellas[i] = null;
    	}
    	cantidadEstrellas=0;
    	
    	for (int i = 0; i < cantidadEscopetas; i++) {
    	    escopetas[i] = null;
    	}
    	cantidadEscopetas=0;
    	
    	for (int i = 0; i < cantidadAutos; i++) {
    	    autos[i] = null;
    	}
    	cantidadAutos=0;
    	
    	for (int i = 0; i < cantidadPows; i++) {
    	    pows[i] = null;
    	}
    	cantidadPows=0;
    	
    	for (int i = 0; i < cantidadRefuerzos; i++) {
    	    refuerzos[i] = null;
    	}
    	cantidadRefuerzos=0;
    	
    	for (int i = 0; i < cantidadMisiles; i++) {
    	    misiles[i] = null;
    	}
    	cantidadMisiles=0;
    
    	for (int i = 0; i < cantidadbarcos; i++) {
    	    barcos[i] = null;
    	}
    	cantidadbarcos=0;
    	
    }
    
    private boolean ataqueEnemigo(Rectangle rEntidad,Arma armaE) {
    	
    	
    	switch(armaE.getTMunicion()){
		case(Arma.MUNICION_ENEMIGO_SIMPLE):
		case(Arma.MUNICION_ENEMIGO_BOS):
		case(Arma.MUNICION_ENEMIGO_FOGONEO):
	    	ArrayList<Rectangle> rProyectiles = armaE.getMunicionEnemigo();
	    	for(int k = 0; k < rProyectiles.size(); k++){
	    		if(rProyectiles.get(k) != null){
	    			if(rProyectiles.get(k).intersects(rEntidad)){
	            	armaE.chocoEnem();
	                return true;
	    			}
	    		} 
	    	}
		break;
		case(Arma.MUNICION_ENEMIGO_TRI):
		case(Arma.MUNICION_ENEMIGO_TRI_BOS):
			rProyectiles = armaE.getMunicionT();
			for(int k = 0; k < rProyectiles.size(); k++){
				if(rProyectiles.get(k) != null){
                if(rProyectiles.get(k).intersects(rEntidad)){
                	armaE.chocoC();
                	return true;
                }
            } 
			}
        
			ArrayList<Rectangle> rProyectilesD = armaE.getMunicionDT();
        	for(int k = 0; k < rProyectilesD.size(); k++){
            if(rProyectilesD.get(k) != null){
                if(rProyectilesD.get(k).intersects(rEntidad)){
                	armaE.chocoD();
                    return true;
                }
            } 
        	}
        
        	ArrayList<Rectangle> rProyectilesI = armaE.getMunicionIT();
        	for(int k = 0; k < rProyectilesI.size(); k++){
            if(rProyectilesI.get(k) != null){
                if(rProyectilesI.get(k).intersects(rEntidad)){
                	armaE.chocoI();
                    return true;
                }
            } 
        	}
        	return false;
    	}
		return false;
   }
    
    
    private boolean ataqueHeroe(Rectangle rEntidad){
    	switch(arma.getTMunicion()){
    		case(Arma.MUNICION_ESTANDAR):
    		case(Arma.MUNICION_AUTO):
       		case(Arma.MUNICION_SHELL):
    			ArrayList<Rectangle> rProyectiles = arma.getMunicionE();
            	for(int k = 0; k < rProyectiles.size(); k++){
            		if(rProyectiles.get(k) != null){
                    if(rProyectiles.get(k).intersects(rEntidad)){
                    	arma.chocoC();
                        return true;
                    }
            		} 
            	}
            	return false;
            	//break;
    		case(Arma.MUNICION_TRI):
    			rProyectiles = arma.getMunicionT();
    			for(int k = 0; k < rProyectiles.size(); k++){
    				if(rProyectiles.get(k) != null){
                    if(rProyectiles.get(k).intersects(rEntidad)){
                    	arma.chocoC();
                    	return true;
                    }
                } 
    			}
            
    			ArrayList<Rectangle> rProyectilesD = arma.getMunicionDT();
            	for(int k = 0; k < rProyectilesD.size(); k++){
                if(rProyectilesD.get(k) != null){
                    if(rProyectilesD.get(k).intersects(rEntidad)){
                    	arma.chocoD();
                        return true;
                    }
                } 
            	}
            
            	ArrayList<Rectangle> rProyectilesI = arma.getMunicionIT();
            	for(int k = 0; k < rProyectilesI.size(); k++){
                if(rProyectilesI.get(k) != null){
                    if(rProyectilesI.get(k).intersects(rEntidad)){
                    	arma.chocoI();
                        return true;
                    }
                } 
            	}
            	return false;
    		case(Arma.MUNICION_SHOTGUN):
    			rProyectiles = arma.getMunicion();
    			for(int k = 0; k < rProyectiles.size(); k++){
    				if(rProyectiles.get(k) != null){
                    if(rProyectiles.get(k).intersects(rEntidad)){
                    	arma.chocoC();
                    	return true;
                    }
                } 
    			}
            
    			rProyectilesD = arma.getMunicionD();
            	for(int k = 0; k < rProyectilesD.size(); k++){
                if(rProyectilesD.get(k) != null){
                    if(rProyectilesD.get(k).intersects(rEntidad)){
                    	arma.chocoD();
                        return true;
                    }
                } 
            	}
            
            	rProyectilesI = arma.getMunicionI();
            	for(int k = 0; k < rProyectilesI.size(); k++){
                if(rProyectilesI.get(k) != null){
                    if(rProyectilesI.get(k).intersects(rEntidad)){
                    	arma.chocoI();
                        return true;
                    	}
                	} 
            	}
            	
    			ArrayList<Rectangle> rProyectilesDD = arma.getMunicionDD();
            	for(int k = 0; k < rProyectilesDD.size(); k++){
                if(rProyectilesDD.get(k) != null){
                    if(rProyectilesDD.get(k).intersects(rEntidad)){
                    	arma.chocoDD();
                        return true;
                    	}
                	} 
            	}
            
            	ArrayList<Rectangle> rProyectilesII = arma.getMunicionII();
            	for(int k = 0; k < rProyectilesII.size(); k++){
                if(rProyectilesII.get(k) != null){
                    if(rProyectilesII.get(k).intersects(rEntidad)){
                    	arma.chocoI();
                        return true;
                    	}
                	}
            	}
            	return false;
            	//break;
       		case(Arma.MUNICION_SHOTGUN_MEJORADO):
    			rProyectiles = arma.getMunicionM();
    			for(int k = 0; k < rProyectiles.size(); k++){
    				if(rProyectiles.get(k) != null){
                    if(rProyectiles.get(k).intersects(rEntidad)){
                    	arma.chocoC();
                    	return true;
                    }
                } 
    			}
            
    			rProyectilesD = arma.getMunicionDM();
            	for(int k = 0; k < rProyectilesD.size(); k++){
                if(rProyectilesD.get(k) != null){
                    if(rProyectilesD.get(k).intersects(rEntidad)){
                    	arma.chocoD();
                        return true;
                    }
                } 
            	}
            
            	rProyectilesI = arma.getMunicionIM();
            	for(int k = 0; k < rProyectilesI.size(); k++){
                if(rProyectilesI.get(k) != null){
                    if(rProyectilesI.get(k).intersects(rEntidad)){
                    	arma.chocoI();
                        return true;
                    	}
                	} 
            	}
            	
    			rProyectilesDD = arma.getMunicionDDM();
            	for(int k = 0; k < rProyectilesDD.size(); k++){
                if(rProyectilesDD.get(k) != null){
                    if(rProyectilesDD.get(k).intersects(rEntidad)){
                    	arma.chocoDD();
                        return true;
                    	}
                	} 
            	}
            
            	rProyectilesII = arma.getMunicionIIM();
            	for(int k = 0; k < rProyectilesII.size(); k++){
                if(rProyectilesII.get(k) != null){
                    if(rProyectilesII.get(k).intersects(rEntidad)){
                    	arma.chocoI();
                        return true;
                    	}
                	}
            	}
            	return false;
            		
    	}
		return false;
    	
    }
    
    
    private void setFondo(int numeroMapa){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        if(!(TESTMODE)){
            this.imagen_fondo = toolkit.getImage(AREAS[numeroMapa]);
        } 
    }

    public int getEnergiaHeroe() {
        return heroe.getEnergia();
    }

    public void setEnergiaHeroe(int energiaHeroe) {
        this.energiaHeroe = energiaHeroe;
    }

    public int getNumeroMapa() {
        return numeroMapa;
    }
    
    public void setNumeroMapa(int numeroMapa) {
        this.numeroMapa = numeroMapa;
    }
    
    public Jugador getJugador() {
        return jugador;
    }

    public BattleOfMidway getbm() {
        return bm;
    }
    
   
}