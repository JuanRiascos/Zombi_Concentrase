/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombi_puzle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Invit
 */
public class Niveles2 extends Niveles {

    public Niveles2(int tamx, int tamy, JPanel c, boolean re, JLabel puntaje,JFrame fra) {
        super(tamx, tamy, c, re, puntaje,fra);
        this.Url = "/Imagenes/PL2.jpg";
        this.numero = 11;
        this.Url_mu = "/Sonido/Dragon.wav";
        this.Url_mf = "/Sonido/RESIDENT_EVIL_ZOMBIES_SOUNDS.wav";
        this.seg = 25;
    }
    

}
