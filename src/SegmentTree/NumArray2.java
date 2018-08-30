package SegmentTree;

/**
 * @author WilsonSong
 * @date 2018/8/28/028
 */
class NumArray2 {

    public  SegmentTree<Integer> segmentTree;
    public NumArray2(int[] nums) {
        Integer[] data = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++){
            data[i] = nums[i];
        }
        segmentTree  = new SegmentTree<Integer>(data, new Merger<Integer>() {
            @Override
            public Integer merge(Integer a, Integer b) {
                return a + b;
            }
        });
    }

    public void update(int i, int val) {
        if(segmentTree == null)
            throw new IllegalArgumentException("Error");
        segmentTree.set(i, val);
    }

    public int sumRange(int i, int j) {
        if(segmentTree == null)
            throw new IllegalArgumentException("Error");
        return segmentTree.query(i,j);
    }
}
