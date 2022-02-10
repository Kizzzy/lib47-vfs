package cn.kizzzy.vfs.tree;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {
    
    @Override
    public int compare(Node x, Node y) {
        if (x.leaf) {
            if (y.leaf) {
                return compareName(x.name, y.name);
            } else {
                return 1;
            }
        } else {
            if (y.leaf) {
                return -1;
            } else {
                return compareName(x.name, y.name);
            }
        }
    }
    
    private int compareName(String name1, String name2) {
        if (name1.matches("^[0-9]+$")) {
            if (name2.matches("^[0-9]+$")) {
                if (name1.length() != name2.length()) {
                    return name1.length() - name2.length();
                }
            }
        }
        return name1.compareTo(name2);
    }
}
