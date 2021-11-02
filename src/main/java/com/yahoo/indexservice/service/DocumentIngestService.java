package com.yahoo.indexservice.service;

import java.util.Set;

public interface DocumentIngestService {

    Set<String> ingest(String url);
}
