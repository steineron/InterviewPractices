import java.util.Scanner;
import java.util.Arrays;

public class Main{

     public static void main(String []args){
        Scanner scanner = new Scanner(System.in);
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] ins = scanner.nextLine().split(" ");

        char t = ins[1].charAt(0);
        int n = Integer.parseInt(ins[0]);


        int[] arr = new int[n];

        String[] numbers = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        System.out.println("Sorting: " + numbers.length);

        for (int i = 0; i < n; i++) {

            arr[i] = Integer.parseInt(numbers[i]);
        }

        int [] sorted = null;
        switch(t){
            case 'q':
                sorted = quickSort(arr);
                break;
            case 'm':
                sorted = mergeSort(arr);
                break;
            case 'h':
                sorted = heapSort(arr);
                break;

        }
        if(sorted!=null){

            System.out.println(stringify(sorted));

        }
        else{
            System.out.println("Something's wrong");
        }
     }

     private static void swap(int[] a, int i, int j){
         int temp = a[i];
         a[i]=a[j];
         a[j]=temp;
     }

    private static String stringify(int[] arr){
      StringBuilder b = new StringBuilder("[");
      for (int i=0; i<arr.length; i++) {
        b.append(arr[i]).append(',');
      }
      if(b.length()>1){
        b.setCharAt(b.length()-1, ']');
      }else{
        b.append("]");
      }
      return b.toString();
    }

    static void log(String msg){
      System.out.println(msg);
    }

     /**
      * MergeSort
     */
     public static int[] mergeSort(int[] arr){

         return mergeSortRange(arr, 0, arr.length-1);
     }

      private static int[] mergeSortRange(int[] arr, int i, int j){
          if(i<j){
             int mid = (j+i)/2; // floors
             System.out.println(String.format("mergeSortRange:%d,%d,%d", i,mid,j));

             return merge(mergeSortRange(arr, i,mid), mergeSortRange(arr, mid+1,j));

          }
          if(i==j){
            int[] res = new int[1];
            res[0]=arr[i];
             return res;
          }
          return new int[0];
      }
      private static int[] merge(int[] left, int[] right){
          int[] sorted = new int[left.length+right.length];
          int l=0;
          int r=0;
          System.out.println(String.format("merge :%s, %s", stringify(left), stringify(right)));

          for (int i=0; i<sorted.length; i++){
              if(l< left.length && r<right.length){
                  boolean fromLeft = left[l]<right[r];
                  sorted[i] = fromLeft ? left[l] : right[r];
                  if(fromLeft){
                      l++;
                  }
                  else{
                      r++;
                  }
              }
              else if(l >= left.length){
                  sorted[i] = right[r];
                  r++;
              }
              else{
                  sorted[i] = left[l];
                  l++;
              }
          }
          System.out.println("Merged: "+stringify(sorted));
          return sorted;
      }

     /**
        QuickSort
    */

     public static int[] quickSort(int[] arr){
         quickSortRange(arr, 0,arr.length-1);
         return arr;
     }

     private static void quickSortRange(int[] arr, int i, int j){
         if(i<j){
             int pivot = quickSortPartition(arr,i,j);
             quickSortRange(arr,i,pivot-1);
             quickSortRange(arr,pivot+1,j);
         }
     }

     private static int quickSortPartition(int[] arr, int s, int t){
        int pivot=s;
        if(s<t){
             int part = arr[t];

             for (int i=s; i<t; i++){
                 if(arr[i] < part){
                     swap(arr, pivot, i);
                     pivot++;
                 }
             }
             swap(arr, t, pivot);
         }
         return pivot;
     }
     /**
     HeapSort
     */

     static class Heap{

       static Heap from(int[] arr){
         Heap h = new Heap();
         h.elements = Arrays.copyOf(arr, arr.length);
         h.size = h.elements.length;

         for (int i=arr.length/2; i>=0; i--){
           h.maxHeapify(i);
         }
         return h;
       }

       int[] elements;
       int size;

        boolean hasElement(int i){
          return i>=0 && i<size;
        }

       int leftIndex(int i){
         return i*2;
       }
       int rightIndex(int i){
         return i*2+1;
       }
       int parentIndex(int i){
         return i<=0 ? -1 : (i-1)/2;
       }
       int left(int i){
         return elements[leftIndex(i)];
       }
       int right(int i){
         return elements[rightIndex(i)];
       }
       int parent(int i){
         return elements[parentIndex(i)];
       }



       void maxHeapify(int i){
         if(i>=0){

            int indexOfLargest = i;
            if(hasElement(leftIndex(i)) && elements[indexOfLargest] < left(i)){
              indexOfLargest = leftIndex(i);
            }
            if(hasElement(rightIndex(i)) && elements[indexOfLargest] < right(i)){
              indexOfLargest = rightIndex(i);
            }
            if(i!=indexOfLargest && hasElement(indexOfLargest)){
              swap(elements, i,indexOfLargest);
              maxHeapify(indexOfLargest);
            }

         }
       }



     }
     public static int[] heapSort(int [] arr){
       // build the heap:
       Heap heap = Heap.from(arr);
       System.out.println("Heap: "+stringify(heap.elements));

       while(heap.size>=1){
         log("Heap size: "+heap.size);

         swap(heap.elements, 0,heap.size-1);
         heap.size--;
         log("Before: "+ stringify(heap.elements));

         heap.maxHeapify(0);

         log("After: "+ stringify(heap.elements));

       }
       return heap.elements;


     }
}
