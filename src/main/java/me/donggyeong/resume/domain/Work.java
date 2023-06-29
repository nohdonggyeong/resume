package me.donggyeong.resume.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "in_use", nullable = false)
    private Boolean inUse;

    @PrePersist
    public void prePersist() {
        this.inUse = this.inUse == null ? true : this.inUse;
    }

    @CreatedDate // 엔티티가 생성될 때 시간 저장
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate // 엔티티가 수정될 때 시간 저장
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "work", cascade = CascadeType.REMOVE)
    private List<WorkCompany> workCompanyList;

    @Builder // 빌더 패턴으로 객체 생성
    public Work(String description, Boolean inUse) {
        this.description = description;
        this.inUse = inUse;
    }

    public void update(String description, Boolean inUse) {
        this.description = description;
        this.inUse = inUse;
    }
}
