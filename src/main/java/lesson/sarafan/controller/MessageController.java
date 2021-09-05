package lesson.sarafan.controller;

import lesson.sarafan.domain.Message;
import lesson.sarafan.exceptions.NotFoundException;
import lesson.sarafan.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public List<Message> list(){
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
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
    public Map<String,String > create (@RequestBody Map<String, String>message){
        message.put("id", String.valueOf(counter++));
        messages.add(message);
        return message;
    }

    @PutMapping("{id}")
    public Map <String, String> update (@PathVariable String id, @RequestBody Map<String,String> message){
        Map<String, String> messageFromDb = getMessage(id);

        messageFromDb.putAll(message);
        messageFromDb.put("id", id);
        return messageFromDb;
    }

    @DeleteMapping("{id}")
    public void delete (@PathVariable String id){
        Map<String, String> message = getMessage(id);

        messages.remove(message);
    }
}
