package cn.kizzzy.vfs.streamable;

import cn.kizzzy.vfs.IStreamable;
import cn.kizzzy.io.FullyReader;
import cn.kizzzy.io.ZipInputStreamReader;

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
    
    public FullyReader OpenStream() throws Exception {
        try (ZipFile zipFile = new ZipFile(file)) {
            ZipEntry entry = zipFile.getEntry(path);
            if (entry == null) {
                throw new NullPointerException();
            }
            return new ZipInputStreamReader(zipFile.getInputStream(entry), entry.getSize());
        }
    }
}