/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombi_puzle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Juan David Restrepo Riascos <your.name at your.org>
 */
public class Reloj extends Thread {

    JLabel x, pun;
    int j, k;
    JFrame ven;

    public Reloj(JLabel y, JFrame ventana, JLabel puntaje) {
        x = y;
        j = 0;
        k = 0;
        pun = puntaje;
        ven = ventana;

    }

    public void run() {

        for (j = 5; j > 0; j--) {
            this.x.setText(Transformacion(j) + ":" + Transformacion(k));
            for (k = 60; k > 0; k--) {
                this.x.setText(Transformacion(j) + ":" + Transformacion(k));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
            }
            k = 0;
        }
        JOptionPane.showMessageDialog(ven, "He toche, ha dejado terminar el Tiempo " + "Su puntaje ha sido de: " + pun.getText(), "Error", JOptionPane.OK_OPTION);
        ven.dispose();
    }

    public void pausa() {
        j = 0;
        k = 0;
    }

    String Transformacion(int x) {
        return x > 9 ? "" + x : "0" + x;
    }
}
