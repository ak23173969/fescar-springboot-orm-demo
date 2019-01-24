package com.alibaba.fescar.tm.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "storage_tbl")
@Data
@NoArgsConstructor
public class Storage implements Serializable {

	private static final long serialVersionUID = -9182696929279926692L;

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "commodity_code")
	private String commodityCode;

	@Column(name = "count")
	private String count;

	public Storage(String commodityCode, String count) {
		super();
		this.commodityCode = commodityCode;
		this.count = count;
	}

}
