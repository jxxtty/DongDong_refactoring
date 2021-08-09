package com.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("PageDTO")
public class PageDTO {

	private List<PostDTO> list;   //페이지번호(curPage)에 해당하는 perPage개수만큼만
	//db에서 가져와서 리스트에 저장
	private int curPage;  //현재 또는 요청 페이지 
	private int perPage = 16;  //한페이지에 보여질 갯수 
	private int totalCount;   //전체 레코드 갯수 
	private int totalCountCategory;
	private int totalCountKeyword;
	private int offset;
	private int blockPerPage; //한블록에 최대 5개의 페이지만 뿌려주게함
	private int prevPageBlock;// 이전 블록 버튼
	private int nextPageBlock;//다음 블록 버튼
	
	
	
	public int getPrevPageBlock() {
		return prevPageBlock;
	}
	public void setPrevPageBlock(int prevPageBlock) {
		this.prevPageBlock = prevPageBlock;
	}
	public int getNextPageBlock() {
		return nextPageBlock;
	}
	public void setNextPageBlock(int nextPageBlock) {
		this.nextPageBlock = nextPageBlock;
	}
	public int getBlockPerPage() {
		return blockPerPage;
	}
	public void setBlockPerPage(int blockPerPage) {
		this.blockPerPage = blockPerPage;
	}

	@Override
	public String toString() {
		return "PageDTO [list=" + list + ", curPage=" + curPage + ", perPage=" + perPage + ", totalCount=" + totalCount
				+ ", totalCountCategory=" + totalCountCategory + ", totalCountKeyword=" + totalCountKeyword+ ", offset=" + offset +
				", blockPerPage=" + blockPerPage + ", prevPageBlock=" + prevPageBlock + ", nextPageBlock=" + nextPageBlock + "]";
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public PageDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<PostDTO> getList() {
		return list;
	}
	public void setList(List<PostDTO> list) {
		this.list = list;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalCountCategory() {
		return totalCountCategory;
	}
	public void setTotalCountCategory(int totalCountCategory) {
		this.totalCountCategory = totalCountCategory;
	}
	public int getTotalCountKeyword() {
		return totalCountKeyword;
	}
	public void setTotalCountKeyword(int totalCountKeyword) {
		this.totalCountKeyword = totalCountKeyword;
	}
	
	
	
	
	
	
	
}
