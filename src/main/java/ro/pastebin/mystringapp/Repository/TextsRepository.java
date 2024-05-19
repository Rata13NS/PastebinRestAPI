package ro.pastebin.mystringapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.pastebin.mystringapp.Models.Text;

import java.util.Optional;

public interface TextsRepository extends JpaRepository<Text, Long> {
    Optional<Text> getTextByContent(String content);
}