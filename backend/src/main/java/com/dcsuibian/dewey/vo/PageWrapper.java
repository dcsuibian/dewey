package com.dcsuibian.dewey.vo;

import com.dcsuibian.dewey.util.Util;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

@Getter
@Setter
public class PageWrapper<T> {
    private List<T> data; // 页内容
    private int pageNumber; // 当前页码
    private int pageSize; // 每页记录数
    private long total; // 总记录数

    private PageWrapper() {
    }

    public long getTotalPages() {
        return 0 == total % pageSize ? total / pageSize : total / pageSize + 1;
    }

    public static <T> PageWrapper<T> of(Page<T> page) {
        PageWrapper<T> result = new PageWrapper<>();
        result.setPageNumber(page.getNumber() + 1);
        result.setPageSize(page.getSize());
        result.setTotal(page.getTotalElements());
        result.setData(page.getContent());
        return result;
    }

    public static <T, R> PageWrapper<R> of(Page<T> page, Function<T, R> converter) {
        PageWrapper<R> result = new PageWrapper<>();
        result.setPageNumber(page.getNumber() + 1);
        result.setPageSize(page.getSize());
        result.setTotal(page.getTotalElements());
        result.setData(Util.batchConvert(page.getContent(), converter));
        return result;
    }
}
