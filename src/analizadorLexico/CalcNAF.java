/*
 Programa realizado por Natalia Avilés Fioravanti para Compiladores 3007, Año 2018
 */
package analizadorLexico;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;
import java.util.Date;
import java.io.*;

/**
 *
 * @author naviles
 */
public class CalcNAF {

        private static Scanner teclado;
	public static List<VariableClass> ArregloVariables = new ArrayList<>(); // guarda las variables y sus tipos
	public static List <String> ArregloComandos = Arrays.asList("CLS", "VER", "DATE", "TIME", "EXIT", "MD","CD", "RD", "PROMPT","DIR","COPY","TYPE","DEL","REN","DIR","TREE"); // Lista de comandos, palabras reservadas
	public static Map <String, Integer> MapaEnteros = new HashMap <String, Integer>();      //Mapa de enteros 
	public static Map <String, Double> MapaReales = new HashMap <String, Double>(); // Mapa de reales
        public static String auxiliar = null; 
	private static Scanner lector1;
	private static Scanner lector;
	private static Scanner lector2;
	private static Scanner lector3;
        public static String resultado=null;
        public static String promptvalue="M:\\>";
//Ingreso de datos
        
public static void Menu () throws IOException {
    String cadena;
    System.out.print("\n " + promptvalue);
    teclado = new Scanner (System.in);
    cadena=teclado.nextLine();
    //Convierte la cadena a mayúscula para mejor manipulación
    cadena = cadena.toUpperCase();
    //Limpia los espacios y tabuladores
    cadena = cadena.replaceAll("\\s","");
      
    //Identifica comandos
    if (cadena.equals("CLS")) {
        CLS();
        Menu();}
    else{
        if (cadena.equals("EXIT")) {
            EXIT();
            Menu();}
        else{
            if (cadena.equals("VER")) {
                VER();
                Menu();}
            else{
                if (cadena.equals("TIME")) {
                    TIME();
                    Menu();}
                else{
                    if (cadena.equals("DATE")) {
                        DATE();
                        Menu();
                    }
                    else{
                        if (cadena.contains ("PROMPT")){
                            auxiliar = cadena;
                            if (auxiliar.contains("PROMPT")){
                               auxiliar = auxiliar.replaceAll("PROMPT", "");
                                }
                                PROMPT(auxiliar);
                            Menu();
                        }
                        else {
                            if (cadena.contains ("MD")){
                                auxiliar = cadena;
                                if (auxiliar.contains("MD")){
                                    auxiliar = auxiliar.replaceAll("MD", "");
                                    }
                                    if (RevisaDir(auxiliar)){
                                        MD (auxiliar);
                                    }
                                Menu();
                        }
  /*                      if (cadena.contains ("GRAB") || cadena.contains ("GRA")){
                            if (resultado.equals(null)){
                                System.out.println("ERROR 009: No se ha ejecutado CALC, no hay resultados para grabar");
                            }
                            else {
                                auxiliar = cadena;
                                if (auxiliar.contains("GRAB")){
                                    auxiliar = auxiliar.replaceAll("GRAB", "");}
                                if (auxiliar.contains("GRA")){
                                    auxiliar = auxiliar.replaceAll("GRA", "");}
                                 GRAB(auxiliar,resultado);
                            }
                            Menu();}
                        else {  
                            if (cadena.contains ("?") ||cadena.contains ("VALI") ||cadena.contains ("VAL")) {
                                auxiliar = cadena;
                                if (auxiliar.contains("VALI")){
                                    auxiliar = auxiliar.replaceAll("VALI", "");}
                                if (auxiliar.contains("VAL")){
                                     auxiliar = auxiliar.replaceAll("VAL", "");}
                                if (auxiliar.contains("?")){
                                    auxiliar = auxiliar.replaceAll("?", "");}
                                VALI(auxiliar);}
                            else{
                                if (cadena.contains ("=")|| cadena.contains ("DEFI") || cadena.contains ("DEF")){
                                    auxiliar = cadena;
                                    if (auxiliar.contains("DEFI")){
                                    auxiliar = auxiliar.replaceAll("DEFI", "");}
                                    if (auxiliar.contains("DEF")){
                                    auxiliar = auxiliar.replaceAll("DEF", "");}
                                    DEFI(auxiliar);
                                    Menu();}
                                else{
                                    if (cadena.contains ("+") ||cadena.contains ("-") ||cadena.contains ("*") ||cadena.contains ("/") ||cadena.contains ("^") ||cadena.contains ("#") ||cadena.contains ("CALC") ||cadena.contains ("CAL")) {
                                        auxiliar = cadena;
                                        if (auxiliar.contains("CALC")){
                                            auxiliar = auxiliar.replaceAll("CALC", "");}
                                        if (auxiliar.contains("CAL")){
                                            auxiliar = auxiliar.replaceAll("CAL", "");}
                                        CALC(auxiliar);
                                        System.out.println("El resultado es: " + resultado);*/
                                    else{
                                        System.out.println("Error 001: no ingresó una opción válida");
                                        Menu();}}}}}}}
}

//Limpia la pantalla
public static void CLS (){
    for (int i=0;i<=100; i++){
        System.out.println("");
    }   
}
//Muestra la fecha
public static void DATE (){
    Date date = new Date();
    System.out.println(date.toString());
}

//Muestra la hora
public static void TIME (){
    Date date = new Date();
    System.out.println(date.toString()); 
}

//Muestra la versión del programa
public static void VER() throws IOException {
    System.out.flush();
    System.out.print("*************************************************\n"+
                       "*        UNIVERSIDAD ESTATAL A DISTANCIA        *\n" +
                       "*      ESCUELA CIENCIAS EXACTAS Y NATURALES     *\n" +
                       "*            PROGRAMA DE INFORMATICA            *\n" +
                       "*         CATEDRA TECNOLOGIA DE SISTEMAS        *\n" +
                       "*            CURSO COMPILADORES (3307)          *\n" +
                       "*************************************************\n" +
                       "*                      MIDOS                    *\n"+
                       "*                   Versión 1.0                 *\n" +
                       "*                                               *\n" +
                       "* ALUMNA: Natalia Avilés Fioravanti 1-1337-0387 *\n" +
                       "*************************************************\n" +
                       " Memoria disponible: ");
    ReadMemory();
    System.out.println("K");
    Menu();
}

//Terminar el programa
public static void EXIT() throws IOException{
    System.out.flush();
    System.out.println("¿Seguro que desea terminar? (S/N)");
    teclado=new Scanner(System.in); 
    char  opc=teclado.next().charAt(0); 
    switch (opc){
        case 's': 
            System.out.println("Digite una tecla para salir ¡Hasta pronto!");
            System.exit(0);
        break;
        case 'S': 
            System.out.println("Digite una tecla para salir ¡Hasta pronto!");
            System.exit(0);
        break;
        case 'n':
            Menu();
        break;
        case 'N':
            Menu();
        break;
        default :
             System.out.println("\n ERROR 001 : No se ingresó una opción válida. \n");
             Menu();
        break;
    }
}

// Realiza la función del prompt
public static void PROMPT (String aux){
    switch (aux) {
        case "$G":
            promptvalue = ">";
        break;
        case "$P":
            promptvalue = "M:\\";
        break;
        case "$G$P":
            promptvalue = ">M:\\";
        break;
        case "$P$G":
            promptvalue = "M:\\>";
        break;
        default:
            System.out.println("Error 001: no ingresó una opción válida");
    }
}

//Copia el directorio en el archivo de texto
public static void MD (String aux) throws IOException {
    System.out.println("CREANDO ARCHIVO........ "+aux);
    System.out.println(ReadMemory());
}

public static boolean RevisaDir (String aux){
    if (aux.length()>8){
        System.out.println("Error 006: el largo del directorio no es válido");
        return false;
    }else{
        if (aux.charAt(0) >='A' && aux.charAt(0) <='Z'){
            boolean validchar=true;
            for (int i = 0, n = aux.length(); i < n; i++) {
                if ((aux.charAt(i) >='A' && aux.charAt(i) <='Z') || (Integer.parseInt(""+aux.charAt(i)) >=0 && Integer.parseInt(""+aux.charAt(i)) <=9)){
                    validchar=true;
                }
                else {
                    System.out.println("Error 008: el directorio contiene caracteres inválidos");
                    return false;
                }
            }
        }
        else{
            System.out.println("Error 007: el directorio debe emepezar por una letra");
            return false;
        }
        
    }
    return true;
}

public static void CreateFiles () throws IOException {
    
  // Crea el archivo que almacena los datos de la memoria
    String routeMem = "C:/Users/Public/midosfre.txt";
    File archivoMem = new File(routeMem);
    if(archivoMem.exists()) {
        // El fichero ya existe
    } else {
        // El fichero no existe y hay que crearlo
        FileWriter memoryfile = new FileWriter ("C:/Users/Public/midosfre.txt");
        memoryfile.write ("256");
        memoryfile.close();
    }
    
    //Crea el archivo que almacena los datos de los directorios
    String routeTree = "C:/midosfre.txt";
    File archivoTree = new File(routeTree);
    if(archivoTree.exists()) {
        // El fichero ya existe
    } else {
        // El fichero no existe y hay que crearlo
        FileWriter treefile = new FileWriter ("C:/Users/Public/midostre.txt");
        treefile.write ("M:\\");
        treefile.close();
    }
}

public static String ReadMemory () throws IOException {
    try
    {
        FileReader memoryfile = new FileReader ("C:/Users/Public/midosfre.txt");
        BufferedReader br = new BufferedReader (memoryfile);
        String cadena;
        
        while ((cadena = br.readLine())!=null){
            System.out.print (""+cadena);
        }
        return cadena;
    }
    catch (IOException e){}
    return "256";
}

/*
//Calcula la frase ingresada
public static void BORR (String aux){
    lector3 = new Scanner(System.in);
    String NVariable;
        //Verifica si el comando borr tiene variables
        if (aux==null){
            System.out.println("\n Digite la variable que desea borrar:\n");
            NVariable = lector3.nextLine();}
        else{
            NVariable=aux;}
        NVariable=NVariable.replaceAll("\\s+", "");
        NVariable=NVariable.toUpperCase();
        aux=null;
        //Verifica si el areglo está vacío
         if(ArregloVariables.isEmpty()){
            System.out.println("\n ERROR 006 : La lista de variables está vacía");
        return;	
        }else{
             //busca la variable
            if(VariableClass.BuscarVariable((ArrayList<VariableClass>) ArregloVariables, NVariable)==null){
                //la variable a eliminar no existe
                System.out.println("\n\n ERROR 008: La variable ingresada no existe");
            }
            else{
                //Verifica si se desea eliminar la variable y si es afirmativo, se elimina
            	System.out.println("\n\n ¿Seguro que deseea eliminarla? S/N ");
                char  opc=teclado.next().charAt(0);
                    if (opc == 'S' || opc == 's'){
                        int indice = ArregloVariables.indexOf(VariableClass.BuscarVariable((ArrayList<VariableClass>) ArregloVariables, NVariable));
                        ArregloVariables.remove(indice);
                        if(MapaReales.containsKey(NVariable)){
                            MapaReales.remove(NVariable);
                        }
                        else{
                            if(MapaEnteros.containsKey(NVariable)){
                                MapaEnteros.remove(NVariable);
                        }
                        System.out.println("\n Variable eliminada. \n");
                        }
                    }else{
                          if (opc == 'N' || opc == 'n'){
                               return;
                          }else{
                                  System.out.println("\n ERROR 001 : No se ingresó una opción válida. \n");
				}}}}}
/*
//Graba el resultado
public static void GRAB(String aux, String resul){
    lector3 = new Scanner(System.in);
    String nombreVariable;
    if (aux==null){
        System.out.println("\n Digite la variable que desea grabar:\n");
	nombreVariable = lector3.nextLine();}
    else{
	nombreVariable=aux;}
        nombreVariable=nombreVariable.replaceAll("\\s+", "");
        nombreVariable=nombreVariable.toUpperCase();
        String valorVariable = resul;
        String NVariable;
        aux=null;
	if (nombreVariable.length()>0 && nombreVariable.length()<=5 && ArregloComandos.contains(nombreVariable)==false){
            NVariable = nombreVariable;
	}else {
            System.out.println("\n ERROR 002 : Nombre de variable es palabra reservada\n");
	return;}
	if (valorVariable.endsWith(".")){
            System.out.println("\n ERROR 003 : No es un valor real \n");
	return;
        }else {
            Pattern p1 = Pattern.compile("\\d+");
            Matcher m1 = p1.matcher(valorVariable);
            if(m1.find()){
                Pattern p = Pattern.compile("[0-9]+\\.[0-9]+");
		Matcher m = p.matcher(valorVariable);
                    if(m.find()){
			VariableClass registro = new VariableClass(NVariable, valorVariable, "REAL");
                        if(VariableClass.BuscarVariable((ArrayList<VariableClass>) ArregloVariables, NVariable)==null){
                            ArregloVariables.add(registro);
                            MapaReales.put(NVariable, Double.valueOf(valorVariable));
			}else{
			System.out.println("\n\n Variable existente ¿Deseea reemplazarla? S/N ");
                            char  opc=teclado.next().charAt(0);
                            if (opc == 'S' || opc == 's'){
                                int indice = ArregloVariables.indexOf(VariableClass.BuscarVariable((ArrayList<VariableClass>) ArregloVariables, NVariable));
                                ArregloVariables.set(indice, registro);
				MapaReales.replace(NVariable, Double.valueOf(valorVariable));
                            }else{
                                if (opc == 'N' || opc == 'n'){
                                    return;
				}else{
					System.out.println("\n ERROR 001 : No se ingresó una opción válida. \n");
				}
                            }
			}
                    }else{
			Pattern p3 = Pattern.compile("\\d+");
			Matcher m3 = p3.matcher(valorVariable);
			if (m3.matches()){
                            VariableClass registro2 = new VariableClass (NVariable, valorVariable, "ENTERO");
                            if(VariableClass.BuscarVariable((ArrayList<VariableClass>) ArregloVariables, NVariable)==null){ 
				ArregloVariables.add(registro2);
				MapaEnteros.put(NVariable, Integer.valueOf(valorVariable));
                            }else{
				System.out.println("\n\n Variable existente ¿Deseea reemplazarla? S/N ");
				char opc2 = teclado.next().charAt(0);
				if (opc2 == 'S' || opc2 == 's'){
                                    int indice = ArregloVariables.indexOf(VariableClass.BuscarVariable((ArrayList<VariableClass>) ArregloVariables, NVariable));
                                    ArregloVariables.set(indice, registro2);
                                    MapaEnteros.replace(NVariable, Integer.valueOf(valorVariable));
				}else{
                                    if (opc2 == 'N' || opc2 == 'n'){
                                        return;
                                    }else{
					System.out.println("\n ERROR 001: No se ingresó una opción válida. \n");}}}}
                                else{
                                    System.out.println("\n ERROR 004: Valor de variable no es numérico. Intente de nuevo \n");
                                    return;
				}
					}
				}else{
                                    Pattern p4 = Pattern.compile("[A-Za-z]+");
                                    Matcher m4 = p4.matcher(valorVariable);
                                    boolean test = m4.matches();
                                    if (m4.matches()){
                                        valorVariable=valorVariable.toUpperCase();
					VariableClass match = VariableClass.BuscarVariable((ArrayList<VariableClass>) ArregloVariables, valorVariable);
					if(VariableClass.BuscarVariable((ArrayList<VariableClass>) ArregloVariables, valorVariable)!=null){
                                            String Valor = match.getValor();
                                            String Tipo = match.getTipo();
                                            VariableClass et = new VariableClass (nombreVariable, Valor, Tipo);
						if (Tipo.equals("ENTERO")){
                                                    ArregloVariables.add(et);
                                                    MapaEnteros.put(nombreVariable, Integer.valueOf(Valor));
						}else{
                                                    ArregloVariables.add(et);
                                                    MapaReales.put(nombreVariable, Double.valueOf(Valor));
						}
						}else{
                                                    System.out.println("\n ERROR 004: Valor de variable no es numérico. Intente de nuevo \n");
						return;
						}
					}else{
						System.out.println("\n ERROR 005 : Valor de variable invalido, intente de nuevo con números únicamente. \n");
						return;
						
					}
				}
				
			}	
		}
			
	

public static void VALI (String aux){
    lector3 = new Scanner(System.in);
    String sentencia;
    if (aux==null){
        System.out.println("\n Digite la expresión que desea validar:\n");
	sentencia = lector3.nextLine();}
    else{
	sentencia=aux;}
        sentencia=sentencia.replaceAll("\\s+", "");
        String expresion = "";
        String valorResultado = "";
        String resultadoCalculo;
        String NVariable;
        int resulExpresion;
        int resulComparar;
        aux=null;
        //si la sentencia desea bucar una igual
        if(sentencia.contains("<=")){
            for(int i = 0; i < sentencia.length(); i++) {
                char x = sentencia.charAt(i);
                if( x =='<'){
                    for (int j = 0; j<i; j++){
                        expresion += sentencia.charAt(j);} //busca la expresion a calcular
                    for (int k=i+2; k<sentencia.length(); k++){
                        valorResultado += sentencia.charAt(k);} //separa el resultado a comprar
                }
            }    
            CALC(expresion);
            resulExpresion = Integer.parseInt(resultado); //convierte el resultado del calculo a entero
            resulComparar = Integer.parseInt(valorResultado); //convierte el resultado a comparar a entero
            if (resulExpresion <= resulComparar) {
                resultado="CIERTO";
            }else{
                    resultado="FALSO";
                }
        }else{
            if(sentencia.contains(">=")){
                for(int i = 0; i < sentencia.length(); i++) {
                    char x = sentencia.charAt(i);
                    if( x =='>'){
                        for (int j = 0; j<i; j++){
                            expresion += sentencia.charAt(j);} //busca la expresion a calcular
                        for (int k=i+2; k<sentencia.length(); k++){
                            valorResultado += sentencia.charAt(k);} //separa el resultado a comprar
                    }
                }    
                CALC(expresion);
                resulExpresion = Integer.parseInt(resultado); //convierte el resultado del calculo a entero
                resulComparar = Integer.parseInt(valorResultado); //convierte el resultado a comparar a entero
                if (resulExpresion >= resulComparar) {
                    resultado="CIERTO";
                }else{
                    resultado="FALSO";
                }
        }else{
            if(sentencia.contains("<>")){
                for(int i = 0; i < sentencia.length(); i++) {
                    char x = sentencia.charAt(i);
                    if( x =='<'){
                        for (int j = 0; j<i; j++){
                            expresion += sentencia.charAt(j);} //busca la expresion a calcular
                        for (int k=i+2; k<sentencia.length(); k++){
                            valorResultado += sentencia.charAt(k);} //separa el resultado a comprar
                    }
                }    
                CALC(expresion);
                resulExpresion = Integer.parseInt(resultado); //convierte el resultado del calculo a entero
                resulComparar = Integer.parseInt(valorResultado); //convierte el resultado a comparar a entero
                if (resulExpresion != resulComparar) {
                    resultado="CIERTO";
                }else{
                    resultado="FALSO";
                }
            }else{
                if(sentencia.contains("=")){
                    for(int i = 0; i < sentencia.length(); i++) {
                        char x = sentencia.charAt(i);
                        if( x =='='){
                            for (int j = 0; j<i; j++){
                                expresion += sentencia.charAt(j);} //busca la expresion a calcular
                            for (int k=i+1; k<sentencia.length(); k++){
                                valorResultado += sentencia.charAt(k);} //separa el resultado a comprar
                        }
                    }    
                    CALC(expresion);
                    resulExpresion = Integer.parseInt(resultado); //convierte el resultado del calculo a entero
                    resulComparar = Integer.parseInt(valorResultado); //convierte el resultado a comparar a entero
                    if (resulExpresion == resulComparar) {
                        resultado="CIERTO";
                    }else{
                        resultado="FALSO";
                    }
                }else{       
                    if(sentencia.contains(">")){
                        for(int i = 0; i < sentencia.length(); i++) {
                            char x = sentencia.charAt(i);
                            if( x =='>'){
                                for (int j = 0; j<i; j++){
                                    expresion += sentencia.charAt(j);} //busca la expresion a calcular
                                for (int k=i+1; k<sentencia.length(); k++){
                                    valorResultado += sentencia.charAt(k);} //separa el resultado a comprar
                            }
                        }    
                        CALC(expresion);
                        resulExpresion = Integer.parseInt(resultado); //convierte el resultado del calculo a entero
                        resulComparar = Integer.parseInt(valorResultado); //convierte el resultado a comparar a entero
                        if (resulExpresion > resulComparar) {
                            resultado="CIERTO";
                        }else{
                            resultado="FALSO";
                        }
                    }else{
                        if(sentencia.contains("<")){
                            for(int i = 0; i < sentencia.length(); i++) {
                                char x = sentencia.charAt(i);
                                if( x =='<'){
                                    for (int j = 0; j<i; j++){
                                        expresion += sentencia.charAt(j);} //busca la expresion a calcular
                                    for (int k=i+1; k<sentencia.length(); k++){
                                        valorResultado += sentencia.charAt(k);} //separa el resultado a comprar
                                }
                            }    
                            CALC(expresion);
                            resulExpresion = Integer.parseInt(resultado); //convierte el resultado del calculo a entero
                            resulComparar = Integer.parseInt(valorResultado); //convierte el resultado a comparar a entero
                            if (resulExpresion < resulComparar) {
                                resultado="CIERTO";
                            }else{
                                resultado="FALSO";}
                
                        }
                    }
                }
            }
            }
        }
    System.out.println("\n La expresión a validar es:" + resultado + "\n\n");
}
        

//Calcula la frase ingresada
public static String CALC (String aux){
    String resul = "0";
    resultado=resul;
    return resul;
}

//Define el nombre y el tipo de variable
public static void DEFI(String aux){
    lector3 = new Scanner(System.in);
    String sentencia;
    if (aux==null){
        System.out.println("\n Digite la variable que desea definir:\n");
	sentencia = lector3.nextLine();}
    else{
	sentencia=aux;}
        sentencia=sentencia.replaceAll("\\s+", "");
        sentencia=sentencia.toUpperCase();
        String nombreVariable = "";
        String valorVariable = "";
        String NVariable;
        aux=null;
        if(sentencia.contains("=")){
	for(int i = 0; i < sentencia.length(); i++) {
            char x = sentencia.charAt(i);
            if( x =='='){
                for (int j = 0; j<i; j++){
                    nombreVariable += sentencia.charAt(j);}
                for (int k=i+1; k<sentencia.length(); k++){
                    valorVariable += sentencia.charAt(k);}
            }
	}
	if (nombreVariable.length()>0 && nombreVariable.length()<=5 && ArregloComandos.contains(nombreVariable)==false){
            NVariable = nombreVariable;
	}else {
            System.out.println("\n ERROR 002 : Nombre de variable es palabra reservada\n");
	return;}
	if (valorVariable.endsWith(".")){
            System.out.println("\n ERROR 003 : No es un valor real \n");
	return;
        }else {
            Pattern p1 = Pattern.compile("\\d+");
            Matcher m1 = p1.matcher(valorVariable);
            if(m1.find()){
                Pattern p = Pattern.compile("[0-9]+\\.[0-9]+");
		Matcher m = p.matcher(valorVariable);
                    if(m.find()){
			VariableClass registro = new VariableClass(NVariable, valorVariable, "REAL");
                        if(VariableClass.BuscarVariable((ArrayList<VariableClass>) ArregloVariables, NVariable)==null){
                            ArregloVariables.add(registro);
                            MapaReales.put(NVariable, Double.valueOf(valorVariable));
			}else{
			System.out.println("\n\n Variable existente ¿Deseea reemplazarla? S/N ");
                            char  opc=teclado.next().charAt(0);
                            if (opc == 'S' || opc == 's'){
                                int indice = ArregloVariables.indexOf(VariableClass.BuscarVariable((ArrayList<VariableClass>) ArregloVariables, NVariable));
                                ArregloVariables.set(indice, registro);
				MapaReales.replace(NVariable, Double.valueOf(valorVariable));
                            }else{
                                if (opc == 'N' || opc == 'n'){
                                    return;
				}else{
					System.out.println("\n ERROR 001 : No se ingresó una opción válida. \n");
				}
                            }
			}
                    }else{
			Pattern p3 = Pattern.compile("\\d+");
			Matcher m3 = p3.matcher(valorVariable);
			if (m3.matches()){
                            VariableClass registro2 = new VariableClass (NVariable, valorVariable, "ENTERO");
                            if(VariableClass.BuscarVariable((ArrayList<VariableClass>) ArregloVariables, NVariable)==null){ 
				ArregloVariables.add(registro2);
				MapaEnteros.put(NVariable, Integer.valueOf(valorVariable));
                            }else{
				System.out.println("\n\n Variable existente ¿Deseea reemplazarla? S/N ");
				char opc2 = teclado.next().charAt(0);
				if (opc2 == 'S' || opc2 == 's'){
                                    int indice = ArregloVariables.indexOf(VariableClass.BuscarVariable((ArrayList<VariableClass>) ArregloVariables, NVariable));
                                    ArregloVariables.set(indice, registro2);
                                    MapaEnteros.replace(NVariable, Integer.valueOf(valorVariable));
				}else{
                                    if (opc2 == 'N' || opc2 == 'n'){
                                        return;
                                    }else{
					System.out.println("\n ERROR 001: No se ingresó una opción válida. \n");}}}}
                                else{
                                    System.out.println("\n ERROR 004: Valor de variable no es numérico. Intente de nuevo \n");
                                    return;
				}
					}
				}else{
                                    Pattern p4 = Pattern.compile("[A-Za-z]+");
                                    Matcher m4 = p4.matcher(valorVariable);
                                    boolean test = m4.matches();
                                    if (m4.matches()){
                                        valorVariable=valorVariable.toUpperCase();
					VariableClass match = VariableClass.BuscarVariable((ArrayList<VariableClass>) ArregloVariables, valorVariable);
					if(VariableClass.BuscarVariable((ArrayList<VariableClass>) ArregloVariables, valorVariable)!=null){
                                            String Valor = match.getValor();
                                            String Tipo = match.getTipo();
                                            VariableClass et = new VariableClass (nombreVariable, Valor, Tipo);
						if (Tipo.equals("ENTERO")){
                                                    ArregloVariables.add(et);
                                                    MapaEnteros.put(nombreVariable, Integer.valueOf(Valor));
						}else{
                                                    ArregloVariables.add(et);
                                                    MapaReales.put(nombreVariable, Double.valueOf(Valor));
						}
						}else{
                                                    System.out.println("\n ERROR 004: Valor de variable no es numérico. Intente de nuevo \n");
						return;
						}
					}else{
						
						System.out.println("\n ERROR 005 : Valor de variable invalido, intente de nuevo con números únicamente. \n");
						return;
						
					}
				}
				
			}	
		}else{
			
			System.out.println("\n ERROR 006 : Debe asingar un valor a la variable usando '=' \n");
			return;
		}
			
	}


public static <T> void LIST(){
    if(ArregloVariables.isEmpty()){
        System.out.println("\n ERROR 006 : La lista de variables está vacía");
    return;	
    }else{
	Collections.sort(ArregloVariables, new Comparator<VariableClass>() {
            public int compare(VariableClass o1, VariableClass o2) {
            return o1.getNombre().compareTo(o2.getNombre());
	}});
            System.out.println("\nVARIABLE" + " , " + "VALOR" + " , " + "TIPO");
            for(int i = 0; i < ArregloVariables.size(); i++) {
		if(ArregloVariables.get(i).getTipo()=="ENTERO"){
                    System.out.println(ArregloVariables.get(i).getNombre()+" , "+ArregloVariables.get(i).getValor()+" , "+ArregloVariables.get(i).getTipo());
		}else{
                    System.out.println(ArregloVariables.get(i).getNombre()+" , "+ArregloVariables.get(i).getValor()+" , "+ArregloVariables.get(i).getTipo());
		}}}	
	}

*/
    

//Clase principal
    public static void main(String [ ] Args) throws IOException {
        CreateFiles(); 
        System.out.print("\n**********************************************" +
                     "\n*             MINGOSOFT ® MIDOS              *" +
                     "\n**********************************************\n" +
                     "\n© Copyright MINGOSOFT CORPORATION 2018\n" +
                     "\n Version 1.0"+
                     "\n Memoria libre ");
        ReadMemory();
        System.out.println("K");
        Menu();
    }

  
}