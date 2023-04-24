package cn.kizzzy.vfs.handler;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class LinesFileHandler extends TextFileHandler<String[]> {
    
    private final String sep;
    
    public LinesFileHandler() {
        this("\r\n");
    }
    
    public LinesFileHandler(String sep) {
        this(StandardCharsets.UTF_8, sep);
    }
    
    public LinesFileHandler(Charset charset, String sep) {
        super(charset);
        this.sep = sep;
    }
    
    @Override
    protected String[] loadImpl(String str) {
        return str.split(sep);
    }
    
    @Override
    protected String saveImpl(String[] data) {
        return String.join(sep, data);
    }
}
