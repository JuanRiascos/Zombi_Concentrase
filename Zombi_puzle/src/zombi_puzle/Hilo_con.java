/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombi_puzle;



/**
 *
 * @author Juan
 */
public class Hilo_con extends Thread {

    Niveles listo;

    public Hilo_con(Niveles x) {
        listo = x;
    }

    public void run() {
//        listo.setVisible(true);
        listo.Quitar_Mostrar();
        for (int j = 0; j < 10; j++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
        listo.Mostrar_Hil();
    }

}
