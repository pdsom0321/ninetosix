package com.ninetosix.coremodule.entity;

import com.ninetosix.coremodule.vo.BoardType;
import com.ninetosix.coremodule.vo.YNCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private BoardType type;

    private String title;

    @Lob
    private String content;

    private String startDate;

    private String endDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private YNCode useYn;

    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private YNCode deleteYn;

    private String insertId;

    private LocalDateTime insertDate;

    private String updateId;

    private LocalDateTime updateDate;

    public static Board create(BoardType type, String title, String content, String startDate, String endDate, YNCode useYn, YNCode deleteYn) {
        return Board.builder()
                .type(type)
                .title(title)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .useYn(useYn)
                .deleteYn(deleteYn)
                .build();
    }

    public void modify(String title, String content, String startDate, String endDate, YNCode useYn, YNCode deleteYn) {
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.useYn = useYn;
        this.deleteYn = deleteYn;
    }
}
