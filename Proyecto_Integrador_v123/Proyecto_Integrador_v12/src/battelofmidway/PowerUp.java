package battelofmidway;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class PowerUp extends ObjetoGrafico {
    protected int puntos;
    protected int contadorTexto = 50;
    protected int segundosExtra;
    protected boolean pausa=false;
	
	public PowerUp(String direcciones[], int cantDirecciones, int numeroImagen) {
        super(direcciones, cantDirecciones, numeroImagen);
    }
	
    @Override
    public void display(Graphics2D g2) {
        AffineTransform transform = new AffineTransform();
        AffineTransform old = g2.getTransform();
        Font f = new Font("Arial Black",16,16);
        g2.setFont(f);
        if(desaparicion == 0){
            this.visible = false;
            this.fueChocado = true;
            this.contadorTexto = 0;
        }
        if(fueChocado && contadorTexto != 0){
            g2.setColor(Color.BLACK);
            g2.drawString(Integer.toString(puntos),(int) posX - 1, (int) posY - 1);
            g2.drawString(Integer.toString(puntos),(int) posX - 1, (int) posY + 1);
            g2.drawString(Integer.toString(puntos),(int) posX + 1, (int) posY - 1);
            g2.drawString(Integer.toString(puntos),(int) posX + 1, (int) posY + 1);
            g2.setColor(Color.WHITE);
            g2.drawString(Integer.toString(puntos),(int) posX, (int) posY);
            this.contadorTexto--;
            this.visible = false;
            this.posY-=0.5;
        }
        if(this.visible && this.contadorTexto == 50){
            if(!pausa){this.desaparicion--;} //Imitando temporizador para que desaparezca la fruta
            if((this.desaparicion%5 != 0 && this.desaparicion > 0 && this.desaparicion <50) || (this.desaparicion>50 && this.visible)){
                g2.drawImage(this.imagen, (int) this.posX, (int) this.posY, this.width, this.height, null);
            }
        } 
        g2.setTransform(old);
        
    }
    
    public int getPuntos(){
   	return this.puntos;
    }

    public int modificarTiempo(){
   	return this.puntos;
    }
    
    public void setDurabilidad(int durabilidad){
        this.desaparicion = durabilidad;
    }

    public int getSegundosExtra() {
        return segundosExtra;
    }
    
    public void setPausa(boolean b){
        this.pausa = b;
    }
	

}
