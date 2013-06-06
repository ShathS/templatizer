/**
 * Copyright (c) 2010 IPC Billing Pricing QE Team. All Rights Reserved. Licensed under
 * the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License
 * at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.sample.template.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * @author sbhaskara
 * 
 */
public class SimpleTemplateEngine implements ITemplateEngine {

	private Logger logger = Logger.getLogger(SimpleTemplateEngine.class);
	public static String PatternString = "\\{\\$([a-zA-Z]+)\\}";

	private class SimpleTemplateEngineRegex {

		public String pattern;

		SimpleTemplateEngineRegex(String pattern) {
			this.pattern = pattern;
		}

		public List<String> matchedSeqence(String input) {
			List<String> matchedStringList;
			Matcher matcher;
			Pattern pattern;
			pattern = Pattern.compile(this.pattern);
			matchedStringList = new ArrayList<String>();
			matcher = pattern.matcher(input);
			while (matcher.find()) {
				String output = matcher.group(1);
				logger.debug(output);
				matchedStringList.add(output);
			}
			return matchedStringList;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sample.template.engine.ITemplateEngine#evaluate(java.lang.String,
	 * java.util.Map)
	 */
	@Override
	public String evaluate(String template, Map<String, String> variableMap) {
		String tempString = template;
		List<String> matchedStringList;
		SimpleTemplateEngineRegex regexMatcher;
		regexMatcher = new SimpleTemplateEngineRegex(PatternString);
		matchedStringList = regexMatcher.matchedSeqence(template);
		do {
			for (String matchedString : matchedStringList) {
				String value = variableMap.get(matchedString);
				if (value == null) {
					throw new MissingValueException("Variable Map does not contain a value for the template String :" + matchedString);
				}
				tempString = tempString.replace("{$" + matchedString + "}", value);
			}
			matchedStringList = regexMatcher.matchedSeqence(tempString);
		} while (matchedStringList != null && matchedStringList.size() != 0);
		return tempString;
	}
}
