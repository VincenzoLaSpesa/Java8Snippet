/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testjava8;

class Scatola{
    int altezza;
    int larghezza; 
    int profondita;

    public Scatola(int altezza, int larghezza, int profondita) {
        this.altezza = altezza;
        this.larghezza = larghezza;
        this.profondita = profondita;
        //System.out.println(this.toString());
    }
    
    @Override
    public final String toString(){
        return String.format("{%d,%d,%d}:scatola",altezza,larghezza,profondita);
    }
    
    public int max(){
        return Integer.max(altezza, Integer.max(larghezza, profondita));
    }
    
}