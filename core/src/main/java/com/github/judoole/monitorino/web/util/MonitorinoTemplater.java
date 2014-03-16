package com.github.judoole.monitorino.web.util;

import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class MonitorinoTemplater {
	public static String replaceTokens(String text, Map<String, ?> replacements) {
		Pattern pattern = Pattern.compile("\\[(.+?)\\]");
		Matcher matcher = pattern.matcher(text);
		StringBuffer buffer = new StringBuffer();
		while (matcher.find()) {
			Object replacement = replacements.get(matcher.group(1));
			if (replacement != null) {
				matcher.appendReplacement(buffer, "");
				buffer.append(replacement.toString());
			}
		}
		matcher.appendTail(buffer);
		return buffer.toString();
	}

	public static String template(String filename) {
		try {
			return IOUtils.toString(MonitorinoTemplater.class.getResourceAsStream(filename), "UTF-8");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
