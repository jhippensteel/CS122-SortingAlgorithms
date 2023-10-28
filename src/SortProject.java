import javax.swing.*;

public class SortProject {
    static int[] list;

    public static void main(String[] args){
        String str;
        int n;
        str = JOptionPane.showInputDialog("How many terms? ");

        n = Integer.parseInt(str);
        list = new int[n];

        for(int i=0; i<n;i++) {
            list[i] = (int) (Math.random() * 1000000);
        }
        System.out.println("Random List");
        for (int i=0; i<list.length; i++) System.out.println(list[i]);

        mergeSort(list);

        System.out.println("Sorted List");
        for (int i=0; i<list.length; i++) System.out.println(list[i]);
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
        splitter(listB, 0, list.length, listA);

    }

    private static int[] deepCopy(int[] list){
        int[] copy = new int[list.length];
        for(int i=0; i<list.length; i++){
            copy[i]=list[i];
        }
        return copy;
    }

    private static void splitter(int[] listB, int startIndex, int endIndex, int[] listA){
        if(endIndex - startIndex <= 1) return;

        int middleIndex = (endIndex + startIndex) / 2;
        splitter(listA, startIndex, middleIndex, listB);
        splitter(listA, middleIndex, endIndex, listB);

        merger(listB, startIndex, middleIndex, endIndex, listA);
    }

    private static void merger(int[] constList, int startIndex, int middleIndex, int endIndex, int[] listerine){

        int x = startIndex; int y = middleIndex;

        for (int i=startIndex; i<endIndex; i++){
            if(x < middleIndex && (y >= endIndex || constList[x] <= constList[y])){
                listerine[i] = constList[x];
                x++;
            }
            else {
                listerine[i] = constList[y];
                y++;
            }
        }
    }
}
