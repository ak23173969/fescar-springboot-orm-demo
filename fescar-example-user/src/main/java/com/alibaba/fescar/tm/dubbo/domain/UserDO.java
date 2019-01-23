package com.alibaba.fescar.tm.dubbo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="user_tbl")
@Entity
public class UserDO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8370609159989394000L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String passoword;
	@Column(name="age")
	private Integer age;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassoword() {
		return passoword;
	}
	public void setPassoword(String passoword) {
		this.passoword = passoword;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "UserDO [id=" + id + ", username=" + username + ", passoword=" + passoword + ", age=" + age + "]";
	}
	public UserDO(String username, String passoword, Integer age) {
		super();
		this.username = username;
		this.passoword = passoword;
		this.age = age;
	}
	public UserDO() {
		super();
	}
	
}
