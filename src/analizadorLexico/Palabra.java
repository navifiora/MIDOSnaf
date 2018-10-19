/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorLexico;

/**
 *
 * @author naviles
 */
public class Palabra extends Token{
    public String lexema = "";
    public Palabra (String s, int etiqueta) {
        super (etiqueta);
        lexema = s;}
    public String toString () {return lexema;}
    public static final Palabra
            CLS = new Palabra ("CLS",Etiqueta.CLS),
            VER = new Palabra ("VER",Etiqueta.VER),
            DATE = new Palabra ("DATE",Etiqueta.DATE),
            TIME = new Palabra ("TIME",Etiqueta.TIME),
            EXIT = new Palabra ("EXIT",Etiqueta.EXIT),
            MD = new Palabra ("MD",Etiqueta.MD),
            CD = new Palabra ("CD",Etiqueta.CD),
            RD = new Palabra ("RD",Etiqueta.RD),
            PROMPT = new Palabra ("PROMPT",Etiqueta.PROMPT),
            DIR = new Palabra ("DIR",Etiqueta.DIR), 
            COPY = new Palabra ("COPY",Etiqueta.COPY),
            TYPE = new Palabra ("TYPE",Etiqueta.TYPE),
            REN = new Palabra ("REN",Etiqueta.REN),
            TREE = new Palabra ("TREE",Etiqueta.TREE),
            DEL = new Palabra ("DEL",Etiqueta.DEL);   
}
