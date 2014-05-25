package net.ichigotake.java.javaumltotemplate.reflect

public class MethodParameterBuilder {

    private String name

    MethodArgument build() {
        return new MethodArgumentImpl(this)
    }

    private static class MethodArgumentImpl implements MethodArgument {

        private final String name

        private MethodArgumentImpl(MethodParameterBuilder builder) {
            name = builder.name
        }

        @Override
        String getName() {
            return name
        }

        @Override
        String getSimpleName() {
            def names = name.split(/\./)
            return names[names.length-1]
        }
    }

    def setName(String name) {
        this.name = name
        return this
    }

}
