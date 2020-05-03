/**
 * 创建日期：May 9, 2012
 * 作者： "王胤洪"
 * 版权： 指明该文件的版权信息
 * 功能： 指明该文件所实现的功能
 */

package com.wondersgroup.base.login.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

public class BaseCache {

	private int refreshPeriod = 1200;//
	private String keyPrefix = ""; //关键字前缀字符;
	private static GeneralCacheAdministrator cache = new GeneralCacheAdministrator();
	private List<String> keyList = new ArrayList<String>();

	public BaseCache(String keyPrefix, int refreshPeriod) {
		super();
		this.keyPrefix = keyPrefix;
		this.refreshPeriod = refreshPeriod;
	}

	public BaseCache(@SuppressWarnings("rawtypes") Class clazz, int refreshPeriod) {
		super();
		this.refreshPeriod = refreshPeriod;
		this.keyPrefix = clazz.getName();
	}

	public BaseCache(String keyPrefix) {
		super();
		this.keyPrefix = keyPrefix;
	}

	public BaseCache(@SuppressWarnings("rawtypes") Class clazz) {
		super();
		this.keyPrefix = clazz.getName();
	}

	public BaseCache() {
		super();
	}

	public void setRefreshPeriod(int value) {
		this.refreshPeriod = value;
	}

	public void put(String key, Object value) {
		if (key == null)
			key = "";
		if (!keyList.contains(this.keyPrefix + "_" + key)) {
			this.keyList.add(this.keyPrefix + "_" + key);
		}
		cache.putInCache(this.keyPrefix + "_" + key, value);
	}

	public void remove(String key) {
		if (key == null)
			key = "";
		if (keyList.contains(this.keyPrefix + "_" + key)) {
			this.keyList.remove(this.keyPrefix + "_" + key);
		}
		cache.flushEntry(this.keyPrefix + "_" + key);
	}

	public void removeAll(Date date) {
		cache.flushAll(date);
	}

	public void removeAll() {
		cache.flushAll();
	}

	public Object get(String key) throws Exception {
		try {
			if (key == null)
				key = "";
			return cache.getFromCache(this.keyPrefix + "_" + key, this.refreshPeriod);
		} catch (NeedsRefreshException e) {
			cache.cancelUpdate(this.keyPrefix + "_" + key);
			throw e;
		}
	}
}
