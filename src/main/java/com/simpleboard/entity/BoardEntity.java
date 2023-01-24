package com.simpleboard.entity;

import java.util.Date;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "board")
@Getter
@Setter
@ToString
public class BoardEntity {
	
	@Id
	@Column(name = "b_id")
	@GeneratedValue(strategy = GenerationType.AUTO) //PK설정!
	private Long id; //글번호
	
	@Column(name = "b_title")
	private String title; //글제목
	
	@Column(name = "b_name")
	private String name; //작성자 이름
	
	@Column(name = "b_content")
	private String content; //글내용
	
	@Column(name = "b_regdate")
	private Date regDate; //작성날짜
	
	
	
}
