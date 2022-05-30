package cn.kizzzy.vfs.streamable;

import cn.kizzzy.io.ByteArrayInputStreamReader;
import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.vfs.IStreamable;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
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
        try (ZipFile zipFile = new ZipFile(file)) {
            ZipEntry entry = zipFile.getEntry(path);
            if (entry == null) {
                throw new NullPointerException();
            }
            
            try (InputStream is = zipFile.getInputStream(entry);
                 ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[8192];
                int n = 0;
                while ((n = is.read(buffer)) > 0) {
                    bos.write(buffer, 0, n);
                }
                return new ByteArrayInputStreamReader(bos.toByteArray());
            }
        }
    }
}
