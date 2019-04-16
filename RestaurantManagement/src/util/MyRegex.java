package util;

import java.util.regex.Pattern;

public class MyRegex {
	private Pattern pattern;
	private String regexString;
	
	public MyRegex() {
		
	}
	
	public MyRegex(String regexString) {
		this.regexString = regexString;
		createPattern();
	}
	
	public void createPattern() {
		pattern = Pattern.compile(regexString);
	}
	
	public boolean checkRegex(String regexInput) {
		return pattern.matcher(regexInput).matches();
	}
	
	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	public String getRegexString() {
		return regexString;
	}

	public void setRegexString(String regexString) {
		this.regexString = regexString;
	}
	
	
}
