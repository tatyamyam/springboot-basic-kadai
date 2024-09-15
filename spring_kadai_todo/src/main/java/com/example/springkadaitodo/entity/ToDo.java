package com.example.springkadaitodo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "todos")
@Data
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "priority")
    private String priority;  // ここをStringに変更

    @Column(name = "status")
    private String status;    // ここもStringに変更

    // カスタムEnumでのデータ変換を行う
    public Priority getPriorityEnum() {
        return Priority.fromLabel(this.priority);
    }

    public Status getStatusEnum() {
        return Status.fromLabel(this.status);
    }

    // Priority Enum
    public enum Priority {
        HIGH("高"),
        MEDIUM("中"),
        LOW("低");

        private final String label;

        Priority(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        // 日本語ラベルからEnumを取得
        public static Priority fromLabel(String label) {
            for (Priority p : Priority.values()) {
                if (p.getLabel().equals(label)) {
                    return p;
                }
            }
            throw new IllegalArgumentException("Unknown Priority label: " + label);
        }
    }

    // Status Enum
    public enum Status {
        NOTSTARTED("未着手"),
        INPROGRESS("着手中"),
        COMPLETED("完了");

        private final String label;

        Status(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        // 日本語ラベルからEnumを取得
        public static Status fromLabel(String label) {
            for (Status s : Status.values()) {
                if (s.getLabel().equals(label)) {
                    return s;
                }
            }
            throw new IllegalArgumentException("Unknown Status label: " + label);
        }
    }
}