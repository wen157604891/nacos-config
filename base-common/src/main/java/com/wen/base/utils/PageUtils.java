package com.wen.base.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wsw
 * @Date 2023/2/2 16:52
 **/
public class PageUtils {
    private PageUtils() {
    }

    public static <T> Page<T> buildPage(IPage<T> page) {
        int pageNum = (new Long(page.getCurrent())).intValue();
        int pageSize = (new Long(page.getSize())).intValue();
        return buildPage(pageNum, pageSize, page.getTotal(), page.getRecords());
    }

    public static <T> Page<T> buildPage(int pageNum, int pageSize, long total, List<T> list) {
        if (list != null) {
            if (pageNum > 0) {
                --pageNum;
            }

            return pageNum >= 0 && pageSize >= 1 ? new PageImpl(list, PageRequest.of(pageNum, pageSize), total) : new PageImpl(list);
        } else {
            return defaultPage();
        }
    }

    public static <T> Page<T> defaultPage() {
        return new PageImpl(new ArrayList());
    }

    public static <T> T getOne(Page<T> page) {
        return page.hasContent() ? page.getContent().get(0) : null;
    }

    public static <T> T getOne(IPage<T> page) {
        return CollectionUtils.isNotEmpty(page.getRecords()) ? page.getRecords().get(0) : null;
    }
}
