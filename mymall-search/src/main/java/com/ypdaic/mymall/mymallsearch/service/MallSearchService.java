package com.ypdaic.mymall.mymallsearch.service;


import com.ypdaic.mymall.mymallsearch.vo.SearchParam;
import com.ypdaic.mymall.mymallsearch.vo.SearchReult;

public interface MallSearchService {
    /**
     *
     * @param param 检索的所有参数
     * @return  检索的结果
     */
    SearchReult search(SearchParam param);
}
