package battelofmidway;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.FileWriter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Configuracion extends JFrame implements ActionListener,KeyListener{

    private JLabel lpantalla,lsonido,lavion,lteclas,lpista,lnombre;
    private JTextField tfnombre;
    private JRadioButton cp1,cp2,sr1,sr2,sa1,sa2,sp1,sp2;
    private ButtonGroup grupopantalla,gruposonido,grupoavion,grupopista;
    private JPanel p1,p2,p3,p4,p5,p6,p7;
    private JButton[] bteclas;
    private JButton breset,bguardar;
    private String[] acciones  = {"Arriba", "Abajo", "Izquierda","Derecha","Disparar","Ataque_Especial","Pausa"};
    private String[] tdefault  = {"Arriba", "Abajo", "Izquierda","Derecha","Z","X","P"};
    private boolean keyDetectionEnabled,repetida=false;
    private String tecla,anterior;
    private int t;
    private Menu Menu;
    
    private Properties properties = new Properties();
    
    public static final Properties leerConfiguracion(){
        try{
            Properties archivoConfiguracion = new Properties();
            archivoConfiguracion.load(new FileInputStream("src\\battelofmidway\\datos.properties"));
            return archivoConfiguracion;
        } catch(IOException e){
            System.out.println("Error en metodo PropertiesFile: "+e);
	}
        return null;
    }

    public Configuracion(Menu Menu){
    		
    	this.Menu = Menu;
    	properties = leerConfiguracion();
        setTitle("Configuracion");
        setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		//setIconImage(new ImageIcon(getClass().getResource("images/icon.png")).getImage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        //PANTALLA

        lpantalla = new JLabel("Pantalla: ");
        lpantalla.setFont(new Font("Arial", Font.PLAIN, 18));

        gbc.gridx = 0; //PRIMERA COLUMNA
        gbc.gridy = 0; //SEGUNDA FILA

        gbc.gridwidth=1; //COLUMNAS QUE VA USAR
        gbc.gridheight=1; //FILAS QUE VA USAR

        gbc.weightx=1;
        //gbc.weighty=1;

        gbc.insets = new Insets(0,20, 0, 0);

        gbc.anchor = GridBagConstraints.FIRST_LINE_START;


        add(lpantalla,gbc); 

        //add(Box.createRigidArea(new Dimension(20, 15)));
     	p1 = new JPanel();
     	grupopantalla = new ButtonGroup();
        cp1 = new JRadioButton("Pantalla Completa");
        cp2 = new JRadioButton("Ventana"); 
        
        if( "true".equals(properties.getProperty("fullScreen")) )
        	cp1.setSelected(true);
        else 
        	cp2.setSelected(true);
        
        grupopantalla.add(cp1);
        grupopantalla.add(cp2);
       
        
        p1.add(cp1);
        //p1.add(Box.createRigidArea(new Dimension(50,0)));
        p1.add(cp2);
     	//p1.setPreferredSize(new Dimension(-270,-270)); 
     	//p1.setBackground(Color.WHITE);

     	gbc.gridx = 0; //PRIMERA COLUMNA
        gbc.gridy = 1; //PRIMERA FILA

        gbc.gridwidth=1; //COLUMNAS QUE VA USAR
        gbc.gridheight=1; //FILAS QUE VA USAR 

        gbc.insets = new Insets(0,35, 0, 0);

        add(p1,gbc); 

        /////////////////////////////
        //SONIDO

      lsonido = new JLabel("Sonido: ");
        lsonido.setFont(new Font("Arial", Font.PLAIN, 18));
      
        gbc.gridx = 0; //PRIMERA COLUMNA
        gbc.gridy = 2; //SEGUNDA FILA

        gbc.gridwidth=1; //COLUMNAS QUE VA USAR
        gbc.gridheight=1; //FILAS QUE VA USAR 

        gbc.insets = new Insets(0,20, 0, 0);

        add(lsonido,gbc); 


      p2 = new JPanel();

      gruposonido = new ButtonGroup();
        sr1 = new JRadioButton("Activado");
        sr2 = new JRadioButton("Desactivado");
        
        if( "true".equals(properties.getProperty("sonidoChoice")) )
        	sr1.setSelected(true);
        else 
        	sr2.setSelected(true);
        gruposonido.add(sr1);
        gruposonido.add(sr2);
        p2.add(sr1);
        //p2.add(Box.createRigidArea(new Dimension(50,0)));
        p2.add(sr2);
        
        gbc.gridx = 0; //PRIMERA COLUMNA
        gbc.gridy = 3; //SEGUNDA FILA

        gbc.gridwidth=1; //COLUMNAS QUE VA USAR
        gbc.gridheight=1; //FILAS QUE VA USAR 

        gbc.insets = new Insets(0,35, 0, 0);


        add(p2,gbc); 
        

        /////////////////////////////
        //SELECCION AVION

         lavion = new JLabel("Avion: ");
        lavion.setFont(new Font("Arial", Font.PLAIN, 18));

        gbc.insets = new Insets(0,20, 0, 0);

        gbc.gridx = 0; //PRIMERA COLUMNA
        gbc.gridy = 4; //SEGUNDA FILA

        gbc.gridwidth=1; //COLUMNAS QUE VA USAR
        gbc.gridheight=1; //FILAS QUE VA USAR 

        gbc.insets = new Insets(0,20, 0, 0);

        add(lavion,gbc); 

        ////////////////

        p3 = new JPanel();

          grupoavion = new ButtonGroup();
        sa1 = new JRadioButton("Por defecto");
        sa2 = new JRadioButton("Otro");
        
        if( "0".equals(properties.getProperty("Avion")) )
        	sa1.setSelected(true);
        else 
        	sa2.setSelected(true);
        
        grupoavion.add(sa1);
        grupoavion.add(sa2);

        p3.add(sa1);
        p3.add(sa2);
        
        gbc.gridx = 0; //PRIMERA COLUMNA
        gbc.gridy = 5; //SEGUNDA FILA

        gbc.gridwidth=2; //COLUMNAS QUE VA USAR
        gbc.gridheight=2; //FILAS QUE VA USAR 

        gbc.insets = new Insets(0,35, 0, 0);

        add(p3,gbc);

        

        /////////////////////////////
        //TECLAS TITULO 

        lteclas = new JLabel("Definicion de teclas: ");
        lteclas.setFont(new Font("Arial", Font.PLAIN, 18));

        gbc.gridx = 0; //PRIMERA COLUMNA
        gbc.gridy = 7; //SEGUNDA FILA

        gbc.gridwidth=1; //COLUMNAS QUE VA USAR
        gbc.gridheight=1; //FILAS QUE VA USAR 

        gbc.insets = new Insets(0,20,0,0);

        add(lteclas,gbc);

        //TECLAS GRID


        p4 = new JPanel(new GridBagLayout());
        GridBagConstraints gbct = new GridBagConstraints();
        //p4.setBackground(Color.BLUE);
        
        for(int i=0; i<acciones.length;i++) {
        	JLabel t1 = new JLabel(acciones[i]);
            t1.setFont(new Font("Arial", Font.ITALIC, 15));
            gbct.gridx = 0; //PRIMERA COLUMNA
            gbct.gridy = i+1; //SEGUNDA FILA
            gbct.gridwidth=1; //COLUMNAS QUE VA USAR
            gbct.gridheight=1; //FILAS QUE VA USAR
            gbct.weightx=1;
            gbct.weighty=1;
            gbct.insets = new Insets(10,40, 0, 0);
            gbct.anchor = GridBagConstraints.FIRST_LINE_START;
            gbct.insets.right = 150;//distancias entre columnas
            p4.add(t1,gbct);
        }

        
        bteclas= new JButton[7]; //vector que contiene a los botones

        
    
            for(int i=0; i<acciones.length;i++) {
            	JButton bt1 = new JButton(properties.getProperty(acciones[i]));
            	bt1.addActionListener(this);
            	bt1.addKeyListener(this);
            	bteclas[i]=bt1;
                gbct.gridx = 2; //PRIMERA COLUMNA
                gbct.gridy = i+1; //SEGUNDA FILA
                gbct.gridwidth=2; //COLUMNAS QUE VA USAR
                gbct.gridheight=1; //FILAS QUE VA USAR
                gbct.weightx=1;
                gbct.weighty=1;
                gbct.fill = GridBagConstraints.HORIZONTAL;
                gbct.ipadx = 50; //tamaño de boton
                gbct.insets = new Insets(0,0, 0, 0);
                gbct.anchor = GridBagConstraints.LAST_LINE_END;
                p4.add(bt1,gbct);
            }
        
     
        gbc.gridy = 8; 

        gbc.gridwidth=1; //COLUMNAS QUE VA USAR
        gbc.gridheight=1; //FILAS QUE VA USAR 

        gbc.insets = new Insets(0,0,0,0);

  		add(p4,gbc);
  		
  		//////////////
  		
  		lpista = new JLabel("Pista Musical: ");
        lpista.setFont(new Font("Arial", Font.PLAIN, 18));
      
     
        gbc.gridy =9 ; //fila

        gbc.gridwidth=1; //COLUMNAS QUE VA USAR
        gbc.gridheight=1; //FILAS QUE VA USAR 

        gbc.insets = new Insets(10,20, 0, 0);

        add(lpista,gbc); 


      p5 = new JPanel();

      grupopista = new ButtonGroup();
        sp1 = new JRadioButton("Original");
        sp2 = new JRadioButton("Otro");
        
        if( "0".equals(properties.getProperty("musica")) )
        	sp1.setSelected(true);
        else 
        	sp2.setSelected(true);
        
        grupopista.add(sp1);
        grupopista.add(sp2);
        p5.add(sp1);
        //p2.add(Box.createRigidArea(new Dimension(50,0)));
        p5.add(sp2);
        
        gbc.gridy = 10; // FILA

        gbc.gridwidth=1; //COLUMNAS QUE VA USAR
        gbc.gridheight=1; //FILAS QUE VA USAR 

        gbc.insets = new Insets(0,35, 0, 0);

        add(p5,gbc);
        ///////////////////////////////////// NOMBRE
        
        p7 = new JPanel(new GridBagLayout());
        gbct = new GridBagConstraints();
        //p4.setBackground(Color.BLUE);
        
        	JLabel t1 = new JLabel("Nombre: ");
            t1.setFont(new Font("Arial", Font.ITALIC, 15));
            gbct.gridx = 0; //PRIMERA COLUMNA
            gbct.gridy = 1; //SEGUNDA FILA
            gbct.gridwidth=1; //COLUMNAS QUE VA USAR
            gbct.gridheight=1; //FILAS QUE VA USAR
            gbct.weightx=1;
            gbct.weighty=1;
            gbct.insets = new Insets(10,40, 0, 0);
            gbct.anchor = GridBagConstraints.FIRST_LINE_START;
            gbct.insets.right = 150;//distancias entre columnas
            p7.add(t1,gbct);

        
        tfnombre= new JTextField(properties.getProperty("nombreJugador"),40); //vector que contiene a los botones

                gbct.gridx = 2; //PRIMERA COLUMNA
                gbct.gridy = 1; //SEGUNDA FILA
                gbct.gridwidth=2; //COLUMNAS QUE VA USAR
                gbct.gridheight=1; //FILAS QUE VA USAR
                gbct.weightx=1;
                gbct.weighty=1;
                gbct.fill = GridBagConstraints.HORIZONTAL;
                gbct.ipadx = 100; //tamaño
                gbct.insets = new Insets(0,0, 0, 0);
                gbct.anchor = GridBagConstraints.LAST_LINE_END;
                p7.add(tfnombre,gbct);
        
        gbc.gridy = 12; 

        gbc.gridwidth=1; //COLUMNAS QUE VA USAR
        gbc.gridheight=1; //FILAS QUE VA USAR 

        gbc.insets = new Insets(0,0,0,0);

  		add(p7,gbc);
        

        
        ///////////////////////////////////// BOTONES GUARDAR RESET
       
        p6 = new JPanel(new GridBagLayout());
        breset = new JButton("Reset");
        breset.addActionListener(this);
        
        gbct.gridx = 0; //PRIMERA COLUMNA
        gbct.gridy = 1; //SEGUNDA FILA
        gbct.gridwidth=2; //COLUMNAS QUE VA USAR
        gbct.gridheight=1; //FILAS QUE VA USAR
        gbct.weightx=1;
        gbct.weighty=1;
        gbct.fill = GridBagConstraints.HORIZONTAL;
        gbct.ipadx = 50; //tamaño de boton
        gbct.insets = new Insets(0,0, 0, 0);
        gbct.anchor = GridBagConstraints.LAST_LINE_END;
        gbct.insets.right = 150;//distancias entre columnas
        p6.add(breset,gbct);
        
        bguardar = new JButton("Guardar");
        bguardar.addActionListener(this);
        
        gbct.gridx = 4; // COLUMNA
        gbct.gridy = 2; 
        gbct.gridwidth=2; //COLUMNAS QUE VA USAR
        gbct.gridheight=1; //FILAS QUE VA USAR
        gbct.weightx=1;
        gbct.weighty=1;
        gbct.fill = GridBagConstraints.HORIZONTAL;
        gbct.ipadx = 50; //tamaño de boton
        gbct.insets = new Insets(0,0, 0, 0);
        gbct.anchor = GridBagConstraints.LAST_LINE_END;
        p6.add(bguardar,gbct);
        
        
        
        gbc.gridy = 13; // FILA

        gbc.gridwidth=1; //COLUMNAS QUE VA USAR
        gbc.gridheight=1; //FILAS QUE VA USAR 

        gbc.insets = new Insets(20,20, 0, 0);
        add(p6,gbc);

      
        
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Menu.setEnabled(true); // Habilitar el Frame Principal al cerrar el Frame 2
            }
        });






    	pack();
    	setSize(450,600);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
        
        
        } 

    
    

    public void actionPerformed(ActionEvent evt){
    	Properties properties = leerConfiguracion();    
            
            //TECLAS CONFIGURABLES
          if(evt.getActionCommand()==bteclas[0].getActionCommand()) {
        	  anterior= bteclas[0].getText();
        	  bteclas[0].setText("Presiona una tecla");
        	  t=0;
        	  keyDetectionEnabled = true;
          }
          
          if(evt.getActionCommand()==bteclas[1].getActionCommand()) {
        	  t=1;
        	  anterior= bteclas[1].getText();
        	  keyDetectionEnabled = true;
        	  bteclas[1].setText("Presiona una tecla");
          }
          
          if(evt.getActionCommand()==bteclas[2].getActionCommand()) {
        	  t=2;
        	  anterior= bteclas[2].getText();
        	  keyDetectionEnabled = true;
        	  bteclas[2].setText("Presiona una tecla");
          }
          
          if(evt.getActionCommand()==bteclas[3].getActionCommand()) {
        	  t=3;
        	  anterior= bteclas[3].getText();
        	  keyDetectionEnabled = true;
        	  bteclas[3].setText("Presiona una tecla");
          }
          
          if(evt.getActionCommand()==bteclas[4].getActionCommand()) {
        	  t=4;
        	  anterior= bteclas[4].getText();
        	  keyDetectionEnabled = true;
        	  bteclas[4].setText("Presiona una tecla");
          }
          
          if(evt.getActionCommand()==bteclas[5].getActionCommand()) {
        	  t=5;
        	  anterior= bteclas[5].getText();
        	  keyDetectionEnabled = true;
        	  bteclas[5].setText("Presiona una tecla");
          }
          
          if(evt.getActionCommand()==bteclas[6].getActionCommand()) {
        	  t=6;
        	  anterior= bteclas[6].getText();
        	  keyDetectionEnabled = true;
        	  bteclas[6].setText("Presiona una tecla");
          }
            
            //BOTON RESET
          if(evt.getActionCommand()==breset.getActionCommand()) {
    		cp1.setSelected(true);
    		sr1.setSelected(true);
    		sa1.setSelected(true);
    		sp1.setSelected(true);
    		
    		 for(int i=0; i<acciones.length;i++) {
    			 bteclas[i].setText(tdefault[i]);
    		 }
    		 
    		tfnombre.setText("Jugador");
    	}
          
          
          //BOTON GUARDAR
    	if(evt.getActionCommand()==bguardar.getActionCommand()) { 
    		if(cp1.isSelected())
    			properties.setProperty("fullScreen","true");
    		else 
    			properties.setProperty("fullScreen","false");
    		
    		if(sr1.isSelected())
    			properties.setProperty("sonidoChoice","true");
    		else 
    			properties.setProperty("sonidoChoice","false");
    		
    		if(sa1.isSelected())
    			properties.setProperty("Avion","0");
    		else
    			properties.setProperty("Avion","1");
    		
    		if(sp1.isSelected())
    			properties.setProperty("musica","0");
    		else
    			properties.setProperty("musica","1");
    		
    		for(int i=0; i<acciones.length;i++) {
    			properties.setProperty(acciones[i], bteclas[i].getText());
    		 
   		 	}
    		
    		properties.setProperty("nombreJugador",tfnombre.getText());
    	}
    	  	
    	try{
            FileOutputStream out=new FileOutputStream("src\\battelofmidway\\datos.properties");
            properties.store(out,"src\\battelofmidway\\datos.properties");
            out.close();
        }catch(IOException e){
            System.out.print("Error guardar Properties");
        }
}


    public static void main(String[] args){
    	
    }

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if (keyDetectionEnabled) {
				tecla=KeyEvent.getKeyText(e.getKeyCode());
					
				for(int i=0; i<6 ;i++) {
						if((bteclas[i].getText()).equals(tecla))
						{
							repetida = true;
							System.out.println("REPETIDA");
							bteclas[t].setText(anterior);
						}
		   		 }
				
				if(!repetida) 
				{
					bteclas[t].setText(tecla);
				}
				
				keyDetectionEnabled = false;
				t=0;
				repetida = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

}
