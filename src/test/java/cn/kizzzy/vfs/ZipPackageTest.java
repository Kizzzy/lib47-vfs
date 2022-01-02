package cn.kizzzy.vfs;

import cn.kizzzy.vfs.handler.StringFileHandler;
import cn.kizzzy.vfs.pack.ZipPackage;

public class ZipPackageTest {
    
    public static void main(String[] args) throws Exception {
        IPackage p = new ZipPackage("D:\\testfs\\testfs.zip");
        p.getHandlerKvs().put(String.class, new StringFileHandler());
        
        System.out.println(p.load("test/123.txt", String.class));
    }
}
