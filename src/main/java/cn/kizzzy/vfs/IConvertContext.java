package cn.kizzzy.vfs;

public interface IConvertContext<Source> {
    
    interface Handler<S, T> {
        
        T handle(S s) throws Exception;
    }
    
    <T> IConvertContext<T> to(Class<T> clazz);
    
    <T> IConvertContext<T> to(Class<T> clazz, Handler<Source, T> handler);
    
    Source get() throws Exception;
}
