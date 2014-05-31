package testjava8;

/**
 *
 * @author darshan
 */
public interface Interfaccia8 {
    boolean metodoastratto();
    //I metodi di default permettono di aggiungere dei metodi implementati all'interno delle interfacce
    default boolean metodo(){
        return true;
    }
}
