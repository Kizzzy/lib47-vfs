package cn.kizzzy.vfs.tree;

import java.util.List;
import java.util.regex.Pattern;

public class TreeHelper {
    
    public static <T> void listLeaf(List<Leaf<T>> list, Node<T> node, boolean recursively) {
        if (node.leaf) {
            list.add((Leaf<T>) node);
            return;
        }
        
        for (Node<T> child : node.children.values()) {
            if (child.leaf) {
                list.add((Leaf<T>) child);
            } else if (recursively) {
                listLeaf(list, child, true);
            }
        }
    }
    
    public static <T> Node<T> listNode(Node<T> target, String path, String separator) {
        String[] names = path.split(separator);
        for (String name : names) {
            Node<T> child = target.children.get(name);
            if (child == null) {
                return null;
            }
            target = child;
        }
        return target;
    }
    
    public static <T> void listNodeByRegex(List<Node<T>> list, Node<T> node, Pattern regex) {
        if (regex.matcher(node.name).find()) {
            list.add(node);
        }
        
        for (Node<T> child : node.children.values()) {
            listNodeByRegex(list, child, regex);
        }
    }
}
