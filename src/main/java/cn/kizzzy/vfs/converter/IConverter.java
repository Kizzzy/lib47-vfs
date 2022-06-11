package cn.kizzzy.vfs.converter;

public interface IConverter<Source, Target> {
    
    Target convert(Source source);
}
