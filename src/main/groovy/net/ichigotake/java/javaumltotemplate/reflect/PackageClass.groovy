package net.ichigotake.java.javaumltotemplate.reflect;

public interface PackageClass {

    public String getName()

    public String getSimpleName()

    public String getPackageName()

    public String getCamelizeName()

    public List<Method> getMethods()

    public boolean isVoid()

}
