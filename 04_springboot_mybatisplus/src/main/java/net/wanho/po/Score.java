package net.wanho.po;

import lombok.Data;

@Data
public class Score {
    private Long id;
    private Integer chinese;
    private Integer math;
    private Integer english;
    private Long stuId;


}
