import javax.swing.*;
import java.util.Arrays;

public class SortProject {
    static int[] list;

    public static void main(String[] args){
        String str;
        int n;
        str = JOptionPane.showInputDialog("How many terms? ");

        n = Integer.parseInt(str);
        list = new int[n];

        for(int i=0; i<n;i++) {
            list[i] = (int) (Math.random() * 100000000);
        }
        /*
        System.out.println("Random List");
        for (int i=0; i<list.length; i++) System.out.println(list[i]);
         */

        mergeSort(list);
        int[] newList = deepCopy(list);
        Arrays.stream(newList).sorted();

        System.out.println("Sorted List");
        for (int i=0; i<list.length; i++) {
            System.out.println(list[i] + "  " + newList[i]);
            if(list[i] != newList[i]) {
                System.out.println("\nSort Failed");
                break;
            }
        }
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
        MergeWorker workerOne = new MergeWorker(listA, 0, listA.length/2, listB);
        MergeWorker workerTwo = new MergeWorker(listA, listA.length/2, listA.length, listB);
        workerOne.start();workerTwo.start();

        try {
            workerOne.join();
            workerTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MergeWorker.merger(listB, 0, listA.length/2, listA.length, listA);
    }

    private static int[] deepCopy(int[] list){
        int[] copy = new int[list.length];
        for(int i=0; i<list.length; i++){
            copy[i]=list[i];
        }
        return copy;
    }
}
