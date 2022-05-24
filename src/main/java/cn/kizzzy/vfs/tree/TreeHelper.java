package cn.kizzzy.vfs.tree;

import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class TreeHelper {
    
    public static void listLeaf(List<Leaf> list, Node node, boolean recursively) {
        if (node.leaf) {
            list.add((Leaf) node);
            return;
        }
        
        for (Node child : node.children.values()) {
            if (child.leaf) {
                list.add((Leaf) child);
            } else if (recursively) {
                listLeaf(list, child, true);
            }
        }
    }
    
    public static void listLeaf(Node node, boolean recursively, Consumer<Leaf> callback) {
        if (node.leaf) {
            Leaf leaf = (Leaf) node;
            
            callback.accept(leaf);
        } else if (recursively) {
            for (Node child : node.children.values()) {
                listLeaf(child, true, callback);
            }
        }
    }
    
    public static Node listNode(Node target, String path, String separator) {
        String[] names = path.split(separator);
        for (String name : names) {
            Node child = target.children.get(name);
            if (child == null) {
                return null;
            }
            target = child;
        }
        return target;
    }
    
    public static void listNodeByRegex(List<Node> list, Node node, Pattern regex) {
        if (regex.matcher(node.name).find()) {
            list.add(node);
        }
        
        for (Node child : node.children.values()) {
            listNodeByRegex(list, child, regex);
        }
    }
}
