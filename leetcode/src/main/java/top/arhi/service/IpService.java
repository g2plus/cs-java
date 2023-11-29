package top.arhi.service;

import com.github.hiwepy.ip2region.spring.boot.IP2regionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.arhi.util.AddressUtil;

@Service
public class IpService {

    @Autowired
    private IP2regionTemplate template;

    public String getRegion(String ip) {
        return template.getRegion(ip);
    }

    public String getCityInfo(String ip) {
        String cityInfo = AddressUtil.getCityInfo(ip);
        return cityInfo;
    }
}
