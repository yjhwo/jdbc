package com.estsoft.bookshop.vo;

// 03.23(1)
public class AuthorVO {
	private Long no;	// Long타입으로 해야 MyBatis할 때 편하다.
	private String name;
	
	public AuthorVO(){		}
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AuthorVO [no=" + no + ", name=" + name + "]";
	}
	
	
}