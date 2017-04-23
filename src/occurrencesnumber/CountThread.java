/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package occurrencesnumber;

/**
 *
 * @author Luca
 */
public class CountThread extends Thread{
    int start;
    int end;
    int[] array;
    int[] occurrences;
    
    public CountThread(int start, int end, int[] array, int max){
        this.start = start;
        this.end = end;
        this.array = array;
        occurrences=new int[max+1];
    }
    
    public int [] getOccurrences(){
        return this.occurrences;
    }
    
    
    @Override
    public void run(){
        for (int i = start; i < end; i++) {
            occurrences[array[i]] += 1;
        }
    }
    
    
}
