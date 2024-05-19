package ro.pastebin.mystringapp.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import ro.pastebin.mystringapp.Models.Text;
import ro.pastebin.mystringapp.Repository.TextsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TextsService implements ITextsService {

    private final TextsRepository textsRepository;

    public TextsService(TextsRepository textRepository) {
        this.textsRepository = textRepository;
    }

    @Override
    public List<Text> getTexts() {
        return textsRepository.findAll();
    }

    @Override
    public void createText(Text text) {
        validateContent(text.getContent());
        textsRepository.save(text);
    }

    @Override
    public void updateText(Long id, Text text) {
        Text textToUpdate = textsRepository.findById(id).orElseThrow(
                () -> new IllegalStateException(String.format("Text with id %s doesn't exists", id)));

        validateContent(textToUpdate.getContent());

        textToUpdate.setTitle(text.getTitle());
        textToUpdate.setContent(text.getContent());

        textsRepository.save(textToUpdate);
    }

    private void validateContent(String content) {
        Optional<Text> textOptional = textsRepository.getTextByContent(content);
        if(textOptional.isPresent()) {
            throw new IllegalArgumentException(String.format("Text with content %s already exists", content));
        }
    }

    public void deleteText(Long id) {
        boolean textExists = textsRepository.existsById(id);
        if(!textExists) {
            throw new IllegalStateException(String.format("Text with id %s doesn't exists", id));
        }
        textsRepository.deleteById(id);
    }

    @Override
    public Text getTextById(Long id) {
        return textsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Text with id " + id + " not found"));
    }
}