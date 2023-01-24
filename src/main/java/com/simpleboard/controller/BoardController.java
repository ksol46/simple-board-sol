package com.simpleboard.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.simpleboard.dto.BoardDto;
import com.simpleboard.entity.BoardEntity;
import com.simpleboard.repository.BoardRepository;

@Controller //컨트롤러 사용
public class BoardController {

	@Autowired //생성자 주입
	BoardRepository boardrepo;
	
	public void insertData() {
		//h2에 데이터를 담아서 확인해봄 한번 쓰고 안씀
		List<BoardEntity> BoardList = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			BoardEntity boardEntity = new BoardEntity();
							//valueOf() : string값으로 변환해줌.
			boardEntity.setId(Long.valueOf(i));
			boardEntity.setTitle("title" +i);
			boardEntity.setName("name" +i);
			boardEntity.setRegDate(new Date());
			
			BoardList.add(boardEntity);
		}
		boardrepo.saveAll(BoardList);
	}
	
	@GetMapping("/")
				//set.attribute를 쓰고 jsp로 받았는데 스프링부트에서는 model을 사용해서 간단하게 처리한다?!
	public String main(Model model) {
		//this.insertData();
		List<BoardEntity> entityList = boardrepo.findAll();
		List<BoardDto> dtoList = new ArrayList<>();
		//비교할 대상 (타입, 변수명) : 비교할 리스트
		for ( BoardEntity entity : entityList) {
			dtoList.add(BoardDto.parse(entity));
		}
		
		model.addAttribute("boardList", dtoList);
		System.out.println("확인");
		return "Main";
	}
	
	//글쓰기 화면을 보여줌.
	@GetMapping("/add")
	public String add() {
		return "add";
	}
	
	//글쓰기 기능을 함. 글쓰기라서 post.
	@PostMapping("/insert")
	public String insert(@RequestParam(name="b_title", required=true) String title, @RequestParam(name="b_name", required=true) String name, @RequestParam(name="b_content", required=true) String content) {
		BoardEntity board = new BoardEntity();
		board.setTitle(title);
		board.setName(name);
		board.setContent(content);
		board.setRegDate(new Date());
		
		boardrepo.save(board);
		//클라이언트의 요청에 의해 서버의 DB에 변화가 생기는 작업이기 떄문에 redirect.
		return "redirect:/";
		
	}
	
	//보여주는 페이지. 읽는 부분이라 get.
	@GetMapping("/view")
	public String view() {
		
		return "view";
	}
	
}
