import javax.swing.*;
import java.util.Arrays;

public class SortProject {
    static long complexity = 0;
    static int mergeCalls = 0;
    static int[] list;
    static int minListSize = 10;

    public static void main(String[] args){
        String str;
        int n;
        str = JOptionPane.showInputDialog("How many terms? ");

        n = Integer.parseInt(str);
        list = new int[n];

        for(int i=0; i<n;i++) {
            list[i] = (int) (Math.random() * 100000);
        }
        /*
        System.out.println("Random List");
        for (int i=0; i<list.length; i++) System.out.println(list[i]);
         */

        long startTime = System.currentTimeMillis();
        mergeSort(list);
        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println("Complexity: " + complexity);
        System.out.println("Merge Calls: " + mergeCalls);

        ///*
        int[] newList = deepCopy(list);



        selectionSort(newList);
        //for(int i=0; i<list.length; i++) System.out.println(newList[i]);





        System.out.println("Sorted List");
        for (int i=0; i<list.length; i++) {
            System.out.println(list[i] + "  " + newList[i]);
            if(list[i] != newList[i]) {
                System.out.println("\nSort Failed");
                break;
            }
        }
        //*/

    }

    public static void selectionSort(int[] list) {
        int s,i,temp, j;
        for (i=0; i<list.length; i++){
            s = i;
            for (j=i+1;j<list.length;j++){
                if(list[j] < list[s]) {s=j;}
            }
            temp = list[i];
            list[i] = list[s];
            list[s] = temp;
        }


    }
    public static void mergeSort(int[] listA){

        int[] listB = deepCopy(list);
        MergeWorker workerPrime = new MergeWorker(listA, 0, listA.length/8, listB, 1);

        workerPrime.start();

        try {
            workerPrime.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static int[] deepCopy(int[] list){
        int[] copy = new int[list.length];
        for(int i=0; i<list.length; i++){
            copy[i]=list[i];
        }
        return copy;
    }
}
