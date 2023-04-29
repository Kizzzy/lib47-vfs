package cn.kizzzy.vfs;

import java.util.function.Function;

public interface IConvertContext<Source> {
    
    <T> IConvertContext<T> to(Class<T> clazz);
    
    <T> IConvertContext<T> to(Class<T> clazz, Function<Source, T> handler);
    
    Source get();
}
