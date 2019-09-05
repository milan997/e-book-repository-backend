package milan.miljus.eBookRepository2019.converter;

import milan.miljus.eBookRepository2019.controller.value.book.CreateBookRequest;
import milan.miljus.eBookRepository2019.service.book.value.CreateBookInfo;

/**
 * Created by milan.miljus on 2019-07-20 05:10.
 */
public final class BookConverter {

    public static CreateBookInfo toCreateBookInfo(CreateBookRequest request) {

        return CreateBookInfo.builder()
                .author(request.getAuthor())
                .categoryId(request.getCategoryId())
                .keywords(request.getKeywords())
                .languageIsoCode(request.getLanguageCode())
                .mimeType(request.getMimeType())
                .year(request.getYear())
                .name(request.getName())
                .fileKey(request.getFileKey())
                .build();
    }

}
