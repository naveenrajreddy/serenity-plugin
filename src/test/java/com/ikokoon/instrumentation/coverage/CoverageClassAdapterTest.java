package com.ikokoon.instrumentation.coverage;

import java.io.InputStream;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import com.ikokoon.ATest;
import com.ikokoon.toolkit.Toolkit;

public class CoverageClassAdapterTest extends ATest {

	@Test
	public void visit() {
		String classPath = Toolkit.dotToSlash(className) + ".class";
		InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(classPath);
		byte[] classfileBuffer = Toolkit.getContents(inputStream).toByteArray();
		ClassReader reader = new ClassReader(classfileBuffer);
		ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_MAXS);
		ClassVisitor visitor = new CoverageClassAdapter(writer, className);
		reader.accept(visitor, 0);
		classfileBuffer = writer.toByteArray();
		String string = new String(classfileBuffer);
		logger.error(string);
	}

}