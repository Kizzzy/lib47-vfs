package cn.kizzzy.vfs;

public interface IConverterProvider {
    
    <Source, Target> boolean addHandler(Class<Source> sourceClazz, Class<Target> clazz, IConverter<Source, Target> handler);
    
    <Source, Target> boolean removeHandler(Class<Source> sourceClazz, Class<Target> clazz);
    
    <Source, Target> IConverter<Source, Target> getHandler(Class<Source> sourceClazz, Class<Target> clazz);
}
