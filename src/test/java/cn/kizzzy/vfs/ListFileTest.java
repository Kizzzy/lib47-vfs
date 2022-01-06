package cn.kizzzy.vfs;

import cn.kizzzy.vfs.tree.FileTreeBuilder;
import cn.kizzzy.vfs.tree.IdGenerator;
import cn.kizzzy.vfs.tree.LocalTree;
import cn.kizzzy.vfs.tree.Node;
import cn.kizzzy.vfs.tree.NodeComparator;

import java.util.List;

public class ListFileTest {
    
    public static void main(String[] args) throws Exception {
        String folder = "D:\\Temp";
        
        ITree<Void> tree = new LocalTree<>(
            new FileTreeBuilder<Void>(folder, new IdGenerator()).build(),
            Separator.BACKSLASH_SEPARATOR_LOWERCASE
        );
        
        String[] paths = new String[]{
            "",
            "dll-dev",
            "xc",
            "dll-dev/xc",
            "dll-dev/xc/Game-001.dll",
        };
        for (String path : paths) {
            listNodeImpl(tree, path);
        }
    }
    
    private static void listNodeImpl(ITree<Void> tree, String path) {
        List<Node<Void>> list = tree.listNode(path);
        list.sort(new NodeComparator<>());
        System.out.printf("path: %-32s, node count: %4d, list:", path, list.size());
        for (Node<Void> item : list) {
            System.out.print(" " + item.name);
        }
        System.out.println();
    }
}
