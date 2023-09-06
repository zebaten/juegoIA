package appescenariovacio;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Celda extends JComponent implements Constantes {
    // Atributos
    public int x;
    public int y;
    public boolean celdaSeleccionada;
    public Rectangle rectanguloCelda;
    public char estado[] = {'J','A','O','R','F','V'};
    public char estados;
        public BufferedImage jugador, obstaculo, adversario, portal, recompensa;

    private boolean usaImagen = true;

    public int indexSprite;
    public BufferedImage sprites[], imagenSprites, imagenSpritesAdversario, spritesAdversario[];
    // Constructor, inicializa los atributos
    public Celda(int x,int y, char estados) {
        this.x=x;
        this.y=y;
        this.estados = 'V';
        this.celdaSeleccionada=false; 
        indexSprite = 0;  
        
        rectanguloCelda=new Rectangle(x,y,PIXEL_CELDA,PIXEL_CELDA);
                try {
            jugador = ImageIO.read(new File("src/imagenes/jugador.png"));
            obstaculo = ImageIO.read(new File("src/imagenes/obstaculo.png"));
            recompensa = ImageIO.read(new File("src/imagenes/recompensa.png"));
            portal = ImageIO.read(new File("src/imagenes/fin.png"));
            imagenSprites = ImageIO.read(new File("src/imagenes/sprite_jugador.png"));
            imagenSpritesAdversario = ImageIO.read(new File("src/imagenes/sprite_adversario.png"));
            /* Crea arreglos para guardar los sprite */
            sprites = new BufferedImage[4 * 1];
            spritesAdversario = new BufferedImage[4 * 1];
            /* Se recorre separando las imágenes */
            for (int i = 0; i < 1; i++) {
                for (int j = 0; j < 4; j++) {
                    sprites[(i * 4) + j] = imagenSprites.getSubimage(i * PIXEL_CELDA, j * PIXEL_CELDA,PIXEL_CELDA, PIXEL_CELDA);
                    spritesAdversario[(i * 4) + j] = imagenSpritesAdversario.getSubimage(i * PIXEL_CELDA, j * PIXEL_CELDA, PIXEL_CELDA, PIXEL_CELDA);
                }
            }
            adversario = spritesAdversario[indexSprite];
        } catch (IOException error) {
            System.out.println("Error : " + error.toString());
        }
    }
    
    
    
        @Override
    public void update(Graphics g) {
        if (!usaImagen) {
            switch (estados) {
                case JUGADOR:
                    g.setColor(COLOR_JUGADOR);
                    break;
                case OBSTACULO:
                    g.setColor(COLOR_OBSTACULO);
                    break;
                case CAMINO:
                    g.setColor(COLOR_CAMINO);
                    break;
                case ADVERSARIO:
                    g.setColor(COLOR_ADVERSARIO);
                    break;
                case FINAL:
                    g.setColor(COLOR_RECOMPENSA);
                    break;
            }
            g.fillRect(x, y, PIXEL_CELDA, PIXEL_CELDA);
        } else {
            switch (estados) {
                case JUGADOR:
                    g.drawImage(jugador, x, y, null);
                    break;
                case OBSTACULO:
                    g.drawImage(obstaculo, x, y, null);
                    break;
                case CAMINO:
                    //g.drawImage(camino,x,y,null);
                    break;
                case ADVERSARIO:
                    g.drawImage(adversario, x, y, null);
                    break;
                case FINAL:
                    g.drawImage(portal, x, y, null);
                    break;
                case RECOMPENSA:
                    g.drawImage(recompensa, x, y, null);
                    break;                    
            }

        }

    }

    /* Método para dibujar una casilla */
    @Override
    public void paintComponent(Graphics g) {
        update(g);
    }
    
    
    /*
    // Método de JComponent para pintar en un contexto grafico
    @Override
    public void paintComponent(Graphics g) {  
        g.drawRect(x,y,PIXEL_CELDA,PIXEL_CELDA);
        g.setColor(Color.black);
        if ( celdaSeleccionada ) {
            g.setColor(Color.black);
            g.fillRect(x,y,PIXEL_CELDA,PIXEL_CELDA);
            g.setColor(Color.green);
            g.fillRect(x,y,PIXEL_CELDA-15,PIXEL_CELDA-15);
            g.setColor(Color.black);
            g.drawChars (estado,0, 1, this.x+10, this.y+15);
            
        }
    }*/
    public void seleccionarCelda(int x,int y){
        celdaSeleccionada = rectanguloCelda.contains(new Point(x,y));
        
    }
    
/*
        public void paintJugador(Graphics g)
    {                                      
        g.setColor(Color.GREEN);
        g.fillRect(this.x, this.y,PIXEL_CELDA ,PIXEL_CELDA);
        g.setColor(Color.black);
        g.drawChars (estado,0, 1, this.x+10, this.y+15);
        
    }
    // minimo 3 adversarios, recompensas 5
    public void paintAdversario(Graphics g)
    {
        g.setColor(Color.BLUE);
        g.fillRect(this.x, this.y,PIXEL_CELDA ,PIXEL_CELDA);
        g.setColor(Color.GREEN);
        g.drawChars (estado,1, 1, this.x+10, this.y+15);
    }
    
    public void paintObstaculos(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(this.x, this.y,PIXEL_CELDA ,PIXEL_CELDA);
        g.setColor(Color.white);
        g.drawChars (estado,2, 1, this.x+10, this.y+15);
    }
    
    public void paintRecompensas(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect(this.x, this.y,PIXEL_CELDA ,PIXEL_CELDA);
        g.setColor(Color.white);
        g.drawChars (estado,3, 1, this.x+10, this.y+15);
    }  
    
    public void paintFinal(Graphics g)
    {
        g.setColor(Color.PINK);
        g.fillRect(this.x, this.y,PIXEL_CELDA ,PIXEL_CELDA);
        g.setColor(Color.white);
        g.drawChars (estado,4, 1, this.x+10, this.y+15);
    }  
}*/  

    
}
