package com.tanmoy.redis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CacheData implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    private Long id;
    private String name;
    private Integer value;

}
