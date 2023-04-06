/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;
import java.util.Objects;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

/**
 *
 * @author macario
 */
public class Escenario extends JFrame
{
    
    private JLabel[][] tablero;     
    private int[][] matrix;
    private final int dim = 12;

    private iconNew robot1;
    private iconNew robot2;
    private iconNew obstacleIcon;
    private iconNew sampleIcon;
    private iconNew sampleIcon2;
    private iconNew sampleIcon3;
    private iconNew sampleIcon4;
    private iconNew actualIcon;
    private iconNew motherIcon;
    public int posx;
    public int posy;
    private Agente kirby;
    private Agente kirby2;
   
    private final BackGroundPanel fondo = new BackGroundPanel(new ImageIcon("imagenes/agujero.jpg"));
    
    private final JMenu settings = new JMenu("Settigs");
    private final JRadioButtonMenuItem obstacle = new JRadioButtonMenuItem("Obstacle");
    private final JRadioButtonMenuItem sample = new JRadioButtonMenuItem("Sample");
    private final JRadioButtonMenuItem sample2 = new JRadioButtonMenuItem("Sample2");
    private final JRadioButtonMenuItem sample3 = new JRadioButtonMenuItem("Sample3");
    private final JRadioButtonMenuItem sample4 = new JRadioButtonMenuItem("Sample4");
    private final JRadioButtonMenuItem motherShip = new JRadioButtonMenuItem("MotherShip");
    
    public Escenario()
    {
        this.setContentPane(fondo);
        this.setTitle("Agentes");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setBounds(50,50,dim*50+35,dim*50+85);
        initComponents();
    }
        
    private void initComponents()
    {
        ButtonGroup settingsOptions = new ButtonGroup();
        settingsOptions.add(sample);
        settingsOptions.add(sample2);
        settingsOptions.add(sample3);
        settingsOptions.add(sample4);
        settingsOptions.add(obstacle);       
        settingsOptions.add(motherShip);
        
        JMenuBar barraMenus = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem run  = new JMenuItem("Run");
        
        JMenuItem exit   = new JMenuItem("Exit");
              
        this.setJMenuBar(barraMenus);
        barraMenus.add(file);
        barraMenus.add(settings);
        file.add(run);
        file.add(exit);
        settings.add(motherShip);
        settings.add(obstacle);
        settings.add(sample);
        settings.add(sample2);
        settings.add(sample3);
        settings.add(sample4);
            
        robot1 = new iconNew("imagenes/kirby_0.png");
        robot1 = new iconNew(robot1.getImage().getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH), 0);
        
        robot2 = new iconNew("imagenes/kirby2_0.png");
        robot2 = new iconNew(robot2.getImage().getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH), 0);
        
        obstacleIcon = new iconNew("imagenes/meteorito.png");
        obstacleIcon = new iconNew(obstacleIcon.getImage().getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH), 1);
        
        sampleIcon = new iconNew("imagenes/sample_1.png");
        sampleIcon = new iconNew(sampleIcon.getImage().getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH), 3);
        
        sampleIcon2 = new iconNew("imagenes/sample_2.png");
        sampleIcon2 = new iconNew(sampleIcon2.getImage().getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH), 4);
        
        sampleIcon3 = new iconNew("imagenes/sample_3.png");
        sampleIcon3 = new iconNew(sampleIcon3.getImage().getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH), 5);
        
        sampleIcon4 = new iconNew("imagenes/sample_4.png");
        sampleIcon4 = new iconNew(sampleIcon4.getImage().getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH), 6);
        
        motherIcon = new iconNew("imagenes/mother.png");
        motherIcon = new iconNew(motherIcon.getImage().getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH), 2);
        
        this.setLayout(null);
        
        formaPlano();  
        
        exit.addActionListener(evt -> gestionaSalir(evt));
        run.addActionListener(evt -> gestionaRun(evt));
        obstacle.addItemListener(evt -> gestionaObstacle(evt));
        sample.addItemListener(evt -> gestionaSample(evt));
        sample2.addItemListener(evt -> gestionaSample2(evt));
        sample3.addItemListener(evt -> gestionaSample3(evt));
        sample4.addItemListener(evt -> gestionaSample4(evt));
        motherShip.addItemListener(evt -> gestionaMotherShip(evt));

              
            
        class MyWindowAdapter extends WindowAdapter
        {
            public void windowClosing(WindowEvent eventObject)
            {
		goodBye();
            }
        }
        addWindowListener(new MyWindowAdapter());
        
        // Crea 2 agentes
        kirby = new Agente("kirby",robot1, matrix, tablero); 
        kirby2 = new Agente("kirby2",robot2, matrix, tablero); 
        
    }
        
    private void gestionaSalir(ActionEvent eventObject)
    {
        goodBye();
    }
        
    private void goodBye()
    {
        int respuesta = JOptionPane.showConfirmDialog(rootPane, "Desea salir?","Aviso",JOptionPane.YES_NO_OPTION);
        if(respuesta==JOptionPane.YES_OPTION) System.exit(0);
    }
  
    private void formaPlano()
    {
        tablero = new JLabel[dim][dim];
        matrix = new int[dim][dim];
        
        int i, j;
        
        for(i=0;i<dim;i++)
            for(j=0;j<dim;j++)
            {
                matrix[i][j]=0;
                tablero[i][j]=new JLabel();
                tablero[i][j].setBounds(j*50+10,i*50+10,50,50);
                tablero[i][j].setBorder(BorderFactory.createDashedBorder(Color.white));
                tablero[i][j].setOpaque(false);
                this.add(tablero[i][j]);
                
                tablero[i][j].addMouseListener(new MouseAdapter() // Este listener nos ayuda a agregar poner objetos en la rejilla
                    {
                        @Override
                        public void mousePressed(MouseEvent e) 
                        {
                               insertaObjeto(e);
                        }   
                
                        @Override
                        public void mouseReleased(MouseEvent e) 
                        {
                                insertaObjeto(e);
                        }   
                
                    });
                                
            }
    }
        
    private void gestionaObstacle(ItemEvent eventObject)
    {
        JRadioButtonMenuItem opt = (JRadioButtonMenuItem) eventObject.getSource();
        if(opt.isSelected())
           actualIcon = obstacleIcon;
        else actualIcon = null;        
    }
    
    private void gestionaSample(ItemEvent eventObject)
    {
        JRadioButtonMenuItem opt = (JRadioButtonMenuItem) eventObject.getSource();
        if(opt.isSelected())
           actualIcon = sampleIcon;
        else actualIcon = null;   
    }
    
    private void gestionaSample2(ItemEvent eventObject)
    {
        JRadioButtonMenuItem opt = (JRadioButtonMenuItem) eventObject.getSource();
        if(opt.isSelected())
           actualIcon = sampleIcon2;
        else actualIcon = null;   
    }
    
    private void gestionaSample3(ItemEvent eventObject)
    {
        JRadioButtonMenuItem opt = (JRadioButtonMenuItem) eventObject.getSource();
        if(opt.isSelected())
           actualIcon = sampleIcon3;
        else actualIcon = null;   
    }
    
    private void gestionaSample4(ItemEvent eventObject)
    {
        JRadioButtonMenuItem opt = (JRadioButtonMenuItem) eventObject.getSource();
        if(opt.isSelected())
           actualIcon = sampleIcon4;
        else actualIcon = null;   
    }
    
    private void gestionaMotherShip(ItemEvent eventObject)
    {
        JRadioButtonMenuItem opt = (JRadioButtonMenuItem) eventObject.getSource();
        if(opt.isSelected())
           actualIcon = motherIcon;
        else actualIcon = null;   
    }
    private void gestionaRun(ActionEvent eventObject)
    {
        if(!kirby.isAlive()) kirby.start();
        if(!kirby2.isAlive()) kirby2.start();
        settings.setEnabled(false);
        //playMusic("file:audio/kirbySongBuena.wav");
        playMusic("file:audio/song.wav");
        
        
    }
       
    public void insertaObjeto(MouseEvent e)
    {
        JLabel casilla = (JLabel) e.getSource();
        
        if (actualIcon.getPeso()==2) {
            
            //String c1;
            //c1 = casilla.toString().strip();

            Rectangle size = casilla.getBounds();

            double x=size.getX();
            double y=size.getY();

            x = (x-10)/50;
            y = (y-10)/50;
            
            posx= (int)x;
            
            posy= (int)y;
            actualIcon.setI(posx);
            actualIcon.setJ(posy);
            
            System.out.println("I: "+x+"J: "+y);
            
        }
        if(actualIcon!=null){
            casilla.setIcon(actualIcon);
            
            //System.out.println("I: "+x+"J: "+y);
        } 
    }
    
    public static void playMusic(String filepath){
        
        try{
            File musicPath = new File(filepath);
            
            
                URL file = new URL(filepath);

                AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                
                clip.start();
                
                float vol = 0.05f;
                setVolume(vol, clip);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                
        }catch(Exception e){
            System.out.println("Error");
        }
    }
    /*
    public static void setVolume(Clip clip, int level) {
        Objects.requireNonNull(clip);
        FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.VOLUME);
        if (volume != null) {
            volume.setValue((float) (level / 100.0));     
        }
    }
    */
    public static float getVolume(Clip clip) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    public static void setVolume(float volume, Clip clip) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
        gainControl.setValue(20f * (float) Math.log10(volume));
    }
}
