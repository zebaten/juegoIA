package appescenariovacio;

import java.awt.Color;
import java.util.Random;


public interface Constantes {
    //size of the cells
    public final int PIXEL_CELDA=64;
    //number of cells - width
    public final int NUMERO_CELDAS_ANCHO=30;
    //number of cells - height
    public final int NUMERO_CELDAS_LARGO=15;
    //size of the stage
    public final int ANCHO_BORDE_VENTANA=30;
    public final int LARGO_BORDE_VENTANA=50;
    
    public final int ANCHURA_ESCENARIO=(PIXEL_CELDA*NUMERO_CELDAS_ANCHO)+
            ANCHO_BORDE_VENTANA;
    public final int LARGO_ESCENARIO=(PIXEL_CELDA*NUMERO_CELDAS_LARGO)+
            LARGO_BORDE_VENTANA;
    
public final char JUGADOR = 'J';
public final char RECOMPENSA='R';
public final char OBSTACULO='O';
public final char ADVERSARIO='A';
public final char FINAL='F';
public final char CAMINO = 'V';


    public final Color COLOR_JUGADOR = Color.BLUE;
    public final Color COLOR_CAMINO = Color.GREEN;
    public final Color COLOR_OBSTACULO = Color.BLACK;
    public final Color COLOR_ADVERSARIO = Color.RED;
    public final Color COLOR_RECOMPENSA = Color.MAGENTA;
    public final Color COLOR_PORTAL = Color.CYAN;
    
    final int TOTAL_RECOMPENSAS = 5;
    final int TOTAL_ADVERSARIOS = 2;
    
        final int RANGO_INICIAL = 20;
    final int RANGO_FINAL = 50;
    
    
        final int CHOQUE_ADVERSARIO = 10;
    final int CAPTURA_RECOMPENSA = 20;
    final int PERDIDA_MOVIMIENTO = 1;
    
        default int numeroAleatorio(int minimo, int maximo) {
        Random random = new Random();
        int numero_aleatorio = random.nextInt((maximo - minimo) + 1) + minimo;
        return numero_aleatorio;
    }

}
