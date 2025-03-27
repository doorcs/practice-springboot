package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Todo {

	@Id
	@GeneratedValue
	@Column(name = "todo id")
	private Long id;

	private String title;
	private String content;

	// JPA 영속성 관리를 위해 기본 생성자는 반드시 필요. protected로 두는 이유는 직접 사용하지 않을 것이기 때문
	protected Todo() {
	}

	public Todo(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public void changeTitle(String title) {
		this.title = title;
	}

	public void changeContent(String content) {
		this.content = content;
	}
}
