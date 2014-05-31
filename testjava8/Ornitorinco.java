package testjava8;

interface Animale {

    public void parla();

    public boolean zompa();
}

interface Papero extends Animale {

    @Override
    default public void parla() {
        System.out.println("Quack!");
    }

    @Override
    public default boolean zompa() {
        return true;
    }

}

interface Castoro extends Animale {

    @Override
    default void parla() {
        System.out.println("Crrrrr !");
    }
}

public class Ornitorinco implements Castoro, Papero {

    /**
     * Se non faccio un override esplicito il compilatore va nel panico e non
     * compila
     */
    @Override
    public void parla() {
        Castoro.super.parla();
    }

    @Override
    public boolean zompa() {
        return true;
    }

    public Ornitorinco() {
        System.out.println("Sono un ornitorinco, sono un mammifero che depone le uova ed è velenoso ed ha il becco da papero e la coda da castoro e sente le onde elettromagnetiche come uno squalo, fossi in te scapperei.");
    }

}
