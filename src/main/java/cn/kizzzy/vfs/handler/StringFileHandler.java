package cn.kizzzy.vfs.handler;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class StringFileHandler extends TextFileHandler<String> {
    
    public StringFileHandler() {
        super(StandardCharsets.UTF_8);
    }
    
    public StringFileHandler(Charset charset) {
        super(charset);
    }
    
    @Override
    protected String loadImpl(String str) {
        return str;
    }
    
    @Override
    protected String saveImpl(String data) {
        return data;
    }
}
