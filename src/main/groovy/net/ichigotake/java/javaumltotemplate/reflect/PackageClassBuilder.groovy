package net.ichigotake.java.javaumltotemplate.reflect;

public class PackageClassBuilder {

    private String name
    private List<Method> methods = new ArrayList<>()

    public PackageClass build() {
        return new PackageClassImpl(this)
    }

    private static class PackageClassImpl implements PackageClass {

        private final String name
        private final List<Method> methods

        private PackageClassImpl(PackageClassBuilder builder) {
            name = builder.name
            methods = builder.methods
        }

        @Override
        public String getName() {
            return name
        }

        @Override
        public String getSimpleName() {
            def paths = name.split(/\./)
            return paths[paths.length-1]
        }

        @Override
        String getPackageName() {
            return name
        }

        @Override
        String getCamelizeName() {
            def camelizeWords = new ArrayList<String>()
            name.split(/\./).each { def word ->
                camelizeWords.add("${word[0].toUpperCase()}${word.substring(1)}")
            }
            return camelizeWords.join(".")
        }

        @Override
        List<Method> getMethods() {
            return methods
        }

        @Override
        boolean isVoid() {
            return name == 'void'
        }
    }

    def PackageClassBuilder addMethod(Method method) {
        methods.add(method)
        return this
    }

    def setName(String name) {
        this.name = name
        return this
    }

}
