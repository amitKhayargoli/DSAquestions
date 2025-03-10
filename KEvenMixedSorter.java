import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class KEvenMixedSorter {

    public static void sortKEvenMixed(int[] A) {
        int n = A.length;
        List<Integer> odds = new ArrayList<>();
        List<Integer> evens = new ArrayList<>();

        for (int num : A) {
            if ((num & 1) == 1) {
                odds.add(num);
            } else {
                evens.add(num);
            }
        }

        int[] evenArray = new int[evens.size()];
        for (int i = 0; i < evens.size(); i++) {
            evenArray[i] = evens.get(i);
        }

        linearSort(evenArray);

        int[] merged = new int[n];
        int i = 0, j = 0, idx = 0;
        while (i < odds.size() && j < evenArray.length) {
            if (odds.get(i) < evenArray[j]) {
                merged[idx++] = odds.get(i++);
            } else {
                merged[idx++] = evenArray[j++];
            }
        }
        while (i < odds.size()) {
            merged[idx++] = odds.get(i++);
        }
        while (j < evenArray.length) {
            merged[idx++] = evenArray[j++];
        }

        System.arraycopy(merged, 0, A, 0, n);
    }

    private static void linearSort(int[] arr) {
        if (arr.length == 0)
            return;

        int min = arr[0], max = arr[0];
        for (int num : arr) {
            if (num < min)
                min = num;
            if (num > max)
                max = num;
        }
        int range = max - min + 1;
        int[] count = new int[range];

        for (int num : arr) {
            count[num - min]++;
        }
        int index = 0;
        for (int i = 0; i < range; i++) {
            while (count[i]-- > 0) {
                arr[index++] = i + min;
            }
        }
    }

    public static void main(String[] args) {
        int[] A = { 3, 8, 5, 2, 7, 4, 9 };
        sortKEvenMixed(A);
        System.out.println(Arrays.toString(A));
    }
}
