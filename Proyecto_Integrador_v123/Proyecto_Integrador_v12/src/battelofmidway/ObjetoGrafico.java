
package battelofmidway;
import java.awt.*;
import java.awt.geom.AffineTransform;

import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class ObjetoGrafico {
    protected Image imagen = null;
    protected Image imagenes[];
    protected int cantidadImagenes;
    protected int numeroImagen;
    protected int sizeImg[][];
    protected boolean fueChocado = false;    
    protected int width;
    protected int height;

    protected double posX = 0;
    protected double posY = 0;
    
    protected int desaparicion = 100;
	
    protected boolean visible = true;
    
    protected int tipo;
    
    public ObjetoGrafico(String direccion) {
        this.imagenes = new Image[1];
        sizeImg = new int[1][2];
        imagenes[0] = obtenerImagen(direccion,0);
        cambiarImagen(0);
        
    }
    
    public ObjetoGrafico(String direcciones[], int cantidadImagenes, int numeroImagen){
        this.imagenes = new Image[cantidadImagenes];
        this.cantidadImagenes = cantidadImagenes;
        this.sizeImg = new int[cantidadImagenes][2];
        for(int i = 0; i < cantidadImagenes; i++){
            this.imagenes[i] = this.obtenerImagen(direcciones[i],i);
        }
        cambiarImagen(numeroImagen);
    }
    
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }

    public void setPosition(double posX, double posY){
        this.posX = posX;
        this.posY = posY;
    }

    public void setPositionX(double posX){
        this.posX = posX;
    }

    public void setPositionY(double posY){
        this.posY = posY;
    }

    public double getPosX(){
        return posX;
    }
    
    public double getPosY(){
        return posY;
    }
    
    public void setVisible(boolean visible){
        this.visible = visible;
    }
    
    public boolean isVisible(){
        return visible;
    }
    
    public void display(Graphics2D g2) {
        if(visible){
            AffineTransform transform = new AffineTransform();
            AffineTransform old = g2.getTransform();
            g2.drawImage(imagen,(int) this.posX,(int) this.posY, this.width, this.height, null);
            g2.setTransform(old);
        }
    }
    
    protected Image obtenerImagen(String direccion, int numeroImagen){
        Image nuevaImagen = null;
        if(!("".equals(direccion))){
            try{
                BufferedImage imagenAux;
                Toolkit toolkit = Toolkit.getDefaultToolkit(); 
                imagenAux = ImageIO.read(new FileInputStream(direccion));
                sizeImg[numeroImagen][0] = imagenAux.getWidth()*2;
                sizeImg[numeroImagen][1] = imagenAux.getHeight()*2;
                nuevaImagen = toolkit.getImage(direccion);
            }catch(IOException e){
                System.out.println(e);
            }
        }else{
            sizeImg[numeroImagen][0] = 0;
            sizeImg[numeroImagen][1] = 0;
        }
        return nuevaImagen;
    }
    
    public final void cambiarImagen(int numeroImagen){
        this.numeroImagen = numeroImagen;
        this.width = sizeImg[numeroImagen][0];
        this.height = sizeImg[numeroImagen][1];
        this.imagen = imagenes[numeroImagen];
    }
    
    public Rectangle getBordes(){
        return new Rectangle((int) this.posX, (int) this.posY, this.width, this.height);
    }
    
    public Rectangle getBordeDerecho(){
        return new Rectangle((int) this.posX + getWidth() - 4,(int) this.width, 4, this.height);
    }
    
    public Rectangle getBordeIzquierdo(){
        return new Rectangle((int) this.posX, (int) this.posY, 4, this.height);
    }
    
    public Rectangle getBordeBase(){
        return new Rectangle((int) this.posX, (int) this.posY + this.height - 4, this.width, 4);
    }
    
    public Rectangle getBordeTop(){
        return new Rectangle((int) this.posX, (int) this.posY, this.width, 4);
    }

    public boolean getFueChocado() {
        return fueChocado;
    }

    public void setFueChocado(boolean fueChocado) {
        this.fueChocado = fueChocado;
    }
    
}