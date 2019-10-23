package cn.innovationai.system.domain.echarts;

import lombok.Data;

import java.util.List;

@Data
public class EchartsMainLineCountModel {
    private List<String> xData;
    private List<Integer> y1Data;
    private List<Integer> y2Data;
}
