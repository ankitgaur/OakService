package com.oak.entities;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.oak.vo.PageVO;

@Table(value = "pages")
public class Page {

	@PrimaryKey
	private String name;
	private String link;
	private String createdby;
	private String updatedby;
	private Long createdon;
	private Long updatedon;
	private String parent;

	public Page() {
		super();
	}

	public Page(PageVO pagesVO) {
		super();
		this.name = pagesVO.getName();
		this.link = pagesVO.getLink();
		this.createdby = pagesVO.getCreatedby();
		this.updatedby = pagesVO.getUpdatedby();
		this.createdon = pagesVO.getCreatedOn();
		this.updatedon = pagesVO.getUpdatedOn();
		this.parent = pagesVO.getParent();
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public Long getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Long createdon) {
		this.createdon = createdon;
	}

	public Long getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(Long updatedon) {
		this.updatedon = updatedon;
	}

}
