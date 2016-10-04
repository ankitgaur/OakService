package com.oak.entities;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.oak.vo.CommentsVO;

@Table("comments")
public class Comments {

	@PrimaryKey
	private CommentsKey commentsKey;
	private String comment;
	private String createdby;
	private String updatedby;
	private Long createdon;
	private Long updatedon;
	private String author;

	public Comments() {

	}

	public Comments(CommentsVO commentsVO) {
		super();
		CommentsKey commentsKey = new CommentsKey();
		commentsKey.setService(commentsVO.getService());
		commentsKey.setService_id(commentsVO.getService_id());
		this.comment = commentsVO.getComment();
		this.commentsKey = commentsKey;
		this.createdby = commentsVO.getCreatedby();
		this.updatedby = commentsVO.getUpdatedby();
		this.createdon = commentsVO.getCreatedon();
		this.updatedon = commentsVO.getUpdatedon();
		this.author = commentsVO.getAuthor();
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public CommentsKey getCommentsKey() {
		return commentsKey;
	}

	public void setCommentsKey(CommentsKey commentsKey) {
		this.commentsKey = commentsKey;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
