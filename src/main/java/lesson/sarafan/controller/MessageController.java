package lesson.sarafan.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lesson.sarafan.domain.Message;
import lesson.sarafan.domain.Views;
import lesson.sarafan.repo.MessageRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping ("message")
public class MessageController {
    private final MessageRepo messageRepo;

    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

   /* private int counter = 4;

    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>(){{
        add(new HashMap<String, String>(){{put("id", "1"); put("text", "Первое сообщение");}});
        add(new HashMap<String, String>(){{put("id", "2"); put("text", "Второе сообщение");}});
        add(new HashMap<String, String>(){{put("id", "3"); put("text", "Третье сообщение");}});
    }};*/

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> list(){
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne(@PathVariable("id") Message message){
        return message;

    }

   /* private Map<String, String> getMessage(String id) {
        return messages.stream()
                .filter(messages -> messages.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }*/

    @PostMapping
    public Message create (@RequestBody Message message){
        message.setCreationDate(LocalDateTime.now());
        return messageRepo.save(message);
    }

    @PutMapping("{id}")
    public Message update (
            @PathVariable ("id") Message messageFromDb,
            @RequestBody Message message){
        BeanUtils.copyProperties(message, messageFromDb, "id");
        return messageRepo.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void delete (@PathVariable ("id") Message message){
        messageRepo.delete(message);
    }
}
