package cn.kizzzy.vfs.tree;

import java.util.List;
import java.util.regex.Pattern;

public class TreeHelper {
    
    public static <T> void ListFileByFolder(Node<T> root, List<Leaf<T>> list) {
        if (root.IsLeaf()) {
            list.add(root.thumbs);
        }
        for (Node<T> v : root.children.values()) {
            if (v.IsLeaf()) {
                list.add(v.thumbs);
            } else {
                ListFileByFolder(v, list);
            }
        }
    }
    
    public static <T> Node<T> ListFolderByPath(Node<T> parent, String path, String separator) {
        String[] paths = path.split(separator);
        for (String v : paths) {
            Node<T> child = parent.children.get(v);
            if (child == null) {
                return null;
            }
            parent = child;
        }
        return parent;
    }
    
    public static <T> void ListFolderByRegex(Node<T> folder, Pattern regex, List<Node<T>> list) {
        if (regex.matcher(folder.name).find()) {
            list.add(folder);
        }
        
        for (Node<T> child : folder.children.values()) {
            ListFolderByRegex(child, regex, list);
        }
    }
}
