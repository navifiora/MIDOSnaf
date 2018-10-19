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
import java.io.*;
import java.util.*;

public class AnalizadorLexico {
    public static int linea = 1;
    char preanalisis = ' ';
    Hashtable palabras = new Hashtable();
    void reservar (Palabra w){palabras.put(w.lexema, w);}
    public AnalizadorLexico (){
        reservar (new Palabra ("CLS", Etiqueta.CLS));
        reservar (new Palabra ("VER", Etiqueta.VER));
        reservar (new Palabra ("DATE", Etiqueta.DATE));
        reservar (new Palabra ("TIME", Etiqueta.TIME));
        reservar (new Palabra ("EXIT", Etiqueta.EXIT));
        reservar (new Palabra ("MD", Etiqueta.MD));
        reservar (new Palabra ("CD", Etiqueta.CD));
        reservar (new Palabra ("RD", Etiqueta.RD));
        reservar (new Palabra ("PROMPT", Etiqueta.PROMPT));
        reservar (new Palabra ("DIR", Etiqueta.DIR));
        reservar (new Palabra ("COPY", Etiqueta.COPY));
        reservar (new Palabra ("TYPE", Etiqueta.TYPE));
        reservar (new Palabra ("DEL", Etiqueta.DEL));
        reservar (new Palabra ("REN", Etiqueta.REN));
        reservar (new Palabra ("DIR", Etiqueta.DIR));
        reservar (new Palabra ("TREE", Etiqueta.TREE));
    }
}
