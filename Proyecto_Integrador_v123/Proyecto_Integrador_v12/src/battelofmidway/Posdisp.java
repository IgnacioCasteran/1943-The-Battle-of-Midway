package battelofmidway;

public class Posdisp {
		private int posX;
	    private int posY;
	    private int dirX;
	    private int dirY;
	    private int cont=0;
	    private boolean der=false;
	    private boolean izq=false;
	    private boolean s=false;
	    private double pasoX=0;
	    private double pasoY=0;
	    private int velocidad;
		


	    public double getPasoX() {
			return pasoX;
		}

		public double getPasoY() {
			return pasoY;
		}

		public Posdisp(int posX, int posY) {
	        this.posX = posX;
	        this.posY = posY;
	        this.cont = 0;
	    }
	    
	    public Posdisp(int posX, int posY, int dirX, int dirY) {
	        this.posX = posX;
	        this.posY = posY;
	        this.dirX = dirX;
	        this.dirY = dirY;
	        this.cont = 0;
	        
	        double deltaX = dirX - posX;
            double deltaY = dirY - posY;
            double distancia = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            pasoX = (deltaX / distancia) * 10;
            pasoY = (deltaY / distancia) * 10;
	        
	        if( dirY > posY) 
	    		s=true;
	        
	        if( dirX > posX)
	        	der=true;
	        else if ( dirX < posX)
	        	izq=true;
	        

	    }
	    
	    public Posdisp(int posX, int posY, int dirX, int dirY,int velocidad) {
	        this.posX = posX;
	        this.posY = posY;
	        this.dirX = dirX;
	        this.dirY = dirY;
	        this.cont = 0;
	        
	        double deltaX = dirX - posX;
            double deltaY = dirY - posY;
            double distancia = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            pasoX = (deltaX / distancia) * velocidad;
            pasoY = (deltaY / distancia) * velocidad;
	        
	        if( dirY > posY) 
	    		s=true;
	        
	        if( dirX > posX)
	        	der=true;
	        else if ( dirX < posX)
	        	izq=true;
	        

	    }
	    
	    public boolean sube() {
	    	return s;
	    }
	    
	    public boolean ladod() {
	    	return der;
	    }
	    
	    public boolean ladoi() {
	    	return izq;
	    }
	    

	    public int getDirX() {
			return dirX;
		}

		public void setDirX(int dirX) {
			this.dirX = dirX;
		}

		public int getDirY() {
			return dirY;
		}

		public void setDirY(int dirY) {
			this.dirY = dirY;
		}

		public int getCont() {
	        return cont;
	    }
	    
	    public int sumarCont() {
	        return cont++;
	    }


		public int getPosX() {
	        return posX;
	    }

	    public void setPosX(int posX) {
	        this.posX = posX;
	    }

	    public int getPosY() {
	        return posY;
	    }

	    public void setPosY(int posY) {
	        this.posY = posY;
	    }
	    



}
