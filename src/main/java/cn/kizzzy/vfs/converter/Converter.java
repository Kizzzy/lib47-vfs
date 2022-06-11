package cn.kizzzy.vfs.converter;

public class Converter {
    
    public static <T> IConvertContext<T> convert(T data, Class<T> clazz) {
        if (data != null) {
            return new BeginConvertContext<>(null, data, clazz);
        }
        return new NullConvertContext<>();
    }
}
