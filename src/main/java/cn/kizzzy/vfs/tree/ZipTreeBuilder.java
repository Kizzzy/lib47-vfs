package cn.kizzzy.vfs.tree;

import cn.kizzzy.helper.LogHelper;
import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.Separator;

import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipTreeBuilder extends TreeBuilderAdapter<ZipFile, ZipEntry> {
    
    protected final String zipFile;
    
    public ZipTreeBuilder(String zipFile) {
        this(zipFile, new IdGenerator());
    }
    
    public ZipTreeBuilder(String zipFile, IdGenerator idGenerator) {
        super(new Separator(Separator.SLASH, false), idGenerator);
        this.zipFile = zipFile;
    }
    
    @Override
    public ITree build() {
        try {
            try (ZipFile zipFile = new ZipFile(this.zipFile)) {
                return buildImpl(zipFile, new TreeBuilderAdapter.Helper<ZipFile, ZipEntry>() {
                    
                    @Override
                    public String idxPath(ZipFile idxFile) {
                        return zipFile.getName();
                    }
                    
                    @Override
                    public Iterable<ZipEntry> entries(ZipFile idxFile) {
                        return idxFile
                            .stream()
                            .filter(e -> !e.getName().endsWith(separator.getDesiredSplitter()))
                            .collect(Collectors.toList());
                    }
                    
                    @Override
                    public String itemPath(ZipEntry item) {
                        return item.getName();
                    }
                });
            }
        } catch (Exception e) {
            LogHelper.error(String.format("build tree failed: %s", zipFile), e);
            return null;
        }
    }
}
