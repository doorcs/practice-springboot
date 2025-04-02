package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Todo;
import com.example.demo.dto.TodoDto;
import com.example.demo.exception.NotFoundTodo;
import com.example.demo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {

	private final TodoRepository todoRepository;

	public void save(TodoDto todoDto) {
		Todo todo = new Todo(todoDto.getTitle(), todoDto.getContent());
		todoRepository.save(todo);
	}

	public TodoDto findTodo(Long id) {
		Todo todo = todoRepository.findById(id).orElseThrow(() -> new NotFoundTodo("존재하는 todo가 없습니다"));
		return new TodoDto(todo.getTitle(), todo.getContent());
	}

	public boolean delete(Long id) {
		Optional<Todo> todo = todoRepository.findById(id);

		if (todo.isPresent()) {
			todoRepository.deleteById(id);
			return true;
		} else
			return false;
	}

	public TodoDto update(Long id, TodoDto todoDto) {
		Todo td = todoRepository.findById(id).orElseThrow(() -> new NotFoundTodo("존재하지 않는 TODO입니다"));

		td.changeTitle(todoDto.getTitle());
		td.changeContent(todoDto.getContent());
		return new TodoDto(td.getTitle(), td.getContent()); // 이게 최선인가?
	}
}
