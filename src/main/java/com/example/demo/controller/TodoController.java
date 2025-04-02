package com.example.demo.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TodoDto;
import com.example.demo.exception.NotFoundTodo;
import com.example.demo.service.TodoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TodoController {

	private final TodoService todoService;

	@PostMapping("/todo")
	public ResponseEntity<TodoDto> saveTodo(@RequestBody TodoDto todoDto) {
		todoService.save(todoDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(todoDto);
	}

	@GetMapping("/todo/{id}")
	public ResponseEntity<?> getTodo(@PathVariable Long id) { // Any 타입을 쓰는 이유? TodoDto를 보내줄 수 없는 경우 에러를 출력하기 위해
		try {
			TodoDto todoDto = todoService.findTodo(id);
			return ResponseEntity.status(HttpStatus.OK).body(todoDto);
		} catch (NotFoundTodo notFoundTodo) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", notFoundTodo.getMessage()));
		}
	}

	@DeleteMapping("/todo/{id}")
	public ResponseEntity<Map<String, String>> deleteTodo(@PathVariable Long id) {
		if (todoService.delete(id))
			return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "삭제 성공"));
		else
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Map.of("message", "삭제 실패"));
	}

	@PatchMapping("/todo/{id}")
	public ResponseEntity<?> updateTodo(@PathVariable Long id, @RequestBody TodoDto td) {
		try {
			TodoDto updated = todoService.update(id, td);
			return ResponseEntity.status(HttpStatus.OK).body(updated);
		} catch (NotFoundTodo nf) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", nf.getMessage()));
		}
	}
}
