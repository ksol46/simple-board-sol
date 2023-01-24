package com.simpleboard.dto;

import java.text.SimpleDateFormat;

import com.simpleboard.entity.BoardEntity;

import lombok.*;

@Getter
@Setter
@ToString
public class BoardDto {
	public Long id;
	public String title;
	public String name;
	public String content;
	public String regDate;
	
	//entity를 dto로 변환시키기 위해 만드는 메소드
	public static BoardDto parse (BoardEntity entity) {
		BoardDto b_dto = new BoardDto();
		b_dto.setId(entity.getId());
		b_dto.setTitle(entity.getTitle());
		b_dto.setName(entity.getName());
		b_dto.setContent(entity.getContent());
		/*
		 * 그냥 이렇게 하니까 DB에는 날짜가 저장이 되지만 화면에는 보이지 않았다.
		 * 날짜 포맷을 해주는 이유가 있었던 것이다...!
		 * b_dto.setRegDate(entity.getRegDate());
		*/
		
		//날짜 포맷
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		String dateStr = sdf.format(entity.getRegDate());
		b_dto.setRegDate(dateStr);
		//위 필드에 date를 String 타입으로 해야 포맷 가능
		
		return b_dto;
	}
	
}
