package cn.innovationai.system.domain.echarts;

import lombok.Data;

import java.util.List;

@Data
public class EchartsMainPieCountModel {
    private String[] legendData;
    private List<PieDataModel> data;
}
