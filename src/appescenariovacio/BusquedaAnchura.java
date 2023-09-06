
package appescenariovacio;

import static appescenariovacio.Constantes.NUMERO_CELDAS_ANCHO;
import static appescenariovacio.Constantes.NUMERO_CELDAS_LARGO;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 *
 * @author djzeb
 */
public class BusquedaAnchura extends TimerTask implements Constantes{
    public Escenario escenario;
    public ArrayList<Estado> lstEstado;
    public ArrayList<Estado> historial;
    public ArrayList<Character> pasos;
    
    public int indexPasos;
    public Estado inicial;
    public Estado objetivo;
    public Estado temporal;
    
    
    public boolean exito;
    
    public BusquedaAnchura(Escenario escenario){
        this.escenario = escenario;
        this.lstEstado = new ArrayList();
        this.historial = new ArrayList();
        this.pasos = new ArrayList();
    }
    
    public void buscar(int x1, int y1, int x2, int y2){
        this.inicial = new Estado(x1, y1, 'N', null);
        this.objetivo = new Estado(x2, y2, 'P', null);
        this.lstEstado.add(this.inicial);
        this.historial.add(this.inicial);
        
        this.exito = this.inicial.equals(this.objetivo);
        
        while(!this.lstEstado.isEmpty() && !this.exito){
            this.temporal = this.lstEstado.get(0);
            this.lstEstado.remove(0);
            this.historial.add(this.temporal);
            
            this.moverArriba(this.temporal);
            this.moverAbajo(this.temporal);
            this.moverIzquierda(this.temporal);
            this.moverDerecha(this.temporal);
        }
        if(this.exito){
            System.out.println("Ruta calculada"); 
        }
        else{
            System.out.println("Ruta sin calculada");
        }
    }
    private void moverDerecha(Estado e){
        if(e.x < NUMERO_CELDAS_ANCHO -1){
            if(this.escenario.celdas[e.x +1][e.y].estados != OBSTACULO){
                Estado derecha = new Estado(e.x +1, e.y, 'R',e);
                if(!this.historial.contains(derecha)){
                    this.lstEstado.add(derecha);
                    if(derecha.equals(this.objetivo)){
                        this.objetivo = derecha;
                        this.exito = true;
                    }
                }
            }
        }
    }
    private void moverArriba(Estado e){
        if(e.y > 0){
            if(this.escenario.celdas[e.x][e.y -1].estados != OBSTACULO){
                Estado arriba = new Estado(e.x, e.y-1, 'U',e);
                if(!this.historial.contains(arriba)){
                    this.lstEstado.add(arriba);
                    if(arriba.equals(this.objetivo)){
                        this.objetivo = arriba;
                        this.exito = true;
                    }
                }
            }
        }
    }
    
    private void moverAbajo(Estado e){
        if(e.y < NUMERO_CELDAS_LARGO -1){
            if(this.escenario.celdas[e.x][e.y +1].estados != OBSTACULO){
                Estado abajo = new Estado(e.x, e.y +1, 'D',e);
                if(!this.historial.contains(abajo)){
                    this.lstEstado.add(abajo);
                    if(abajo.equals(this.objetivo)){
                        this.objetivo = abajo;
                        this.exito = true;
                    }
                }
            }
        }
    }
    
    private void moverIzquierda(Estado e){
        if(e.x > 0){
            if(this.escenario.celdas[e.x -1][e.y].estados != OBSTACULO){
                Estado izquierda = new Estado(e.x -1, e.y, 'L',e);
                if(!this.historial.contains(izquierda)){
                    this.lstEstado.add(izquierda);
                    if(izquierda.equals(this.objetivo)){
                        this.objetivo = izquierda;
                        this.exito = true;
                    }
                }
            }
        }
    }
    public void calcularRuta(){
        Estado predecesor = this.objetivo;
        do{
            this.pasos.add(predecesor.operacion);
            predecesor = predecesor.predecesor;
        }while(predecesor != null);
        this.indexPasos = this.pasos.size() -1;
    }
    
    private void resetear(){
        System.out.println("Reseteando");
        this.lstEstado.clear();
        this.pasos.clear();
        this.historial.clear();
        this.indexPasos = 0;
        this.exito = false;
    }
    
    public char darMovimiento(){ 
        return this.pasos.get(indexPasos-1);
        
    }
    @Override
    public synchronized void run(){
        this.escenario.jugador.inteligencia.resetear();
        if(this.escenario.darCeldaTipo('J') == null){
            JOptionPane.showMessageDialog(escenario,"Ha sido capturado por el enemigo",
                                        "Fin del juego", JOptionPane.ERROR_MESSAGE);
            this.cancel();
            this.escenario.lanzadorTareas.cancel();
            System.exit(0);
        }
        int x_jugador = this.escenario.darCeldaTipo('J').getKey();
        int y_jugador = this.escenario.darCeldaTipo('J').getValue();
       
        if (this.escenario.darCeldaTipo('F')== null){
            JOptionPane.showMessageDialog(escenario, "Ha capturado todas las recompensas.\n Su puntaje es "
                                            + this.escenario.jugador.energia, "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
        this.cancel();
        this.escenario.lanzadorTareas.cancel();
        System.exit(0);
        }
        int x_recompensa = this.escenario.darCeldaTipo('F').getKey();
        int y_recompensa = this.escenario.darCeldaTipo('F').getValue();
        
         
        
        this.buscar(x_jugador, y_jugador, x_recompensa, y_recompensa);
        this.calcularRuta();
        switch(this.darMovimiento()){
            case 'D':
                this.escenario.jugador.moverAbajo();
                break;
            case 'U':
                this.escenario.jugador.moverArriba();
                break;
            case 'R':
                this.escenario.jugador.moverDerecha();
                break;
            case 'L':
                this.escenario.jugador.moverIzquierda();
                break;
        }
        
        this.escenario.lienzo.repaint();
    }
}
