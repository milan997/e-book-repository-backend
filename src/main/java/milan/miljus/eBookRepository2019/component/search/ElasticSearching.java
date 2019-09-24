package milan.miljus.eBookRepository2019.component.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import milan.miljus.eBookRepository2019.component.json.JsonMapper;
import milan.miljus.eBookRepository2019.component.search.value.BookSearchInfo;
import milan.miljus.eBookRepository2019.component.search.value.BookSearchResponse;
import milan.miljus.eBookRepository2019.config.CustomProperties;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.term.TermSuggestion;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by milan.miljus on 9/23/19 21:04.
 */
@Component
@Slf4j
public class ElasticSearching {


    // TODO: milan.miljus 9/19/19 20:05 extend this to multiple indices in the future
    private static final String BOOK_INDEX = "books";
    private static final String SEARCH_FIELD = "text";
    private static final String[] SEARCH_FIELDS = new String[] { "text", "author", "title" };

    private final RestHighLevelClient client;

    private final ObjectMapper mapper = JsonMapper.getMapper();

    public ElasticSearching(CustomProperties customProperties) {
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
    public BookSearchResponse search(final BookSearchInfo searchInfo) {
        final QueryBuilder queryBuilder = getQueryBuilder(searchInfo);

        final SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
                .query(queryBuilder)
                .from(0)
                .size(100)
                .timeout(new TimeValue(60, TimeUnit.SECONDS));

        addHighlightsAndSuggestions(searchSourceBuilder, searchInfo);

        final SearchRequest searchRequest = new SearchRequest()
                .indices(BOOK_INDEX)
                .source(searchSourceBuilder);

        final SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        final BookSearchResponse result = convertToBookSearchResponse(searchResponse, searchInfo);

        if (searchInfo.filteringCategories()) {
            result.setBooks(result.getBooks().stream().filter(book -> searchInfo.getCategories().contains(book.getCategoryId())).collect(Collectors.toList()));
        }
        return result;
    }

    @SneakyThrows(IOException.class)
    private BookSearchResponse convertToBookSearchResponse(final SearchResponse searchResponse, final BookSearchInfo searchInfo) {
        final List<String> suggestions = getSuggestions(searchResponse, searchInfo);
        List<BookSearchResponse.Book> result = convertBooks(searchResponse.getHits().getHits(), searchInfo.isSearch());
        if (!searchInfo.isSearch()) {
            result = result
                    .stream()
                    .sorted((book0, book1) -> book1.getTimestamp().compareTo(book0.getTimestamp()))
                    .collect(Collectors.toList());
        }
        return new BookSearchResponse(suggestions, result);
    }

    private List<BookSearchResponse.Book> convertBooks(final SearchHit[] hits, final boolean isSearch) throws IOException {
        final List<BookSearchResponse.Book> result = new ArrayList<>();
        for (SearchHit hit: hits) {
            final String json = hit.getSourceAsString();
            final BookSearchResponse.Book book = mapper.readValue(json, BookSearchResponse.Book.class);
            if (isSearch) {
                book.setHighlight(getHighlight(hit));
            }
            result.add(book);
        }
        return result;
    }

    private List<String> getSuggestions(SearchResponse searchResponse, BookSearchInfo searchInfo) {
        final List<String> suggestions = new ArrayList<>();
        if (searchInfo.isSearch()) {
            final Suggest suggest = searchResponse.getSuggest();
            final TermSuggestion termSuggestion = suggest.getSuggestion("suggestions");
            for (TermSuggestion.Entry entry: termSuggestion) {
                for (TermSuggestion.Entry.Option option: entry) {
                    final String suggestText = option.getText().string();
                    suggestions.add(suggestText);
                }
            }
        }
        return suggestions;
    }

    private void addHighlightsAndSuggestions(final SearchSourceBuilder searchSourceBuilder, final BookSearchInfo bookSearchInfo) {
        if (bookSearchInfo.isSearch()) {
            searchSourceBuilder.highlighter(
                    new HighlightBuilder()
                            .field(SEARCH_FIELD, 400)
                            .highlighterType("unified")
            );
            searchSourceBuilder.suggest(
                    new SuggestBuilder()
                            .addSuggestion(
                                    "suggestions",
                                    SuggestBuilders
                                            .termSuggestion("text")
                                            .text(bookSearchInfo.getSearchTerm())
                            )
            );
        }
    }

    private QueryBuilder getQueryBuilder(final BookSearchInfo info) {
        if (info.isSearch()) {
            return QueryBuilders
//                .termQuery("text", searchTerm)
                    .multiMatchQuery(info.getSearchTerm(), SEARCH_FIELDS)
                    .analyzer("english")
                    .fuzziness(Fuzziness.AUTO)
                    .prefixLength(3)
                    .maxExpansions(10);
        } else {
            return QueryBuilders.matchAllQuery();
        }

    }

    private String getHighlight(SearchHit hit) {
        final Map<String, HighlightField> highlightFields = hit.getHighlightFields();
        final HighlightField highlight = highlightFields.get(SEARCH_FIELD);
        if (highlight != null) {
            final Text[] fragments = highlight.fragments();
            return fragments[0].string();
        }
        return null;
    }


}
