package org.VoPhiHai_MedicalNotify.controller.Statistical;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/statistical")
public class StatisticalController {
    @GetMapping("/")
    public ModelAndView statisticalPage(){
        ModelAndView modelAndView = new ModelAndView("statistical/statistical");
        return modelAndView;
    }
}
