package cn.kizzzy.vfs.tree;

import java.util.Comparator;

public class NodeComparator<T> implements Comparator<Node<T>> {
    
    @Override
    public int compare(Node<T> x, Node<T> y) {
        String name1 = x.name;
        String name2 = y.name;
        
        try {
            int n1 = Integer.parseInt(name1);
            try {
                int n2 = Integer.parseInt(name2);
                return n1 - n2;
            } catch (Exception e0) {
                return -1;
            }
        } catch (Exception e1) {
            try {
                int n2 = Integer.parseInt(name2);
                return 1;
            } catch (Exception e2) {
                return name1.compareTo(name2);
            }
        }
    }
}
