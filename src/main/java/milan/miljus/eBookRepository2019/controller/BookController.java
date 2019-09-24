package milan.miljus.eBookRepository2019.controller;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.controller.value.IdWrapper;
import milan.miljus.eBookRepository2019.controller.value.book.BookResponse;
import milan.miljus.eBookRepository2019.controller.value.book.CreateBookRequest;
import milan.miljus.eBookRepository2019.controller.value.book.UpdateBookRequest;
import milan.miljus.eBookRepository2019.controller.value.book.UploadBookFileResponse;
import milan.miljus.eBookRepository2019.model.Book;
import milan.miljus.eBookRepository2019.model.User;
import milan.miljus.eBookRepository2019.service.auth.exception.AccessDeniedCustomException;
import milan.miljus.eBookRepository2019.service.book.*;
import milan.miljus.eBookRepository2019.service.book.value.CreateBookInfo;
import milan.miljus.eBookRepository2019.service.book.value.UpdateBookInfo;
import milan.miljus.eBookRepository2019.service.files.FilesService;
import milan.miljus.eBookRepository2019.service.user.GetUser;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by milan.miljus on 2019-07-08 01:14.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final CreateBook createBook;
    private final UploadBookFile uploadBookFile;
    private final DeleteBook deleteBook;
    private final GetBook getBook;
    private final UpdateBook updateBook;
    private final FilesService filesService;
    private final GetUser getUser;

    @GetMapping("/{bookId}")
    @PreAuthorize("@auth.hasRole('ADMIN')")
    public ResponseEntity<BookResponse> getById(@PathVariable final long bookId) {
        final Book book = getBook.execute(bookId);
        return new ResponseEntity<>(new BookResponse(book), HttpStatus.OK);
    }

    @GetMapping(path = "/{bookId}/download")
    @PreAuthorize("@auth.loggedIn()")
    public ResponseEntity<Resource> download(@ApiIgnore @AuthenticationPrincipal Object principal,
                                             @PathVariable final long bookId) throws IOException {
        final User user = getUser.byId(Long.valueOf(principal.toString()));
        final Book book = getBook.execute(bookId);

        if (!user.canDownloadBook(book)) {
            throw new AccessDeniedCustomException();
        }

        final File file = filesService.get(book.getFileKey());
        final String downloadFileName = book.getTitle() + ".pdf";

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + downloadFileName + "\"")
                .header("Content-Type:", "application/octet-stream")
                .contentLength(file.length())
                .body(new InputStreamResource(new FileInputStream(file)));
    }

    @PostMapping
    @PreAuthorize("@auth.hasRole('ADMIN')")
    public ResponseEntity<IdWrapper> createBookEntity(@ApiIgnore @AuthenticationPrincipal Object principal,
                                                      @RequestBody @Valid final CreateBookRequest request) {
        final long bookId = createBook.execute(new CreateBookInfo(request, Long.valueOf(principal.toString())));
        return new ResponseEntity<>(IdWrapper.of(bookId), HttpStatus.CREATED);
    }

    @PostMapping("/file")
    @PreAuthorize("@auth.hasRole('ADMIN')")
    public ResponseEntity<UploadBookFileResponse> uploadBookFile(@RequestParam("file") MultipartFile multipartFile) {
        final UploadBookFileResponse response = uploadBookFile.execute(multipartFile);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{bookId}")
    @PreAuthorize("@auth.hasRole('ADMIN')")
    public ResponseEntity<Void> updateBook(@PathVariable final long bookId,
                                           @RequestBody @Valid final UpdateBookRequest request) {
        updateBook.execute(new UpdateBookInfo(request, bookId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{bookId}")
    @PreAuthorize("@auth.hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBook(@PathVariable final long bookId) {
        deleteBook.execute(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
