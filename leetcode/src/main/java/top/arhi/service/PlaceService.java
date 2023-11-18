package top.arhi.service;



import top.arhi.model.pojo.Place;

import java.util.List;

public interface PlaceService {
    List<Place> load(Integer pid);
}
