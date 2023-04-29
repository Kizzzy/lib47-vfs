package cn.kizzzy.vfs;

public interface IConverterProvider {
    
    <Source, Target> boolean addHandler(Class<Source> sourceClazz, Class<Target> targetClazz, IConverter<Source, Target> handler);
    
    <Source, Target> boolean removeHandler(Class<Source> sourceClazz, Class<Target> targetClazz);
    
    <Source, Target> IConverter<Source, Target> getHandler(Class<Source> sourceClazz, Class<Target> targetClazz);
}
