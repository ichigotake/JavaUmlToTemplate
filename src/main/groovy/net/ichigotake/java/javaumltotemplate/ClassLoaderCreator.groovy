package net.ichigotake.java.javaumltotemplate;

public class ClassLoaderCreator {

    public static ClassLoader createClassLoader(String dirname) throws IOException {
        def url = new URL[1]
        def file = new File((dirname.endsWith("/") ? dirname : dirname + "/"))
        url[0]= file.toURI().toURL()

        ClassLoader parent = ClassLoader.getSystemClassLoader();
        return new URLClassLoader(url, parent);
    }

}
