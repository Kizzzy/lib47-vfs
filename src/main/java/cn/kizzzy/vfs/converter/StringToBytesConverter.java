package cn.kizzzy.vfs.converter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class StringToBytesConverter implements IConverter<String, byte[]> {
    
    private final Charset charset;
    
    public StringToBytesConverter() {
        this(StandardCharsets.UTF_8);
    }
    
    public StringToBytesConverter(Charset charset) {
        this.charset = charset;
    }
    
    @Override
    public byte[] convert(String s) {
        return s.getBytes(charset);
    }
}
