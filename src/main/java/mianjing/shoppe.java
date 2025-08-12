package mianjing;

public class shoppe {
    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // 找到分区索引
            int pi = partition(arr, low, high);

            // 递归排序分区
            quickSort(arr, low, pi - 1);  // 排序左边
            quickSort(arr, pi + 1, high); // 排序右边
        }
    }

    private int partition(int[] arr, int low, int high) {
        // 选择最后一个元素作为基准
        int pivot = arr[low];
        int left = low, right = high;
        while (left < right) {
            while (right > left && arr[right] >= pivot) {
                right--;
            }
            if (left <= right) {
                arr[left] = arr[right];
            }
            while (right > left && arr[left] <= pivot) {
                left++;
            }
            if (left <= right) {
                arr[right] = arr[left];
            }
        }
        arr[left] = pivot;
        return left; // 返回基准元素的索引
    }

    public static void main(String[] args) {
        shoppe s = new shoppe();
        int[] input = new int[]{3,4,6,1,2,4,7};
        s.quickSort(input, 0, 6);
        System.out.println(input.toString());
    }
}
