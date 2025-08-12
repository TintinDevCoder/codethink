package mianjing;

public class jd {
    public void quickSort(int[] input, int low, int high) {
        int mid = partition(input, low, high);
        quickSort(input, low, mid - 1);
        quickSort(input, mid + 1, high);
    }
    public int partition(int[] input, int left, int right) {
        int p = input[left];
        while (left < right) {
            while (left < right && input[right] > p) {
                right--;
            }
            if (left < right) {
                input[left] = input[right];
            }
            while (left < right && input[left] < p) {
                left++;
            }
            if (left < right) {
                input[right] = input[left];
            }
        }
        input[left] = p;
        return left;
    }
}
