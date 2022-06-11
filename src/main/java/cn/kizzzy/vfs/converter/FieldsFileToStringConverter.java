package cn.kizzzy.vfs.converter;

import cn.kizzzy.data.FieldsFile;

public class FieldsFileToStringConverter extends TableFileToStringConverter<String[], FieldsFile> {
    
    public FieldsFileToStringConverter() {
        super(FieldsFile.class);
    }
    
    public FieldsFileToStringConverter(boolean skip, boolean skipFirst, String lineSeparator, String fieldSeparator) {
        super(FieldsFile.class, skip, skipFirst, lineSeparator, fieldSeparator);
    }
    
    @Override
    protected String[] fromEntity(String[] fields) {
        return fields;
    }
}
