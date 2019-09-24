package milan.miljus.eBookRepository2019.component.pdf.value;

import lombok.Getter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

/**
 * Created by milan.miljus on 9/20/19 17:53.
 */
@Getter
public class PdfMeta {

    private int pageCount;

    private String title;

    private String author;

    private String[] keywords;

    private Integer year;

    public PdfMeta(PDDocument document) {
        final PDDocumentInformation info = document.getDocumentInformation();

        this.pageCount = document.getNumberOfPages();
        this.title = info.getTitle();
        this.author = info.getAuthor();

        final String keywords = info.getKeywords();
        this.keywords = keywords != null ? keywords.split(" ") : new String[0];

        this.year = info.getCreationDate() != null ? info.getCreationDate().getWeekYear() : null;
    }

}
