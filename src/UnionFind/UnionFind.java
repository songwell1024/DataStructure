package UnionFind;

/**
 * 基于树结构并查集
 * @author WilsonSong
 * @date 2018/8/29/029
 */
public class UnionFind implements UF {
    private int[] parent;

    private int[] rank;

    /**
     * 并查集的初始化
     * @param size
     */
    public UnionFind(int size){
        parent = new int[size];
        rank = new  int[size];
        for (int i = 0; i < size; i++){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * 找到p所在的集合
     * @param p 节点
     * @return
     */
    private int find(int p){
        if (p < 0 || p > parent.length){
            throw  new IllegalArgumentException("p is illegal");
        }
        while (parent[p] != p){
            parent[p] = parent[parent[p]];
            p = parent[p];
//            parent[p] = find(parent[p]);
        }
        return parent[p];
    }

    /**
     * 查看元素p和元素q是否所属一个集合
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnected(int p, int q){
        return find(p) == find(q);
    }

    /**
     * 合并两个集合
     * @param p
     * @param q
     */
    @Override
    public void UnionElements(int p, int q){
        if (p < 0 || q < 0 ||  p > parent.length || q > parent.length){
            throw new IllegalArgumentException("index is illegal");
        }
        int pRoot = find(p);
        int qRoot = find(q);
        if (rank[pRoot] > rank[qRoot]){
            parent[qRoot] = pRoot;
        }else if (rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        }else {
            parent[pRoot] = pRoot;
            rank[pRoot] += 1;
        }


    }


}
