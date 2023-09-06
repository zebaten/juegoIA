package appescenariovacio;



import java.awt.Dimension;
import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame implements Constantes{
    
    //nuestra clase se compone de un lienzo de dibujo (herada de canvas)
    public Lienzo lienzo;
    public Dimension sizeScreen;

    //constructor
    public VentanaPrincipal() {
        lienzo=new Lienzo();
        this.getContentPane().add(lienzo);
        sizeScreen=super.getToolkit().getScreenSize();
        //el tamaño de la venta es la del escenario y el incremento de los bordes
        this.setSize(lienzo.getWidth(),lienzo.getHeight()); 
        setResizable(false);
    }
    
}
