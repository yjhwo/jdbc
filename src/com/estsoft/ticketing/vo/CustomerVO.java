package com.estsoft.ticketing.vo;

public class CustomerVO {
	private Long no;
	private String phone;
	private int reservation;
	private Long movieNo;
	private String movieName;
	
	public CustomerVO(){	}
	
	public CustomerVO(String movieName, int reservation,Long movieNo){
		this.movieName = movieName;
		this.reservation = reservation;
		this.movieNo = movieNo;
	}
	public CustomerVO(Long no, String phone, int reservation, Long movieNo) {
		this.no = no;
		this.phone = phone;
		this.reservation = reservation;
		this.movieNo = movieNo;
	}
	
	public CustomerVO(Long no, String phone, int reservation, Long movieNo, String movieName) {
		this.no = no;
		this.phone = phone;
		this.reservation = reservation;
		this.movieNo = movieNo;
		this.movieName = movieName;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getReservation() {
		return reservation;
	}

	public void setReservation(int reservation) {
		this.reservation = reservation;
	}

	public Long getMovieNo() {
		return movieNo;
	}

	public void setMovieNo(Long movieNo) {
		this.movieNo = movieNo;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	@Override
	public String toString() {
		return "CustomerVO [no=" + no + ", phone=" + phone + ", reservation=" + reservation + ", movieNo=" + movieNo
				+ ", movieName=" + movieName + "]";
	}
	
}
