package com.withmore.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.system.common.BaseQuery;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {
    /**
     * 分页函数
     *
     * @param baseQuery 分页参数
     * @param list      要进行分页的数据列表
     * @return 当前页要展示的数据
     */
    public static <T> IPage<T> getPages(BaseQuery baseQuery, List<T> list) {
        if(list.size() == 0){
            return new Page<>(baseQuery.getPage(), baseQuery.getLimit());
        }

        Integer currentPage = baseQuery.getPage();
        Integer pageSize = baseQuery.getLimit();
        Page<T> page = new Page<>();
        int size = list.size();

        if (pageSize > size) {
            pageSize = size;
        }

        // 求出最大页数，防止currentPage越界
        int maxPage = size % pageSize == 0 ? size / pageSize : size / pageSize + 1;

        if (currentPage > maxPage) {
            currentPage = maxPage;
        }

        // 当前页第一条数据的下标
        int curIdx = currentPage > 1 ? (currentPage - 1) * pageSize : 0;

        List<T> pageList = new ArrayList<>();

        // 将当前页的数据放进pageList
        for (int i = 0; i < pageSize && curIdx + i < size; i++) {
            pageList.add(list.get(curIdx + i));
        }

        page.setCurrent(currentPage)
                .setSize(baseQuery.getLimit())
                .setTotal(list.size())
                .setRecords(pageList);
        return page;
    }
}
