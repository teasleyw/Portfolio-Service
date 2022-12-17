package com.FrostMilano.Portfolio.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/health")
    public String health() { return "I heard her at the back door\n" +
            "The little-wing bird\n" +
            "Lying there, it should've been\n" +
            "The end for her\n" +
            "Was she going somewhere?\n" +
            "Did she have a place to be?\n" +
            "Perhaps, today, was on her way\n" +
            "To rescue me \n" +
            "I could see some movement\n" +
            "An idea came clear\n" +
            "To care for one another is\n" +
            "The reason we are here\n" +
            "I wrapped her in a blanket\n" +
            "The sky falling stray\n" +
            "Took her to the river\n" +
            "And watched her get away\nSometimes you fly\n" +
            "On top of the world\n" +
            "Sometimes you cry\n" +
            "Sometimes it hurts\n" +
            "We all need somebody\n" +
            "To understand\n" +
            "We all need somebody\n" +
            "Lend a hand\nSometimes you fly\n" +
            "On top of the world\n" +
            "Sometimes you cry\n" +
            "Sometimes it hurts\n" +
            "We all need somebody\n" +
            "To understand\n" +
            "We all need somebody\n" +
            "Lend a hand";}
}
