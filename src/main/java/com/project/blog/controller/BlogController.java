package com.project.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.blog.service.BoardService;

@Controller
public class BlogController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping({"", "/"})
	public String index(Model model, @PageableDefault(size = 3, sort="id", direction = Sort.Direction.DESC) Pageable pageable ) {
	
		model.addAttribute("boardList", boardService.boardList(pageable));
		
		return "index.html";
		
	}
	
	@GetMapping("/board/{id}")
	public String boardDetail(@PathVariable int id, Model model) {
		model.addAttribute("details", boardService.boardDetail(id));
		return "detail.html";
	}
	
	@GetMapping("/board/writeForm")
	public String board() {
		return "board.html";
	}
	
	@GetMapping("/board/updateForm/{id}")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("details", boardService.boardDetail(id));
		return "updateForm.html";
	}
}
