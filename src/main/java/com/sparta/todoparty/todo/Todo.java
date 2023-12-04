package com.sparta.todoparty.todo;

import com.sparta.todoparty.comment.Comment;
import com.sparta.todoparty.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Todo implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private LocalDateTime createDate;

    @Column
    private Boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "todo")
    private List<Comment> comments;

    @Builder
    public Todo(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Todo(TodoRequestDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.createDate = LocalDateTime.now();
        this.isCompleted = false;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void complete() {
        this.isCompleted = true;
    }
}
