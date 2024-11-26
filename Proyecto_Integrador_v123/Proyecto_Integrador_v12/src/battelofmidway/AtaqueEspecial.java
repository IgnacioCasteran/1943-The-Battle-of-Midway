package battelofmidway;

import java.awt.Graphics2D;

public class AtaqueEspecial extends ObjetoGrafico implements ObjetoMovible{
	
	public final static int TSUNAMI = 0;
    public final static int RELAMPAGO = 1;
	
    private int tipo=0;
    private boolean activo=false;
    private int contA = 0;
    
    private int PasoT=50;
    private int PasoTY=-300;
    
   
	 private static final String Ataque[] = {
	    		"recursos\\Etapas\\Tsunami.png",
	    		"recursos\\Etapas\\Relampago.gif"};
	
	  public AtaqueEspecial(int t) {
	        super(Ataque, 2, 0);
	        this.visible = false;
	        this.tipo = t;
	    }
	  
	 public void activar() {
		 activo=true; 
		 this.visible = true;
	 }
	 
	 public void desactivar() {
		 activo=false; 
		 this.visible = false;
	 }
	 
	 public int getTipo() {
		 return tipo;
	 }
	 public void display(Graphics2D g2, int alto,int bajo) {
		 if(contA < 50) {
			 switch(tipo) {
			 case TSUNAMI: 
				 cambiarImagen(0);
				 g2.drawImage(this.imagen,120-PasoT,alto-PasoTY, this.imagen.getWidth(null), this.imagen.getHeight(null),null);
				 PasoT=PasoT+13;
				 PasoTY=PasoTY+13;	 
			 break;
			 case RELAMPAGO: 
				 cambiarImagen(1);
				 g2.drawImage(this.imagen,10,alto+20, this.imagen.getWidth(null), this.imagen.getHeight(null),null);
				 //PasoT=PasoT+13;
				 //PasoTY=PasoTY+13;	 
			 break;
			 }
				 
		 contA++;
		 }else {
			 contA=0;
			 PasoT=50;
			 PasoTY=-300;
			 desactivar();
		 }
			 
	 }
	 
	 public int getCont() {
		 return contA;
	 }
	 
	 public boolean activado() {
		 return activo;
	 }

	@Override
	public void update(double delta) {
		
		
	}

	@Override
	public void movimiento() {
		
	}
	
	
	

	

}
