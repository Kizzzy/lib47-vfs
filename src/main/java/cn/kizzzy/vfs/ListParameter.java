package cn.kizzzy.vfs;

public class ListParameter {
    
    public boolean recursively;
    
    public ListParameter() {
        this(false);
    }
    
    public ListParameter(boolean recursively) {
        this.recursively = recursively;
    }
    
    public boolean isRecursively() {
        return recursively;
    }
    
    public void setRecursively(boolean recursively) {
        this.recursively = recursively;
    }
}
