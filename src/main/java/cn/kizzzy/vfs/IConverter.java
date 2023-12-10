package cn.kizzzy.vfs;

public interface IConverter<Source, Target> {
    
    Target to(Source source) throws Exception;
    
    Source from(Target target) throws Exception;
}
