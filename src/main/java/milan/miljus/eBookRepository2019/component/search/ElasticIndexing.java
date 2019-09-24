package milan.miljus.eBookRepository2019.component.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import milan.miljus.eBookRepository2019.component.json.JsonMapper;
import milan.miljus.eBookRepository2019.component.search.value.BaseIndexingObject;
import milan.miljus.eBookRepository2019.config.CustomProperties;
import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by milan.miljus on 9/15/19 16:02.
 */
@Component
@Slf4j
public class ElasticIndexing {

    // TODO: milan.miljus 9/19/19 20:05 extend this to multiple indices in the future
    private static final String BOOK_INDEX = "books";

    private final RestHighLevelClient client;
    private final ObjectMapper mapper = JsonMapper.getMapper();

    public ElasticIndexing(CustomProperties customProperties) {
        this.client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(
                                customProperties.getElastic().getHostname(),
                                customProperties.getElastic().getPort(),
                                customProperties.getElastic().getProtocol()
                        )
                )
        );
    }

    @SneakyThrows(IOException.class)
    public IndexResponse index(BaseIndexingObject document) {
        final String json = mapper.writeValueAsString(document);

        final IndexRequest request = new IndexRequest(BOOK_INDEX)
                .id(document.getId())
                .source(json, XContentType.JSON);

        final IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);

        return indexResponse;
    }

    @SneakyThrows(IOException.class)
    public void indexAll(List<? extends BaseIndexingObject> documents) {
        final List<IndexRequest> indexRequests = new ArrayList<>();
        for (BaseIndexingObject document: documents) {
            final String json = mapper.writeValueAsString(document);
            indexRequests.add(
                    new IndexRequest(BOOK_INDEX)
                            .id(document.getId())
                            .source(json, XContentType.JSON)
            );
        }

        final BulkRequest request = new BulkRequest();
        indexRequests.forEach(request::add);

        client.bulk(request, RequestOptions.DEFAULT);
    }

    @SneakyThrows(IOException.class)
    public void clearIndex() {
        try {
            client.indices().delete(new DeleteIndexRequest(BOOK_INDEX), RequestOptions.DEFAULT);
        } catch (ElasticsearchStatusException e) {
            log.warn("Index already deleted...");
        }
    }

    @SneakyThrows(IOException.class)
    public void updateBook(final BaseIndexingObject document) {
        final String json = mapper.writeValueAsString(document);

        final UpdateRequest request = new UpdateRequest(BOOK_INDEX, document.getId())
                .doc(json, XContentType.JSON);

        client.update(request, RequestOptions.DEFAULT);
    }

    @SneakyThrows(IOException.class)
    public void deleteBook(final String id) {
        final DeleteRequest deleteRequest = new DeleteRequest()
                .index(BOOK_INDEX)
                .id(id);
        client.delete(deleteRequest, RequestOptions.DEFAULT);
    }

    @PreDestroy
    private void destroyOnClose() {
        log.info("Closing ElasticSearch client connection......");
        try {
            this.client.close();
            log.info("ElasticSearch client connection closed.");
        } catch (IOException e) {
            log.error("Encountered error while closing ElasticSearch client connection.");
            e.printStackTrace();
        }

    }
}
