package bz.sunlight.util;


public class PageUtil {

	/**
	 * 获取页面总数
	 * @param totalItemCount 数据总数
	 * @param pageSize 每页展示数量
	 */
	public static int getTotalPage(int totalItemCount, int pageSize) {
		if(totalItemCount % pageSize == 0){
			return totalItemCount / pageSize;
		}else{
			return totalItemCount / pageSize + 1;
		}
	}

	/**
	 * 获取当前页面数据起始位置
	 * @param currentPage
	 * @param pageSize
	 */
	public static int getPageItemStart(int currentPage, int pageSize) {
		if(currentPage == 0){
			return 0;
		}
		return pageSize * (currentPage - 1);
	}
	
	public static void main(String[] args) {
		System.out.println(PageUtil.getTotalPage(15, 3));
		System.out.println(PageUtil.getPageItemStart(3, 4));
	}
	
}
