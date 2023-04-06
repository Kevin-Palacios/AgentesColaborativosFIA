/*0000  
0001
0010
0011
0100
0101
0110
0111=====
1000==========
1001
1010
1011
1100 ===========
1101=========
1110========
1111==========
 */
 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentes;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author macario
 */
public class mover {

    private boolean romper;
    private int direccionCol;
    private int direccionRow;
    private int direccionAgente;
    private boolean bandera;

    public boolean isRomper() {
        return romper;
    }

    public void setRomper(boolean romper) {
        this.romper = romper;
    }

    public int getDireccionCol() {
        return direccionCol;
    }

    public void setDireccionCol(int direccionCol) {
        this.direccionCol = direccionCol;
    }

    public int getDireccionRow() {
        return direccionRow;
    }

    public void setDireccionRow(int direccionRow) {
        this.direccionRow = direccionRow;
    }

    public int getDireccionAgente() {
        return direccionAgente;
    }

    public void setDireccionAgente(int direccionAgente) {
        this.direccionAgente = direccionAgente;
    }

    public boolean isBandera() {
        return bandera;
    }

    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

}