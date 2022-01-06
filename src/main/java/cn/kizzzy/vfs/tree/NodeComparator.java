package cn.kizzzy.vfs.tree;

import java.util.Comparator;

public class NodeComparator<T> implements Comparator<Node<T>> {
    
    @Override
    public int compare(Node<T> x, Node<T> y) {
        String xName = x.name;
        String yName = y.name;
        
        try {
            int n1 = Integer.parseInt(xName);
            try {
                int n2 = Integer.parseInt(yName);
                return n1 - n2;
            } catch (Exception e0) {
                return -1;
            }
        } catch (Exception e1) {
            try {
                int n2 = Integer.parseInt(yName);
                return 1;
            } catch (Exception e2) {
                return xName.compareTo(yName);
            }
        }
    }
}
