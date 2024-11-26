package battelofmidway;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;


public class Ranking extends JFrame{
    //Variables para tomar_datos
    Button b_aceptar; 
    TextField tf1;   
    
    String[] ranking;
    
    private Image imagen_fondo;
    private Image imagen_logo;
    
    public void Ranking() throws FileNotFoundException, IOException{}

    public void cargar(String nombre, int puntaje){
        FileWriter archivo = null;
        PrintWriter pw = null;
        try{
            archivo = new FileWriter("Ranking\\Ranking.dat", true);
            pw = new PrintWriter(archivo);
            
            pw.write(puntaje + " " + nombre + "\n");
            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
                if (null != archivo){
                    archivo.close();
                }
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    
    public void ordenar(){
        String linea = null;
        String[] datos = null;
        java.util.List<Jugador> jugadores = new ArrayList<Jugador>();
        Jugador jugador = null;
        try{
            BufferedReader br = new BufferedReader(new FileReader("Ranking\\Ranking.dat")); 
            while((linea=br.readLine())!=null){
                datos = linea.split(" ");
                jugador = new Jugador(datos[1], Integer.parseInt(datos[0]));
                jugadores.add(jugador);
            }
            br.close();

            Collections.sort(jugadores);


            FileWriter writer = new FileWriter("Ranking\\Ranking.dat"); 
            for(int i = 1; i <= 5; i++){
                writer.write(jugadores.get(jugadores.size()-i).getPuntajeJugador()+ " " + jugadores.get(jugadores.size()-i).getNombre() + System.lineSeparator());
            }
            writer.close();
        }catch(IOException e){
          System.out.println(e);   
        }
    }
    
    public void display(Graphics2D g2) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        imagen_fondo = toolkit.getImage("recursos\\Etapas\\fondo.png"); 
        imagen_logo = toolkit.getImage("recursos\\Etapas\\Logo.png");
        g2.drawImage(imagen_fondo, 8, 7, imagen_fondo.getWidth(null), imagen_fondo.getHeight(null), null);
        g2.drawImage(imagen_logo, 50, 140, imagen_logo.getWidth(null)*3/4, imagen_logo.getHeight(null)*3/4, null);
        
        g2.setColor(Color.WHITE);
        g2.setFont( new Font( "Lazer Game Zone", Font.PLAIN, 20) );
        
        Font f = new Font("Lazer Game Zone", Font.ITALIC,25); 
        g2.setFont(f);
        g2.setColor(Color.BLACK);
        g2.drawString("RANKING BEST 5", 173, 325);
        g2.drawString("1 PLAYER", 117, 65);
        g2.drawString("0000000", 133, 95);
        g2.drawString("1ST", 133, 370);
        g2.drawString("2ND", 133, 400);
        g2.drawString("3RD", 133, 430);
        g2.drawString("4TH", 133, 460);
        g2.drawString("5TH", 133, 490);
        
        g2.setColor(Color.RED);
        g2.drawString("HI-SCORE", 277, 65);
        g2.drawString("9999999", 275, 90);
        
        //TEXTO
        g2.setColor(Color.WHITE);
        g2.drawString("1 PLAYER", 120, 60);
        g2.drawString("0000000", 130, 90);
        g2.drawString("RANKING BEST 5", 170, 320);
        g2.drawString("HI-SCORE", 280, 60);
        g2.drawString("9999999", 278, 85);
        
        g2.drawString("1ST", 130, 365);
        g2.drawString("2ND", 130, 395);
        g2.drawString("3RD", 130, 425);
        g2.drawString("4TH", 130, 455);
        g2.drawString("5TH", 130, 485);
        
        
        String linea = null;
        String[] datos = null;

        try{
            BufferedReader br = new BufferedReader(new FileReader("Ranking\\Ranking.dat")); 
            int i = 30;
            while((linea=br.readLine())!=null){
                datos = linea.split(" ");
                Jugador nJugador = new Jugador();
                nJugador.setNombre(datos[1]);
                nJugador.setPuntajeJugador(Integer.parseInt(datos[0]));
                

                g2.setColor(Color.BLACK);
                g2.drawString(nJugador.getMPuntos(" "), 213, 340 + i);
                //g2.drawString(nJugador.getMPuntos(6, " "), 213, 340 + i);
                g2.drawString(nJugador.getNombre(), 323, 340 + i);
                
                g2.setColor(Color.WHITE);
                g2.drawString(nJugador.getMPuntos(" "), 210, 335 + i);
                //g2.drawString(nJugador.getMPuntos(6, " "), 210, 335 + i);
                g2.drawString(nJugador.getNombre(), 320, 335 + i);
                i = i + 30;
            }
            br.close();
        }catch(IOException e){
          System.out.println(e);   
        }
        
    }
}

