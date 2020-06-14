/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.ypdaic.mymall.common.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public R() {
		put("code", 0);
		put("msg", "success");
	}
	
	public static R error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static R error(String msg) {
		return error(500, msg);
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
	public int getCode() {
		return (int) get("code");
	}

	public <T> T  getData(String key, TypeReference<T> tTypeReference){
		Object data = get(key);
		String jsonString = JSON.toJSONString(data);
		T t = JSON.parseObject(jsonString, tTypeReference);
		return t;
	}
	public <T> T  getData(TypeReference<T> tTypeReference){
		Object data = get("data");
		String jsonString = JSON.toJSONString(data);
		T t = JSON.parseObject(jsonString, tTypeReference);
		return t;
	}
	public R setData(Object data){
		put("data",data);
		return this;
	}

}
