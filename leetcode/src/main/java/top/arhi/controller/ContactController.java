//package top.arhi.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import top.arhi.mapper.ContactMapper;
//import top.arhi.model.pojo.Contact;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/contact")
//public class ContactController {
//
//    @Autowired
//    private ContactMapper mapper;
//
//    @GetMapping
//    public List<Contact> getContact() {
//        return mapper.selectList(null);
//    }
//
//    @PostMapping
//    public void addContact(@RequestBody Contact contact) {
//        mapper.insert(contact);
//    }
//
//}