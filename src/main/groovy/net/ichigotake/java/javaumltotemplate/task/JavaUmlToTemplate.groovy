package net.ichigotake.java.javaumltotemplate.task

import net.ichigotake.java.javaumltotemplate.ClassLoaderCreator
import net.ichigotake.java.javaumltotemplate.reflect.ClassTemplate
import net.ichigotake.java.javaumltotemplate.sandbox.PerlTemplate
import net.ichigotake.java.javaumltotemplate.reflect.MethodBuilder
import net.ichigotake.java.javaumltotemplate.reflect.MethodParameterBuilder
import net.ichigotake.java.javaumltotemplate.reflect.PackageClass
import net.ichigotake.java.javaumltotemplate.reflect.PackageClassBuilder
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.Velocity

import static org.apache.velocity.app.Velocity.*

/**
 * TODO: be gradle task
 */
class JavaUmlToTemplate {

    private static final String CHARSET = "UTF-8"

    static void main(String[] args) {
        //TODO: be pluggable
        def template = new PerlTemplate()

        ClassLoaderCreator.createClassLoader(template.getClassPath().toString())
        template.getClassPath().eachFileRecurse{
            if (it.file && it.name =~ /.*\.java/) {
                def clazz = getClass(template, it.path)
                def builder = new PackageClassBuilder().setName(clazz.getCanonicalName())
                clazz.getMethods().each {
                    def methodBuilder = new MethodBuilder()
                    it.getParameterTypes().each { parameter ->
                        methodBuilder.addArguments(
                                new MethodParameterBuilder()
                                        .setName(parameter.name)
                                        .build()
                        )
                    }
                    def method = methodBuilder
                            .setName(it.name)
                            .setReturnType(new PackageClassBuilder().setName(it.returnType.getCanonicalName()).build())
                            .build()
                    builder.addMethod(method)
                }

                write(template, builder.build())
            }
        };
    }

    private static void write(ClassTemplate template, PackageClass packageClass) {
        Velocity.init()
        VelocityContext context = new VelocityContext()
        context.put("packageClass", packageClass)
        def outputTemplate = getTemplate(template.getTemplatePath(packageClass).toString(), CHARSET)
        def outputFile = template.getOutputFile(packageClass)
        def outputStream = new OutputStreamWriter(new FileOutputStream(outputFile as File), CHARSET)
        outputTemplate.merge(context, outputStream)
        outputTemplate.process()
        outputStream.flush()
        outputStream.close()
    }

    private static Class<?> getClass(ClassTemplate template, String filePath) {
        def classPath = filePath.replaceFirst("${template.getClassPath().toString()}/", "")
        def className = classPath
                .replaceAll("/", ".")
                .replace(".java", "")
        return Class.forName(className)
    }

}
