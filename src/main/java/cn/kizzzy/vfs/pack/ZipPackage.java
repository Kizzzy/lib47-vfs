package cn.kizzzy.vfs.pack;

import cn.kizzzy.vfs.IFileLoader;
import cn.kizzzy.vfs.IFileSaver;
import cn.kizzzy.vfs.IStrategy;
import cn.kizzzy.vfs.ListNode;
import cn.kizzzy.vfs.ListParameter;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipPackage extends PackageAdapter {
    
    public ZipPackage(String root) {
        this(root, null);
    }
    
    public ZipPackage(String root, IStrategy<String, Object> strategy) {
        super(root, '/', strategy);
    }
    
    @Override
    protected ListNode listImpl(String path, ListParameter param) throws Exception {
        ListNode parent = new ListNode();
        parent.root = root;
        parent.name = path;
        parent.path = path;
        parent.children = new LinkedList<>();
        
        try (ZipFile zipFile = new ZipFile(root)) {
            for (Enumeration<? extends ZipEntry> e = zipFile.entries(); e.hasMoreElements(); ) {
                ZipEntry entry = e.nextElement();
                if (entry.getName().startsWith(path)) {
                    String tail = entry.getName();
                    if (!Objects.equals(path, "")) {
                        tail = tail.replace(path + separator, "");
                    }
                    if (tail.length() > 0) {
                        listImpl(parent, path, tail, param.recursively);
                    }
                }
            }
        }
        return parent;
    }
    
    private void listImpl(ListNode parent, String path, String tail, boolean recursively) throws Exception {
        String fulPath = Objects.equals(path, "") ? tail : (path + separator + tail);
        
        int index = tail.indexOf(separator);
        if (index < 0) {
            ListNode child = new ListNode();
            child.root = root;
            child.name = tail;
            child.path = fulPath;
            
            parent.children.add(child);
        } else {
            String folder = tail.substring(0, index);
            tail = tail.substring(index + 1);
            fulPath = Objects.equals(path, "") ? folder : (path + separator + folder);
            
            ListNode child = null;
            
            for (ListNode temp : parent.children) {
                if (temp.name.equals(folder)) {
                    child = temp;
                    break;
                }
            }
            
            if (child == null) {
                child = new ListNode();
                child.root = root;
                child.name = folder;
                child.path = fulPath;
                child.children = new LinkedList<>();
                
                parent.children.add(child);
            }
            
            if (tail.length() > 0 && recursively) {
                listImpl(child, fulPath, tail, true);
            }
        }
    }
    
    @Override
    public boolean exist(String path) {
        try (ZipFile zipFile = new ZipFile(root)) {
            ZipEntry entry = zipFile.getEntry(path);
            return entry != null;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    protected Object loadImpl(String path, IFileLoader<?> loader) throws Exception {
        try (ZipFile zipFile = new ZipFile(root)) {
            ZipEntry entry = zipFile.getEntry(path);
            if (entry != null) {
                return loader.load(this, path, zipFile.getInputStream(entry), entry.getSize());
            }
        }
        return null;
    }
    
    @Override
    protected <T> boolean saveImpl(String path, T data, IFileSaver<T> saver) throws Exception {
        /*try (ZipFile zipFile = new ZipFile(root)) {
            ZipEntry entry = zipFile.getEntry(path);
            return saver.save(zipFile.getOutputStream(entry), data);
        }*/
        return false;
    }
}
