package cn.kizzzy.vfs.converter;

import cn.kizzzy.vfs.IConvertContext;
import cn.kizzzy.vfs.IConverterProvider;
import cn.kizzzy.vfs.IPackage;
import cn.kizzzy.vfs.context.BeginConvertContext;
import cn.kizzzy.vfs.context.NullConvertContext;

@SuppressWarnings("unchecked")
public class Converter {
    
    public static <T> IConvertContext<T> getContext(IConverterProvider provider, IPackage vfs, String path, Class<T> clazz) {
        T data = vfs.load(path, clazz);
        return getContext(provider, data);
    }
    
    public static <T> IConvertContext<T> getContext(IConverterProvider provider, T data) {
        if (data != null) {
            return new BeginConvertContext<>(provider, data, (Class<T>) data.getClass());
        }
        return new NullConvertContext<>();
    }
}
