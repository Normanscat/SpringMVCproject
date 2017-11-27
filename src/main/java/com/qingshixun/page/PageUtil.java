package com.qingshixun.page;

public class PageUtil {
	
	public static Page creatPage(Page page,int totalcount){
		int everyPage=getEverypage(page.getEveryPage());
		int currentpage=getCurrentpage(page.getCurrentPage());
		int totalpage=getTotalpage(everyPage, totalcount);
		int beginindex=getbeginIndex(everyPage, currentpage);
		boolean hasPrepage=gethasPrepage(currentpage);
		boolean hasNextpage=gethasNextpage(totalpage, currentpage);
		return new Page(everyPage,totalcount,totalpage, currentpage,beginindex, hasPrepage, hasNextpage);
	}

	// 每页显示数量
	public static int getEverypage(int everypage) {
		// 默认为5条记录
		return everypage == 0 ? 5 : everypage;
	}

	// 当前页
	public static int getCurrentpage(int currentpage) {
		// 默认为第一页
		return currentpage == 0 ? 1 : currentpage;
	}

	/**
	 *  总记录数能整除页数不加1
	 *  不能被整除加1
	 * @param everypage
	 * @param Totalcount
	 * @return
	 */
	public static int getTotalpage(int everypage, int Totalcount) {
		int Totalpage = 0;
		
		if (Totalcount % everypage == 0) {
			Totalpage = Totalcount / everypage;
		} else {
			
			Totalpage = Totalcount / everypage + 1;
		}
		return Totalpage;
	}

	// 起始点
	public static int getbeginIndex(int everypage, int currentpage) {
		
		return (currentpage - 1) * everypage;
	}

	// 是否有上一页
	public static Boolean gethasPrepage(int currentpage) {
		//当前页为不为1 为1则无上一页，反之则有
		return currentpage == 1 ? false : true;
	}

	/**
	 * 是否有下一页
	 * 1：当前页等于总页数，无下一页
	 * 2：总页数为0
	 */
	public static Boolean gethasNextpage(int totalpage, int currentpage) {
		return currentpage == totalpage || totalpage == 0 ? false : true;
	}
}
