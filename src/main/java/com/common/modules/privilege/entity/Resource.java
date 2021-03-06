/**
 * <html>
 * <body>
 *  <P>  Copyright 2014-2015 www.yx129.com Group.</p>
 *  <p>  All rights reserved.</p>
 *  <p> Created on 2015年4月28日</p>
 *  <p> Created by 黄忠英</p>
 *  </body>
 * </html>
 */
package com.common.modules.privilege.entity;

/**
 * @Package: com.yxw.platform.user.entity
 * @ClassName: Resource
 * @Statement: <p></p>
 * @JDK version used: 
 * @Author: 黄忠英
 * @Create Date: 2015年8月26日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class Resource {
	private Integer id;

	/**资源名称*/
	private String name;
	/**编码(预留)*/
	private String code;
	/**资源抽象(类型为菜单则值为菜单ID;其它则为字符串或正规表达式)*/
	private String abstr;
	/**资源类型(1菜单;2;按钮;3普通链接)*/
	private Integer type;
	/**备注*/
	private String memo;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the abstr
	 */
	public String getAbstr() {
		return abstr;
	}

	/**
	 * @param abstr the abstr to set
	 */
	public void setAbstr(String abstr) {
		this.abstr = abstr;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Resource [name=" + name + ", code=" + code + ", abstr=" + abstr + ", type=" + type + ", memo=" + memo + "]";
	}

}
