package mk.ukim.finki.wp.web;

import mk.ukim.finki.wp.service.impl.ListingServiceImpl;
import mk.ukim.finki.wp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Darko on 2/19/2016.
 */

@Controller
public class HomeController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    ListingServiceImpl listingService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("forma");

        //User user = userService.getUser((long) 6);
        //String url = request.getHeader("Host") + request.getContextPath();
        //modelAndView.addObject("imageURL", url);
        //modelAndView.addObject("usr", getClass().getResource("classpath:resources") + user.getUsername());

        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView afterSubmit(@RequestParam String name, @RequestParam String surname, @RequestParam String birthDate, @RequestParam String email, @RequestParam String username, @RequestParam String password, @RequestParam(value = "file") MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView("afterEffect");
        birthDate = "13/08/1994";
        userService.signUp(name, surname, birthDate, email, username, password, true, file);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = simpleDateFormat.parse(birthDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null)
            date = new Date();
        modelAndView.addObject("sent", birthDate);
        modelAndView.addObject("converted", date.toString());

        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("loginRes", userService.logIn("crestafore", "1234"));
        return mav;

    }

    @RequestMapping(value = "/listing", method = RequestMethod.POST)
    public ModelAndView createListing(@RequestParam String title, @RequestParam String content, @RequestParam ArrayList<MultipartFile> file, @RequestParam(defaultValue = "", required = false) String locationName, @RequestParam(defaultValue = "0", required = false) String lat, @RequestParam(defaultValue = "0", required = false) String lng) {
        ModelAndView mav = new ModelAndView("afterEffect");
        //Long userId = Long.parseLong(String.valueOf(session.getAttribute("userId")));
        //comment the line bellow in production
        Long userId = (long) 1;
        listingService.createListing(title, content, new Date(), file, userService.getUser(userId), locationName, lat, lng);
        for (int i = 0; i < file.size(); i++) {
            mav.addObject("file" + i + 1, file.get(i).getOriginalFilename());
        }
        return mav;
    }

    @RequestMapping(value = "/listing", method = RequestMethod.GET)
    public ModelAndView indexListing() {
        ModelAndView mav = new ModelAndView("listing");
        return mav;
    }

}
