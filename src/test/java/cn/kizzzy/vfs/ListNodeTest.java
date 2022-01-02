package cn.kizzzy.vfs;

import cn.kizzzy.vfs.pack.FilePackage;

public class ListNodeTest {
    
    public static void main(String[] args) throws Exception {
        IPackage vfs = new FilePackage("D:\\DevProject\\lib47\\lib47_java\\lib47-vfs\\src\\main\\java\\cn\\kizzzy\\vfs");
        //IPackage vfs = new ZipPackage("D:\\Temp.zip");
        ListNode root = vfs.list("", new ListParameter(true));
        listNode(root);
    }
    
    private static void listNode(ListNode parent) {
        System.out.println(parent);
        if (parent.children != null) {
            for (ListNode child : parent.children) {
                listNode(child);
            }
        }
    }
}
