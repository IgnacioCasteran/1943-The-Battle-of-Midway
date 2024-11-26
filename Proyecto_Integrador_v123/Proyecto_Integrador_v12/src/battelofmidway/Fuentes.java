package battelofmidway;

import java.awt.Font;
import java.io.InputStream;

public class Fuentes {
    public String Midway = "recursos\\Fuentes\\Lazer Game Zone.ttf";

    public Font fuente(String fontName, float tamanio) {
    		InputStream is;
    		Font ttfBase;
    		Font ttfReal;
        	String fontFileName = fontName;
        	try{
           		is = this.getClass().getResourceAsStream(fontFileName);
           		ttfBase = Font.createFont(Font.TRUETYPE_FONT, is);
           		ttfReal = ttfBase.deriveFont(Font.PLAIN, tamanio);
        	}catch(Exception e){
        		 System.err.println(fontName + " No se cargo la fuente");
        		 ttfReal = new Font("Arial", Font.PLAIN, 14);   
        	}
        	return ttfReal;
    	}
}