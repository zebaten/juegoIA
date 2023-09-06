/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appescenariovacio;

/**
 *
 * @author djzeb
 */
import java.util.TimerTask;

public class Adversario extends TimerTask implements Constantes {

    public int posicionX;
    public int posicionY;
    public Escenario escenario;

    public Celda adversario;

    public boolean movimientoVertical;
    
    public int numeroAdversario;

    public Adversario(Escenario escenario, int xi, int yi, boolean movimientoVertical, int numero) {
        posicionX = xi;
        posicionY = yi;
        adversario = escenario.celdas[xi][yi];
        this.escenario = escenario;
        this.movimientoVertical = movimientoVertical;
        this.numeroAdversario = numero;
    }

    public void moverArriba() {
        if (posicionY > 0) {
            if (escenario.celdas[posicionX][posicionY - 1].estados != OBSTACULO
                    && escenario.celdas[posicionX][posicionY - 1].estados != RECOMPENSA
                    && escenario.celdas[posicionX][posicionY - 1].estados != JUGADOR
                    && escenario.celdas[posicionX][posicionY - 1].estados != FINAL) {

                escenario.celdas[posicionX][posicionY].estados = CAMINO;
                posicionY = posicionY - 1;
                escenario.celdas[posicionX][posicionY].estados = ADVERSARIO;
            }
        }
    }

    public void moverAbajo() {
        if (posicionY + 1 < NUMERO_CELDAS_LARGO) {
            if (escenario.celdas[posicionX][posicionY + 1].estados != OBSTACULO
                    && escenario.celdas[posicionX][posicionY + 1].estados != RECOMPENSA
                    && escenario.celdas[posicionX][posicionY + 1].estados != JUGADOR
                    && escenario.celdas[posicionX][posicionY + 1].estados != FINAL) {

                escenario.celdas[posicionX][posicionY].estados = CAMINO;
                posicionY = posicionY + 1;
                escenario.celdas[posicionX][posicionY].estados = ADVERSARIO;

            }
        }
    }

    public void moverIzquierda() {
        if (posicionX > 0) {
            if (escenario.celdas[posicionX - 1][posicionY].estados != OBSTACULO
                    && escenario.celdas[posicionX - 1][posicionY].estados != RECOMPENSA
                    && escenario.celdas[posicionX - 1][posicionY].estados != JUGADOR
                    && escenario.celdas[posicionX - 1][posicionY].estados != FINAL) {

                escenario.celdas[posicionX][posicionY].estados = CAMINO;
                posicionX = posicionX - 1;
                escenario.celdas[posicionX][posicionY].estados = ADVERSARIO;
            }
        }
    }

    public void moverDerecha() {
        if (posicionX + 1 < NUMERO_CELDAS_ANCHO) {
            if (escenario.celdas[posicionX + 1][posicionY].estados != OBSTACULO
                    && escenario.celdas[posicionX + 1][posicionY].estados != RECOMPENSA
                    && escenario.celdas[posicionX + 1][posicionY].estados != JUGADOR
                    && escenario.celdas[posicionX + 1][posicionY].estados != FINAL) {
                escenario.celdas[posicionX][posicionY].estados = CAMINO;
                posicionX = posicionX + 1;
                escenario.celdas[posicionX][posicionY].estados = ADVERSARIO;
            }

        }
    }

    public void moverAdversarioHorizontal() {
        if (posicionX > 0 && escenario.celdas[posicionX - 1][posicionY].estados != FINAL
                && escenario.celdas[posicionX - 1][posicionY].estados != ADVERSARIO
                && escenario.celdas[posicionX - 1][posicionY].estados != OBSTACULO) {

            if (escenario.celdas[posicionX - 1][posicionY].estados == JUGADOR) {
                escenario.celdas[posicionX][posicionY].estados = CAMINO;
                /* Deja al jugador donde estaba */
                escenario.celdas[posicionX - 1][posicionY].estados = JUGADOR;
                escenario.jugador.energia -= CHOQUE_ADVERSARIO;
                /* El adversario pasa por sobre el jugador */
                posicionX = posicionX - 2;
                /* Comprueba que si está ocupada la nueva casilla */
                if (escenario.celdas[posicionX][posicionY].estados != CAMINO) {
                    posicionX = NUMERO_CELDAS_ANCHO - 1;
                    posicionY = numeroAleatorio(2, NUMERO_CELDAS_LARGO - 1);
                }
                escenario.celdas[posicionX][posicionY].estados = ADVERSARIO;
            } else {

                if (this.escenario.celdas[posicionX-1][posicionY].estados == RECOMPENSA) {                    
                    this.escenario.recompensasAdversarios[this.numeroAdversario-1] += 1;
                    System.out.println("Adversario toma una recompensa LLEVA " +  this.escenario.recompensasAdversarios[this.numeroAdversario-1]);
                }
                escenario.celdas[posicionX][posicionY].estados = CAMINO;
                posicionX = posicionX - 1;

                escenario.celdas[posicionX][posicionY].estados = ADVERSARIO;
                escenario.celdas[posicionX][posicionY].indexSprite = 1;
                escenario.celdas[posicionX][posicionY].adversario = escenario.celdas[posicionX][posicionY].spritesAdversario[escenario.celdas[posicionX][posicionY].indexSprite];
            }
        } else {

            escenario.celdas[posicionX][posicionY].estados = CAMINO;

            posicionX = NUMERO_CELDAS_ANCHO - 1;
            posicionY = numeroAleatorio(2, NUMERO_CELDAS_LARGO - 1);

            escenario.celdas[posicionX][posicionY].estados = ADVERSARIO;
        }
    }

    public void moverAdversarioVertical() {
        if (posicionY > 0 && escenario.celdas[posicionX][posicionY - 1].estados != FINAL
                && escenario.celdas[posicionX][posicionY - 1].estados != OBSTACULO
                && escenario.celdas[posicionX][posicionY - 1].estados != ADVERSARIO) {

            if (escenario.celdas[posicionX][posicionY - 1].estados == JUGADOR) {
                escenario.celdas[posicionX][posicionY].estados = CAMINO;
                /* Deja al jugador donde estaba */
                escenario.celdas[posicionX][posicionY - 1].estados = JUGADOR;
                escenario.jugador.energia -= CHOQUE_ADVERSARIO;
                /* El adversario pasa por sobre el jugador */
                posicionY = posicionY - 2;
                if (escenario.celdas[posicionX][posicionY].estados != CAMINO) {
                    posicionY = NUMERO_CELDAS_LARGO - 1;
                    posicionX = numeroAleatorio(2, NUMERO_CELDAS_ANCHO - 1);
                }
                escenario.celdas[posicionX][posicionY].estados = ADVERSARIO;
                /* Agregado 2019-II */
                escenario.celdas[posicionX][posicionY].indexSprite = 3;
                escenario.celdas[posicionX][posicionY].adversario = escenario.celdas[posicionX][posicionY].spritesAdversario[escenario.celdas[posicionX][posicionY].indexSprite];
            } else {
                if (this.escenario.celdas[posicionX][posicionY-1].estados == RECOMPENSA) {                    
                    this.escenario.recompensasAdversarios[this.numeroAdversario-1] += 1;
                    System.out.println("Adversario toma una recompensa LLEVA " +  this.escenario.recompensasAdversarios[this.numeroAdversario-1]);
                }

                escenario.celdas[posicionX][posicionY].estados = CAMINO;
                posicionY = posicionY - 1;
                escenario.celdas[posicionX][posicionY].estados = ADVERSARIO;
                /* Agregado 2019-II */
                escenario.celdas[posicionX][posicionY].indexSprite = 3;
                escenario.celdas[posicionX][posicionY].adversario = escenario.celdas[posicionX][posicionY].spritesAdversario[escenario.celdas[posicionX][posicionY].indexSprite];

            }

        } else {
            escenario.celdas[posicionX][posicionY].estados = CAMINO;

            posicionY = NUMERO_CELDAS_LARGO - 1;
            posicionX = numeroAleatorio(2, NUMERO_CELDAS_ANCHO - 1);

            escenario.celdas[posicionX][posicionY].estados = ADVERSARIO;
            /* Agregado 2019-II */
            escenario.celdas[posicionX][posicionY].indexSprite = 3;
            escenario.celdas[posicionX][posicionY].adversario = escenario.celdas[posicionX][posicionY].spritesAdversario[escenario.celdas[posicionX][posicionY].indexSprite];
        }
    }

    @Override
    public void run() {
        if (this.movimientoVertical) {
            moverAdversarioVertical();
        } else {
            moverAdversarioHorizontal();
        }
        escenario.lienzo.repaint();
    }

}
