package com.showing.backend.api.service;

import com.showing.backend.api.response.SearchRes;

import java.util.List;

public interface SearchService {

    List<SearchRes> getPerformanceList(String keyword);

}
