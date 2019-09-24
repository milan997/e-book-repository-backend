package milan.miljus.eBookRepository2019.controller;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.util.languages.LanguageUtil;
import milan.miljus.eBookRepository2019.util.languages.value.Language;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by milan.miljus on 9/20/19 16:40.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/languages")
public class LanguageController {

    @GetMapping
    public ResponseEntity<List<Language>> getAll() {
        final List<Language> languages = LanguageUtil.getLanguages();
        return new ResponseEntity<>(languages, HttpStatus.OK);
    }
}
