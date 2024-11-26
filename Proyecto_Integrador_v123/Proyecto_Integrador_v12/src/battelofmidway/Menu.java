package battelofmidway;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

public class Menu extends JFrame implements ActionListener {
    private JButton der,izq,jugar,config;
    private JLayeredPane layeredPane;
    private ImageIcon imagenFondo;
    private JLabel etiquetaFondo,etiquetaImagen;
    private ImageIcon imagenes[];
    private int im=0;

    public Menu() {
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        imagenes= new ImageIcon[3];
        
        imagenes[0]=new ImageIcon("recursos\\Sistema\\1943.PNG");
        imagenes[1]=new ImageIcon("recursos\\Sistema\\mario.PNG");
        imagenes[2]=new ImageIcon("recursos\\Sistema\\tetris.PNG");
        
        
     // Imagen de fondo
        layeredPane = new JLayeredPane();
        imagenFondo = new ImageIcon("recursos\\Sistema\\fondo.jpg");
        etiquetaFondo = new JLabel(imagenFondo);
        etiquetaFondo.setBounds(0, 0, imagenFondo.getIconWidth(), imagenFondo.getIconHeight());
        layeredPane.add(etiquetaFondo, 0);
        
        
        ///////////////////////////
        
		etiquetaImagen = new JLabel(imagenes[0]);
		etiquetaImagen.setBounds(130,170, 250, 353);
        add(etiquetaImagen);
		
		
		//////////////////////////
		
		izq= new JButton(new ImageIcon("recursos\\Sistema\\izquierda.PNG"));
		izq.setBounds(85, 320, 60, 60);
		izq.addActionListener(this);
        add(izq, BorderLayout.CENTER);
		
		jugar= new JButton(new ImageIcon("recursos\\Sistema\\jugar.PNG"));
		jugar.addActionListener(this);
		jugar.setBounds(225, 540, 60, 60);
        add(jugar, BorderLayout.CENTER);
		
		der= new JButton(new ImageIcon("recursos\\Sistema\\derecha.PNG"));
		der.addActionListener(this);
		der.setBounds(368, 320, 60, 60);
        add(der, BorderLayout.CENTER);
				
		config= new JButton(new ImageIcon("recursos\\Sistema\\configuracion.PNG"));
		config.addActionListener(this);
		config.setBounds(400, 445, 50, 50);
        add(config, BorderLayout.CENTER);
		


        //pack();
		getContentPane().add(layeredPane, BorderLayout.CENTER);
    	setSize(516,700);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == der) {
        	im++;
        	if(im == 3){
        		im=0;
        		config.setEnabled(true);
        	}
            etiquetaImagen.setIcon(imagenes[im]);
            if(im != 0) {
            	jugar.setEnabled(false);
            	config.setEnabled(false);
            }
            else {
            	jugar.setEnabled(true);
            	config.setEnabled(true);
            }
        }
        
        if (e.getSource() == izq) {
        	im = im-1;
        	if(im == -1)
        		im=2;
            etiquetaImagen.setIcon(imagenes[im]);
            if(im != 0) {
            	jugar.setEnabled(false);
            	config.setEnabled(false);
            }
            else {
            	jugar.setEnabled(true);
            	config.setEnabled(true);
            }
        }

        if (e.getSource() == jugar) {
        	BattleOfMidway game = new BattleOfMidway(Configuracion.leerConfiguracion().getProperty("nombreJugador"));
            Thread t = new Thread() {
                @Override
                public void run() {
                    game.run(1.5 / 60.0);
                }
            };
            t.start();
            this.setEnabled(false);
        }
       
        if (e.getSource() == config) {
        	
        	 Configuracion frame2 = new Configuracion(this);
             frame2.setVisible(true);
             setEnabled(false); 
       
        	
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Menu();
            }
        });
    }

	
	
	
	

	

}
