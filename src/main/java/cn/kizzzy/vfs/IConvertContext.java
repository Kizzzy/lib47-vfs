package cn.kizzzy.vfs;

public interface IConvertContext<Source> {
    
    <T> IConvertContext<T> to(Class<T> clazz);
    
    <T> IConvertContext<T> to(Class<T> clazz, IConverter<Source, T> handler);
    
    Source get();
}
