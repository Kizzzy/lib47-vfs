package cn.kizzzy.vfs.tree;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {
    
    @Override
    public int compare(Node x, Node y) {
        if (x.leaf) {
            if (y.leaf) {
                return naturalCompare(x.name, y.name);
            } else {
                return 1;
            }
        } else {
            if (y.leaf) {
                return -1;
            } else {
                return naturalCompare(x.name, y.name);
            }
        }
    }
    
    private int naturalCompare(String xs, String ys) {
        if (xs == null && ys == null)
            return 0;
        if (xs == null)
            return -1;
        if (ys == null)
            return 1;
        
        char[] x = xs.toCharArray();
        char[] y = ys.toCharArray();
        
        int lx = x.length, ly = y.length;
        
        int mx = 0, my = 0;
        for (; mx < lx && my < ly; mx++, my++) {
            if (Character.isDigit(x[mx]) && Character.isDigit(y[my])) {
                long vx = 0, vy = 0;
                
                for (; mx < lx && Character.isDigit(x[mx]); mx++)
                    vx = vx * 10 + x[mx] - '0';
                
                for (; my < ly && Character.isDigit(y[my]); my++)
                    vy = vy * 10 + y[my] - '0';
                
                if (vx != vy)
                    return vx > vy ? 1 : -1;
            }
            
            if (mx < lx && my < ly && x[mx] != y[my])
                return x[mx] > y[my] ? 1 : -1;
        }
        
        return lx - mx - (ly - my);
    }
}
