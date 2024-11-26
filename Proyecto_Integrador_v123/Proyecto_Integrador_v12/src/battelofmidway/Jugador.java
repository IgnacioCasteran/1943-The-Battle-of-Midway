
package battelofmidway;

public class Jugador implements Comparable<Jugador>{
    private String nombre;
    private int puntajeJugador;
    private int temp;
    
    public Jugador(){
       this.puntajeJugador=0;
       this.nombre="Play";
    }

    public Jugador(String nombre, int puntajeJugador) {
        this.nombre = nombre;
        this.puntajeJugador = puntajeJugador;
    }
    
    public void sumarPuntajeJugador(int puntaje){
        this.puntajeJugador=this.puntajeJugador+puntaje;
    }
    
    public int getPuntajeJugador(){
        return this.puntajeJugador;
    }
    
    public void setTemp() {
    	temp = puntajeJugador;
    }
    
    public void resetP() {
    	puntajeJugador = temp;
    }
    
    public void setPuntajeJugador(int puntajeJugador){
        this.puntajeJugador = puntajeJugador;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    
    public String aString() {
        return ("Nombre "+this.nombre + ", Puntaje " + this.puntajeJugador);
    }
    
    // Este metodo es el que nos permite comparar entre puntajes y de esta forma puedan ser ordenados
    @Override
    public int compareTo(Jugador p){
        return puntajeJugador - p.puntajeJugador;
    }
    
    public String getMPuntos(String cambio){
        String newPuntos = Integer.toString(puntajeJugador);
            newPuntos = cambio+newPuntos;
        return newPuntos;
    }
}

