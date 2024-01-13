package com.vseven.launchpad.dto.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.bind.annotation.RequestParam;

@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class LinkOrderDTO {

    private String sectionId;

    private Integer userId;

    private String linkId;

    private Integer linkOrder;
}
