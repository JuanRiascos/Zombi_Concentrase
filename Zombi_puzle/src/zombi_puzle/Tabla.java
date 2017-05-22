/*
 * Juego para POE (Programación Orientada a Eventos).
 */
package zombi_puzle;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Juan David Restrepo Riascos
 * @version 1.0
 */
class Tabla extends JFrame implements ActionListener, MouseListener {

    /**
     * @see Constructor de la clase
     *
     */
    JPanel Mostrar;
    JPanel Mostrar_;
    JLabel Imagen;
    JTextArea Intrucciones;
    JLabel Reloj;
    JLabel Punta;
    JLabel Titulo;
    JMenuBar Menu;
    JMenu Juego, Ayuda;
    JMenuItem Facil, Medio, Avanzado, MostrarAu;
    Niveles Boton;
    Niveles2 Boton1;
    int[] numer = new int[10];
    JButton Iniciar, Terminar;
    Reloj reloj;
    Hilo_con mos;
    Clip Sonido;
    boolean Ini = false;
    int vec = 0;

    public Tabla() {
        try {

            this.Sonido = AudioSystem.getClip();
        } catch (Exception e) {
        }

        Configuracion();
        Mostrar();
        Intrucciones = new JTextArea();
        Intrucciones.setText("Bienvenido a este hermosisimo juego, Para poder pasar al siguiente Nivel debes de terminar el nivel anterior te deseamos lo mejor. Si desea Reiniciar el Juego debes de preccionar doble click en Inicio");
        Mostrar.add(Intrucciones);
        Intrucciones.setFont(new Font("Rockwell Extra Bold", 0, 20));
        Intrucciones.setLineWrap(true);
        Intrucciones.setEditable(false);

        Intrucciones.setBounds(0, 0, Mostrar.getWidth(), Mostrar.getHeight());
        try {
            Sonido.open(AudioSystem.getAudioInputStream(getClass().getResource("/Sonido/Plants_vs_Zombies.wav")));
            Sonido.start();
        } catch (Exception ex) {
        }
//        Intrucciones.setOpaque(false);
        this.setJMenuBar(Menu);
        add(Reloj);
        add(Punta);
        add(Mostrar);
        add(Mostrar_);
        add(Titulo);
        add(Terminar);
        add(Iniciar);
        add(Imagen);

        setVisible(true);
    }

    /**
     * @see Configuración del JFrame
     */
    private void Configuracion() {
        setTitle("Zombi mania");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        Image icon = new ImageIcon(getClass().getResource("/Imagenes/3.png")).getImage();
        setIconImage(icon);
        Menu = new JMenuBar();
        Juego = new JMenu("Juego");
        Ayuda = new JMenu("Ayuda");
        MostrarAu = new JMenuItem("Mostrar");
        MostrarAu.addActionListener(this);
        Facil = new JMenuItem("Facil");
        Facil.addActionListener(this);
        Medio = new JMenuItem("Medio");
        Medio.addActionListener(this);
        Reloj = new JLabel("00:00");
        Reloj.setBounds(500, 45, 140, 30);
        Reloj.setFont(new Font("Space Age", 0, 20));
        Reloj.setBorder(BorderFactory.createLoweredBevelBorder());
        Punta = new JLabel("0");
        Punta.setBounds(40, 45, 50, 30);
        Punta.setFont(new Font("Colonna MT", 0, 30));
        Punta.setBorder(BorderFactory.createLoweredBevelBorder());
        Juego.add(Facil);
        Ayuda.add(MostrarAu);
        Juego.add(Medio);
        Menu.add(Juego);
        Menu.add(Ayuda);
    }

    /**
     * @see Metodo donde se encuentra la Imagen del Fondo el panel donde ira los
     * botones o las cartas en su defecto
     */
    private void Mostrar() {
        Mostrar = new JPanel();
        Mostrar_ = new JPanel();
        Imagen = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/Fondo_3.png")));
        Imagen.setBounds(0, 0, 700, 508);
        Mostrar.setBorder(BorderFactory.createLoweredBevelBorder());
        Mostrar.setBounds(200, 170, 300, 250);
        Mostrar.setOpaque(false);
        Mostrar.setLayout(null);
        Mostrar.setVisible(true);
//        Mostrar.setLayout(new GridLayout(5, 4));
        Mostrar_.setBorder(BorderFactory.createLoweredBevelBorder());
        Mostrar_.setBounds(150, 150, 370, 270);
        Mostrar_.setOpaque(false);
        Mostrar_.setLayout(null);
        Mostrar_.setVisible(false);
        Terminar = new JButton(new ImageIcon(getClass().getResource("/Imagenes/Terminar.png")));
        Terminar.setBounds(350, 445, 180, 40);
        Terminar.setContentAreaFilled(false);
        Terminar.setFocusable(false);
        Terminar.setBorderPainted(false);
        Terminar.addActionListener(this);
        Iniciar = new JButton(new ImageIcon(getClass().getResource("/Imagenes/inicio.png")));
        Iniciar.setBounds(150, 445, 180, 40);
        Iniciar.setContentAreaFilled(false);
        Iniciar.setFocusable(false);
        Iniciar.setBorderPainted(false);
        Iniciar.addActionListener(this);
        Iniciar.addMouseListener(this);
        Titulo = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/Titulo.png")));
        Titulo.setBounds(130, 25, 370, 80);
//        Boton.Llenar_Botones_dos();
    }

    public void Salir() {
        reloj.suspend();
        int answer = JOptionPane.showConfirmDialog(null, "Su Puntaje ha sido de: " + Punta.getText() + " Desea seguir Jugando?", " Salir ", JOptionPane.YES_NO_OPTION);
        if (answer == 1) {
            this.dispose();
        } else {
            Boton.Nulidad(1);
            Boton1.Nulidad(11);
        }
        reloj.resume();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (Ini == true) {
            if (ae.getSource() == MostrarAu) {
                vec += 1;
                if (vec < 3) {
                    if (Mostrar.isVisible() == true) {
                        mos = new Hilo_con(Boton);
                        mos.start();
//                        Boton.Quitar();
                    }
                    if (Mostrar_.isVisible() == true) {
                        mos = new Hilo_con(Boton1);
                        mos.start();
//                        Boton1.Quitar();
                    }
//                    Salir();

                }
            }
        }
        if (ae.getSource() == Terminar) {
            Salir();
        }
        if (Ini == false) {
            if (ae.getSource() == Iniciar) {
                Ini = true;
                Sonido.stop();
                Intrucciones.setVisible(false);
                Boton = new Niveles(5, 4, Mostrar, false, Punta, this);
                Boton.mos2 = Mostrar_;
                Boton.re = true;
                reloj = new Reloj(Reloj, this, Punta);
                Boton.Llenar_Botones_dos();
                Boton.x_p = Boton.y_p = 0;
                Boton.Llenar_Botones();
                Mostrar.add(Boton);
                Boton1 = new Niveles2(6, 5, Mostrar_, false, Punta, this);
                Boton1.Llenar_Botones_dos();
                Boton1.x_p = Boton1.y_p = 0;
                Boton1.Llenar_Botones();
                Boton1.re = true;
                Mostrar_.add(Boton1);

//                System.out.println("llll");
                reloj.start();

            }

        }
        if (Ini == true) {
            if (ae.getSource() == Medio) {
                Mostrar_.setVisible(true);
                Mostrar.setVisible(false);
//            this.repaint();
            }
            if (ae.getSource() == Facil) {
                Mostrar_.setVisible(false);
                Mostrar.setVisible(true);
//            this.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Disculpa antes debes de preccionar Iniciar, para aceptar los terminos", "Toche", JOptionPane.OK_OPTION);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (me.getSource() == Iniciar) {
            Iniciar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/iniciopr.png")));
//            Boton.re = false;
        }
        if (Ini == true) {
            if (me.getClickCount() == 2) {
                Boton.Nulidad(1);
                Boton1.Nulidad(11);
//                    Boton.Llenar_Imagenes();
//                    Boton.Quitar();
//                    Boton.Llenar_Botones_dos();
//                    Boton.Llenar_Botones();

            }

        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == Iniciar) {
            Iniciar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/iniciop.png")));
        }
    }

    @Override
    public void mouseExited(MouseEvent me) {
        if (me.getSource() == Iniciar) {
            Iniciar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/inicio.png")));
        }
    }

}
