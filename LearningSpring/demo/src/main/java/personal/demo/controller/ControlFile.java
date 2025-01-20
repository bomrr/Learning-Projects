package personal.demo.controller;

/*
 * This class is a controller class that handles the requests from the user.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import personal.demo.repository.DataRepository;
import personal.demo.Data;

@Controller
public class ControlFile {

    @Autowired
    private DataRepository dataRepository;
    
    // GetMapping determines where the server gets the web page itself
    // In this case, "/" is the root of the server, the homepage
    @GetMapping("/")
    public String home(Model model) {
        // Retrieve all data from the database and add to the model
        model.addAttribute("dataList", dataRepository.findAll().toArray());
        
        return "home"; // Refers to home.html in the templates folder
    }

    // PostMapping determines where the server gets the data from the web page
    // In this case, "/submit" is the endpoint where the server gets the data
    @PostMapping("/submit")
    public String submit(@RequestParam("inputText") String inputData, RedirectAttributes redirectAttributes, Model model) {
        // Save the data to the database
        Data data = new Data();
        data.setData(inputData);
        dataRepository.save(data);

        // Retrieve all data from the database and add to the model
        model.addAttribute("dataList", dataRepository.findAll());

        // Redirect back to the home page
        return "redirect:/";
    }

    // PutMapping updates an existing resource
    @PutMapping("/update")
    public String update(@RequestParam("id") Long id, @RequestParam("inputText") String inputData, RedirectAttributes redirectAttributes, Model model) {
        // Find the data by id and update it
        Data data = dataRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid data Id:" + id));
        data.setData(inputData);
        dataRepository.save(data);

        // Retrieve all data from the database and add to the model
        model.addAttribute("dataList", dataRepository.findAll());

        // Redirect back to the home page
        return "redirect:/";
    }

    // DeleteMapping deletes an existing resource
    @DeleteMapping("/delete")
    public String delete(@RequestParam("id") Long id, RedirectAttributes redirectAttributes, Model model) {
        // Find the data by id and delete it
        Data data = dataRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid data Id:" + id));
        dataRepository.delete(data);

        // Retrieve all data from the database and add to the model
        model.addAttribute("dataList", dataRepository.findAll());

        // Redirect back to the home page
        return "redirect:/";
    }

    // PatchMapping partially updates an existing resource
    @PatchMapping("/patch")
    public String patch(@RequestParam("id") Long id, @RequestParam("inputText") String inputData, RedirectAttributes redirectAttributes, Model model) {
        // Find the data by id and partially update it
        Data data = dataRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid data Id:" + id));
        if (inputData != null && !inputData.isEmpty()) {
            data.setData(inputData);
        }
        dataRepository.save(data);

        // Retrieve all data from the database and add to the model
        model.addAttribute("dataList", dataRepository.findAll());

        // Redirect back to the home page
        return "redirect:/";
    }

    // To add a new page, add a new GetMapping
    // Page to generate random names for RTGame (template called RTName.html)
    @GetMapping("/rtgame")
    public String rtgame(Model model) {
        return "RTName";
    }

    
    
}
