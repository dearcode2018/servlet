/**
 * 描述: 
 * OraclePager.java
 * @author qye.zheng
 * 
 *  version 1.0
 */
package com.hua.bean.pager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.hua.constant.Constant;

/**
 * 描述: 接收/响应分页请求
 * @author qye.zheng
 * 
 * OraclePager
 */
public final class OraclePager<T> implements Serializable
{
	/**
	 * 开始/结束下标是根据请求的相关分页参数
	 * 计算出来的.
	 */
	
	 /* long */
	private static final long serialVersionUID = 1L;
	
	/* 页_大小 */
	private Integer pageSize = 10;
	
	/* 当前页 */
	private Integer currentPage = 0;
	
	/* 是否分页 */
	private boolean needPage = true;
	
	/* 数据页_开始下标 */
	private long startIndex;
	
	/* 数据页_结束下标 */
	private long endIndex;
	
	/* 响应 : 分页数据 */
	private List<T> beanList = new ArrayList<T>();
	
	/* 符合搜索条件的总条数 */
	private Integer dataSize = 0;

	/* 响应 : 总共多少页 */
	private int pageCount;
	
	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the dataSize
	 */
	public Integer getDataSize() {

		return dataSize;
	}

	/**
	 * @param dataSize the dataSize to set
	 */
	public void setDataSize(Integer dataSize) {
		this.dataSize = dataSize;
	}

	/**
	 * @return the needPage
	 */
	public boolean isNeedPage() {
		return needPage;
	}

	/**
	 * @param needPage the needPage to set
	 */
	public void setNeedPage(boolean needPage) {
		this.needPage = needPage;
	}

	/**
	 * @return the beanList
	 */
	public List<T> getBeanList() {
		return beanList;
	}

	/**
	 * @param beanList the beanList to set
	 */
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}

	/**
	 * @return the startIndex
	 */
	public long getStartIndex()
	{
		// 包含 startIndex (Oralce 分页是 闭区间)
		startIndex = (currentPage - 1) * pageSize + 1;

		return startIndex;
	}

	/**
	 * @param startIndex the startIndex to set
	 */
	public void setStartIndex(long startIndex)
	{
		this.startIndex = startIndex;
	}

	/**
	 * @return the endIndex
	 */
	public long getEndIndex()
	{
		// 包含 endIndex (Oralce 分页是 闭区间)
		endIndex = currentPage * pageSize;
		
		return endIndex;
	}

	/**
	 * @param endIndex the endIndex to set
	 */
	public void setEndIndex(long endIndex)
	{
		this.endIndex = endIndex;
	}

	/**
	 * 总页数
	 * @return the pageCount
	 */
	public int getPageCount()
	{
		int count = dataSize / pageSize;
		final int result = dataSize % pageSize;
		if (Constant.ZERO != result) {
			count++;
		}
		pageCount = count;
		
		return pageCount;
	}

	/**
	 * @param pageCount the pageCount to set
	 */
	public void setPageCount(int pageCount)
	{
		this.pageCount = pageCount;
	}
	
}
