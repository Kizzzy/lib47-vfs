package cn.kizzzy.vfs.streamable;

import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.InputStreamReader;
import cn.kizzzy.vfs.IStreamable;

import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipStreamable implements IStreamable {
    
    private final String file;
    private final String path;
    
    private IStreamable source;
    
    public ZipStreamable(String file, String path) {
        this.file = file;
        this.path = path;
    }
    
    @Override
    public IStreamable getSource() {
        return source;
    }
    
    @Override
    public void setSource(IStreamable source) {
        this.source = source;
    }
    
    public IFullyReader OpenStream() throws Exception {
        ZipFile zipFile = new ZipFile(file);
        ZipEntry entry = zipFile.getEntry(path);
        if (entry == null) {
            zipFile.close();
            throw new NullPointerException();
        }
        return new InputStreamReader(zipFile.getInputStream(entry), entry.getSize());
    }
}
