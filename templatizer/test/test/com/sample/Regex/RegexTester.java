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

package test.com.sample.Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * @author sbhaskara
 * 
 */
public class RegexTester {

	public static final String sample1 = "Hello {$name}";
	public static final String sample2 = "Hello {$firstName} {$lastName}";
	public static final String sample3 = "Hello ${{$firstName}}";
	public static final String sample4 = "Hello {${$firstName}}";
	public static final String sample5 = "Hello {${$firstName}} testme";
	// public static final String regex = ".*\\{$([a-z]?)\\}.*";
	public static final String regex = "\\{\\$([a-zA-Z]+)\\}";

	@Test
	public void regexPattern() {
		Matcher matcher;
		Pattern pattern;
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(sample5);
		while (matcher.find()) {
			String output = matcher.group(1);
			System.out.println(output);
		}
	}
}
