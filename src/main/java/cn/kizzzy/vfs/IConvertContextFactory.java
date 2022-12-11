package cn.kizzzy.vfs;

import cn.kizzzy.vfs.context.BeginConvertContext;
import cn.kizzzy.vfs.context.NullConvertContext;

@SuppressWarnings("unchecked")
public interface IConvertContextFactory extends IConverterProvider {
    
    default <T> IConvertContext<T> load(T data) {
        if (data != null) {
            return new BeginConvertContext<>(this, data, (Class<T>) data.getClass());
        }
        return new NullConvertContext<>();
    }
}
