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

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sample.template.engine.ITemplateEngine;
import com.sample.template.engine.SimpleTemplateEngine;

/**
 * @author sbhaskara
 * 
 */
public class TemplateEngineTester {

	public static final String sample1 = "Hello {$name}";
	public static final String sample2 = "Hello {$firstName} {$lastName}";
	public static final String sample3 = "Hello ${{$firstName}}";
	public static final String sample4 = "Hello {${$firstName}}";
	public static final String sample5 = "Hello {${$name}} testme";
	private static String[] samples;
	private static Map<String, String> variableMap;

	@BeforeClass
	public static void initilaize() {
		variableMap = new HashMap<String, String>();
		variableMap.put("name", "clement");
		variableMap.put("clement", "Clement Titus Thomas");
		variableMap.put("firstName", "Bob");
		variableMap.put("Bob", "Bob Cyrus");
		variableMap.put("lastName", "David");
		samples = new String[5];
		samples[0] = sample1;
		samples[1] = sample2;
		samples[2] = sample3;
		samples[3] = sample4;
		samples[4] = sample5;
	}

	@Test
	public void testTemplateEngineTester() {
		String result;
		ITemplateEngine engine;
		engine = new SimpleTemplateEngine();
		for (String sample : samples) {
			result = engine.evaluate(sample, variableMap);
			System.out.println("Result for : " + sample + " is : " + result);
		}
	}
}
