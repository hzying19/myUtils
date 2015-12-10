/**
 * <html>
 * <body>
 *  <P>  Copyright 2016-2017 www.phone580.com Group.</p>
 *  <p>  All rights reserved.</p>
 *  <p> Created by 黄忠英</p>
 *  <p> Email:h419802957@foxmail.com
 *  </body>
 * </html>
 */
package com.common.modules.fo.conver;

/**
 * @Package: com.fbs.samp.sys.pub.fs.conver
 * @ClassName: ConverToObjIface
 * @Statement: <p>将字符串转为对象接口</p>
 * @JDK version used: 
 * @Author: 黄忠英
 * @Create Date: 2016年4月28日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public interface ConverToObjIface {
	public <T> T strToObject(String str, Class<T> c) throws Exception;
}
