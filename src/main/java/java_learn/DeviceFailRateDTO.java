package java_learn;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DeviceFailRateDTO {
    /**
     * 供应商
     */
    private List<String> supplierName;
    /**
     * 区域
     */
    private List<String> regions;
    /**
     * 区域层级
     */
    private Integer regionLevel;
    /**
     * 查询类型
     * true:supplier
     * false:region
     */
    private Boolean queryType;
    /**
     * 当前是否默认查找
     */
    private Boolean isAll;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
}
