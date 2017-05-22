/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombi_puzle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Juan David Restrepo Riascos <your.name at your.org>
 */
public class Niveles extends JLabel implements ActionListener {
    
    JButton[][] Boton;
    JButton[][] Suc;
    int x;
    int y;
    int x_p;
    int y_p;
    int numero = 1;
    int[][] t;
    int[][] p;
    int clip;
    JPanel mos;
    JPanel mos2;
    JLabel Puntaje;
    JFrame Experimento;
    
    int[] diga = new int[3];
    int[] d = new int[3];
    boolean re;
    String Url;
    String Url_mu;
    String Url_mf;
    Clip Sonido;
    int seg;
    
    public Niveles() {
    }
    
    public Niveles(int tamx, int tamy, JPanel c, boolean re, JLabel puntaje, JFrame exp) {
        Boton = new JButton[tamx][tamy];
        Suc = new JButton[tamx][tamy];
        this.Puntaje = puntaje;
        x = tamx;
        y = tamy;
        Experimento = exp;
        
        t = new int[tamx][tamy];
        p = new int[tamx][tamy];
        clip = -1;
        mos = c;
        x_p = y_p = 0;
        this.re = re;
//        Sonido();
        numero = 1;
        Url = "/Imagenes/Pl.png";
        Url_mu = "/Sonido/Granade_Granada.wav";
        Url_mf = "/Sonido/Fireworks_Compilation_Fuegos_Artificiales_Pack.wav";
        seg = 10;
//        Llenar_Botones_dos();
    }

//    Niveles(int i, int i0, JPanel Mostrar, boolean b, JLabel Punta, JPanel Mostrar_) {
//        mos2 = Mostrar_;
//    }
    public void Sonido(String x) {
        try {
            Sonido = AudioSystem.getClip();
            Sonido.open(AudioSystem.getAudioInputStream(getClass().getResource(x)));
        } catch (Exception ex) {
        }
    }
    
    public void Mostrar() {
        System.out.println("");
        for (int[] t1 : t) {
            for (int i = 0; i < t1.length; i++) {
                System.out.print("[" + t1[i] + "]");
            }
            System.out.println("");
        }
    }
    
    public void Truco(int x, int y) {
        t[x][y] = numero;
        Mostrar();
    }
    
    public void Llenar_Imagenes() {
        try {
            for (int i = 0; i < 2; i++) {
                int posx = (int) (Math.random() * x);
                int posy = (int) (Math.random() * this.y);
                if (Boton[posx][posy].getIcon() == null) {
                    Boton[posx][posy].setIcon(new ImageIcon(getClass().getResource("/Imagenes/" + numero + ".png")));
                    Truco(posx, posy);
                } else {
                    i--;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        numero++;
        
    }
    
    public void Llenar_Botones() {
        for (JButton[] x1 : Boton) {
            for (int j = 0; j < x1.length; j++) {
                x1[j] = new JButton();
                x1[j].setContentAreaFilled(false);
                x1[j].setFocusable(false);
                x1[j].addActionListener(this);
                mos.add(x1[j]);
                x1[j].setBounds(x_p * (mos.getWidth() / y), y_p * (mos.getHeight() / x), (mos.getWidth() / y), (mos.getHeight() / x));
//                x1[j].setBounds(0, 0, 1, 1);
                x_p++;
            }
            y_p++;
            x_p = 0;
        }
        System.out.println(numero);
        while (numero <= seg) {
            Llenar_Imagenes();
        }
    }
    
    public void Llenar_Botones_dos() {
        for (JButton[] x1 : Suc) {
            for (int j = 0; j < x1.length; j++) {
                x1[j] = new JButton();
                x1[j].setContentAreaFilled(false);
                x1[j].setFocusable(false);
                x1[j].addActionListener(this);
                x1[j].setIcon(new ImageIcon(getClass().getResource(Url)));
                mos.add(x1[j]);
                x1[j].setBounds(x_p * (mos.getWidth() / y), y_p * (mos.getHeight() / x), (mos.getWidth() / y), (mos.getHeight() / x));
                x_p++;
            }
            y_p++;
            x_p = 0;
        }
        
    }
    
    boolean validar() {
        boolean cono = false;
        Salir:
        for (JButton[] Suc1 : Suc) {
            for (JButton Suc11 : Suc1) {
                if (Suc11.isVisible() == true) {
                    cono = true;
                    break Salir;
                } else {
                    cono = false;
                }
            }
        }
        return cono;
    }
    
    public void Mostrar_Hil() {
        clip=-1;
        for (int i = 0; i < Suc.length; i++) {
            for (int j = 0; j < Suc[i].length;) {
                if (p[i][j] != 1) {
                    Suc[i][j].setVisible(true);
                } 
                    j++;
            }
        }
    }

    public void Quitar_Mostrar() {
        for (JButton[] Suc1 : Suc) {
            for (JButton Suc11 : Suc1) {
                Suc11.setVisible(false);
//                Boton[i][j].setIcon(new ImageIcon(getClass().getResource("/Imagenes/" + t[i][j] + "o.png")));
            }
        }
    }

    public void Quitar() {
        for (int i = 0; i < Suc.length; i++) {
            for (int j = 0; j < Suc[i].length; j++) {
                Suc[i][j].setVisible(false);
                Boton[i][j].setIcon(new ImageIcon(getClass().getResource("/Imagenes/" + t[i][j] + "o.png")));
            }
        }
    }
    
    public void Nulidad(int x) {
        numero = x;
        x_p = 0;
        y_p = 0;
        clip = -1;
//        re=true;
        for (int i = 0; i < Suc.length; i++) {
            for (int j = 0; j < Suc[i].length; j++) {
                Suc[i][j].setIcon(new ImageIcon(getClass().getResource(Url)));
                Suc[i][j].setVisible(true);
                Boton[i][j].setIcon(null);
            }
        }
        
        while (numero <= seg) {
            Llenar_Imagenes();
        }
    }
//    public void Salir(){
//        Niveles2 x=new Niveles2(TOP, y, mos, re, Puntaje, Experimento);
//    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (re == true) {
            for (int i = 0; i < Suc.length; i++) {
                for (int j = 0; j < Suc[i].length; j++) {
                    if (ae.getSource() == Suc[i][j]) {
//                        System.out.println("lkkkk");
                        Suc[i][j].setVisible(false);
                        if (validar() == true) {
                            if (clip == 1) {
                                clip = 0;
                                diga[0] = t[i][j];
                                diga[1] = i;
                                diga[2] = j;
                                if (d[0] == diga[0]) {
                                    p[d[1]][d[2]] = 1;
                                    p[diga[1]][diga[2]] = 1;
                                    int pun = Integer.parseInt(Puntaje.getText()) + 10;
                                    Puntaje.setText(String.valueOf(pun));
                                    Boton[d[1]][d[2]].setIcon(new ImageIcon(getClass().getResource("/Imagenes/" + d[0] + "o.png")));
                                    Boton[diga[1]][diga[2]].setIcon(new ImageIcon(getClass().getResource("/Imagenes/" + diga[0] + "o.png")));
                                    Sonido(Url_mf);
                                    Sonido.start();
                                    clip = -1;
                                } else {
                                    int pun = Integer.parseInt(Puntaje.getText()) - 5;
                                    Puntaje.setText(String.valueOf(pun));
                                    Suc[d[1]][d[2]].setVisible(true);
                                    Sonido(Url_mu);
                                    Sonido.start();
                                    d[0] = diga[0];
                                    d[1] = diga[1];
                                    d[2] = diga[2];
                                    clip++;
//                            Suc[diga[1]][diga[2]].setVisible(true);
                                }
                            } else {
                                if (clip == -1) {
                                    d[0] = t[i][j];
                                    d[1] = i;
                                    d[2] = j;
                                    clip = 0;
                                }
                                clip++;
                            }
                            return;
                        } else {
                            System.out.println("Gane");
                            Boton[d[1]][d[2]].setIcon(new ImageIcon(getClass().getResource("/Imagenes/" + d[0] + "o.png")));
                            Boton[i][j].setIcon(new ImageIcon(getClass().getResource("/Imagenes/" + d[0] + "o.png")));
                            int answer = JOptionPane.showConfirmDialog(Experimento, "Su Puntaje ha sido de: " + Puntaje.getText() + " Desea seguir Jugando?", " Salir ", JOptionPane.YES_NO_OPTION);
                            if (answer == 1) {
                                Experimento.dispose();
                            }
                            try {
                                if (mos.isVisible() == true) {
                                    mos.setVisible(false);
                                    Nulidad(11);
                                    mos2.setVisible(true);
                                } else {
                                    Nulidad(1);
                                    mos.setVisible(true);
                                    mos2.setVisible(false);
                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(Experimento, "Felicidades ha completado con exito el Juego " + "Su puntaje ha sido de: " + Puntaje.getText(), "Felicidades", JOptionPane.OK_OPTION);
                                Experimento.dispose();
                            }
                            
                        }
                    }
                }
            }
            
        }
    }
    
}
