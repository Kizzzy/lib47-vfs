package cn.kizzzy.vfs.handler;

import java.nio.charset.Charset;

public class FieldsFileHandler extends TableFileHandler<String[]> {
    
    public FieldsFileHandler() {
        super();
    }
    
    public FieldsFileHandler(Charset charset, boolean skip, boolean skipFirst, String lineSeparator, String fieldSeparator) {
        super(charset, skip, skipFirst, lineSeparator, fieldSeparator);
    }
    
    @Override
    protected String[] from(String[] fields) {
        return fields;
    }
    
    @Override
    protected String[] to(String[] data) {
        return data;
    }
}
