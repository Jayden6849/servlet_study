package com.gn.common.vo;

public class Paging {
	// 게시글 목록 관련 필드
	private int totalData;				// 전체 게시글의 개수를 가져와서 담을 필드
	private int numPerPage = 10;		// 한 페이지 당 게시글의 개수
	private int totalPage;				// 전체 페이지의 개수를 도출해서 담을 필드 
	private int	limitPageNo;			// LIMIT의 첫번째 매개변수에 넣어줄 값 : 건너뛸 게시글의 수
	private int nowPage = 1;			// 현재 페이지 번호
	
	// 페이징 바 관련 필드
	private int pageBarSize = 5;		// 페이징 바에 표시될 페이지의 개수
	private int pageBarStart;			// 해당 페이징 바의 첫번째 페이지
	private int pageBarEnd;				// 해당 페이징 바의 마지막 페이지
	
	// 이전페이지, 다음페이지 관련 필드
	private boolean prev = true;
	private boolean next = true;
	
	/* ********************************************* */
	
	// Getter, Setter
	public int getTotalData() {
		return totalData;
	}
	public void setTotalData(int totalData) {
		this.totalData = totalData;
		// 전체 개시글의 개수가 조회되어 담기는 순간 수행될 로직
		calcPaging();
	}
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getLimitPageNo() {
		return limitPageNo;
	}
	public void setLimitPageNo(int limitPageNo) {
		this.limitPageNo = limitPageNo;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getPageBarSize() {
		return pageBarSize;
	}
	public void setPageBarSize(int pageBarSize) {
		this.pageBarSize = pageBarSize;
	}
	public int getPageBarStart() {
		return pageBarStart;
	}
	public void setPageBarStart(int pageBarStart) {
		this.pageBarStart = pageBarStart;
	}
	public int getPageBarEnd() {
		return pageBarEnd;
	}
	public void setPageBarEnd(int pageBarEnd) {
		this.pageBarEnd = pageBarEnd;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	
	/* ********************************************* */
	
	private void calcPaging() {
		// 현재 페이지를 기준으로 건너뛸 데이터 개수
		limitPageNo =  (nowPage-1) * numPerPage;
		
		// 전체 게시글 개수 기준으로 페이지 개수를 계산
		totalPage = (int)(Math.ceil(totalData / (double)numPerPage));
		
		// 페이징바를 계산
		pageBarStart = ((nowPage-1) / pageBarSize) * pageBarSize + 1;
		pageBarEnd = pageBarStart + pageBarSize - 1;
		if(pageBarEnd > totalPage) {
			pageBarEnd = totalPage;
		}
		
		// 이전, 이후 버튼
		if(pageBarStart == 1) prev = false;			// 1페이지라면 이전버튼은 없음
		if(pageBarEnd >= totalPage) next = false; 	// 마지막페이지라면 다음버튼은 없음
	}
}
