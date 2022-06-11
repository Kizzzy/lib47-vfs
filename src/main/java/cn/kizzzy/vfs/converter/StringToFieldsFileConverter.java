package cn.kizzzy.vfs.converter;

import cn.kizzzy.data.FieldsFile;

public class StringToFieldsFileConverter extends StringToTableFileConverter<String[], FieldsFile> {
    
    public StringToFieldsFileConverter() {
        super(FieldsFile.class);
    }
    
    public StringToFieldsFileConverter(boolean skip, boolean skipFirst, String lineSeparator, String fieldSeparator) {
        super(FieldsFile.class, skip, skipFirst, lineSeparator, fieldSeparator);
    }
    
    @Override
    protected String[] toEntity(String[] fields) {
        return fields;
    }
}
