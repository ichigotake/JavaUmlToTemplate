package net.ichigotake.java.javaumltotemplate.reflect;

class MethodBuilder {

    private String name
    private List<MethodArgument> arguments = new ArrayList<>()
    private PackageClass returnType = new PackageClassBuilder().setName("void").build()

    def build() {
        return new MethodImpl(this)
    }

    private static class MethodImpl implements Method {

        private final String name
        private final PackageClass returnType
        private List<MethodArgument> arguments

        private MethodImpl(MethodBuilder builder) {
            name = builder.name
            returnType = builder.returnType
            arguments = builder.arguments
        }

        @Override
        def String getName() {
            return name
        }

        @Override
        def PackageClass getReturnType() {
            return returnType
        }

        @Override
        List<MethodArgument> getArguments() {
            return arguments
        }
    }

    def addArguments(MethodArgument methodParameter) {
        arguments.add(methodParameter)
        return this
    }

    def setReturnType(PackageClass returnType) {
        this.returnType = returnType
        return this
    }

    def setName(String name) {
        this.name = name
        return this
    }

}
