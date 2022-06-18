package cn.kizzzy.vfs.stream;

import cn.kizzzy.io.ByteArrayInputStreamReader;
import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.IFullyWriter;
import cn.kizzzy.vfs.IInputStreamGetter;
import cn.kizzzy.vfs.IOutputStreamGetter;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipStreamGetter implements IInputStreamGetter, IOutputStreamGetter {
    
    private final String file;
    
    private final String path;
    
    public ZipStreamGetter(String file, String path) {
        this.file = file;
        this.path = path;
    }
    
    public IFullyReader getInput() throws Exception {
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
    
    @Override
    public IFullyWriter getOutput() throws Exception {
        return null;
    }
}
