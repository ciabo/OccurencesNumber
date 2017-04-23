/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package occurrencesnumber;

import java.util.Scanner;
import jdk.internal.org.objectweb.asm.commons.StaticInitMerger;

/**
 *
 * @author Luca
 */
public class OccurrencesNumber {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Ask for number or thread and number of elements
        int nThread;
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("Insert the number of Threads: ");
            nThread = scanner.nextInt();
        }while(nThread<=0);
        int arrayLength;
        do{
            System.err.println("Insert the array lentgth: ");
            arrayLength=scanner.nextInt();
        }while(arrayLength<=0);
        
        //create new array and fill it with random values
        int[] array=new int[arrayLength];
        int max=0;
        for (int i = 0; i < arrayLength; i++) {
            int rand=(int)(Math.random()*101);
            array[i]=rand;
            if(array[i]>max)
                max=array[i];
        }
        //create an array of threads and start threads
        CountThread[] threadsArray=new CountThread[nThread];
        int size=arrayLength/nThread;
        for (int i = 0; i < nThread; i++) {
            int start=i*size;
            int end=start+size+( (i+1 == nThread) && (0 != arrayLength%nThread ) ? arrayLength%nThread : 0);
            threadsArray[i]=new CountThread( start, end, array, max);
            threadsArray[i].start();
        }
        int[] totOccurrences= new int[max+1];
        try{
            for (int i = 0; i < nThread; i++) {
                threadsArray[i].join();
                int[] occurencesFromThread=threadsArray[i].getOccurrences();
                for (int j = 0; j < max+1; j++) {
                    totOccurrences[j]+= occurencesFromThread[j];
                }
            }
        }catch(InterruptedException e){}
        
        for (int i = 0; i < totOccurrences.length; i++) {
            System.out.println("Value: "+i+" Occurrences: "+totOccurrences[i]);
        }
    }
    
}
