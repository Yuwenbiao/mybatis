package com.example.demo.mapper.remote;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 医院地区信息
 *
 * @author yuwb@corp.21cn.com
 * @date 19-2-27 下午2:25
 */
@Component
public interface TSpiderAddressMapper {
    /**
     * 通过医院名查询对应的地区编号
     *
     * @param hospital 医院名
     * @return 地区编号（省份id以及城市id）
     */
    Map<String, Integer> getAddressIdByHospitalName(String hospital);

    /**
     * 获取城市表中所有城市
     *
     * @return 城市列表
     */
    List<String> listAllCities();
}
