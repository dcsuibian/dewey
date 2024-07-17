package com.dcsuibian.dewey.service;

import com.dcsuibian.dewey.entity.Document;
import com.dcsuibian.dewey.vo.PageWrapper;

public interface DocumentService {
    PageWrapper<Document> getNewest(int pageNumber, int pageSize);
}
