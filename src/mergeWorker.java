public class mergeWorker extends Thread{
    int[] listB;
    int startIndex;
    int endIndex;
    int[] listA;
    public mergeWorker(int[] secondaryList, int sIndex, int eIndex, int[] primaryList) {
        listB = secondaryList;
        startIndex = sIndex;
        endIndex = eIndex;
        listA = primaryList;
    }

    public synchronized void run() {
        if(endIndex - startIndex <= 1) return;

        int middleIndex = (endIndex + startIndex) / 2;

        mergeWorker threadOne = new mergeWorker(listA, startIndex, middleIndex, listB);
        mergeWorker threadTwo = new mergeWorker(listA, startIndex, middleIndex, listB);
        threadOne.start();threadTwo.start();
        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        merger(listB, startIndex, middleIndex, endIndex, listA);


    }
    private synchronized void merger(int[] constList, int startIndex, int middleIndex, int endIndex, int[] listerine){

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
