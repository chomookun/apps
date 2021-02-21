package net.chomookun.apps.sdk.core;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pagination {

	private int rows = 100;
	private int page = 1;
	private int totalCount = -1;

	/**
	 * Returns spring-data PageRequest object.
	 * 
	 * @return
	 */
	public PageRequest toPageRequest() {
		return PageRequest.of(page - 1, rows);
	}

	/**
	 * Returns spring-data PageRequest object.
	 * 
	 * @param sort
	 * @return
	 */
	public PageRequest toPageRequest(Sort sort) {
		return PageRequest.of(page - 1, rows, sort);
	}

	/**
	 * Returns MYBATIS RowBounds object.
	 * 
	 * @return
	 */
	public RowBounds toRowBounds() {
		return new RowBounds(getOffset(), getLimit());
	}

	/**
	 * Gets Content-Range value
	 * 
	 * @param response
	 */
	public String getContentRange() {
		StringBuffer contentRange = new StringBuffer();
		contentRange.append("items");
		contentRange.append(" ");
		contentRange.append(getOffset() + 1).append("-").append(getOffset() + getLimit());
		contentRange.append("/");
		contentRange.append(getTotalCount());
		return contentRange.toString();
	}

	/**
	 * toResponse
	 * 
	 * @param response
	 */
	public void setContentRangeHeader(HttpServletResponse response) {
		response.setHeader("Content-Range", this.getContentRange());
	}

	public int getOffset() {
		return (rows * page) - this.rows;
	}

	public int getLimit() {
		return rows;
	}

}
