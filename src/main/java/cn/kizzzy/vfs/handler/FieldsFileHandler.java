package cn.kizzzy.vfs.handler;

public class FieldsFileHandler extends TableFileHandler<String[]> {
    
    @Override
    protected String[] from(String[] fields) {
        return fields;
    }
    
    @Override
    protected String[] to(String[] data) {
        return data;
    }
}
