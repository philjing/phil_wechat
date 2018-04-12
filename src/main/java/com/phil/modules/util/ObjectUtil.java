package com.phil.modules.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class ObjectUtil {
	
	public static Map<String, Object> toMap(Object obj) throws Exception {
		if (obj == null) {
			return null;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		Field[] declaredFields = obj.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			map.put(field.getName(), field.get(obj));
		}
		return map;
	}

	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
		if (map == null) {
			return null;
		}

		Object obj = beanClass.newInstance();

		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			int mod = field.getModifiers();
			if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
				continue;
			}

			field.setAccessible(true);
			field.set(obj, map.get(field.getName()));
		}

		return obj;
	}
	
	public static int toInt(Object obj) {
		if (obj == null) {
			return 0;
		}
		try {
			return Integer.parseInt(obj.toString());
		} catch (Exception e) {
			return 0;
		}
	}

	public static long toLong(Object obj) {
		if (obj == null) {
			return 0;
		}
		try {
			return Long.parseLong(obj.toString());
		} catch (Exception e) {
			return 0;
		}
	}

	public static float toFloat(Object obj) {
		if (obj == null) {
			return 0;
		}
		try {
			return Float.parseFloat(obj.toString());
		} catch (Exception e) {
			return 0.0F;
		}
	}

	public static double toDouble(Object obj) {
		if (obj == null) {
			return 0;
		}
		try {
			return Double.parseDouble(obj.toString());
		} catch (Exception e) {
			return 0.0D;
		}
	}

	public static boolean toBoolean(Object obj) {
		if (obj == null) {
			return false;
		}
		if (toInt(obj) == 1) {
			return true;
		}
		try {
			return Boolean.parseBoolean(obj.toString());
		} catch (Exception e) {
			return false;
		}
	}

	public static String toString(Object obj, boolean escapeNull) {
		if (escapeNull) {
			if (String.valueOf(obj).equalsIgnoreCase("null")) {
				return "";
			} else {
				return String.valueOf(obj);
			}
		} else {
			return String.valueOf(obj);
		}
	}

	public static boolean equals(Object src, Object dest, String[] ignores) {
		if (src == null && dest == null) {
			return true;
		}
		if (src == null || dest == null) {
			return false;
		}
		if (src.getClass() != dest.getClass()) {
			return false;
		}
		Class<?> c = src.getClass();
		Field[] fileds = c.getDeclaredFields();
		try {
			for (Field f : fileds) {
				if (ArrayUtils.contains(ignores, f.getName())) {
					continue;
				}
				f.setAccessible(true);
				Object oldVal = f.get(src);
				Object newVal = f.get(dest);
				if ((oldVal == null && newVal == null)) {
					continue;
				}
				if (f.getType() == String.class) {
					if (StringUtils.isEmpty((String) oldVal)
							&& StringUtils.isEmpty((String) newVal)) {
						continue;
					}
				}
				if (! Objects.equals(oldVal, newVal)) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static <T> boolean equalsAsList(List<T> src, List<T> dest,
			String[] ignores) {
		if (src == null && dest == null) {
			return true;
		}
		if (src == null || dest == null) {
			return false;
		}
		if (src.size() == 0 && dest.size() == 0) {
			return true;
		}
		if (src.size() != dest.size()) {
			return false;
		}
		for (T left : src) {
			boolean leftEqRight = false;
			for (T right : dest) {
				if (equals(left, right, ignores)) {
					leftEqRight = true;
					break;
				}
			}
			if (!leftEqRight) {
				return false;
			}
		}
		return true;
	}

	public static boolean equalsAsArray(Object[] src, Object[] dest,
			String[] ignores) {
		if (src == null && dest == null) {
			return true;
		}
		if (src == null || dest == null) {
			return false;
		}
		if (src.length == 0 && dest.length == 0) {
			return true;
		}
		if (src.length != dest.length) {
			return false;
		}
		for (Object left : src) {
			boolean leftEqRight = false;
			for (Object right : dest) {
				if (equals(left, right, ignores)) {
					leftEqRight = true;
					break;
				}
			}
			if (!leftEqRight) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 相互补集，去掉两个列表中的重复项
	 * 
	 * @param left
	 * @param right
	 * @param ignore
	 */
	public static <T> void complement(List<T> left, List<T> right,
			String[] ignore) {
		if (left == null || right == null) {
			return;
		}
		for (int i = left.size() - 1; i > -1; i--) {
			T src = left.get(i);
			for (int j = right.size() - 1; j > -1; j--) {
				T dest = right.get(j);
				if (equals(src, dest, ignore)) {
					left.remove(i);
					right.remove(j);
					break;
				}
			}
		}
	}
	
	public static String toDateString(Object obj, String fmt) {
		if (obj == null || !(obj instanceof java.util.Date)) {
			return toString(obj, true);
		}
		return new SimpleDateFormat(fmt).format((java.util.Date) obj);
	}
	
	public static String parseEmailAddress(String old) {
		if (!StringUtils.isEmpty(old)) {
			Pattern p = Pattern
					.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
			if (p.matcher(old.trim()).matches()) {
				return old.trim();
			}
		}
		return null;
	}

}
