package com.mjc813.jwtsecurity_login.models.music;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MusicRequestDto implements IMusic {
    private Long id;
    @Size(min = 4, max = 100)
    private String title;
    @Pattern(regexp = "^[a-zA-Z0-9_]{6,20}$", message = "Username must be between 6 and 20 characters and can only contain letters, numbers, and underscores.")
    private String artist;
    @Pattern(regexp = "^[0-9]{2}:[0-9]{2}$", message = "00:00~99:99")
    private String playtime;

    @JsonIgnore
    private String createId;
    @JsonIgnore
    private LocalDateTime createDt;
    @JsonIgnore
    private String updateId;
    @JsonIgnore
    private LocalDateTime updateDt;
    @JsonIgnore
    private String deleteId;
    @JsonIgnore
    private LocalDateTime deleteDt;
}
