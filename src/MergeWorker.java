public class MergeWorker extends Thread{

    int[] secondaryList;
    int startIndex;
    int endIndex;
    int[] firstList;
    static int depth;
    static Object lock = true;
    public MergeWorker(int[] secondaryList, int sIndex, int eIndex, int[] primaryList, int fogie) {
        this.secondaryList = secondaryList;
        this.startIndex = sIndex;
        this.endIndex = eIndex;
        this.firstList = primaryList;
        this.depth = fogie;
    }

    public void run() {
        splitter(secondaryList, startIndex, endIndex, firstList);
    }
    private void splitter(int[] listB, int startIndex, int endIndex, int[] listA){
        if(endIndex - startIndex < SortProject.minListSize){SortProject.selectionSort(listA); return;}

        //if(endIndex - startIndex <= 1) return;

        int middleIndex = (endIndex + startIndex) / 2;
        if(depth <= 2){
            MergeWorker workerOne = new MergeWorker(listA, startIndex, middleIndex, listB, depth++);
            MergeWorker workerTwo = new MergeWorker(listA, middleIndex, endIndex, listB, depth++);
            workerOne.start();workerTwo.start();
            try{
                workerOne.join();
                workerTwo.join();
            } catch (InterruptedException e) {}
        }
        splitter(listA, startIndex, middleIndex, listB);
        splitter(listA, middleIndex, endIndex, listB);


        merger(listB, startIndex, middleIndex, endIndex, listA);
    }

    public synchronized static void merger(int[] constList, int startIndex, int middleIndex, int endIndex, int[] listerine){
        SortProject.mergeCalls++;

        int x = startIndex; int y = middleIndex;

        for (int i=startIndex; i<endIndex; i++){
            SortProject.complexity++;
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
