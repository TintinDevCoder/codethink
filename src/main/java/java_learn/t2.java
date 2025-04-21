package java_learn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class t2 extends StaticTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        DeviceFailRateDTO deviceFailRateDTO = new DeviceFailRateDTO();
        deviceFailRateDTO.setIsAll(true);
        deviceFailRateDTO.setQueryType(false);
        //deviceFailRateDTO.setSupplierName(list);
        deviceFailRateDTO.setRegionLevel(3);
        deviceFailRateDTO.setRegions(list);
        deviceFailRateDTO.setStartTime("2023-01-01");
        deviceFailRateDTO.setEndTime("2023-12-31");
        String sql = buildFailRateSql(deviceFailRateDTO);
        System.out.println(sql);
    }
    public static String buildFailRateSql(DeviceFailRateDTO deviceFailRateDTO) {
        String start = deviceFailRateDTO.getStartTime() + "-59-59-59";
        String end = deviceFailRateDTO.getEndTime() + "-59-59-59";
        long startTime = 0l;
        long endTime = 0l;
        if (start.equals("N")) {
            startTime = new Date().getTime();
        }else startTime = convertToTimestamp(start);
        if (end.equals("N")) {
            endTime = new Date().getTime();
        }else endTime = convertToTimestamp(end);
        boolean n = deviceFailRateDTO.getQueryType();
        boolean isAll = deviceFailRateDTO.getIsAll();
        StringBuffer sql = new StringBuffer();
        List<String> queryNames = new ArrayList<>();
        String queryName = "";
        if (n) {
            queryName = "supplier";
            queryNames = deviceFailRateDTO.getSupplierName();
        }else {
            if (isAll) {
                queryName = "province_id";
            }else {
                int regionLevel = deviceFailRateDTO.getRegionLevel();
                if (regionLevel == 1) {
                    queryName = "province_id";
                }else if (regionLevel == 2) {
                    queryName = "city_id";
                }else {
                    queryName = "region_id";
                }
                queryNames = deviceFailRateDTO.getRegions();
            }
        }
        sql.append("SELECT ddi." + queryName + " AS dimension_value, ");
        sql.append("COALESCE(COUNT(ddi.id), 0) AS total_devices, ")
                .append("COALESCE(COUNT(DISTINCT combined.device_id), 0) AS unreported_and_alarmed_devices FROM");
        if (isAll) {
            sql.append("(SELECT DISTINCT " + queryName + " FROM dev_device_instance) s");
        }else {
            sql.append("(SELECT DISTINCT " + queryName + " FROM dev_device_instance WHERE supplier IN (");
            if (queryNames != null && !queryNames.isEmpty()) {
                for (int i = 0; i < queryNames.size(); i++) {
                    sql.append("'" + queryNames.get(i));
                    if (i < queryNames.size() - 1) {
                        sql.append("', ");
                    }else sql.append("'");
                }
            }
            sql.append(")) s");
        }
        sql.append(" LEFT JOIN dev_device_instance ddi ON s." + queryName + " = ddi." + queryName)
                .append(" AND ddi.create_time BETWEEN " + startTime + " AND " + endTime)
                .append(" AND ddi.is_deleted = 0 ")
                .append("LEFT JOIN ( ")
                .append("SELECT du.device_id ")
                .append("FROM dev_device_unreported du ")
                .append("WHERE du.unreport_time BETWEEN " + startTime + " AND " + endTime)
                .append(" UNION ")
                .append("SELECT ar.target_id AS device_id ")
                .append("FROM alarm_record ar ")
                .append("WHERE ar.alarm_time BETWEEN " + startTime + " AND " + endTime)
                .append(") AS combined ON ddi.id = combined.device_id ")
                .append("GROUP BY ddi." + queryName + " ");
        return sql.toString();
    }

    public static long convertToTimestamp(String dateString){
        // 创建一个 SimpleDateFormat 实例
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        // 解析字符串为 Date 对象
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        // 返回时间戳（以毫秒为单位）
        return date.getTime();
    }
}
