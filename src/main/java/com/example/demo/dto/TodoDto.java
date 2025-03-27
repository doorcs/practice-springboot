package com.example.demo.dto;

import lombok.Getter;

@Getter
public class TodoDto {
	private String title;
	private String content;

	protected TodoDto() {
	}

	public TodoDto(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
