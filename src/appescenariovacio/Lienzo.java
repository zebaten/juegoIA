package appescenariovacio;

import static appescenariovacio.Constantes.NUMERO_CELDAS_ANCHO;
import static appescenariovacio.Constantes.NUMERO_CELDAS_LARGO;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Lienzo extends Canvas implements Constantes{
   
    public Escenario escenario;
    
    /* Atributos locales para manejar los eventos del teclado */
    private int xPulsar;
    private int yPulsar;
    private int xSoltar;
    private int ySoltar;
    private int xMoviendo;
    private int yMoviendo;
    private boolean pulsado;
    public Image fondo;

    public Graphics graficoBuffer;
    public Image imagenBuffer;
    public Lienzo(){
        escenario=new Escenario(this);
        this.setSize(ANCHURA_ESCENARIO,LARGO_ESCENARIO); 
         
        try {
            fondo = ImageIO.read(new File("src/imagenes/mapa.jpg"));
            Image tmp= fondo.getScaledInstance(ANCHURA_ESCENARIO, LARGO_ESCENARIO, BufferedImage.SCALE_SMOOTH);
            BufferedImage resized = new BufferedImage(ANCHURA_ESCENARIO, LARGO_ESCENARIO, BufferedImage.TYPE_INT_ARGB);
            Graphics2D gp = resized.createGraphics();
            gp.drawImage(tmp,0,0,null);
            gp.dispose();
            fondo = resized;
        } catch (IOException error) {
            System.out.println("Error al cargar el fondo!!");
        }
        this.setFocusable(true);
        pulsado = false;

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent evt) {
                System.out.println("Se selecciono");
                xPulsar = evt.getX();
                yPulsar = evt.getY();
                pulsado = true;
                activarCelda(xPulsar, yPulsar);
                repaint();

            }

            @Override
            public void mouseReleased(MouseEvent evt) {

                System.out.println("Se solto");
                xSoltar = evt.getX();
                ySoltar = evt.getY();
                pulsado = false;
                activarCelda(xSoltar, ySoltar);
                repaint();

            }

        });

        // Añadimos el escuchador
        addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent evt) {

                escenario.vaciarEscenario();
                modificarPosicion(evt);
                repaint();

            }
        });

        // Escuchador eventos de teclado
         addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                escenario.moverCelda(evt);
                repaint();
            }
        });
    }
    
    /* Método paint para pintar el escenario */
    @Override
    public void paint(Graphics g) {
        update(g);
        
    }
    
    private void activarCelda(int x, int y) {
        for (int i = 0; i < NUMERO_CELDAS_ANCHO; i++) {
            for (int j = 0; j < NUMERO_CELDAS_LARGO; j++) {
                escenario.celdas[i][j].seleccionarCelda(x, y);
            }
        }
    }

    public void modificarPosicion(MouseEvent evt) {

        xMoviendo = evt.getX();
        yMoviendo = evt.getY();

    }
        @Override
    public void update(Graphics g) {
        // Inicialización del buffer gráfico mediante la imagen
        if (graficoBuffer == null) {
            imagenBuffer = createImage(this.getWidth(), this.getHeight());
            graficoBuffer = imagenBuffer.getGraphics();
        }
        // Volcamos color de fondo e imagen en el nuevo buffer grafico
        graficoBuffer.setColor(getBackground());
        graficoBuffer.fillRect(0, 0, this.getWidth(), this.getHeight());
        graficoBuffer.drawImage(fondo, 0, 0, null);
        escenario.update(graficoBuffer);
        // Pintamos la imagen previa
                       if (pulsado) {
            graficoBuffer.fillRect(xMoviendo, yMoviendo, PIXEL_CELDA, PIXEL_CELDA);
            graficoBuffer.setColor(Color.GREEN);
            graficoBuffer.fillRect(xMoviendo, yMoviendo, PIXEL_CELDA, PIXEL_CELDA);
            
        }
        escenario.paintComponent(graficoBuffer);
        
        g.drawImage(imagenBuffer, 0, 0, null);
    }
}
