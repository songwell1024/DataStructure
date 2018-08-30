package SegmentTree;

/**
 * @author WilsonSong
 * @date 2018/8/28/028
 */
class NumArray {

    //预处理
    int[] sum;    //存储nums中前i个元素的和 sum[0] = 0; sum[i] = [0,1,2,...,i-1]的和

    public NumArray(int[] nums) {
        sum = new int[nums.length +1];
        sum[0] = 0;
        for (int i = 1; i < nums.length; i++ ){
            sum[i] = sum[i-1] + nums[i-1];     //初始化的时候就把sum初始化完成了
        }
    }

    public int sumRange(int i, int j) {
        return sum[j+1] - sum[i];


    }




    public static void  main(String[] args){
        int[] array = {-2, 0, 3, -5, 2, -1};
        NumArray obj = new NumArray(array);
        System.out.println(obj.sumRange(2,5));
    }
}