package org.personal.lemuracg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VideoController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String root() {
        return "forward:/video";
    }

    @RequestMapping(path = "/video", method = RequestMethod.GET)
    public String getVideoPage() {
        return "/video.html";
    }

}
