/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appescenariovacio;

/**
 *
 * @author Giancarlo
 */
import javax.swing.JOptionPane;

public class Jugador implements Constantes {

    public int posicionX;
    public int posicionY;
    public Escenario escenario;
    public int energia;
    
    public BusquedaAnchura inteligencia;

    public Jugador(Escenario escenario, int energia) {
        posicionX = 5;
        posicionY = 5;
        this.escenario = escenario;
        this.energia = energia;
        
        this.inteligencia = new BusquedaAnchura(escenario);
    }

    public void moverArriba() {

        if (posicionY > 0) {
            if (escenario.celdas[posicionX][posicionY - 1].estados != OBSTACULO
                    && escenario.celdas[posicionX][posicionY - 1].estados != FINAL) {
                switch (escenario.celdas[posicionX][posicionY - 1].estados) {
                    case CAMINO:
                        energia -= PERDIDA_MOVIMIENTO;
                        break;
                    case RECOMPENSA:
                        energia += CAPTURA_RECOMPENSA;
                        this.escenario.recompensasRecogidas += 1;
                        break;
                    case ADVERSARIO:
                        energia -= CHOQUE_ADVERSARIO;
                        break;

                }

                escenario.celdas[posicionX][posicionY].estados = CAMINO;
                posicionY = posicionY - 1;
                escenario.celdas[posicionX][posicionY].estados = JUGADOR;
                escenario.celdas[posicionX][posicionY].indexSprite = 3;
                escenario.celdas[posicionX][posicionY].jugador = escenario.celdas[posicionX][posicionY].sprites[escenario.celdas[posicionX][posicionY].indexSprite];
            }
            if (escenario.celdas[posicionX][posicionY - 1].estados == FINAL) {
                int recompensasRestantes = this.escenario.recompensasRestantes();
                if (escenario.recompensasRecogidas == 0) {
                    JOptionPane.showMessageDialog(null, "No puedes escapar si no has capturado hojas\nAún quedan "
                            + (recompensasRestantes) + " recompensas");
                } else {
                    if (recompensasRestantes == 0) {
                        JOptionPane.showMessageDialog(null, "Escapaste con " + this.energia + " de vida y "
                                + this.escenario.recompensasRecogidas + " hojas");
                        System.exit(1);
                    } else {
JOptionPane.showMessageDialog(null, "Escapas con " + this.energia + " de vida y "
                                + this.escenario.recompensasRecogidas + " hojas, ve por las que faltan!");

                    }
                }
            }
        }
    }

    public void moverAbajo() {
        if (posicionY + 1 < NUMERO_CELDAS_LARGO) {
            if (escenario.celdas[posicionX][posicionY + 1].estados != OBSTACULO
                    && escenario.celdas[posicionX][posicionY + 1].estados != FINAL) {
                switch (escenario.celdas[posicionX][posicionY + 1].estados) {

                    case CAMINO:
                        energia -= PERDIDA_MOVIMIENTO;
                        break;
                    case RECOMPENSA:
                        energia += CAPTURA_RECOMPENSA;
                        this.escenario.recompensasRecogidas += 1;
                        break;
                    case ADVERSARIO:
                        energia -= CHOQUE_ADVERSARIO;
                        break;

                }
                escenario.celdas[posicionX][posicionY].estados = CAMINO;
                posicionY = posicionY + 1;
                escenario.celdas[posicionX][posicionY].estados = JUGADOR;
                escenario.celdas[posicionX][posicionY].indexSprite = 0;
                escenario.celdas[posicionX][posicionY].jugador = escenario.celdas[posicionX][posicionY].sprites[escenario.celdas[posicionX][posicionY].indexSprite];
            }
                        if (escenario.celdas[posicionX][posicionY + 1].estados == FINAL) {
                int recompensasRestantes = this.escenario.recompensasRestantes();
                if (escenario.recompensasRecogidas == 0) {
                    JOptionPane.showMessageDialog(null, "No puedes escapar si no has capturado hojas\nAún quedan "
                            + (recompensasRestantes) + " recompensas");
                } else {
                    if (recompensasRestantes == 0) {
                        JOptionPane.showMessageDialog(null, "Escapaste con " + this.energia + " de vida y "
                                + this.escenario.recompensasRecogidas + " hojas");
                        System.exit(1);
                    } else {
JOptionPane.showMessageDialog(null, "Escapas con " + this.energia + " de vida y "
                                + this.escenario.recompensasRecogidas + " hojas, ve por las que faltan!");

                    }
                }
            }
        }
    }

    public void moverIzquierda() {
        if (posicionX > 0) {
            if (escenario.celdas[posicionX - 1][posicionY].estados != OBSTACULO
                    && escenario.celdas[posicionX - 1][posicionY].estados != FINAL) {
                switch (escenario.celdas[posicionX - 1][posicionY].estados) {

                    case CAMINO:
                        energia -= PERDIDA_MOVIMIENTO;
                        break;
                    case RECOMPENSA:
                        energia += CAPTURA_RECOMPENSA;
                        this.escenario.recompensasRecogidas += 1;
                        break;
                    case ADVERSARIO:
                        energia -= CHOQUE_ADVERSARIO;
                        break;

                }
                escenario.celdas[posicionX][posicionY].estados = CAMINO;
                posicionX = posicionX - 1;
                escenario.celdas[posicionX][posicionY].estados = JUGADOR;
                escenario.celdas[posicionX][posicionY].indexSprite = 1;
                escenario.celdas[posicionX][posicionY].jugador = escenario.celdas[posicionX][posicionY].sprites[escenario.celdas[posicionX][posicionY].indexSprite];
            }
                        if (escenario.celdas[posicionX-1][posicionY].estados == FINAL) {
                int recompensasRestantes = this.escenario.recompensasRestantes();
                if (escenario.recompensasRecogidas == 0) {
                    JOptionPane.showMessageDialog(null, "No puedes escapar si no has capturado hojas\nAún quedan "
                            + (recompensasRestantes) + " recompensas");
                } else {
                    if (recompensasRestantes == 0) {
                        JOptionPane.showMessageDialog(null, "Escapaste con " + this.energia + " de vida y "
                                + this.escenario.recompensasRecogidas + " hojas");
                        System.exit(1);
                    } else {
JOptionPane.showMessageDialog(null, "Escapas con " + this.energia + " de vida y "
                                + this.escenario.recompensasRecogidas + " hojas, ve por las que faltan!");

                    }
                }
            }
        }
    }

    public void moverDerecha() {
        if (posicionX + 1 < NUMERO_CELDAS_ANCHO) {
            if (escenario.celdas[posicionX + 1][posicionY].estados != OBSTACULO
                    && escenario.celdas[posicionX + 1][posicionY].estados != FINAL) {
                switch (escenario.celdas[posicionX + 1][posicionY].estados) {

                    case CAMINO:
                        energia -= PERDIDA_MOVIMIENTO;
                        break;
                    case RECOMPENSA:
                        energia += CAPTURA_RECOMPENSA;
                        this.escenario.recompensasRecogidas += 1;
                        break;
                    case ADVERSARIO:
                        energia -= CHOQUE_ADVERSARIO;
                        break;

                }
                escenario.celdas[posicionX][posicionY].estados = CAMINO;
                posicionX = posicionX + 1;
                escenario.celdas[posicionX][posicionY].estados = JUGADOR;
                escenario.celdas[posicionX][posicionY].indexSprite = 2;
                escenario.celdas[posicionX][posicionY].jugador = escenario.celdas[posicionX][posicionY].sprites[escenario.celdas[posicionX][posicionY].indexSprite];
            }
            if (escenario.celdas[posicionX+1][posicionY].estados == FINAL) {
                int recompensasRestantes = this.escenario.recompensasRestantes();
                if (escenario.recompensasRecogidas == 0) {
                    JOptionPane.showMessageDialog(null, "No puedes escapar si no has capturado hojas\nAún quedan "
                            + (recompensasRestantes) + " recompensas");
                } else {
                    if (recompensasRestantes == 0) {
                        JOptionPane.showMessageDialog(null, "Escapaste con " + this.energia + " de vida y "
                                + this.escenario.recompensasRecogidas + " hojas");
                        System.exit(1);
                    } else {
JOptionPane.showMessageDialog(null, "Escapas con " + this.energia + " de vida y "
                                + this.escenario.recompensasRecogidas + " hojas, ve por las que faltan!");

                    }
                }
            }
        }
    }

}

