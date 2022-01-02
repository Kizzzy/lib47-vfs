package cn.kizzzy.vfs;

import cn.kizzzy.vfs.pack.FilePackage;
import cn.kizzzy.vfs.handler.PropertiesHandler;
import cn.kizzzy.vfs.handler.StringFileHandler;

import java.util.Properties;

public class FilePackageTest {
    
    public static void main(String[] args) throws Exception {
        
        IPackage p = new FilePackage("D:\\testfs");
        p.getHandlerKvs().put(String.class, new StringFileHandler());
        p.getHandlerKvs().put(Properties.class, new PropertiesHandler());
        
        p.save("123.txt", "test file package save");
        
        String str = p.load("123.txt", String.class);
        System.out.println(str);
    }
}
