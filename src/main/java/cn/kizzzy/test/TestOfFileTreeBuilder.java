package cn.kizzzy.test;

import cn.kizzzy.vfs.tree.FileTreeBuilder;
import cn.kizzzy.vfs.tree.Node;
import cn.kizzzy.vfs.tree.Root;

import java.io.File;

public class TestOfFileTreeBuilder {
    
    public static void main(String[] args) {
        FileTreeBuilder<Void> builder = new FileTreeBuilder<>();
        Root<Void> root = builder.build(new File("E:\\04Games\\Tencent\\QQ堂"));
        listRootImpl(root);
    }
    
    private static void listRootImpl(Node<Void> node) {
        if (node.IsLeaf()) {
            if (node.thumbs != null) {
                System.out.println(node.thumbs.path);
            } else {
                //System.out.println(node.name);
            }
        } else {
            System.out.println(node.name);
            for (Node<Void> child : node.children.values()) {
                listRootImpl(child);
            }
        }
    }
}
