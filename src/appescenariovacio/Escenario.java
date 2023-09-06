package appescenariovacio;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Timer;
import javafx.util.Pair;
import javax.swing.JComponent;
import javax.swing.JOptionPane;


public class Escenario extends JComponent implements Constantes {
    
    public Celda[][] celdas;
    public Celda[] resultado;
    public int xMov;
    public int yMov;
    public Lienzo lienzo;
        public Jugador jugador;
    public Adversario adversario, adversario2, adversario3;
    public Timer lanzadorTareas;
    private final int alturaLaberinto, anchuraLaberinto;
    public int recompensasRecogidas;
    public int recompensasAdversarios[];
    private boolean aviso;
    public Escenario(Lienzo lienzo) {
          /*      int contadorRecompensas=5,
            contadorAdversarios=3,
            contadorFinal=1;  
            //contadorObstaculos= (int) (celdas.length*0.85);
        //int num = contadorRecompensas+contadorObstaculos+contadorAdversarios+contadorFinal+1;
        resultado = new Celda[23];
       celdas=new Celda[NUMERO_CELDAS_ANCHO][NUMERO_CELDAS_LARGO];
       /* Inicializar el array de celdas */
      /* for(int i=0; i < NUMERO_CELDAS_ANCHO; i++){
          for ( int j=0 ; j <  NUMERO_CELDAS_LARGO ; j++) {
              celdas[i][j]=new Celda(i*PIXEL_CELDA+10,j*PIXEL_CELDA+10);

            }
       }
       xMov=0;
        yMov=0;
        celdas[xMov][yMov].celdaSeleccionada=true;
        celdas[xMov][yMov].estados = 'J';
        
        
                for(int i = 1; i<23; i++){
            int rand = (int)(Math.random()*NUMERO_CELDAS_ANCHO);
            int rand2 = (int)(Math.random()*NUMERO_CELDAS_LARGO);
            resultado[i] = new Celda(rand,rand2);
            resultado[0] = new Celda(0,0);
        }
        
        for(int i = 0; i<23; i++){
            for(int j = 0; j<23; j++){
                if(resultado[i].x == resultado[j].x && resultado[i].y == resultado[j].y && i != j){
                    int rand = (int)(Math.random()*NUMERO_CELDAS_ANCHO);
                    int rand2 = (int)(Math.random()*NUMERO_CELDAS_LARGO);
                    resultado[i] = new Celda(rand,rand2);
                    i=0;
                }
                if(resultado[0].x == resultado [j].x && resultado[0].y == resultado [j].y){
                    resultado[0] = resultado[j];
                   
                }
            }
        }*/
        
         this.lienzo = lienzo;
        celdas = new Celda[NUMERO_CELDAS_ANCHO][NUMERO_CELDAS_LARGO];
        /* Inicializar el array de celdas */
        for (int i = 0; i < NUMERO_CELDAS_ANCHO; i++) {
            for (int j = 0; j < NUMERO_CELDAS_LARGO; j++) {
                celdas[i][j] = new Celda(i + (i * PIXEL_CELDA), j + (j * PIXEL_CELDA), CAMINO);
            }
        }

        this.recompensasAdversarios = new int[TOTAL_ADVERSARIOS];
        jugador = new Jugador(this, numeroAleatorio(RANGO_INICIAL, RANGO_FINAL));
        celdas[5][5].estados = JUGADOR;

        adversario = new Adversario(this, 7, 7, true, 1);
        adversario2 = new Adversario(this, 10, 9, false, 2);
        adversario3 = new Adversario(this, 1, 2, false, 3);

        celdas[7][7].estados = ADVERSARIO;
        celdas[10][9].estados = ADVERSARIO;
        celdas[1][2].estados = ADVERSARIO;
        //celdas[3][3].tipo = OBSTACULO;
        ubicarEntidadesAleatorias(7, OBSTACULO);
        celdas[8][1].estados = FINAL;


        /*
        celdas[0][5].tipo = RECOMPENSA;
        celdas[5][0].tipo = RECOMPENSA;
        celdas[3][5].tipo = RECOMPENSA;
        celdas[5][3].tipo = RECOMPENSA;
         */
        ubicarEntidadesAleatorias(TOTAL_RECOMPENSAS, RECOMPENSA);

        this.anchuraLaberinto = ANCHURA_ESCENARIO * PIXEL_CELDA;
        this.alturaLaberinto = LARGO_ESCENARIO * PIXEL_CELDA;

        this.setSize(this.anchuraLaberinto, this.alturaLaberinto);

        lanzadorTareas = new Timer();
        lanzadorTareas.scheduleAtFixedRate(adversario, 0, 1000);
        lanzadorTareas.scheduleAtFixedRate(adversario2, 0, 500);
        lanzadorTareas.scheduleAtFixedRate(adversario3, 0, 750);
        
        
        lanzadorTareas.scheduleAtFixedRate(jugador.inteligencia, 0, 1000);
    }
    
    
        public void vaciarEscenario() {
        
        for(int i=0; i < NUMERO_CELDAS_ANCHO; i++)
          for ( int j=0 ; j <  NUMERO_CELDAS_LARGO ; j++) 
             if ( celdas[i][j].celdaSeleccionada)
                 celdas[i][j].celdaSeleccionada=false;
        
        
    }
    
        public Celda darCeldaSeleccionada() {
        Celda seleccionada=null;
        for(int i=0; i < NUMERO_CELDAS_ANCHO; i++)
          for ( int j=0 ; j <  NUMERO_CELDAS_LARGO ; j++) 
             if ( celdas[i][j].celdaSeleccionada)
                 seleccionada=celdas[i][j];
        
        return seleccionada;
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
    /*  for(int i=0; i < NUMERO_CELDAS_ANCHO ; i++) 
            for ( int j=0 ; j < NUMERO_CELDAS_LARGO; j++) 
                celdas[i][j].paintComponent(g); 
        
        if(celdas[xMov][yMov].celdaSeleccionada == true){
            celdas[xMov][yMov].estados = 'J';
            celdas[xMov][yMov].paintJugador(g);
        }
        
        //3
        for(int a =1; a<4; a++){
            celdas[resultado[a].x][resultado[a].y].estados = 'A';
            celdas[resultado[a].x][resultado[a].y].paintAdversario(g);
        }
        //5
        for(int a = 4; a<9; a++){
            celdas[resultado[a].x][resultado[a].y].estados = 'R';
            celdas[resultado[a].x][resultado[a].y].paintRecompensas(g);
        }
        //5% 12
        for(int a =9; a<21; a++){
            celdas[resultado[a].x][resultado[a].y].estados = 'O';
            celdas[resultado[a].x][resultado[a].y].paintObstaculos(g); 
        }
        celdas[resultado[21].x][resultado[21].y].estados = 'F';
        celdas[resultado[21].x][resultado[21].y].paintFinal(g);
*/
                for (int i = 0; i < NUMERO_CELDAS_ANCHO; i++) {
            for (int j = 0; j < NUMERO_CELDAS_LARGO; j++) {
                celdas[i][j].paintComponent(g);
            }
        }

        if (darCeldaJugador() != null) {
            g.drawString("♥: " + jugador.energia + " R: " + this.recompensasRecogidas,
                    darCeldaJugador().x + 10, //45,
                    darCeldaJugador().y + 10);
        }
        
        g.drawString("Recompensas : " + this.recompensasRestantes(), 10, 10);
    }
   
        public void moverCelda( KeyEvent evento ) {
 
        /* Verifica que quede energía */
        if (jugador.energia <= 0) {
            JOptionPane.showMessageDialog(null, "Fin del juego\nLogró recoger " + this.recompensasRecogidas + " recompensas de un total de " + TOTAL_RECOMPENSAS);
            System.exit(1);
        }
        
        /* Verifica que queden recompensas */
        if (!this.aviso && this.recompensasRestantes() == 0) {
            JOptionPane.showMessageDialog(null, "No hay recompensas. Vaya al tunel");
            this.aviso = true;
        }
        
        /* Analiza la tecla presionada */
        switch (evento.getKeyCode()) {

            case KeyEvent.VK_UP:

                System.out.println("Mover arriba");
                jugador.moverArriba();

                break;

            case KeyEvent.VK_DOWN:

                System.out.println("Mover abajo");
                jugador.moverAbajo();

                break;
                
            case KeyEvent.VK_LEFT:

                System.out.println("Mover izquierda");
                jugador.moverIzquierda();

                break;

            case KeyEvent.VK_RIGHT:

                System.out.println("Mover derecha");
                jugador.moverDerecha();

                break;
                
            case KeyEvent.VK_P:  
                System.out.println("Pausa");
               try{ 
                   Thread.sleep(3000); 
               }catch(InterruptedException e)
               { 
                   System.out.println("Thread Interrupted"); 
               }
               /*
               JOptionPane.showMessageDialog(null, "Detenido");
               System.exit(1);
               */
               break;
 
        }

      
    }
     
        public Celda darCeldaJugador() {
        Celda jug = null;
        for (int i = 0; i < NUMERO_CELDAS_ANCHO; i++) {
            for (int j = 0; j < NUMERO_CELDAS_LARGO; j++) {
                if (celdas[i][j].estados == JUGADOR) {
                    jug = celdas[i][j];
                    break;
                }
            }
        }

        return jug;
    }
    
    
    private void moverCeldaArriba(){
         
        if (yMov > 0 ) {
            if(celdas[xMov][yMov-1].estados!='O'){
             celdas[xMov][yMov].celdaSeleccionada=false;
           yMov-=1;
           celdas[xMov][yMov].celdaSeleccionada=true;
         }
            
        }

    }
     
    private void moverCeldaAbajo(){
         
        if (yMov+1 < NUMERO_CELDAS_LARGO ) {
            if(celdas[xMov][yMov+1].estados!='O'){
                celdas[xMov][yMov].celdaSeleccionada=false;
           yMov+=1;
           celdas[xMov][yMov].celdaSeleccionada=true; 
            }
           
        }
         
    }
     
    private void moverCeldaIzquierda(){
         
        if (xMov > 0 ) {
            if(celdas[xMov-1][yMov].estados!='O'){
                celdas[xMov][yMov].celdaSeleccionada=false;
           xMov-=1;
           celdas[xMov][yMov].celdaSeleccionada=true; 
            }
           
        }
         
    }
     
    private void moverCeldaDerecha(){
         
        if (xMov+1 < NUMERO_CELDAS_ANCHO  ) {
            if(celdas[xMov+1][yMov].estados!='O'){
                celdas[xMov][yMov].celdaSeleccionada=false;
           xMov+=1;
           celdas[xMov][yMov].celdaSeleccionada=true; 
            }
           
        }
         
    }
        public int recompensasRestantes() {
        int total = 0;
        for (int i = 0; i < TOTAL_ADVERSARIOS; i++) {
            total += this.recompensasAdversarios[i];
        }
        return TOTAL_RECOMPENSAS - this.recompensasRecogidas - total;
    }
        
        
            private void ubicarEntidadesAleatorias(int total, char tipo) {
        int i = 0;
        while (i < total) {
            int yRandom = numeroAleatorio(2, NUMERO_CELDAS_LARGO - 1);
            int xRandom = numeroAleatorio(2, NUMERO_CELDAS_ANCHO - 2);
            if (celdas[xRandom][yRandom].estados == CAMINO) {
                celdas[xRandom][yRandom].estados = tipo;
                i += 1;
            }
        }
    }
            
            public Pair<Integer, Integer> darCeldaTipo(char tipoC){
                Pair<Integer, Integer> celda = null;
                for(int i=0;i<NUMERO_CELDAS_ANCHO;i++){
                    for(int j=0;j<NUMERO_CELDAS_LARGO; j++){
                        if(celdas[i][j].estados == tipoC){
                            celda = new Pair(i, j);

                            break;
                        }
                    }
                }
                return celda;
            }
}
