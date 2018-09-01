package com.saqibayub.twentytwoendpoints.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class Merger {

//	private static final String DEFAULT_FIELD_STARTS_WITH = "${";
	private static final String DEFAULT_FIELD_STARTS_WITH = "{";
	private static final String DEFAULT_FIELD_ENDS_WITH = "}";
	//private static final String DEFAULT_OBJECT_FIELD_SEPARATOR = ".";
	private static final String objOrFieldNameRegex = "\\w+";

	private final String fieldStartsWith = DEFAULT_FIELD_STARTS_WITH;
	private final String fieldEndsWith = DEFAULT_FIELD_ENDS_WITH;
	//private final String objectFieldSeparator = DEFAULT_OBJECT_FIELD_SEPARATOR;
	private final String regex = buildRegex();

	public String getFieldStartsWith() {
		return fieldStartsWith;
	}

	public String getFieldEndsWith() {
		return fieldEndsWith;
	}



	// chars to escape when building your own mergecode regex
	private static final String SPECIAL_CHARS = "${}[]().-_@<>%:";

	// escape special chars used in regex
	// default regex will be: "(\\$\\{\\w+\\.\\w+\\})"; //${blah.blah} but not
	// ${blah.b}ah}
	public static String escape(String string) {
		StringBuffer escapedString = new StringBuffer();
		for (char letter : string.toCharArray()) {
			if (SPECIAL_CHARS.indexOf(letter) > -1) {
				escapedString.append("\\");
			}
			escapedString.append(letter);
		}
		return escapedString.toString();
	}

	// build your own regex using start and end of shell and obj/field separator
	private String buildRegex() {
//		return "(" + escape(getFieldStartsWith()) + objOrFieldNameRegex + escape(getObjectFieldSeparator())
//				+ objOrFieldNameRegex + escape(getFieldEndsWith()) + ")";

		return "(" + escape(getFieldStartsWith()) + objOrFieldNameRegex + escape(getFieldEndsWith()) + ")";

	}

	public String getRegex() {
		return this.regex;
	}

	public String repaceMergeFields(String endPoint, List<String> mergeFields, Map<String, String> sampleData) {
		for(String mergeField : mergeFields){
			System.out.println("mergeField : "+ mergeField);
			String sampleDataKey = mergeField.substring(fieldStartsWith.length(), mergeField.length()-fieldEndsWith.length());
			System.out.println("sampleDataKey : "+ sampleDataKey);
			String value = sampleData.get(sampleDataKey);
			System.out.println("value : "+ value);
			if(value!=null){
				endPoint = endPoint.replaceAll(escape(mergeField), Matcher.quoteReplacement(value));
			}
			System.out.println("endPoint : "+ endPoint);
		}
		return endPoint;
	}

	public List<String> findMergeFiends(String endPoint) {
		List<String> mergeFiends = new ArrayList<String>();

		String regix = this.getRegex();
		Pattern pattern = Pattern.compile(regix);
		Matcher matcher = pattern.matcher(endPoint);

		while(matcher.find()) {
			String mergeFiend = matcher.group(1);
			System.out.println("merge Field : "+ mergeFiend);
			mergeFiends.add(mergeFiend);
		}
		return mergeFiends;
	}

}
