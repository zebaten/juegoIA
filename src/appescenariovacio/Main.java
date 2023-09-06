package appescenariovacio;

import javax.swing.JFrame;

public class Main {
    
    public static void main (String[]args) {
        
        VentanaPrincipal vp=new VentanaPrincipal();
        vp.setState(JFrame.MAXIMIZED_BOTH);
        
        vp.setVisible(true);
        
        vp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    }
    
}
