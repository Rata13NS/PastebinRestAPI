package ro.pastebin.mystringapp.Controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.pastebin.mystringapp.Models.Text;
import ro.pastebin.mystringapp.Repository.TextsRepository;
import ro.pastebin.mystringapp.Service.TextsService;

import java.util.List;

@RestController
@RequestMapping(path = "/texts")
public class TextsController {

    private final TextsService textsService;
    private final TextsRepository textsRepository;

    public TextsController(TextsService textsService, TextsRepository textsRepository) {
        this.textsService = textsService;
        this.textsRepository = textsRepository;
    }

    @GetMapping
    public List<Text> getTexts() {
        return textsRepository.findAll();
    }

    @PostMapping
    public void createText(@RequestBody Text text) {
        textsService.createText(text);
    }

    @PutMapping(path = "{id}")
    public void updateText(@PathVariable Long id, @RequestBody Text text) {
        textsService.updateText(id, text);
    }

    @DeleteMapping(path = "{id}")
    public void deleteText(@PathVariable Long id) {
        textsService.deleteText(id);
    }

    @RequestMapping("/main-page")
    public ModelAndView showMainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainPage");
        List<Text> savedTexts = textsService.getTexts();
        modelAndView.addObject("savedTexts", savedTexts);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Text> getTextById(@PathVariable Long id) {
        Text text = textsService.getTextById(id);
        return ResponseEntity.ok().body(text);
    }
}