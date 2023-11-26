package dto;

import enums.Type;

import java.time.LocalDateTime;

public class Task {
    private Integer id;
    private String content;
    private String title;
    private Type type;
    private LocalDateTime created_date;
    private LocalDateTime finished_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    public LocalDateTime getFinished_date() {
        return finished_date;
    }

    public void setFinished_date(LocalDateTime finished_date) {
        this.finished_date = finished_date;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", created_date=" + created_date +
                ", finished_date=" + finished_date +
                '}';
    }
}
