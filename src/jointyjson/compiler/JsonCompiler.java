
/**
 *  Copyright 2017 Salvatore Giamp�
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 **/

package jointyjson.compiler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import jointyc.analysis.StandardCompiler;
import jointyc.analysis.parser.exception.UnexpectedSymbolException;
import jointyc.analysis.semantic.exception.SemanticException;
import jointyc.jdlc.JdlCompiler;
import jointyjson.model.EncodingVisitor;
import jointyjson.model.JsonElement;
import jointyjson.model.JsonObject;
import jointyjson.model.JsonString;

/**
 * Defines the JSON compiler
 * @author Salvatore Giampa'
 *
 */
public class JsonCompiler {
	private StandardCompiler compiler;
	
	public JsonCompiler() {
		JdlCompiler jdlc = new JdlCompiler();
		
		try {
			compiler = jdlc.compileResource("Json.jdl", new JsonInterpreter());
			return;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnexpectedSymbolException e) {
			e.printStackTrace();
		} catch (SemanticException e) {
			e.printStackTrace();
		}
		System.exit(-1);
	}
	
	/**
	 * Compile an encoded JSON string and builds the JSON composite structure
	 * @param source an encoded JSON string
	 * @return the root JSON element of the structure
	 */
	public JsonElement compile(String source) {
		try {
			return (JsonElement) compiler.compile(source);
		} catch (UnexpectedSymbolException e) {
			e.printStackTrace();
		} catch (SemanticException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	public static void main(String[] args) throws IOException {
		String jsonSource = new String(Files.readAllBytes(new File("test.json").toPath()));
		JsonCompiler jsonc = new JsonCompiler();
		JsonElement json = jsonc.compile(jsonSource);
		
		JsonObject jObj = (JsonObject) json;
		System.out.println("root.object2.object3.name = " + jObj.getJsonObject("object2").getJsonObject("object3").get("letter \"a\""));
		System.out.println("root.object2.object3.name = " + jObj.getJsonObject("object2").getJsonObject("object3").get("letter \"a\""));
		//json = jsonc.compile("[1,2,3.45,8.24,10]");
		EncodingVisitor encoder = new EncodingVisitor();
		json.accept(encoder);
		System.out.println("\nRe-encoded JSON:\n");
		System.out.println(encoder.getJsonString());
	}
	*/
}
