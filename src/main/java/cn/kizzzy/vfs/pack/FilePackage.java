package cn.kizzzy.vfs.pack;

import cn.kizzzy.helper.FileHelper;
import cn.kizzzy.helper.LogHelper;
import cn.kizzzy.vfs.IFileLoader;
import cn.kizzzy.vfs.IFileSaver;
import cn.kizzzy.vfs.IStrategy;
import cn.kizzzy.vfs.IStreamable;
import cn.kizzzy.vfs.ListNode;
import cn.kizzzy.vfs.ListParameter;
import cn.kizzzy.vfs.streamable.FileStreamable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Objects;

public class FilePackage extends PackageAdapter {
    
    public FilePackage(String root) {
        this(root, null);
    }
    
    public FilePackage(String root, IStrategy<String, Object> strategy) {
        super(root, '\\', strategy);
    }
    
    @Override
    protected ListNode listImpl(String path, ListParameter param) throws Exception {
        ListNode parent = new ListNode();
        parent.root = root;
        parent.name = path;
        parent.path = path;
        parent.children = new LinkedList<>();
        
        listImpl(Paths.get(root, path).toString(), parent, param.recursively);
        return parent;
    }
    
    private void listImpl(String path, ListNode parent, boolean recursively) throws Exception {
        try {
            path = path.replace(undesired, separator);
            
            for (File file : Objects.requireNonNull(new File(path).listFiles())) {
                if (file.isDirectory()) {
                    ListNode child = new ListNode();
                    child.root = root;
                    child.name = file.getName();
                    child.path = file.getPath().replace(root + separator, "");
                    child.children = new LinkedList<>();
                    
                    parent.children.add(child);
                    
                    if (recursively) {
                        listImpl(file.getAbsolutePath(), child, true);
                    }
                } else if (file.isFile()) {
                    ListNode child = new ListNode();
                    child.root = root;
                    child.name = file.getName();
                    child.path = file.getPath().replace(root + separator, "");
                    
                    parent.children.add(child);
                }
            }
        } catch (Exception e) {
            LogHelper.error(null, e);
        }
    }
    
    @Override
    public boolean exist(String path) {
        Path fullPath = Paths.get(root, path);
        return fullPath.toFile().exists();
    }
    
    @Override
    protected Object loadImpl(String path, IFileLoader<?> loader) throws Exception {
        Path fullPath = Paths.get(root, path);
        try (FileInputStream fis = new FileInputStream(fullPath.toFile())) {
            Object obj = loader.load(this, path, fis, fis.available());
            if (obj instanceof IStreamable) {
                ((IStreamable) obj).setSource(new FileStreamable(fullPath.toString()));
            }
            return obj;
        }
    }
    
    @Override
    protected <T> boolean saveImpl(String path, T data, IFileSaver<T> saver) throws Exception {
        Path fullPath = Paths.get(root, path);
        if (FileHelper.createFolderIfAbsent(fullPath.getParent().toFile())) {
            try (FileOutputStream fos = new FileOutputStream(fullPath.toFile())) {
                return saver.save(this, path, fos, data);
            }
        } else {
            LogHelper.error("Save Data Failed: Create Folder Failed");
            return false;
        }
    }
}
