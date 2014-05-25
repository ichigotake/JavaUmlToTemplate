package net.ichigotake.java.javaumltotemplate.sandbox

import net.ichigotake.java.javaumltotemplate.reflect.PackageClass
import net.ichigotake.java.javaumltotemplate.reflect.ClassTemplate
import org.apache.commons.io.FilenameUtils

class PerlTemplate implements ClassTemplate {

    @Override
    File getOutputFile(PackageClass packageClass) {
        def outputFile = new File("output/${packageClass.getCamelizeName().replaceAll(/\./, "/")}.pm")
        new File(FilenameUtils.getPath(outputFile.toString())).mkdirs()
        return outputFile
    }

    @Override
    File getClassPath() {
        return new File("src/main/java/")
    }

    @Override
    File getTemplatePath(PackageClass packageClass) {
        return new File("src/main/groovy/net/ichigotake/java/javaumltotemplate/sandbox/PerlTemplate.vm")
    }
}
