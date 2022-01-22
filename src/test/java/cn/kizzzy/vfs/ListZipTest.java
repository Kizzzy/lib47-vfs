package cn.kizzzy.vfs;

import cn.kizzzy.vfs.tree.IdGenerator;
import cn.kizzzy.vfs.tree.Node;
import cn.kizzzy.vfs.tree.NodeComparator;
import cn.kizzzy.vfs.tree.ZipTreeBuilder;

import java.util.List;

public class ListZipTest {
    
    public static void main(String[] args) throws Exception {
        String zipFile = "D:\\Temp.zip";
        String[] paths = new String[]{
            "",
            "dll-dev",
            "xc",
            "dll-dev/xc",
            "dll-dev/xc/Game-001.dll",
        };
        
        ITree tree = new ZipTreeBuilder(zipFile, new IdGenerator()).build();
        
        for (String path : paths) {
            listNodeImpl(tree, path);
        }
    }
    
    private static void listNodeImpl(ITree tree, String path) {
        List<Node> list = tree.listNode(path);
        list.sort(new NodeComparator());
        System.out.printf("path: %-32s, node count: %4d, list:", path, list.size());
        for (Node item : list) {
            System.out.print(" " + item.name);
        }
        System.out.println();
    }
}
