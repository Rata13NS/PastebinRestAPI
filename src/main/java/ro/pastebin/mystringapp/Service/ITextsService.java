package ro.pastebin.mystringapp.Service;

import ro.pastebin.mystringapp.Models.Text;

import java.util.List;

public interface ITextsService {
    Text getTextById(Long id);
    List<Text> getTexts();
    void createText(Text text);
    void updateText(Long id, Text text);
    void deleteText(Long id);
}