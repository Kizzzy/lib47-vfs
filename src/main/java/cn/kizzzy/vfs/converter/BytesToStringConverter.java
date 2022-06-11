package cn.kizzzy.vfs.converter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class BytesToStringConverter implements IConverter<byte[], String> {
    
    private final Charset charset;
    
    public BytesToStringConverter() {
        this(StandardCharsets.UTF_8);
    }
    
    public BytesToStringConverter(Charset charset) {
        this.charset = charset;
    }
    
    @Override
    public String convert(byte[] bytes) {
        return new String(bytes, charset);
    }
}
