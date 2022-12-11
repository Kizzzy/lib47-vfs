package cn.kizzzy.vfs;

public interface IConverter<Source, Target> {
    
    Target to(Source source);
    
    Source from(Target target);
}
