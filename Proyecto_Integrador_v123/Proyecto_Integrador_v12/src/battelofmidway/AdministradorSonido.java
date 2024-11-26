//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : AdministradorSonido.java
//  @ Date : 02/05/2021
//  @ Author : 
//
//
package battelofmidway;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class AdministradorSonido {
    private static Hashtable<String,Clip> table_sounds;

    
    private String start_end[] = {"Sonidos\\Efectos\\ganar_nivel.wav", //0
    								"Sonidos\\Efectos\\Music.wav", //1
                                  "Sonidos\\GameOver.wav", //2
                                  "Sonidos\\Ranking.wav"}; //3
    
    private String efectos[] = {"Sonidos\\Efectos\\bajada_agua.wav",  //0
                                "Sonidos\\Efectos\\destruccion_jefe.wav", //1
                                "Sonidos\\Efectos\\disparo_normal.wav",  //2
                                "Sonidos\\Efectos\\explosion_muerte.wav", //3
                                "Sonidos\\Efectos\\explota_aviones.wav",  //4
                                "Sonidos\\Efectos\\ganar_nivel.wav",  //5
                                "Sonidos\\Efectos\\poca_vida.wav",  //6
                                "Sonidos\\Efectos\\power_up.wav",  //7
                                "Sonidos\\Efectos\\relampago.wav",  //8      
                                "Sonidos\\Efectos\\tsunami.wav",
                                "Sonidos\\Efectos\\recibe_disparo_heroe.wav"
                                };
    
    private Clip m_area1;
    private Clip m_area2;
    private Clip win;
    private Clip music;
    private Clip gameover;
    private Clip ranking;
    private Clip efectos_clip[];
    private static AdministradorSonido instancia;
    
    private boolean act_musica = true;
    private boolean act_sonido = true;

    public AdministradorSonido(){
        try {
            table_sounds=new Hashtable<String,Clip>();
            
            
            win=loadSound(start_end[0]);
            table_sounds.put("Win", win);
            
            music=loadSound(start_end[1]);
            table_sounds.put("Music", music);
            
            gameover=loadSound(start_end[2]);
            table_sounds.put("GameOver", gameover);
            
            ranking=loadSound(start_end[3]);
            table_sounds.put("Ranking", ranking);
            
            lista_efectos();
            
        } catch (IOException ex) {
            Logger.getLogger(AdministradorSonido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    private Clip loadSound(String str) throws IOException{
        try {
            Clip clip= AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(str)));
            return clip;
        } catch (LineUnavailableException ex) {
            Logger.getLogger(AdministradorSonido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(AdministradorSonido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AdministradorSonido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void playSonido(String strSonido,boolean flagSonido){
        if(flagSonido==true){
            (table_sounds.get(strSonido)).setFramePosition(0);
            
            (table_sounds.get(strSonido)).start();
        }else{
            (table_sounds.get(strSonido)).stop();
        }
    }
    
    
    public void pararMusica(String str){
        if((table_sounds.get(str)).isRunning()==true){
           (table_sounds.get(str)).stop();
        }
    }

    public Hashtable<String, Clip> getTable_sounds() {
        return table_sounds;
    }


    public void lista_efectos() throws IOException{
        efectos_clip = new Clip[17];
            
        efectos_clip[0]=loadSound(efectos[0]);
        table_sounds.put("bajada_agua", efectos_clip[0]);

        efectos_clip[1]=loadSound(efectos[1]);
        table_sounds.put("destruccion_jefe", efectos_clip[1]);

        efectos_clip[2]=loadSound(efectos[2]);
        table_sounds.put("disparo_normal", efectos_clip[2]);

        efectos_clip[3]=loadSound(efectos[3]);
        table_sounds.put("explosion_muerte", efectos_clip[3]);

        efectos_clip[4]=loadSound(efectos[4]);
        table_sounds.put("explota_aviones", efectos_clip[4]);

        efectos_clip[5]=loadSound(efectos[5]);
        table_sounds.put("ganar_nivel", efectos_clip[5]);

        efectos_clip[6]=loadSound(efectos[6]);
        table_sounds.put("poca_vida", efectos_clip[6]);

        efectos_clip[7]=loadSound(efectos[7]);
        table_sounds.put("power_up", efectos_clip[7]);

        efectos_clip[8]=loadSound(efectos[8]);
        table_sounds.put("relampago", efectos_clip[8]);

        efectos_clip[9]=loadSound(efectos[9]);
        table_sounds.put("tsunami", efectos_clip[9]);
        
        efectos_clip[10]=loadSound(efectos[10]);
        table_sounds.put("recibe_disparo_heroe", efectos_clip[10]);

    }

    public void setact_sonido(boolean b){
        this.act_sonido = b;
    }
    
    public boolean getact_sonido(){
        return this.act_sonido;
    }
    
    public boolean getact_musica(){
        return this.act_musica;
    }
    
    public void setact_musica(int etapaJuego,int area, boolean act){
        this.act_musica = act;
        switch(etapaJuego){
            case 0:
                playSonido("Win", act);
                break;
            case 1:
                playSonido("Music", act);
                break;
            case 2:
                playSonido("Music", act);
                break;
            case 6:
                playSonido("GameOver", act);
                break;
            case 7:
                playSonido("Ranking", act);                    
                break;
               
        }
    }
}