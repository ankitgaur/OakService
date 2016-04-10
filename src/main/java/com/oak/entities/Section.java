package com.oak.entities;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.oak.vo.SectionVO;

@Table(value = "sections")
public class Section {

	@PrimaryKey
	private SectionKey key;
	private String createdby;
	private String updatedby;
	private Long createdon;
	private Long updatedon;

	public Section() {
		super();
	}

	public Section(SectionVO vo) {
		super();
		this.key = new SectionKey();
		this.key.setName(vo.getName());
		this.key.setPage(vo.getPage());
		this.createdby = vo.getCreatedby();
		this.updatedby = vo.getUpdatedby();
		this.createdon = vo.getCreatedOn();
		this.updatedon = vo.getUpdatedOn();
	}

	public SectionKey getKey() {
		return key;
	}

	public void setKey(SectionKey key) {
		this.key = key;
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
