package testjava8;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author darshan
 */

public class Main {

    public static void main(String[] args) {
        testainterfacce();
        testalambda();
        testastream();
        testaereditarietamultipla();
    }
    
    public static void testaereditarietamultipla(){
        Ornitorinco o= new Ornitorinco();
        o.parla();
    }
    
    public static void testastream(){
        Random r= new Random(0);
        //List<Scatola> scatole= new ArrayList<>();
        
        BufferCircolare<Scatola> scatole= new BufferCircolare<>(10);
        for(int i=0; i<500 ;  i++)scatole.add(new Scatola(1+r.nextInt(10), 1+r.nextInt(10), 1+r.nextInt(10)));
        //il buffer scorre, ci saranno solo 10 scatole dentro.
        System.out.println(scatole.dump());                
        
        System.out.println(scatole.stream().filter(elemento -> elemento.altezza> elemento.profondita).count());
        
        //è interessante notare che filter è un metodo pigro, fa il meno possibile, se mi limito a
        System.out.println(scatole.stream().filter(elemento -> {System.out.println("ciao"); return elemento.altezza> elemento.profondita ; }));
        //non succede un bel niente, è il metodo count che forza filter ad agire
        
        scatole.stream().filter(elemento -> elemento.altezza> elemento.profondita).forEach(i-> System.out.println(i));
        List<Scatola>scatole2 =scatole.stream().filter(elemento -> elemento.altezza> elemento.profondita).collect(Collectors.toList());
        System.out.println(scatole2.toString());
        
        List<Integer>massime =scatole.stream().filter(elemento -> elemento.altezza> elemento.profondita).map(i-> i.max()).collect(Collectors.toList());
        System.out.println(massime);
        
    }
    
    public static void testalambda(){
        //funzionale
        Predicate<Double> positivo = x -> x >= 0;
        positivo.test(-1.0);//il compilatore è un po' tonto, i tipi vanno specificati molto bene
        
        BinaryOperator<Integer> somma = (x, y) -> (x + y); // tutto dello stesso tipo
        Integer c = somma.apply(2, 3);
        
        Runnable r=()->{System.out.println("Finalmente, niente orrende classi anonime");};
        Thread t= new Thread(r);
        t.start();
    
    }
    
    public static void testainterfacce(){
        //test delle interfacce con metodi di default
        class Classe implements Interfaccia8 {

            @Override
            public boolean metodoastratto() {
                return false;
            }
        }
        Classe o = new Classe();
        System.out.println(o.metodo());
        System.out.println(o.metodoastratto());    
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
