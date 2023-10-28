public class MergeWorker extends Thread{

    int[] secondaryList;
    int startIndex;
    int endIndex;
    int[] firstList;
    public MergeWorker(int[] secondaryList, int sIndex, int eIndex, int[] primaryList) {
        this.secondaryList = secondaryList;
        this.startIndex = sIndex;
        this.endIndex = eIndex;
        this.firstList = primaryList;
    }

    public void run() {
        splitter(secondaryList, startIndex, endIndex, firstList);
    }
    private static void splitter(int[] listB, int startIndex, int endIndex, int[] listA){
        if(endIndex - startIndex <= 1) return;

        int middleIndex = (endIndex + startIndex) / 2;
        splitter(listA, startIndex, middleIndex, listB);
        splitter(listA, middleIndex, endIndex, listB);

        merger(listB, startIndex, middleIndex, endIndex, listA);
    }

    public static void merger(int[] constList, int startIndex, int middleIndex, int endIndex, int[] listerine){

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
    private void printList(int[] listy) {
        for(int i=0; i<listy.length; i++){
            System.out.println(listy[i]);
        }
    }

}
