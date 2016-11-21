/*
 * Copyright 2013-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.mongodb.core.spel;

import static org.springframework.data.mongodb.core.spel.MethodReferenceNode.AggregationMethodReference.ArgumentType.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.ast.MethodReference;

/**
 * An {@link ExpressionNode} representing a method reference.
 *
 * @author Oliver Gierke
 * @author Thomas Darimont
 * @author Sebastien Gerard
 * @author Christoph Strobl
 */
public class MethodReferenceNode extends ExpressionNode {

	private static final Map<String, AggregationMethodReference> FUNCTIONS;

	static {

		Map<String, AggregationMethodReference> map = new HashMap<String, AggregationMethodReference>();

		// boolean operators
		map.put("and", AggregationMethodReference.builder().methodName("$and").argumentType(ARRAY).build());
		map.put("or", AggregationMethodReference.builder().methodName("$or").argumentType(ARRAY).build());
		map.put("not", AggregationMethodReference.builder().methodName("$not").argumentType(ARRAY).build());

		// set operators
		map.put("setEquals", AggregationMethodReference.builder().methodName("$setEquals").argumentType(ARRAY).build());
		map.put("setIntersection", AggregationMethodReference.builder().methodName("$setIntersection").argumentType(ARRAY).build());
		map.put("setUnion", AggregationMethodReference.builder().methodName("$setUnion").argumentType(ARRAY).build());
		map.put("setDifference", AggregationMethodReference.builder().methodName("$setDifference").argumentType(ARRAY).build());
		// 2nd.
		map.put("setIsSubset", AggregationMethodReference.builder().methodName("$setIsSubset").argumentType(ARRAY).build());
		map.put("anyElementTrue", AggregationMethodReference.builder().methodName("$anyElementTrue").argumentType(ARRAY).build());
		map.put("allElementsTrue", AggregationMethodReference.builder().methodName("$allElementsTrue").argumentType(ARRAY).build());

		// comparison operators
		map.put("cmp", AggregationMethodReference.builder().methodName("$cmp").argumentType(ARRAY).build());
		// second, and -1 if the first value is less than the second.
		map.put("eq", AggregationMethodReference.builder().methodName("$eq").argumentType(ARRAY).build());
		map.put("gt", AggregationMethodReference.builder().methodName("$gt").argumentType(ARRAY).build());
		map.put("gte", AggregationMethodReference.builder().methodName("$gte").argumentType(ARRAY).build());
		map.put("lt", AggregationMethodReference.builder().methodName("$lt").argumentType(ARRAY).build());
		map.put("lte", AggregationMethodReference.builder().methodName("$lte").argumentType(ARRAY).build());
		map.put("ne", AggregationMethodReference.builder().methodName("$ne").argumentType(ARRAY).build());

		// arithmetic operators
		map.put("abs", AggregationMethodReference.builder().methodName("$abs").argumentType(SINGLE).build());
		map.put("add", AggregationMethodReference.builder().methodName("$add").argumentType(ARRAY).build());
		map.put("ceil", AggregationMethodReference.builder().methodName("$ceil").argumentType(SINGLE).build());
		map.put("divide", AggregationMethodReference.builder().methodName("$divide").argumentType(ARRAY).build());
		map.put("exp", AggregationMethodReference.builder().methodName("$exp").argumentType(SINGLE).build());
		map.put("floor", AggregationMethodReference.builder().methodName("$floor").argumentType(SINGLE).build());
		map.put("ln", AggregationMethodReference.builder().methodName("$ln").argumentType(SINGLE).build());
		map.put("log", AggregationMethodReference.builder().methodName("$log").argumentType(ARRAY).build());
		map.put("log10", AggregationMethodReference.builder().methodName("$log10").argumentType(SINGLE).build());
		map.put("mod", AggregationMethodReference.builder().methodName("$mod").argumentType(ARRAY).build());
		map.put("multiply", AggregationMethodReference.builder().methodName("$multiply").argumentType(ARRAY).build());
		map.put("pow", AggregationMethodReference.builder().methodName("$pow").argumentType(ARRAY).build());
		map.put("sqrt", AggregationMethodReference.builder().methodName("$sqrt").argumentType(SINGLE).build());
		map.put("subtract", AggregationMethodReference.builder().methodName("$subtract").argumentType(ARRAY).build());
		map.put("trunc", AggregationMethodReference.builder().methodName("$trunc").argumentType(SINGLE).build());

		// string operators
		map.put("concat", AggregationMethodReference.builder().methodName("$concat").argumentType(ARRAY).build());
		map.put("strcasecmp", AggregationMethodReference.builder().methodName("$strcasecmp").argumentType(ARRAY).build());
		map.put("substr", AggregationMethodReference.builder().methodName("$substr").argumentType(ARRAY).build());
		map.put("toLower", AggregationMethodReference.builder().methodName("$toLower").argumentType(SINGLE).build());
		map.put("toUpper", AggregationMethodReference.builder().methodName("$toUpper").argumentType(SINGLE).build());
		map.put("strcasecmp", AggregationMethodReference.builder().methodName("$strcasecmp").argumentType(ARRAY).build());

		// text search operators
		map.put("meta", AggregationMethodReference.builder().methodName("$meta").argumentType(SINGLE).build());

		// array operators
		map.put("arrayElemAt", AggregationMethodReference.builder().methodName("$arrayElemAt").argumentType(ARRAY).build());
		map.put("concatArrays", AggregationMethodReference.builder().methodName("$concatArrays").argumentType(ARRAY).build());
		map.put("filter", AggregationMethodReference.builder().methodName("$filter").argumentType(MAPPED).argumentMap(new String[]{"input", "as", "cond"}).build());
		map.put("isArray", AggregationMethodReference.builder().methodName("$isArray").argumentType(SINGLE).build());
		map.put("size", AggregationMethodReference.builder().methodName("$size").argumentType(SINGLE).build());
		map.put("slice", AggregationMethodReference.builder().methodName("$slice").argumentType(ARRAY).build());

		// variable operators
		map.put("map", AggregationMethodReference.builder().methodName("$map").argumentType(MAPPED).argumentMap(new String[]{"input", "as", "in"}).build());
		map.put("let", AggregationMethodReference.builder().methodName("$let").argumentType(MAPPED).argumentMap(new String[]{"vars", "in"}).build());

		// literal operators
		map.put("literal", AggregationMethodReference.builder().methodName("$literal").argumentType(SINGLE).build());

		// date operators
		map.put("dayOfYear", AggregationMethodReference.builder().methodName("$dayOfYear").argumentType(SINGLE).build());
		map.put("dayOfMonth", AggregationMethodReference.builder().methodName("$dayOfMonth").argumentType(SINGLE).build());
		map.put("dayOfWeek", AggregationMethodReference.builder().methodName("$dayOfWeek").argumentType(SINGLE).build());
		map.put("year", AggregationMethodReference.builder().methodName("$year").argumentType(SINGLE).build());
		map.put("month", AggregationMethodReference.builder().methodName("$month").argumentType(SINGLE).build());
		map.put("week", AggregationMethodReference.builder().methodName("$week").argumentType(SINGLE).build());
		map.put("hour", AggregationMethodReference.builder().methodName("$hour").argumentType(SINGLE).build());
		map.put("minute", AggregationMethodReference.builder().methodName("$minute").argumentType(SINGLE).build());
		map.put("second", AggregationMethodReference.builder().methodName("$second").argumentType(SINGLE).build());
		map.put("millisecond", AggregationMethodReference.builder().methodName("$millisecond").argumentType(SINGLE).build());
		map.put("dateToString", AggregationMethodReference.builder().methodName("$dateToString").argumentType(MAPPED).argumentMap(new String[]{"format", "date"}).build());

		// conditional operators
		map.put("cond", AggregationMethodReference.builder().methodName("$cond").argumentType(MAPPED).argumentMap(new String[]{"if", "then", "else"}).build());
		map.put("ifNull", AggregationMethodReference.builder().methodName("$ifNull").argumentType(ARRAY).build());

		// group operators
		map.put("sum", AggregationMethodReference.builder().methodName("$sum").argumentType(ARRAY).build());
		map.put("avg", AggregationMethodReference.builder().methodName("$avg").argumentType(ARRAY).build());
		map.put("first", AggregationMethodReference.builder().methodName("$first").argumentType(SINGLE).build());
		map.put("last", AggregationMethodReference.builder().methodName("$last").argumentType(SINGLE).build());
		map.put("max", AggregationMethodReference.builder().methodName("$max").argumentType(ARRAY).build());
		map.put("min", AggregationMethodReference.builder().methodName("$min").argumentType(ARRAY).build());
		map.put("push", AggregationMethodReference.builder().methodName("$push").argumentType(SINGLE).build());
		map.put("addToSet", AggregationMethodReference.builder().methodName("$addToSet").argumentType(SINGLE).build());
		map.put("stdDevPop", AggregationMethodReference.builder().methodName("$stdDevPop").argumentType(ARRAY).build());
		map.put("stdDevSamp", AggregationMethodReference.builder().methodName("$stdDevSamp").argumentType(ARRAY).build());

		FUNCTIONS = Collections.unmodifiableMap(map);
	}

	MethodReferenceNode(MethodReference reference, ExpressionState state) {
		super(reference, state);
	}

	/**
	 * Returns the name of the method.
	 * @Deprecated since 1.10. Please use {@link #getMethodReference()}.
	 */
	@Deprecated
	public String getMethodName() {

		AggregationMethodReference methodReference = getMethodReference();
		return methodReference != null ? methodReference.getMethodName() : null;
	}

	/**
	 * Return the {@link AggregationMethodReference}.
	 *
	 * @return can be {@literal null}.
	 * @since 1.10
	 */
	public AggregationMethodReference getMethodReference() {

		String name = getName();
		String methodName = name.substring(0, name.indexOf('('));
		return FUNCTIONS.get(methodName);
	}

	/**
	 * @author Christoph Strobl
	 * @since 1.10
	 */
	@lombok.Data
	@lombok.Builder
	public static class AggregationMethodReference {

		private final String methodName;
		private final ArgumentType argumentType;
		private final String[] argumentMap;

		public enum ArgumentType {
			SINGLE, ARRAY, MAPPED
		}
	}
}
