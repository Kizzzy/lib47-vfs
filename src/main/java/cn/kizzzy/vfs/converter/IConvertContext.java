package cn.kizzzy.vfs.converter;

public interface IConvertContext<Source> {
    
    <T> IConvertContext<T> load(Class<T> clazz) throws Exception;
    
    <T> IConvertContext<T> load(Class<T> clazz, IConverter<Source, T> handler);
    
    Source get();
}
