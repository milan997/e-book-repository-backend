package milan.miljus.eBookRepository2019.service.book;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.component.pdf.PdfTextExtractor;
import milan.miljus.eBookRepository2019.model.Category;
import milan.miljus.eBookRepository2019.repository.BookRepository;
import milan.miljus.eBookRepository2019.service.book.value.CreateBookInfo;
import milan.miljus.eBookRepository2019.service.category.GetCategory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by milan.miljus on 2019-07-20 16:51.
 */
@Service
@RequiredArgsConstructor
public class CreateBook {

    private final GetCategory getCategory;
    private final BookRepository bookRepository;
    private final PdfTextExtractor pdfTextExtractor;

    @Transactional(rollbackFor = Throwable.class)
    public UUID execute(final CreateBookInfo info) {
        final Category category = getCategory.execute(info.getCategoryId());
        final String text = pdfTextExtractor.extractText(info.getFileKey());

//        final Book book = bookRepository.save(Book.builder()
////                .language(info.getLanguageIsoCode())
////                .year(info.getYear())
////                .mimeType(info.getMimeType())
////                .author(info.getAuthor())
////                .name(info.getName())
////                .category(category)
////                .keywords(info.getKeywords())
////                .
////                .build());
////
////        // save to disk
////
////        // index
////        return book.getId();
        return null;
    }

}
