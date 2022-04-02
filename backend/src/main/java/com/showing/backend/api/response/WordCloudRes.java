package com.showing.backend.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel("WordCloudRes")
@AllArgsConstructor
@Getter
@Setter
public class WordCloudRes {

    @ApiModelProperty(name = "단어", example = "배우")
    String word;

    @ApiModelProperty(name = "가중치", example = "10")
    int weight;

}
